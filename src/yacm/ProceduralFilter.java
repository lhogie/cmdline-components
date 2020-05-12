package yacm;

import toools.thread.Q;

public abstract class ProceduralFilter<IN, OUT> extends Filter<IN, OUT>
{
	public ProceduralFilter(String id, ElementParms p)
	{
		super(id, p);
	}

	protected abstract void run(Q inbox);

	@Override
	public MessageInput<IN> createMessageInput()
	{
		return new ProceduralMessageInput(10)
		{
			@Override
			protected void run(Q inbox)
			{
				ProceduralFilter.this.run(inbox);
			}
		};
	}
}
