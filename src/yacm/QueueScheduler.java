package yacm;

import java.util.HashMap;
import java.util.Map;

import toools.thread.Q;

public abstract class QueueScheduler
{
	protected final Map<Q<?>, MessageListener<?>> entries = new HashMap<>();

	public <IN> void add(Q<IN> q, MessageListener<IN> l)
	{
		entries.put(q, l);
	}

	public <IN >void add(EventDrivenAsyncMessageInput<IN> in)
	{
		add(in.inbox, in.target);
	}

	public abstract void start();

	public long nbPendingMessages()
	{
		return countNbPendingMessages();
	}

	public long countNbPendingMessages()
	{
		return entries.keySet().stream().mapToInt(i -> i.size()).sum();
	}

	public abstract long getNbMessagesProcessed();

}
