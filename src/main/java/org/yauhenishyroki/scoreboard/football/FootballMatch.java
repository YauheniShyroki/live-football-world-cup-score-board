package org.yauhenishyroki.scoreboard.football;

import lombok.Getter;
import org.yauhenishyroki.scoreboard.Match;

@Getter
public final class FootballMatch extends Match<FootballTeam> {

    private final String name;

    public FootballMatch(String name, FootballTeam homeTeam, FootballTeam awayTeam) {
        super(homeTeam, awayTeam);
        this.name = name;
    }

    private FootballMatch(String name) {
        super(null, null);
        this.name = name;
    }

    public static FootballMatch of(String name) {
        return new FootballMatch(name);
    }

    public int incrementHomeTeamScore() {
        return this.homeTeam.incrementScore();
    }

    public int incrementAwayTeamScore() {
        return this.awayTeam.incrementScore();
    }

    @Override
    public String toString() {
        return String.format("%s %d - %s %d",
                homeTeam.getName(), homeTeam.getScore(),
                awayTeam.getName(), awayTeam.getScore());
    }
}
