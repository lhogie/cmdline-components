package yacm.base;

import yacm.ElementParms;
import yacm.SimpleEventDrivenFilter;

public class stdout extends SimpleEventDrivenFilter<Object, Object>
{
	private final String prefix;

	public stdout(String id, ElementParms p)
	{
		super(id, p);
		prefix = p.contains("prefix") ? p.remove("prefix") : "";
	}

	@Override
	public void newMessage(Object o)
	{
		System.out.println(prefix + o);
		forward(o);
	}
}
