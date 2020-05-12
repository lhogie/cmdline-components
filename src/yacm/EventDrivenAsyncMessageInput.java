package yacm;

public class EventDrivenAsyncMessageInput<IN> extends AsyncMessageInput<IN>
{
	public final MessageListener<IN> target;

	public EventDrivenAsyncMessageInput(int queueSize, MessageListener<IN> deliverTo)
	{
		super(queueSize);
		this.target = deliverTo;
	}
	
	public void deliver(IN o)
	{
		target.newMessage(o);
	}
}
