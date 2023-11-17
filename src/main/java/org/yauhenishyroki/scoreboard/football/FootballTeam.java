package org.yauhenishyroki.scoreboard.football;

import org.yauhenishyroki.scoreboard.Team;

public final class FootballTeam extends Team {

    public FootballTeam(String name, int score) {
        super(name, score);
    }

    public FootballTeam(String name) {
        super(name);
    }

    public int incrementScore() {
        return ++this.score;
    }
}
