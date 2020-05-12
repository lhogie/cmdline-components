package yacm.base;

import toools.text.TextUtilities;
import yacm.ElementParms;
import yacm.SimpleEventDrivenFilter;

public class txt2lines extends SimpleEventDrivenFilter<String, String>
{

	public txt2lines(String id, ElementParms p)
	{
		super(id, p);
	}

	@Override
	public void newMessage(String in)
	{
		TextUtilities.splitInLines(in).forEach(l -> forward(l));
	}
}
