package yacm;

import toools.thread.Q;

public abstract class ProceduralMessageInput<IN> extends AsyncMessageInput<IN>
{
	private final Thread t;

	public ProceduralMessageInput(int queueSize)
	{
		super(queueSize);

		t = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				ProceduralMessageInput.this.run(inbox);
			}
		});

		t.start();
	}

	protected abstract void run(Q inbox);
}
