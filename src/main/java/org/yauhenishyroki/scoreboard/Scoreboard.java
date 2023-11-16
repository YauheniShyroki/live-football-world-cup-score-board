package org.yauhenishyroki.scoreboard;

public interface Scoreboard<T extends Match<? extends Team>> {

    void startMatch(T match);

    void updateScore(T match, int homeTeamScore, int awayTeamScore);

    void finishMatch(T match);

    String summary();
}
