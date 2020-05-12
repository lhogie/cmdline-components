package yacm.base;

import java.util.ArrayList;
import java.util.List;

import yacm.ElementParms;
import yacm.EventDrivenAsyncMessageInput;
import yacm.MessageInput;
import yacm.MessageListener;
import yacm.SingleInputAgent;

public class dispatcher<IN> extends SingleInputAgent<IN> implements MessageListener<IN>
{
	private final List<MessageInput<IN>> recipients = new ArrayList<>();

	public dispatcher(String id, ElementParms parms)
	{
		super(id, parms);
	}

	@Override
	public void newMessage(IN msg)
	{
		recipients.forEach(r -> r.receive(msg));
	}

	protected MessageInput<IN> createMessageDriver()
	{
		return new EventDrivenAsyncMessageInput<IN>(10, this);
	}

}
