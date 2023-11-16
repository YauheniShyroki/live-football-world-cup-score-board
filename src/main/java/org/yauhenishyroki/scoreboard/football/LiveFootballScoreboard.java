package org.yauhenishyroki.scoreboard.football;

import lombok.Getter;
import org.yauhenishyroki.scoreboard.Match;
import org.yauhenishyroki.scoreboard.Scoreboard;

import java.util.List;

@Getter
public final class LiveFootballScoreboard implements Scoreboard<Match<FootballTeam>> {

    private List<Match<FootballTeam>> liveMatches;

    @Override
    public void startMatch(Match<FootballTeam> match) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateScore(Match<FootballTeam> match, int homeTeamScore, int awayTeamScore) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void finishMatch(Match<FootballTeam> match) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String summary() {
        throw new UnsupportedOperationException();
    }
}
