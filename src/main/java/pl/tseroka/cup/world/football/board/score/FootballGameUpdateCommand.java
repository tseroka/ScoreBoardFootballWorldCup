package pl.tseroka.cup.world.football.board.score;

import java.util.Objects;

public final class FootballGameUpdateCommand {
    public static FootballGameUpdateCommandBuilder builder() {
        return new FootballGameUpdateCommandBuilder();
    }

    private final FootballTeam homeTeam;
    private final int homeTeamScore;

    private final FootballTeam awayTeam;
    private final int awayTeamScore;

    public FootballTeam getHomeTeam() {
        return homeTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public FootballTeam getAwayTeam() {
        return awayTeam;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    private FootballGameUpdateCommand(FootballTeam homeTeam, int homeTeamScore, FootballTeam awayTeam, int awayTeamScore) {
        this.homeTeam = homeTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeam = awayTeam;
        this.awayTeamScore = awayTeamScore;
    }

    public static class FootballGameUpdateCommandBuilder {
        private FootballTeam homeTeam;
        private int homeTeamScore;
        private FootballTeam awayTeam;
        private int awayTeamScore;

        public FootballGameUpdateCommandBuilder homeTeam(FootballTeam homeTeam) {
            this.homeTeam = Objects.requireNonNull(homeTeam);
            return this;
        }

        public FootballGameUpdateCommandBuilder homeTeamScore(int homeTeamScore) {
            this.homeTeamScore = homeTeamScore;
            return this;
        }

        public FootballGameUpdateCommandBuilder awayTeam(FootballTeam awayTeam) {
            this.awayTeam = Objects.requireNonNull(awayTeam);
            return this;
        }

        public FootballGameUpdateCommandBuilder awayTeamScore(int awayTeamScore) {
            this.awayTeamScore = awayTeamScore;
            return this;
        }

        public FootballGameUpdateCommand build() {
            return new FootballGameUpdateCommand(homeTeam, homeTeamScore, awayTeam, awayTeamScore);
        }
    }
}
