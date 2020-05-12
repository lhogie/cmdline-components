package yacm;

import java.util.List;

import toools.io.file.RegularFile;
import yacm.base.fileis;

public class cmdline
{
	public static void main(String[] args)
	{
		if (args.length == 0)
			throw new IllegalStateException("no argument specified on the command line");
		
		List<AgentDescription> descriptions = AgentDescription.f(args);

		// special case of 1st arg
		{
			AgentDescription d = descriptions.get(0);
			String filename = d.name;

			if (new RegularFile(filename).exists())
			{
				d.name = fileis.class.getName();
				d.parms.addAffectation("path", filename);
			}
		}

		// special case of last arg
		{
			AgentDescription d = descriptions.get(descriptions.size() - 1);
			RegularFile f = new RegularFile(d.name);

			if (f.exists())
			{
				d.parms.addAffectation("path", d.name);
				d.name = fileis.class.getName();
			}
		}

		List<Agent> elements = AgentDescription.createAgents(descriptions);

		AgentSystem system = new AgentSystem();
		elements.forEach(e -> system.add(e));

		chain(elements);

		system.start();
		// system.waitForEnd();
	}

	private static void chain(List<Agent> agents)
	{
		// chain the elements with respect to their order
		for (int i = 1; i < agents.size(); ++i)
		{
			SingleOutputAgent a = (SingleOutputAgent) agents.get(i - 1);
			Filter b = (Filter) agents.get(i);
			a.setNext(b.getInput());
			//System.out.println(a + " -> " + b);
		} 

	}
}
