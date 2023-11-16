package org.yauhenishyroki.scoreboard;

import lombok.Getter;

@Getter
public class Match<T extends Team> {

    private final T homeTeam;
    private final T awayTeam;

    public Match(T homeTeam, T awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

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
