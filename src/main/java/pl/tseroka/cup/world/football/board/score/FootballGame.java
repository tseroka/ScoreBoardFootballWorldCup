package pl.tseroka.cup.world.football.board.score;

public class FootballGame {

    static FootballGame start(FootballTeam homeTeam, FootballTeam awayTeam) {
        return new FootballGame(
            new TeamScore(homeTeam, 0),
            new TeamScore(awayTeam, 0),
            GameStatus.STARTED
        );
    }

    private final TeamScore homeTeam;
    private final TeamScore awayTeam;

    private GameStatus status;

    private FootballGame(TeamScore homeTeam, TeamScore awayTeam, GameStatus status) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.status = status;
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
}
