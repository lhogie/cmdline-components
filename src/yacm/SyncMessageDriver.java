package yacm;

/**
 * When a messages arrives, it triggers a call to the target method on the
 * recipient object
 * 
 * @author lhogie
 *
 * @param <IN>
 */

public class SyncMessageDriver<IN> extends MessageInput<IN>
{
	private MessageListener<IN> target;

	public SyncMessageDriver(MessageListener<IN> target)
	{
		this.target = target;
	}

	@Override
	public void receive(IN in)
	{
		target.newMessage(in);
	}
}
