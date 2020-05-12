package yacm.base;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import yacm.ElementParms;

public class url_is extends InputStreamFactory
{
	private final String url;

	public url_is(String id, ElementParms p)
	{
		super(id, p);
		url = p.remove("url");
	}

	@Override
	protected InputStream createInputStream() throws IOException
	{
		return new URL(url).openStream();
	}
}
