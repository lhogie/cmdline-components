package ochain.schedule;

import toools.thread.Q;
import toools.thread.QListener;
import yacm.MessageListener;
import yacm.QueueScheduler;

public class OneThreadScheduler extends QueueScheduler
{
	private final Q<Q> qq;
	private long nbMsgsAdded = 0;
	private long nbMsgsProcessed = 0;
	private final QListener listener = new QListener()
	{
		@Override
		public void newElement(Q q, Object e)
		{
			qq.add_blocking(q);
			nbMsgsAdded++;
		}
	};

	public OneThreadScheduler(int queueSize)
	{
		qq = new Q<>(queueSize);
	}

	@Override
	public void start()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while (true)
				{
					Q e = qq.get_blocking();
					
					if (e.size() == 0)
						throw new IllegalStateException();
					
					Object msg = e.get_blocking();
					MessageListener target = entries.get(e);
					target.newMessage(msg);
					++nbMsgsProcessed;
				}
			}
		}).start();
	}

	@Override
	public void add(Q q, MessageListener l)
	{
		if (q.size() > 0)
			throw new IllegalArgumentException();

		super.add(q, l);
		q.listener = listener;
	}

	@Override
	public long nbPendingMessages()
	{
		return nbMsgsAdded - nbMsgsProcessed;
	}

	@Override
	public long getNbMessagesProcessed()
	{
		return nbMsgsProcessed;
	}
}
