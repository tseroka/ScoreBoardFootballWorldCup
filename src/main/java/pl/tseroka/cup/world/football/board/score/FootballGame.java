package pl.tseroka.cup.world.football.board.score;

public class FootballGame {

    static FootballGame start(FootballTeam homeTeam, FootballTeam awayTeam) {
        return new FootballGame(
            new TeamScore(homeTeam, 0),
            new TeamScore(awayTeam, 0)
        );
    }

    private final TeamScore homeTeam;
    private final TeamScore awayTeam;

    private FootballGame(TeamScore homeTeam, TeamScore awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
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
