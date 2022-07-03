package pl.tseroka.cup.world.football.board.score;

import java.util.Objects;

public final record FootballTeam(String name) {

    public FootballTeam {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name provided for a team must not be null or empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballTeam that = (FootballTeam) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
