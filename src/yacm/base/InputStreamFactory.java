package yacm.base;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import toools.text.Text;
import yacm.ElementException;
import yacm.ElementParms;
import yacm.Source;

/**
 * Gets a stream and pass it to components able to deal with it
 * 
 * @author lhogie
 *
 */
public abstract class InputStreamFactory extends Source<InputStream>
{
	private final int bufSize;

	public InputStreamFactory(String id, ElementParms p)
	{
		super(id, p);
		bufSize = Text.toInt(p.remove("bs", 1024 * 1024));
	}

	@Override
	public final void run()
	{
		try
		{
			forward(new BufferedInputStream(createInputStream(), bufSize));
		}
		catch (IOException e)
		{
			throw new ElementException(e);
		}
	}

	protected abstract InputStream createInputStream() throws IOException;

}
