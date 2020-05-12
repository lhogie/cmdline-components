package yacm.base;

import java.io.IOException;
import java.io.ObjectOutputStream;

import toools.io.file.Directory;
import toools.io.file.RegularFile;
import yacm.ElementException;
import yacm.ElementParms;
import yacm.SimpleEventDrivenFilter;

public class os2dir extends SimpleEventDrivenFilter<Object, Object>
{
	private final Directory d;

	public os2dir(String id, ElementParms p)
	{
		super(id, p);

		String path = p.remove("path");
		d = new Directory(path);
		d.ensureExists();
	}

	@Override
	public void newMessage(Object in)
	{
		try
		{
			RegularFile f = new RegularFile(d, d.getNbFiles() + ".ser");
			ObjectOutputStream oos = new ObjectOutputStream(f.createWritingStream());
			oos.writeObject(in);
			oos.close();
			forward(in);
		}
		catch (IOException e)
		{
			throw new ElementException(e);
		}
	}
}
