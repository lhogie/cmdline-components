package yacm.base;

import java.io.IOException;
import java.io.OutputStream;

import toools.text.Text;
import yacm.ElementParms;
import yacm.SimpleEventDrivenFilter;

/**
 * Gets a stream and pass it to components able to deal with it
 * 
 * @author lhogie
 *
 */
public abstract class OutputStreamFactory extends SimpleEventDrivenFilter<Object, OutputStream>
{
	private final int bufSize;

	public OutputStreamFactory(String id, ElementParms p)
	{
		super(id, p);
		bufSize = Text.toInt(p.remove("bs", 1024 * 1024));
	}

	protected abstract OutputStream getOutputStream() throws IOException;

}
