package yacm.base;

import toools.progression.LongProcess;
import toools.text.Text;
import yacm.ElementParms;
import yacm.SimpleEventDrivenFilter;

public class pv extends SimpleEventDrivenFilter<byte[], byte[]>
{
	private final LongProcess lp;

	public pv(String id, ElementParms p)
	{
		super(id, p);
		double expected = Text.toDouble(p.remove("expected", - 1));
		lp = new LongProcess("transfer monitoring", "bytes", expected);
	}

	@Override
	public void newMessage(byte[] in)
	{
		lp.sensor.progressStatus += in.length;
		forward(in);
	}
}
