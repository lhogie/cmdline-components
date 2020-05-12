package yacm;

import toools.thread.Q;

public class AsyncMessageInput<IN> extends MessageInput<IN>
{
	public final Q<IN> inbox;

	public AsyncMessageInput(int queueSize)
	{
		this.inbox = new Q<>(queueSize);
	}

	@Override
	public void receive(IN msg)
	{
		inbox.add_blocking(msg);
	}
}
