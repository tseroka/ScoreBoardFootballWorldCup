package pl.tseroka.cup.world.football.board.score;

import java.time.LocalDateTime;

public class FootballGame implements Comparable<FootballGame> {

    static FootballGame start(FootballTeam homeTeam, FootballTeam awayTeam) {
        return new FootballGame(
            new TeamScore(homeTeam, 0),
            new TeamScore(awayTeam, 0),
            GameStatus.STARTED,
            LocalDateTime.now()
        );
    }

    private final TeamScore homeTeam;
    private final TeamScore awayTeam;

    private GameStatus status;

    private final LocalDateTime started;

    private FootballGame(TeamScore homeTeam, TeamScore awayTeam, GameStatus status, LocalDateTime started) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.status = status;
        this.started = started;
    }

    void updateScore(int homeTeamScore, int awayTeamScore) {
        if (isFinished()) {
            throw new IllegalStateException("Cannot update score of finished game");
        }
        this.homeTeam.updateScore(homeTeamScore);
        this.awayTeam.updateScore(awayTeamScore);
    }

    private boolean isFinished() {
        return this.status == GameStatus.FINISHED;
    }

    void finishGame() {
        if (isFinished()) {
            throw new IllegalStateException("Game was already finished");
        }
        this.status = GameStatus.FINISHED;
    }

    public int calculateTotalScore() {
        return homeTeam.getScore() + awayTeam.getScore();
    }

    public FootballTeam getHomeTeam() {
        return this.homeTeam.getTeam();
    }

    public int getHomeTeamScore() {
        return this.homeTeam.getScore();
    }

    public FootballTeam getAwayTeam() {
        return this.awayTeam.getTeam();
    }

    public int getAwayTeamScore() {
        return this.awayTeam.getScore();
    }

    @Override
    public String toString() {
        return String.format(
            "%s - %s: %s - %s",
            homeTeam.getTeam().name(),
            awayTeam.getTeam().name(),
            homeTeam.getScore(),
            awayTeam.getScore()
        );
    }

    @Override
    public int compareTo(FootballGame otherGame) {
        if (this.calculateTotalScore() > otherGame.calculateTotalScore()) {
            return 1;
        }
        if (this.calculateTotalScore() < otherGame.calculateTotalScore()) {
            return -1;
        } else {
            return this.started.compareTo(otherGame.started);
        }
    }
}
