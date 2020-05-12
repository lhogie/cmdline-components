package yacm.base;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import toools.io.file.RegularFile;
import toools.text.Text;
import yacm.ElementException;
import yacm.ElementParms;

public class o2file extends OutputStreamFactory
{
	private final ObjectOutputStream os;

	public o2file(String id, ElementParms p)
	{
		super(id, p);

		try
		{
			String path = p.remove("path");
			RegularFile f = new RegularFile(path);
			boolean append = Text.toBoolean(p.remove("append"));
			int bufSize = Text.toInt(p.remove("bs"));
			os = new ObjectOutputStream(f.createWritingStream(append, bufSize));
		}
		catch (IOException e)
		{
			throw new ElementException(e);
		}
	}

	@Override
	public void newMessage(Object in)
	{
		try
		{
			os.writeObject(in);
		}
		catch (IOException e)
		{
			throw new ElementException(e);
		}
	}

	@Override
	protected OutputStream getOutputStream()
	{
		return os;
	}
}
