package org.yauhenishyroki.scoreboard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Team {

    protected String name;
    protected int score;

    protected Team(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score couldn't be negative");
        } else {
            this.score = score;
        }
    }
}
