package mlb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by KON7993 on 2/23/2015.
 */
public class MLBAttendance {
    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getHomeDivision() {
        return homeDivision;
    }

    public String getHomeLeague() {
        return homeLeague;
    }

    public String getMatchup() {
        return matchup;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public String getAwayCity() {
        return awayCity;
    }

    public int getAttendance() {
        return attendance;
    }

    private String date;
    private String day;
    private String homeTeam;
    private String homeCity;
    private String awayCity;
    private String homeDivision;
    private String homeLeague;
    private String matchup;
    private int attendance;

    public MLBAttendance(String date, String day, String homeTeam, String homeDivision, String homeLeague, String matchup, String attendance) {
        this.date = date;
        this.day = day;
        this.homeTeam = homeTeam;
        this.homeDivision = homeDivision;
        this.homeLeague = homeLeague;
        this.matchup = matchup;
        this.awayCity = filterAwayCity(matchup);
        this.homeCity = filterHomeCity(matchup);
        try {
            this.attendance = Integer.parseInt(attendance);
        } catch (NumberFormatException ex){
            this.attendance = 0;
        }
    }

    static String filterHomeCity(String matchup){
        Pattern p = Pattern.compile("at (.+?) \\d+");
        Matcher matcher = p.matcher(matchup);
        matcher.find();
        return matcher.group(1);
    }

    static String filterAwayCity(String matchup) {
        String awaycity = matchup.replaceAll("at (.+?) \\d+", "");
        Pattern p = Pattern.compile(" ?(.+?) \\d+");
        Matcher matcher = p.matcher(awaycity);
        matcher.find();
        return matcher.group(1);
    }


    public MLBAttendance(String[] data){
        this(data[0], data[1], data[2], data[3], data[4], data[5], data[9]);
    }


}
