package org.yauhenishyroki.scoreboard;

import lombok.Getter;

@Getter
public class Match<T extends Team> {

    protected final T homeTeam;
    protected final T awayTeam;

    public Match(T homeTeam, T awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public void updateScores(int homeTeamScore, int awayTeamScore) {
        updateHomeTeamScore(homeTeamScore);
        updateAwayTeamScore(awayTeamScore);
    }

    public void updateHomeTeamScore(int homeTeamScore) {
        this.homeTeam.setScore(homeTeamScore);
    }

    public void updateAwayTeamScore(int awayTeamScore) {
        this.awayTeam.setScore(awayTeamScore);
    }
}
