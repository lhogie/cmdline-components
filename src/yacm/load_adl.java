package yacm;

import java.util.List;

import toools.io.file.RegularFile;
import toools.text.TextUtilities;

public class load_adl
{
	public static void main(String[] args)
	{
		for (String filename : args)
		{
			if (args.length > 1)
			{
				System.out.println("processing " + filename);
			}

			String text = new RegularFile(filename).getContentAsText();
			String[] paragraphs = TextUtilities.getParagraphs(text);
			List<AgentDescription> descriptions = AgentDescription.f(paragraphs);
			List<Agent> elements = AgentDescription.createAgents(descriptions);

			AgentSystem system = new AgentSystem();
			elements.forEach(e -> system.add(e));

			system.start();
//			system.waitForEnd();
		}
	}
}
