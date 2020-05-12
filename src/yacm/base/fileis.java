package yacm.base;

import java.io.FileNotFoundException;
import java.io.InputStream;

import toools.io.file.RegularFile;
import toools.text.Text;
import toools.thread.Threads;
import yacm.ElementParms;

public class fileis extends InputStreamFactory
{
	private final String path;
	private final long waitDurationMs;

	public fileis(String id, ElementParms p)
	{
		super(id, p);
		path = p.remove("path");
		waitDurationMs = Text.toLong(p.remove("wait", 0));
	}

	@Override
	protected InputStream createInputStream() throws FileNotFoundException
	{
		RegularFile f = new RegularFile(path);
		long remains = waitDurationMs;

		while ( ! f.exists() && remains > 0)
		{
			remains -= 1000;
			Threads.sleepMs(1000);
		}

		return f.createReadingStream();
	}
}
