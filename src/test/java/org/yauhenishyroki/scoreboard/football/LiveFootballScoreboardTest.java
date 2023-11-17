package org.yauhenishyroki.scoreboard.football;

import net.bytebuddy.utility.RandomString;
import org.apache.commons.collections4.ComparatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LiveFootballScoreboardTest {

    private LiveFootballScoreboard scoreboard;

    private final Random random = new Random();
    private final RandomString randomString = new RandomString();

    private static final String SUMMARY_LINE_PATTERN = "%s %d - %s %d";

    @BeforeEach
    void setUp() {
        this.scoreboard = new LiveFootballScoreboard();
    }

    @Test
    @DisplayName("liveMatches field should be initialized (not be null) and be empty")
    void liveMatchesListIsInitialized() {
        assertNotNull(scoreboard.getLiveMatches());
        assertEquals(0, scoreboard.getLiveMatches().size());
    }

    @Test
    @DisplayName("startMatch method should add a match to liveMatches list")
    void startMatchShouldAddAMatchToList() {
        //given
        FootballMatch footballMatch = generateTestFootballMatch();

        //when
        scoreboard.startMatch(footballMatch);

        //then
        assertEquals(1, scoreboard.getLiveMatches().size());
        assertEquals(footballMatch, scoreboard.getLiveMatches().get(0));
    }

    @Test
    @DisplayName("startMatch method should add all matches to liveMatches list")
    void startMatchShouldAddAllMatchesToList() {
        //given
        int numberOfMatches = random.nextInt(1, 10);
        List<FootballMatch> matches = generateTestFootballMatches(numberOfMatches);

        //when
        matches.forEach(footballMatch -> scoreboard.startMatch(footballMatch));

        //then
        assertEquals(numberOfMatches, scoreboard.getLiveMatches().size());
    }

    @Test
    @DisplayName("Trying to start null match")
    void startNullMatch() {
        assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.startMatch(null);
        });
    }

    @Test
    @DisplayName("startMatch method trying to add existing match")
    void startExistingMatch() {
        //given
        FootballMatch footballMatch = generateTestFootballMatch();

        //when
        scoreboard.startMatch(footballMatch);

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.startMatch(footballMatch);
        });
    }

    @ParameterizedTest
    @MethodSource("provideScoresForPositiveTest")
    @DisplayName("Positive: update scores for a match")
    void updateScorePositive(int homeScore, int awayScore) {
        //given
        FootballMatch footballMatch = generateTestFootballMatch();
        scoreboard.getLiveMatches().add(footballMatch);

        //when
        scoreboard.updateScore(footballMatch, homeScore, awayScore);

        //then
        assertEquals(homeScore, scoreboard.getLiveMatches().get(0).getHomeTeam().getScore());
        assertEquals(awayScore, scoreboard.getLiveMatches().get(0).getAwayTeam().getScore());
    }

    private static Stream<Arguments> provideScoresForPositiveTest() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(2, 3),
                Arguments.of(Integer.MAX_VALUE, 0),
                Arguments.of(123, Integer.MAX_VALUE),
                Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideScoresForNegativeTest")
    @DisplayName("Negative: update scores for a match")
    void updateScoreNegative(int homeScore, int awayScore) {
        //given
        FootballMatch footballMatch = generateTestFootballMatch();
        scoreboard.getLiveMatches().add(footballMatch);

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.updateScore(footballMatch, homeScore, awayScore);
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

    @Test
    @DisplayName("trying to update scores for a non-existing match")
    void updateScoreForNonExistingMatch() {
        assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.updateScore(FootballMatch.of(randomString.nextString()), 0, 0);
        });
    }

    @Test
    @DisplayName("finnishMatch method should remove a match from liveMatches list with only one match")
    void finishMatchShouldRemoveMatchFromSingletonLiveMatches() {
        //given
        FootballMatch footballMatch = generateTestFootballMatch();
        scoreboard.getLiveMatches().add(footballMatch);

        //when
        scoreboard.finishMatch(footballMatch);

        //then
        assertFalse(scoreboard.getLiveMatches().contains(footballMatch));
        assertEquals(0, scoreboard.getLiveMatches().size());
    }

    @Test
    @DisplayName("finnishMatch method should remove a match from liveMatches list with many matches")
    void finishMatchShouldRemoveMatchFromLiveMatches() {
        //given
        int numberOfMatchesToStayInLiveMatches = random.nextInt(1, 10);
        List<FootballMatch> footballMatches = generateTestFootballMatches(numberOfMatchesToStayInLiveMatches);
        FootballMatch footballMatchToFinish = generateTestFootballMatch();
        scoreboard.getLiveMatches().addAll(footballMatches);
        scoreboard.getLiveMatches().add(footballMatchToFinish);

        //when
        scoreboard.finishMatch(footballMatchToFinish);

        //then
        assertEquals(numberOfMatchesToStayInLiveMatches, scoreboard.getLiveMatches().size());
        assertFalse(scoreboard.getLiveMatches().contains(footballMatchToFinish));
    }

    @Test
    @DisplayName("Trying to finish null match")
    void finishNullMatch() {
        assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.finishMatch(null);
        });
    }

    @Test
    @DisplayName("finnishMatch method shouldn't throw exception on non-existing match")
    void finishMatchOnNonExistingMatch() {
        //given
        FootballMatch footballMatch = generateTestFootballMatch();

        //when
        //then
        assertDoesNotThrow(() -> scoreboard.finishMatch(footballMatch));
        assertFalse(scoreboard.getLiveMatches().contains(footballMatch));
        assertEquals(0, scoreboard.getLiveMatches().size());
    }

    @Test
    @DisplayName("Getting summary for multiple matches")
    void summaryTest() {
        //given
        int numberOfMatches = random.nextInt(1, 10);
        List<FootballMatch> footballMatches = generateTestFootballMatches(numberOfMatches);
        scoreboard.getLiveMatches().addAll(footballMatches);
        String expectedSummary = getExpectedSummaryForMatches(footballMatches);

        //when
        String summary = scoreboard.summary();

        //then
        assertTrue(StringUtils.isNotEmpty(summary));
        //check number of lines, should be the same as number of matches
        assertEquals(numberOfMatches, summary.split("\n").length);
        assertEquals(expectedSummary, summary);

    }

    private String getExpectedSummaryForMatches(List<FootballMatch> footballMatches) {
        return footballMatches.stream()
                .sorted(comparatorForSummary())
                .map(this::getSummaryLineForMatch)
                .collect(Collectors.joining("\n"));
    }

    @SuppressWarnings("unchecked")
    private Comparator<FootballMatch> comparatorForSummary() {
        return ComparatorUtils.chainedComparator(
                Comparator.comparingInt(this::getTotalScore).reversed(),
                Comparator.comparingInt(this::getIndexInList));
    }

    private int getTotalScore(FootballMatch footballMatch) {
        return footballMatch.getHomeTeam().getScore() + footballMatch.getAwayTeam().getScore();
    }

    private int getIndexInList(FootballMatch footballMatch) {
        return scoreboard.getLiveMatches().indexOf(footballMatch);
    }

    private String getSummaryLineForMatch(FootballMatch footballMatch) {
        FootballTeam homeTeam = footballMatch.getHomeTeam();
        FootballTeam awayTeam = footballMatch.getAwayTeam();

        return String.format(SUMMARY_LINE_PATTERN,
                homeTeam.getName(), homeTeam.getScore(),
                awayTeam.getName(), awayTeam.getScore());
    }

    @Test
    @DisplayName("Getting summary for zero matches")
    void zeroMatchesSummaryTest() {
        String summary = scoreboard.summary();

        assertNotNull(summary);
        assertTrue(StringUtils.isBlank(summary));
    }

    private List<FootballMatch> generateTestFootballMatches(int numberOfMatches) {
        List<FootballMatch> matches = new ArrayList<>();
        for (int i = 0; i < numberOfMatches; i++) {
            matches.add(generateTestFootballMatch());
        }
        return matches;
    }

    private FootballMatch generateTestFootballMatch() {
        FootballTeam homeTeam = new FootballTeam(randomString.nextString(), random.nextInt(10));
        FootballTeam awayTeam = new FootballTeam(randomString.nextString(), random.nextInt(10));
        return new FootballMatch(randomString.nextString(), homeTeam, awayTeam);
    }
}