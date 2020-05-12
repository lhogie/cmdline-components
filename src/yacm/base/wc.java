package yacm.base;

import yacm.ElementParms;
import yacm.SimpleEventDrivenFilter;

public class wc extends SimpleEventDrivenFilter<Object, Integer>
{
	public wc(String id, ElementParms p)
	{
		super(id, p);
	}

	int count;

	@Override
	public void newMessage(Object in)
	{
		++count;
		System.out.println(count);
	}

	@Override
	public void end()
	{
		System.out.println(count);
	}
}
