package org.yauhenishyroki.scoreboard.football;

import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FootballTeamTest {

    private final Random random = new Random();
    private final RandomString randomString = new RandomString();

    @Test
    @DisplayName("Score for football team should be zero on init without specifying it")
    void zeroScoreOnConstructor() {
        //given
        FootballTeam footballTeam;

        //when
        footballTeam = new FootballTeam(randomString.nextString());

        //then
        assertEquals(0, footballTeam.getScore());
    }

    @Test
    @DisplayName("Increment score for football team")
    void incrementScoreTest() {
        //given
        int score = random.nextInt(10);
        FootballTeam team = new FootballTeam(randomString.nextString(), score);

        //when
        int incrementedScore = team.incrementScore();

        //then
        assertEquals(score + 1, incrementedScore);
        assertEquals(score + 1, team.getScore());
    }
}