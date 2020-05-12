package ochain.schedule;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import toools.thread.Q;
import yacm.MessageListener;
import yacm.QueueScheduler;

public class OneThreadPerNodeScheduler extends QueueScheduler
{
	AtomicLong nbMsgsProcessed = new AtomicLong(0);

	@Override
	public void start()
	{
		for (Map.Entry<Q<?>, MessageListener<?>> e : entries.entrySet())
		{
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					while (true)
					{
						Q q = e.getKey();
						MessageListener agent = e.getValue();
						Object msg = q.get_blocking();
						agent.newMessage(msg);
					}
				}
			}).start();
		}
	}

	@Override
	public long getNbMessagesProcessed()
	{
		return nbMsgsProcessed.get();
	}
}
