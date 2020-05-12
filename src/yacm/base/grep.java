package yacm.base;

import yacm.ElementParms;
import yacm.SimpleEventDrivenFilter;

public class grep extends SimpleEventDrivenFilter<String, String>
{
	private final String regex;

	public grep(String id, ElementParms p)
	{
		super(id, p);

		if (p.contains("matches"))
		{
			regex = p.remove("matches");
		}
		else if (p.contains("contains"))
		{
			regex = ".*" + p.remove("contains") + ".*";
		}
		else
			throw new IllegalStateException();
	}

	@Override
	public void newMessage(String in)
	{
		if (in.matches(regex))
		{
			forward(in);
		}
	}
}
