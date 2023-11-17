package org.yauhenishyroki;

import org.yauhenishyroki.scoreboard.football.FootballMatch;
import org.yauhenishyroki.scoreboard.football.FootballTeam;
import org.yauhenishyroki.scoreboard.football.LiveFootballScoreboard;

public class Main {

    private static final String LIVERPOOL_TOTTENHAM_1_12_PREMIER_MATCH_NAME = "Liverpool-Tottenham-1/12-premier";
    private static final String PSG_BARCELONA_FRIENDLY_MATCH_NAME = "PSG-Barcelona-Friendly";
    public static final String JUNIORS_MATCH_NAME = "Juniors";

    public static void main(String[] args) {
        LiveFootballScoreboard scoreboard = new LiveFootballScoreboard();

        FootballTeam liverpoolTeam = new FootballTeam("Liverpool");
        FootballTeam tottenhamTeam = new FootballTeam("Tottenham");
        FootballMatch liverpoolTottenhamMatch = new FootballMatch(LIVERPOOL_TOTTENHAM_1_12_PREMIER_MATCH_NAME, liverpoolTeam, tottenhamTeam);

        scoreboard.startMatch(liverpoolTottenhamMatch);

        FootballTeam psgTeam = new FootballTeam("PSG");
        FootballTeam barcelonaTeam = new FootballTeam("Barcelona");
        FootballMatch psgBarcelonaMatch = new FootballMatch(PSG_BARCELONA_FRIENDLY_MATCH_NAME, psgTeam, barcelonaTeam);

        scoreboard.startMatch(psgBarcelonaMatch);

        FootballTeam juniorOneTeam = new FootballTeam("Junior1");
        FootballTeam juniorTwoTeam = new FootballTeam("Junior2");
        FootballMatch juniorsMatch = new FootballMatch(JUNIORS_MATCH_NAME, juniorOneTeam, juniorTwoTeam);

        scoreboard.startMatch(juniorsMatch);

        scoreboard.updateScore(FootballMatch.of(LIVERPOOL_TOTTENHAM_1_12_PREMIER_MATCH_NAME), 1, 1);
        scoreboard.updateScore(FootballMatch.of(PSG_BARCELONA_FRIENDLY_MATCH_NAME), 3, 2);
        scoreboard.updateScore(FootballMatch.of(JUNIORS_MATCH_NAME), 2, 3);

        System.out.println("Before Finishing Match:");
        System.out.println(scoreboard.summary());

        scoreboard.finishMatch(FootballMatch.of(PSG_BARCELONA_FRIENDLY_MATCH_NAME));

        System.out.println();
        System.out.println("After Finishing Match:");
        System.out.println(scoreboard.summary());
    }
}
