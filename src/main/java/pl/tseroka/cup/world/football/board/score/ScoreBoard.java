package pl.tseroka.cup.world.football.board.score;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ScoreBoard {

    public static ScoreBoard create() {
        return new ScoreBoard(new ArrayList<>());
    }

    private final List<FootballGame> games;

    ScoreBoard(List<FootballGame> games) {
        this.games = games;
    }

    public FootballGame startNewGame(FootballTeam homeTeam, FootballTeam awayTeam) {
        if (homeTeam == null || awayTeam == null) {
            throw new IllegalArgumentException("Teams provided to start new game must not be null");
        }
        if (!areTeamsAvailable(homeTeam, awayTeam)) {
            throw new IllegalStateException("One or both of provided to start new game are already playing");
        }
        var newGame = FootballGame.start(homeTeam, awayTeam);
        games.add(newGame);
        return newGame;
    }

    private boolean areTeamsAvailable(FootballTeam homeTeam, FootballTeam awayTeam) {
        return games.stream().noneMatch(game ->
                game.getHomeTeam().equals(homeTeam) || game.getAwayTeam().equals(homeTeam) ||
                    game.getHomeTeam().equals(awayTeam) || game.getAwayTeam().equals(awayTeam)
        );
    }

    public FootballGame updateGame(FootballGameUpdateCommand gameUpdate) {
        var game = getGame(
            gameUpdate.getHomeTeam(), gameUpdate.getAwayTeam()
        );
        game.updateScore(
            gameUpdate.getHomeTeamScore(),
            gameUpdate.getAwayTeamScore()
        );
        return game;
    }

    private FootballGame getGame(FootballTeam homeTeam, FootballTeam awayTeam) {
        return games.stream().filter(
            game -> game.getHomeTeam().equals(homeTeam) && game.getAwayTeam().equals(awayTeam)
        ).findFirst().orElseThrow(() ->
            new NoSuchElementException("Provided teams does not currently playing game with each other")
        );
    }

    public void finishGame(FootballGame footballGame) {
        finishGame(footballGame.getHomeTeam(), footballGame.getAwayTeam());
    }

    public void finishGame(FootballTeam homeTeam, FootballTeam awayTeam) {
        if (homeTeam == null || awayTeam == null) {
            throw new IllegalArgumentException("Teams provided to finish game must not be null");
        }
        var game = getGame(homeTeam, awayTeam);
        game.finishGame();
        games.remove(game);
    }

    public ScoreBoardSummary getSummary() {
        return null;
    }
}
