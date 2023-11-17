package org.yauhenishyroki.scoreboard.football;

import lombok.Getter;
import org.apache.commons.collections4.ComparatorUtils;
import org.yauhenishyroki.scoreboard.Scoreboard;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public final class LiveFootballScoreboard implements Scoreboard<FootballMatch> {

    private final List<FootballMatch> liveMatches;

    public LiveFootballScoreboard() {
        this.liveMatches = new ArrayList<>();
    }

    @Override
    public void startMatch(FootballMatch match) {
        getFootballMatchFromLiveMatches(match)
                .ifPresentOrElse(
                        foundMatch -> { throw new IllegalArgumentException("A match with name '" + match.getName() + "' exists"); },
                        () -> liveMatches.add(match));

    }

    @Override
    public void updateScore(FootballMatch match, int homeTeamScore, int awayTeamScore) {
        getFootballMatchFromLiveMatches(match)
                .ifPresentOrElse(
                        foundMatch -> foundMatch.updateScores(homeTeamScore, awayTeamScore),
                        () -> { throw new IllegalArgumentException("Failed to find a match with name '" + match.getName() + "'"); }
                );
    }

    @Override
    public void finishMatch(FootballMatch match) {
        getFootballMatchFromLiveMatches(match)
                .ifPresent(liveMatches::remove);
    }

    private Optional<FootballMatch> getFootballMatchFromLiveMatches(FootballMatch match) {
        if (Objects.isNull(match)) {
            throw new IllegalArgumentException("Match is null");
        }
        List<FootballMatch> foundMatches = liveMatches.stream()
                .filter(liveMatch -> liveMatch.getName().equals(match.getName()))
                .toList();
        if (foundMatches.isEmpty()) {
            return Optional.empty();
        } else if (foundMatches.size() > 1) {
            throw new IllegalArgumentException("Found more than one match by name '" + match.getName() + "'");
        } else {
            return Optional.of(foundMatches.get(0));
        }
    }

    @Override
    public String summary() {
        return getSummaryForMatches(liveMatches);
    }

    private String getSummaryForMatches(List<FootballMatch> footballMatches) {
        return footballMatches.stream()
                .sorted(comparatorForSummary())
                .map(FootballMatch::toString)
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
        return liveMatches.indexOf(footballMatch);
    }
}
