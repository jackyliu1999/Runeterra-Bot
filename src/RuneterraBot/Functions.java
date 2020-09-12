package RuneterraBot;

import java.io.IOException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.awt.Color;

public class Functions extends ListenerAdapter {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event)
	{
		String[] args = event.getMessage().getContentRaw().split(" ");
		if (args[0].equalsIgnoreCase("!livegame"))
		{
				String str = "Test";
				String name = String.join(" ",args).replaceAll("!livegame ", "");
				try {
					str = methods.getGame(name);
					
				} catch (IOException e) {
					
				}
				EmbedBuilder eb = new EmbedBuilder();
				String title = "Summoner Ranked Game Information for: " + name;
				eb.setTitle(title, null);
				eb.setColor(Color.orange);
				eb.setDescription(str);
				event.getChannel().sendMessage(eb.build()).queue();

		}
		else if (args[0].equalsIgnoreCase("!info"))
		{
			String name = String.join(" ",args).replaceAll("!info ", "");
			EmbedBuilder eb = new EmbedBuilder();
			String title = "Summoner Information for: " + name;
			eb.setTitle(title, null);
			eb.setColor(Color.red);
			eb.setDescription(methods.getSummoner(name));
			eb.setImage(methods.GetIconID(name));
			event.getChannel().sendMessage(eb.build()).queue();
		}
		else if (args[0].equalsIgnoreCase("!rank"))
		{
			String name = String.join(" ",args).replaceAll("!rank ", "");
			EmbedBuilder eb = new EmbedBuilder();
			String title = "Summoner Ranked Information for: " + name;
			eb.setTitle(title, null);
			eb.setColor(Color.blue);
			eb.setDescription(methods.rank(name));
			event.getChannel().sendMessage(eb.build()).queue();
		}
		else if (args[0].equalsIgnoreCase("!champion"))
		{
			String champion = args[args.length-1];
			String name = String.join(" ",args).replaceAll("!champion ", "").replaceAll(champion, "");
			String mast = methods.mastery(name,champion);
			EmbedBuilder eb = new EmbedBuilder();
			eb.setColor(Color.green);
			eb.setDescription(mast);
			event.getChannel().sendMessage(eb.build()).queue();
		}
	}
}
