package org.yauhenishyroki.scoreboard;

public class Match<T extends Team> {

    T homeTeam;
    T awayTeam;

    public void updateScores(int homeTeamScore, int awayTeamScore) {
        throw new UnsupportedOperationException();
    }

    public void updateHomeTeamScore(int homeTeamScore) {
        throw new UnsupportedOperationException();
    }

    public void updateAwayTeamScore(int awayTeamScore) {
        throw new UnsupportedOperationException();
    }
}
