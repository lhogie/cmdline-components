package yacm.base;

import java.util.Random;

import toools.text.Text;
import yacm.ElementParms;
import yacm.Source;

public class prng extends Source<Double>
{
	final private Random r;

	public prng(String id, ElementParms p)
	{
		super(id, p);
		long seed = Text.toLong(p.remove("seed", System.currentTimeMillis()));
		r = new Random(seed);
	}

	@Override
	public void run()
	{
		while (true)
		{
			forward(r.nextDouble());
		}
	}
}
