package org.yauhenishyroki.scoreboard.football;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class FootballTeamTest {

    @Test
    @DisplayName("Score for football team should be zero on init without specifying it")
    void zeroScoreOnConstructor() {
        //given
        FootballTeam footballTeam;

        //when
        footballTeam = new FootballTeam("Any Team");

        //then
        assertEquals(0, footballTeam.getScore());
    }

    @Test
    @DisplayName("Increment score for football team")
    void incrementScoreTest() {
        //given
        int score = new Random().nextInt(10);
        FootballTeam team = new FootballTeam("Liverpool", score);

        //when
        int incrementedScore = team.incrementScore();

        //then
        assertEquals(score + 1, incrementedScore);
        assertEquals(score + 1, team.getScore());
    }
}