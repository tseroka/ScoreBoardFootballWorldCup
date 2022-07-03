package pl.tseroka.cup.world.football.board.score;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoreBoardSummary {

    private final List<FootballGame> gamesByTotalScore;

    ScoreBoardSummary(List<FootballGame> games) {
        this.gamesByTotalScore = games.stream().sorted(Comparator.reverseOrder()).toList();
    }

    public List<FootballGame> getGamesByTotalScore() {
        return new ArrayList<>(gamesByTotalScore);
    }

    @Override
    public String toString() {
        var builder = new StringBuilder(gamesByTotalScore.size());
        gamesByTotalScore.forEach(game ->
            builder
            .append(game.toString())
            .append("\n")
        );
        return builder.toString();
    }
}
