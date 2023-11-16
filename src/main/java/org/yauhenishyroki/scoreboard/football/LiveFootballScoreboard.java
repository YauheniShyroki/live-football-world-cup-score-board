package org.yauhenishyroki.scoreboard.football;

import lombok.Getter;
import org.yauhenishyroki.scoreboard.Scoreboard;

import java.util.List;

@Getter
public final class LiveFootballScoreboard implements Scoreboard<FootballMatch> {

    private List<FootballMatch> liveMatches;

    @Override
    public void startMatch(FootballMatch match) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateScore(FootballMatch match, int homeTeamScore, int awayTeamScore) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void finishMatch(FootballMatch match) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String summary() {
        throw new UnsupportedOperationException();
    }
}
