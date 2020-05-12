package yacm.base;

import java.io.InputStream;

import yacm.ElementParms;
import yacm.SimpleEventDrivenFilter;

public abstract class stream2xxx<O> extends SimpleEventDrivenFilter<InputStream, O>
{
	public stream2xxx(String id, ElementParms p)
	{
		super(id, p);
	}

}
