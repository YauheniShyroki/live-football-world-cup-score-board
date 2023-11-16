package org.yauhenishyroki.scoreboard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Team {

    private String name;
    private int score;

    protected Team(String name) {
        this.name = name;
    }
}
