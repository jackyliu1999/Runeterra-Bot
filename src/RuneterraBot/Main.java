package RuneterraBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import javax.security.auth.login.LoginException;

public class Main {
	public static JDA jda;
	//Main
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder();
        builder.setToken("INSERT BUILD TOKEN HERE");
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("League of Legends"));
        builder.addEventListeners(new Functions());
        builder.build();
	}
}
