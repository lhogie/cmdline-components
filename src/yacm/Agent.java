package yacm;

import toools.text.Text;
import toools.thread.MultiThreadProcessing;

public abstract class Agent
{
	final public String id;
	// public AgentDescription d;

	public final int nbThreads;

	public Agent(String id, ElementParms parms)
	{
		this.id = id;
		nbThreads = Text.toInt(
				parms.remove("nbThreads", MultiThreadProcessing.NB_THREADS_TO_USE));
	}

	public void end()
	{

	}

	@Override
	public String toString()
	{
		if (id == null)
		{
			return getClass().getName();
		}
		else
		{
			return id;
		}
	}
}
