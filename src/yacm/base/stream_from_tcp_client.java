package yacm.base;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

import toools.text.Text;
import yacm.ElementParms;

public class stream_from_tcp_client extends InputStreamFactory implements Runnable
{

	private final int port;

	public stream_from_tcp_client(String id, ElementParms p)
	{
		super(id, p);
		port = Text.toInt(p.remove("server"));
	}

	@Override
	protected InputStream createInputStream() throws IOException
	{
		ServerSocket ss = new ServerSocket(port);
		InputStream is = ss.accept().getInputStream();
		ss.close();
		return is;
	}

}
