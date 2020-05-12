package yacm.base;

import toools.text.Text;
import toools.thread.Threads;
import yacm.ElementParms;
import yacm.SimpleEventDrivenFilter;

public class slowdown extends SimpleEventDrivenFilter<Object, Object>
{
	private final long pauseMs;

	public slowdown(String id, ElementParms p)
	{
		super(id, p);
		pauseMs = Text.toLong(p.remove("ms"));
	}

	@Override
	public void newMessage(Object l)
	{
		Threads.sleepMs(pauseMs);
		forward(l);
	}
}
