package yacm;

public abstract class SimpleEventDrivenFilter<IN, OUT> extends Filter<IN, OUT>
		implements MessageListener<IN>
{
	private final int inboxCapacity = 10;

	public SimpleEventDrivenFilter(String id, ElementParms p)
	{
		super(id, p);
	}

	protected MessageInput<IN> createMessageInput()
	{
		return new EventDrivenAsyncMessageInput<IN>(inboxCapacity, this);
	}

	@Override
	public void registerEventDrivenAsyncInputs(QueueScheduler s)
	{
		if (getInput() instanceof AsyncMessageInput)
		{
			s.add(((AsyncMessageInput) getInput()).inbox, this);
		}
	}
}
