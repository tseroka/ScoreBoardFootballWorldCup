package pl.tseroka.cup.world.football.board.score

import spock.lang.Specification

class ScoreBoardTests extends Specification {

    def "starting new game test"() {
        given: "new score board"
        def scoreBoard = ScoreBoard.create()

        when: "Germany - France game is requested to start"
        def newGame = scoreBoard.startNewGame(new FootballTeam("Germany"), new FootballTeam("France"))

        then: "Germany - France game with initial score 0 - 0 is created"
        with (newGame) {
            getHomeTeam().name() == "Germany"
            getAwayTeam().name() == "France"
            getHomeTeamScore() == 0
            getAwayTeamScore() == 0
        }

        when: "France - Germany is requested to start again despite already running game"
        scoreBoard.startNewGame(new FootballTeam("France"), new FootballTeam("Germany"))

        then: "exception is thrown"
        IllegalStateException bothTeamsAlreadyPlayingException = thrown()
        bothTeamsAlreadyPlayingException.message == "One or both of provided to start new game are already playing"

        when: "Poland - France is requested to start again despite France already playing game"
        scoreBoard.startNewGame(new FootballTeam("Poland"), new FootballTeam("France"))

        then: "exception is thrown"
        IllegalStateException oneTeamAlreadyPlayingException = thrown()
        oneTeamAlreadyPlayingException.message == "One or both of provided to start new game are already playing"
    }

    def "updating started game test"() {
        given: "existing board with England - Spain game started"
        def scoreBoard = ScoreBoard.create()
        def startedGame = scoreBoard.startNewGame(new FootballTeam("England"), new FootballTeam("Spain"))

        when: "England - Spain game update with new score is requested"
        scoreBoard.updateGame(
            FootballGameUpdateCommand.builder()
                .homeTeam(new FootballTeam("England"))
                .homeTeamScore(2)
                .awayTeam(new FootballTeam("Spain"))
                .awayTeamScore(1)
            .build()
        )

        then: "England - Spain game score is updated"
        with (startedGame) {
            getHomeTeamScore() == 2
            getAwayTeamScore() == 1
        }

        when: "the same England - Spain game update with 0 - 0 score is requested"
        scoreBoard.updateGame(
            FootballGameUpdateCommand.builder()
                .homeTeam(new FootballTeam("England"))
                .homeTeamScore(0)
                .awayTeam(new FootballTeam("Spain"))
                .awayTeamScore(0)
            .build()
        )

        then: "exception is thrown"
        IllegalArgumentException exception = thrown()
        exception.message == "Team score cannot be updated with lesser than current value"
    }

    def "finishing started game test"() {
        given: "existing board with England - Spain game started"
        def scoreBoard = ScoreBoard.create()
        def startedGame = scoreBoard.startNewGame(new FootballTeam("England"), new FootballTeam("Spain"))

        when: "England - Spain game finish without goals is requested"
        scoreBoard.finishGame(startedGame)
        and: "score update is requested"
        scoreBoard.updateGame(
            FootballGameUpdateCommand.builder()
                .homeTeam(new FootballTeam("England"))
                .homeTeamScore(2)
                .awayTeam(new FootballTeam("Spain"))
                .awayTeamScore(1)
            .build()
        )

        then: "exception is thrown"
        NoSuchElementException exception = thrown()
        exception.message == "Provided teams does not currently playing game with each other"
    }
}
