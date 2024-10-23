package model;

/**
 * Définit le mouvement possible sur 4 positions cardinal
 */
public enum Direction {
    NORTH(new Position(-1, 0)),
    SOUTH(new Position(1, 0)),
    EAST(new Position(0, 1)),
    WEST(new Position(0, -1));

    private final Position move;

    /**
     * Constructeur d'une direction
     *
     * @param move la position vers où aller
     */
    Direction(Position move) {
        this.move = move;
    }

    /**
     * @return getter de la position où aller
     */
    public Position getMove() {
        return move;
    }
}
