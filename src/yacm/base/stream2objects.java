package yacm.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import yacm.ElementException;
import yacm.ElementParms;

public class stream2objects extends stream2xxx<Object>
{
	public stream2objects(String id, ElementParms p)
	{
		super(id, p);
	}

	@Override
	public void newMessage(InputStream in)
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream(in);

			while (true)
			{
				forward(ois.readObject());
			}
		}
		catch (IOException | ClassNotFoundException e)
		{
			throw new ElementException(e);
		}
	}
}
