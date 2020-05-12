package yacm.base;

import yacm.ElementParms;
import yacm.SimpleEventDrivenFilter;

public class bytes2txt extends SimpleEventDrivenFilter<byte[], String>
{
	public bytes2txt(String id, ElementParms p)
	{
		super(id, p);
	}

	@Override
	public void newMessage(byte[] in)
	{
		forward(new String(in));
	}

}
