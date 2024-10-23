package model;

import java.util.Objects;

/**
 * Une Position ayant deux axes Y = Col et X = Row
 *
 * @param y axe Y Col
 * @param x axe X Row
 */
public record Position(int y, int x) {

    /**
     * renvoie une nouvelle position en fonction de la direction donnée
     *
     * @param dir la direction où l'on doit aller
     * @return la nouvelle position où l'on va
     */
    public Position move(Direction dir) {
        return new Position(this.y + dir.getMove().y,
                this.x + dir.getMove().x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return y == position.y && x == position.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    @Override
    public String toString() {
        return "(" + this.y + "," + this.y + ")";
    }
}
