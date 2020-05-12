package yacm;

import java.util.ArrayList;
import java.util.List;

import ochain.schedule.OneThreadPerNodeScheduler;

public class AgentSystem
{
	private final List<Agent> agents = new ArrayList<>();
	private final List<Thread> threads = new ArrayList<>();
//	private final QueueScheduler scheduler = new OneThreadScheduler(100);
	private final QueueScheduler scheduler = new OneThreadPerNodeScheduler();

	public void start()
	{
		agents.stream().filter(e -> e instanceof Runnable)
				.forEach(e -> threads.add(new Thread((Runnable) e)));
		threads.forEach(t -> t.start());
		scheduler.start();
	}

	public void add(Agent e)
	{
		agents.add(e);

		if (e instanceof AgentWithInputs)
		{
			((AgentWithInputs) e).registerEventDrivenAsyncInputs(scheduler);
		}
	}
}
