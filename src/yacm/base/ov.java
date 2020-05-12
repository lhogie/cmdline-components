package yacm.base;

import toools.progression.LongProcess;
import toools.text.Text;
import yacm.ElementParms;
import yacm.SimpleEventDrivenFilter;

public class ov extends SimpleEventDrivenFilter
{
	private final LongProcess lp;

	public ov(String id, ElementParms p)
	{
		super(id, p);
		double expected = Text.toDouble(p.remove("expected", - 1));
		lp = new LongProcess("transfer monitoring", "object", expected);
	}

	@Override
	public void newMessage(Object in)
	{
		lp.sensor.progressStatus++;
		forward(in);
	}

	@Override
	public void end()
	{
		lp.end();
	}
}
