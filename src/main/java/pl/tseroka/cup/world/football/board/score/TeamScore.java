package pl.tseroka.cup.world.football.board.score;

public final class TeamScore {
    private final FootballTeam team;
    private int score;

    public TeamScore(FootballTeam team, int score) {
        this.team = team;
        this.score = score;
    }

    public FootballTeam getTeam() {
        return this.team;
    }

    public int getScore() {
        return this.score;
    }
}
