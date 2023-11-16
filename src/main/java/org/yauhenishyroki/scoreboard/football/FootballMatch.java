package org.yauhenishyroki.scoreboard.football;

import org.yauhenishyroki.scoreboard.Match;

public final class FootballMatch extends Match<FootballTeam> {

    public FootballMatch(FootballTeam homeTeam, FootballTeam awayTeam) {
        super(homeTeam, awayTeam);
    }

    public void incrementHomeTeamScore() {
        throw new UnsupportedOperationException();
    }

    public void incrementAwayTeamScore() {
        throw new UnsupportedOperationException();
    }
}
