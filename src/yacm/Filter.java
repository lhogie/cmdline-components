package yacm;

public abstract class Filter<IN, OUT> extends SingleOutputAgent<OUT>
		implements AgentWithInputs
{
	private final MessageInput<IN> in;

	public Filter(String id, ElementParms parms)
	{
		super(id, parms);
		in = createMessageInput();
	}

	protected abstract MessageInput<IN> createMessageInput();

	public MessageInput<IN> getInput()
	{
		return in;
	}
}
