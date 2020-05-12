package yacm.base;

import java.io.InputStream;

import yacm.ElementParms;

public class stdin extends InputStreamFactory
{
	public stdin(String id, ElementParms p)
	{
		super(id, p);
	}

	@Override
	protected InputStream createInputStream()
	{
		return System.in;
	}

}
