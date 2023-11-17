package org.yauhenishyroki.scoreboard.football;

import org.yauhenishyroki.scoreboard.Match;

public final class FootballMatch extends Match<FootballTeam> {

    public FootballMatch(FootballTeam homeTeam, FootballTeam awayTeam) {
        super(homeTeam, awayTeam);
    }

    public int incrementHomeTeamScore() {
        throw new UnsupportedOperationException();
    }

    public int incrementAwayTeamScore() {
        throw new UnsupportedOperationException();
    }
}
