package ochain.schedule;

import java.util.ArrayList;
import java.util.List;

import toools.io.Cout;
import toools.progression.LongProcess;
import yacm.ElementParms;
import yacm.QueueScheduler;
import yacm.SimpleEventDrivenFilter;

public class Bench
{
	static class DummyFilter extends SimpleEventDrivenFilter<int[], int[]>
	{
		public DummyFilter(String id, ElementParms parms)
		{
			super(id, parms);
		}

		@Override
		public void newMessage(int[] o)
		{
			Cout.debug(this + " receives");
			for (int r = 0; r < o.length; ++r)
			{
				for (int i = 0; i < o.length; ++i)
				{
					o[i] = o[o.length - 1 - i];
				}
			}

			if (hasNext())
				forward(o);
		}
	}

	public static void main(String[] args)
	{
		int nbElements = 10;
		int nbMessages = 10000;

		Cout.progress("Creating " + nbElements + " elements");
		List<DummyFilter> elements = new ArrayList<>(nbElements);

		for (int i = 0; i < nbElements; ++i)
		{
			elements.add(new DummyFilter(i + "", new ElementParms()));
		}

		// chain the elements with respect to their order
		{
			LongProcess lp = new LongProcess("connecting them", "elements", nbElements);

			for (int i = 0; i < elements.size() - 1; ++i)
			{
				DummyFilter src = elements.get(i);
				DummyFilter dest = elements.get(i + 1);
				src.setNext(dest.getInput());
			}

			lp.end();
		}

		Cout.progress("Starting scheluder");
		// MessageManager sc = new OneThreadPerNode(elements);
		QueueScheduler sc = new OneThreadScheduler(1);

		elements.forEach(a -> a.registerEventDrivenAsyncInputs(sc));

		sc.start();

		{
			int[] msg = new int[100];
			LongProcess lp = new LongProcess("creating messages", "msg", nbMessages);

			for (int i = 0; i < nbMessages; ++i)
			{
				elements.get(0).getInput().receive(msg);
				lp.sensor.progressStatus = sc.getNbMessagesProcessed();
				System.out.println("*** " + sc.getNbMessagesProcessed());
			}

			lp.end();
		}
	}
}
