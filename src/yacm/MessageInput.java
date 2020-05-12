package yacm;

// something that does something when a message arrives

public abstract class MessageInput<IN>
{
	public abstract void receive(IN msg);
}
