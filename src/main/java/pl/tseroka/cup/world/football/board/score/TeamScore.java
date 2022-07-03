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

    void updateScore(int latestScore) {
        if (latestScore < this.score) {
            throw new IllegalArgumentException("Team score cannot be updated with lesser than current value");
        }
        this.score = latestScore;
    }
}
