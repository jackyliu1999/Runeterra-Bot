package RuneterraBot;

import java.io.IOException;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.league.LeagueEntry;
import com.merakianalytics.orianna.types.core.spectator.CurrentMatch;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMasteries;
import com.merakianalytics.orianna.types.core.championmastery.ChampionMastery;

public class methods {

private static String apiKey = "INSERT API KEY HERE";

public static String getRanked(String summName, int position)
{
	final Summoner summoner = Summoner.named(summName).withRegion(Region.NORTH_AMERICA).get();
	CurrentMatch currentGame = summoner.getCurrentMatch();
	if (currentGame.getParticipants().get(position).getSummoner().getLeaguePosition(Queue.RANKED_SOLO) != null)
		return currentGame.getParticipants().get(position).getSummoner().getLeaguePosition(Queue.RANKED_SOLO).getTier() + " " + currentGame.getParticipants().get(position).getSummoner().getLeaguePosition(Queue.RANKED_SOLO).getDivision() + " " + currentGame.getParticipants().get(position).getSummoner().getLeaguePosition(Queue.RANKED_SOLO).getLeaguePoints();	
	return "Unranked";
}

public static String redTeam(String summName, CurrentMatch currentGame)
{
	String allied = "";
	for (int i = 0; i < 10; i++) {
		  if (currentGame.getParticipants().get(i).getTeam().getSide().getId() == 200)
		  {
			  String champ = currentGame.getParticipants().get(i).getChampion().getName();
			  String nameOf = currentGame.getParticipants().get(i).getSummoner().getName();
			  String rankOf = getRanked(nameOf, i);
			  allied = allied + champ + " - " + nameOf + " "+ rankOf + "\n";
		  }
		}
	return allied;
}

public static String blueTeam(String summName, CurrentMatch currentGame)
{
	String enemy = "";
	for (int i = 0; i < 10; i++) {
		  if (currentGame.getParticipants().get(i).getTeam().getSide().getId() == 100)
		  {
			  String champ = currentGame.getParticipants().get(i).getChampion().getName();
			  String nameOf = currentGame.getParticipants().get(i).getSummoner().getName();
			  String rankOf = getRanked(nameOf, i);
			  enemy = enemy + champ + " - " + nameOf + " "+ rankOf + "\n";
		  }
		}
	return enemy;
}
	
public static String getGame(String summName) throws IOException {
	Orianna.setRiotAPIKey(apiKey);
    Orianna.setDefaultRegion(Region.NORTH_AMERICA);
    try {
		final Summoner summoner = Summoner.named(summName).withRegion(Region.NORTH_AMERICA).get();
		CurrentMatch currentGame = summoner.getCurrentMatch();
		String red = redTeam(summName, currentGame);
		String blue = blueTeam(summName, currentGame);
		return "Red Team:\n"+ red + "\n" + "Blue Team:\n"+blue;
    }
    catch(Exception e) {
    	  return "Not in Game";
    	}
}
	
public static String getSummoner(String summName)
{
	Orianna.setRiotAPIKey(apiKey);
    Orianna.setDefaultRegion(Region.NORTH_AMERICA);
	final Summoner summoner = Summoner.named(summName).withRegion(Region.NORTH_AMERICA).get();
    String summonerID = "Summoner ID: " + summoner.getId();
    String accountID = "Account ID: " + summoner.getAccountId();
    String summonerLevel = "Level: " + summoner.getLevel();
    String summonerIconID = "Profile Icon ID: " + summoner.getProfileIcon().getId();
    String Final = summonerID + "\n\n" +  accountID + "\n\n" + summonerLevel + "\n\n" + summonerIconID;
	return Final;
}

public static String GetIconID(String summName)
{
	Orianna.setRiotAPIKey(apiKey);
    Orianna.setDefaultRegion(Region.NORTH_AMERICA);
	final Summoner summoner = Summoner.named(summName).withRegion(Region.NORTH_AMERICA).get();
	String summonerIconURL = summoner.getProfileIcon().getImage().getURL();
	return summonerIconURL;
}

public static String rank(String summName)
{
	Orianna.setRiotAPIKey(apiKey);
    Orianna.setDefaultRegion(Region.NORTH_AMERICA);
	final Summoner summoner = Summoner.named(summName).withRegion(Region.NORTH_AMERICA).get();
	LeagueEntry ranking = summoner.getLeaguePosition(Queue.RANKED_SOLO);
	String summonerRank = ranking.getTier() + " " + ranking.getDivision() + " " + ranking.getLeaguePoints() + "LP";
	String winLossInfo = ranking.getWins() + " Wins " + ranking.getLosses() + " Losses";
	String Final = summonerRank + "\n" + winLossInfo;
	return Final;
}

public static String title(String input) {
    StringBuilder titleCase = new StringBuilder(input.length());
    boolean next = true;
    for (char c : input.toCharArray()) {
        if (Character.isSpaceChar(c)) {
        	next = true;
        } else if (next) {
            c = Character.toTitleCase(c);
            next = false;
        }
        titleCase.append(c);
    }
    return titleCase.toString();
}

public static String mastery(String summName, String champion)
{
	Orianna.setRiotAPIKey(apiKey);
    Orianna.setDefaultRegion(Region.NORTH_AMERICA);
    try
    {
		final Summoner summoner = Summoner.named(summName).withRegion(Region.NORTH_AMERICA).get();
		final Champion champ = Champion.named(title(champion)).withRegion(Region.NORTH_AMERICA).get();
		final ChampionMastery cm = summoner.getChampionMastery(champ);
		String points = Integer.toString(cm.getPoints());
		return summName + " has " + points + " Mastery Points on " + title(champion);
    }
    catch (Exception E)
    {
    	return champion + " is not a valid champion";
    }
}
public static void main(String[] args) throws IOException
{
	System.out.println(mastery("Juhkee", "kassadin"));
}
	
}
