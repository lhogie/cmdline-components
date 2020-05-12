package yacm.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import yacm.ElementException;
import yacm.ElementParms;

public class stream2lines extends stream2xxx<String>
{
	public stream2lines(String id, ElementParms p)
	{
		super(id, p);
	}

	@Override
	public void newMessage(InputStream in)
	{
		try
		{
			BufferedReader r = new BufferedReader(new InputStreamReader(in));

			while (true)
			{
				String line = r.readLine();

				if (line == null)
				{
					r.close();
					return;
				}
				else
				{
					forward(line);
				}
			}
		}
		catch (IOException e)
		{
			throw new ElementException(e);
		}
	}
}
