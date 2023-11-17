package org.yauhenishyroki.scoreboard.football;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FootballMatchTest {

    private FootballMatch footballMatch;

    private final Random random = new Random();

    @BeforeEach
    void setUp() {
        FootballTeam homeTeam = new FootballTeam("home", random.nextInt(10));
        FootballTeam awayTeam = new FootballTeam("away", random.nextInt(10));
        this.footballMatch = new FootballMatch("match", homeTeam, awayTeam);
    }

    @Test
    @DisplayName("Home team score should be incremented")
    void incrementHomeTeamTest() {
        int homeScore = footballMatch.getHomeTeam().getScore();
        int awayScore = footballMatch.getAwayTeam().getScore();

        int incrementedScore = footballMatch.incrementHomeTeamScore();

        assertEquals(homeScore + 1, footballMatch.getHomeTeam().getScore());
        assertEquals(homeScore + 1, incrementedScore);
        assertEquals(awayScore, footballMatch.getAwayTeam().getScore());
    }

    @Test
    @DisplayName("Away team score should be incremented")
    void incrementAwayTeamTest() {
        int homeScore = footballMatch.getHomeTeam().getScore();
        int awayScore = footballMatch.getAwayTeam().getScore();

        int incrementedScore = footballMatch.incrementAwayTeamScore();

        assertEquals(homeScore, footballMatch.getHomeTeam().getScore());
        assertEquals(awayScore + 1, footballMatch.getAwayTeam().getScore());
        assertEquals(awayScore + 1, incrementedScore);
    }
}