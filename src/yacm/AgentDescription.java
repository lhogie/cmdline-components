package yacm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

import toools.io.file.RegularFile;
import toools.reflect.Clazz;

/*


org.test.MyB B, posts to A, receives from C D, p=3
	
com.chain.Test A
> ?

p=2
	
com.chain.Test2 C
< *


com.chain.Test3 D
> A






 
 */
public class AgentDescription
{
	public static Properties aliases = new Properties();

	static
	{
		RegularFile f = new RegularFile("$HOME/.ochain/aliases.txt");

		try
		{
			if (f.exists())
			{
				InputStream is = f.createReadingStream();
				aliases.load(is);
				is.close();
			}
			else
			{
				f.getParent().ensureExists();
				OutputStream os = f.createWritingStream();
				aliases.store(os, "Filters alias. alias => class name");
				os.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	String name;
	final String id;
	final List<String> from = new ArrayList<>();
	final List<String> to = new ArrayList<>();
	final ElementParms parms = new ElementParms();

	public AgentDescription(String s)
	{
		List<String> statements = new ArrayList<>(Arrays.asList(s.split(" *[,\n] *")));

		{
			String declaration = statements.remove(0);

			int i = declaration.indexOf(' ');

			// no ID
			if (i == - 1)
			{
				name = declaration;
				id = null;
			}
			else
			{
				name = s.substring(0, i);
				id = s.substring(i + 1).trim();
			}
		}

		while ( ! statements.isEmpty())
		{
			String statement = statements.remove(0);

			if (statement.startsWith("posts to "))
			{
				to.addAll(Arrays.asList(statement.substring(9).split(" +")));
			}
			else if (statement.startsWith("receives from "))
			{
				from.addAll(Arrays.asList(statement.substring(14).split(" +")));
			}
			else
			{
				parms.addStatement(statement);
			}
		}
	}

	@Override
	public String toString()
	{
		return name + (id == null ? "" : id + " ") + ", receives from " + from
				+ ", sends to " + to + ", " + parms;
	}

	public Agent instantiate()
	{
		Class<? extends Agent> clazz = searchClass(name);

		if (clazz != null)
		{
			try
			{
				Constructor<? extends Agent> c = clazz.getConstructor(String.class,
						ElementParms.class);
				Agent e = Clazz.makeInstance(c, id, parms);
				parms.ensureAllUsed();
				return e;
			}
			catch (NoSuchMethodException | SecurityException e)
			{
				throw new IllegalStateException(e);
			}
		}
		else
		{
			throw new IllegalStateException("can't find class nor file " + name);
		}
	}

	public static List<AgentDescription> f(String[] text)
	{
		return Arrays.stream(text).map(p -> new AgentDescription(p))
				.collect(Collectors.toList());
	}

	public static List<Agent> createAgents(List<AgentDescription> descriptions)
	{
		return descriptions.stream().map(d -> d.instantiate())
				.collect(Collectors.toList());
	}

	static private Class<? extends Agent> searchClass(String s)
	{
		if (s == null)
			return null;

		Class clazz = Clazz.findClass(s);

		if (clazz == null)
		{
			clazz = Clazz.findClass(Agent.class.getPackage().getName() + ".base." + s);
		}

		if (clazz == null)
		{
			return searchClass((String) aliases.get(s));
		}
		else
		{
			return clazz;
		}
	}
}