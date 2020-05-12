package yacm;

public interface MessageListener<IN>
{
	void newMessage(IN in);
}
