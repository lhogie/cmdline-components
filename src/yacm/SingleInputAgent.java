package yacm;

public abstract class SingleInputAgent<IN> extends Agent implements AgentWithInputs
{
	public final MessageInput<IN> in;

	public SingleInputAgent(String id, ElementParms parms)
	{
		super(id, parms);
		in = createMessageDriver();
	}

	protected abstract MessageInput<IN> createMessageDriver();

	public MessageInput<IN> getInput()
	{
		return in;
	}

	@Override
	public void registerEventDrivenAsyncInputs(QueueScheduler s)
	{
		if (in instanceof EventDrivenAsyncMessageInput)
		{
			s.add((EventDrivenAsyncMessageInput<IN>) in);
		}
	}
}
