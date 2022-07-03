package pl.tseroka.cup.world.football.board.score;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    public static ScoreBoard create() {
        return new ScoreBoard(new ArrayList<>());
    }

    private final List<FootballGame> games;

    public ScoreBoard(List<FootballGame> games) {
        this.games = games;
    }

    public FootballGame startNewGame(FootballTeam homeTeam, FootballTeam awayTeam) {
        return null;
    }

    public FootballGame updateGame(FootballGameUpdateCommand gameUpdate) {
        return null;
    }

    public void finishGame(FootballTeam homeTeam, FootballTeam awayTeam) {
    }

    public ScoreBoardSummary getSummary() {
        return null;
    }
}
