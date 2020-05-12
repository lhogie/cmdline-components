package yacm.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import toools.text.Text;
import yacm.ElementException;
import yacm.ElementParms;

public class stream2bytes extends stream2xxx<byte[]>
{
	private final int blockSize;
	private final byte[] b;

	public stream2bytes(String id, ElementParms p)
	{
		super(id, p);
		this.blockSize = Text.toInt(p.remove("bs", - 1));
		b = new byte[blockSize];
	}

	@Override
	public void newMessage(InputStream in)
	{
		try
		{
			int n = in.read(b);

			if (n == - 1)
			{
			}
			else
			{
				if (n < b.length)
				{
					forward(Arrays.copyOf(b, n));
				}
				else
				{
					forward(b);
				}
			}
		}
		catch (IOException e)
		{
			throw new ElementException(e);
		}
	}

}
