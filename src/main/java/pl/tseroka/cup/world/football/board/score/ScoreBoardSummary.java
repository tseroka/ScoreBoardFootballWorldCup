package pl.tseroka.cup.world.football.board.score;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardSummary {

    private final List<FootballGame> gamesByTotalScore;

    public ScoreBoardSummary(List<FootballGame> games) {
        this.gamesByTotalScore = games;
    }

    public List<FootballGame> getGamesByTotalScore() {
        return new ArrayList<>(gamesByTotalScore);
    }
}
