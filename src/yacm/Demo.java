package yacm;

import java.util.Arrays;

import ochain.schedule.OneThreadPerNodeScheduler;

public class Demo
{
	static class Person extends SimpleEventDrivenFilter<String, String>
	{
		public Person(String id)
		{
			super(id, null);
		}

		@Override
		public void newMessage(String s)
		{
			System.out.println(this + " receives " + s);
			if (hasNext())
			{
				forward(s);
			}
		}
	}

	public static void main(String[] args) throws Throwable
	{
		Person bob = new Person("Bob");
		Person mary = new Person("Mary");
		Person sam = new Person("Sam");
		bob.setNext(mary.getInput());
		mary.setNext(sam.getInput());

		// MessageScheduler sc = new OneThreadScheduler(1);
		QueueScheduler sc = new OneThreadPerNodeScheduler();

		Arrays.asList(bob, mary, sam).forEach(a -> a.registerEventDrivenAsyncInputs(sc));
		
		sc.start();

		bob.getInput().receive("coucou");

		// sc.injectMessage("coucou", bob.in);
	}
}
