package yacm;

public abstract class SingleOutputAgent<OUT> extends Agent
{
	private MessageInput<OUT> next;

	public SingleOutputAgent(String id, ElementParms parms)
	{
		super(id, parms);
	}

	public void forward(OUT msg)
	{
		if (next != null)
		{
			next.receive(msg);
		}
	}

	public boolean hasNext()
	{
		return next != null;
	}

	public void setNext(MessageInput<OUT> next)
	{
		this.next = next;
	}

}
