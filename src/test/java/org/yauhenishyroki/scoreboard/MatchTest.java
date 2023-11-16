package org.yauhenishyroki.scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    private Match<TestTeam> match;

    @BeforeEach
    void setUp() {
        //given
        TestTeam homeTeam = new TestTeam("home");
        TestTeam awayTeam = new TestTeam("away");
        this.match = new Match<>(homeTeam, awayTeam);
    }

    @ParameterizedTest
    @MethodSource("provideScoresForPositiveTest")
    @DisplayName("Positive: update scores")
    void updateScoresPositive(int homeTeamScore, int awayTeamScore) {
        assertDoesNotThrow(() -> {
            match.updateScores(homeTeamScore, awayTeamScore);

            assertEquals(homeTeamScore, match.getHomeTeam().getScore());
            assertEquals(awayTeamScore, match.getAwayTeam().getScore());
        });
    }

    private static Stream<Arguments> provideScoresForPositiveTest() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1, 2),
                Arguments.of(Integer.MAX_VALUE, 0),
                Arguments.of(10, Integer.MAX_VALUE),
                Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideScoresForNegativeTest")
    @DisplayName("Negative: update scores")
    void updateScoresNegative(int homeTeamScore, int awayTeamScore) {
        assertThrows(IllegalArgumentException.class, () -> {
            match.updateScores(homeTeamScore, awayTeamScore);
        });
    }

    private static Stream<Arguments> provideScoresForNegativeTest() {
        return Stream.of(
                Arguments.of(1, -1),
                Arguments.of(-1, 1),
                Arguments.of(Integer.MIN_VALUE, 0),
                Arguments.of(0, Integer.MIN_VALUE),
                Arguments.of(Integer.MIN_VALUE, Integer.MIN_VALUE)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MAX_VALUE, 0, 1, 11})
    @DisplayName("Positive: update home team score")
    void updateHomeTeamScorePositive(int homeTeamScore) {
        assertDoesNotThrow(() -> {
            match.updateHomeTeamScore(homeTeamScore);

            assertEquals(match.getHomeTeam().getScore(), homeTeamScore);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, -11})
    @DisplayName("Negative: update home team score")
    void updateHomeTeamScoreNegative(int homeTeamScore) {
        assertThrows(IllegalArgumentException.class, () -> {
            match.updateHomeTeamScore(homeTeamScore);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MAX_VALUE, 0, 1, 2, 22})
    @DisplayName("Positive: update away team score")
    void updateAwayTeamScorePositive(int awayTeamScore) {
        assertDoesNotThrow(() -> {
            match.updateAwayTeamScore(awayTeamScore);

            assertEquals(match.getAwayTeam().getScore(), awayTeamScore);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, -22})
    @DisplayName("Negative: update away team score")
    void updateAwayTeamScoreNegative(int awayTeamScore) {
        assertThrows(IllegalArgumentException.class, () -> {
            match.updateAwayTeamScore(awayTeamScore);
        });
    }

    static class TestTeam extends Team {
        public TestTeam(String name) {
            super(name);
        }
    }
}