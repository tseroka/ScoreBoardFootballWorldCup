package pl.tseroka.cup.world.football.board.score

import spock.lang.Specification

import java.util.concurrent.TimeUnit

class ScoreBoardSummaryTests extends Specification {

    def "getting games summary by total score test"() {
        given: "brand new score board"
        def scoreBoard = ScoreBoard.create()
        and: "many games are started and being played"
        startAndPlayGame(scoreBoard, "France", "Mexico", 0, 2)
        startAndPlayGame(scoreBoard, "Germany", "England", 3, 5)
        startAndPlayGame(scoreBoard, "Finland", "Spain", 2, 2)
        startAndPlayGame(scoreBoard, "Italy", "Japan", 4, 5)
        startAndPlayGame(scoreBoard, "Poland", "Switzerland", 1, 3)


        when: "board summary is requested"
        def summary = scoreBoard.getSummary()

        then: "games are sorted by score"
        with (summary.getGamesByTotalScore()) {
            get(0).getHomeTeam().name() == "Italy" && get(0).getAwayTeam().name() == "Japan" && get(0).calculateTotalScore() == 9
            get(1).getHomeTeam().name() == "Germany" && get(1).getAwayTeam().name() == "England" && get(1).calculateTotalScore() == 8
            get(2).getHomeTeam().name() == "Poland" && get(2).getAwayTeam().name() == "Switzerland" && get(2).calculateTotalScore() == 4
            get(3).getHomeTeam().name() == "Finland" && get(3).getAwayTeam().name() == "Spain" && get(3).calculateTotalScore() == 4
            get(4).getHomeTeam().name() == "France" && get(4).getAwayTeam().name() == "Mexico" && get(4).calculateTotalScore() == 2
        }

        when: "first started game finish is requested"
        scoreBoard.finishGame(new FootballTeam("France"), new FootballTeam("Mexico"))
        then: "first started game is removed from score board"
        scoreBoard.getSummary().getGamesByTotalScore().stream().noneMatch(game -> game.getHomeTeam().name() == "France")
    }

    private static void startAndPlayGame(
        ScoreBoard board,
        String homeTeamName,
        String awayTeamName,
        int homeTeamScore,
        int awayTeamScore
    ) {
        board.startNewGame(new FootballTeam(homeTeamName), new FootballTeam(awayTeamName))
        board.updateGame(
            FootballGameUpdateCommand.builder()
                .homeTeam(new FootballTeam(homeTeamName))
                .awayTeam(new FootballTeam(awayTeamName))
                .homeTeamScore(homeTeamScore)
                .awayTeamScore(awayTeamScore)
            .build()
        )
        // workaround to eliminate sometimes failing test in case when time difference between games starts is too short to be detected
        TimeUnit.MILLISECONDS.sleep(50)
    }
}
