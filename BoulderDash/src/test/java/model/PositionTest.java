package model;

import junit.framework.TestCase;

/**
 * Test si le mouvement se fait bien sur une seule position avec les différentes directions cardinales.
 */
public class PositionTest extends TestCase {

    private final Position posTest = new Position(0, 0);

    /**
     * Test si le mouvement se fait bien vers le Nord d'une case
     */
    public void testMoveNorth() {
        Position actual = this.posTest.move(Direction.NORTH);
        Position expected = new Position(-1, 0);

        assertEquals(expected, actual);
    }

    /**
     * Test si le mouvement se fait bien vers le Sud d'une case
     */
    public void testMoveSouth() {
        Position actual = this.posTest.move(Direction.SOUTH);
        Position expected = new Position(1, 0);

        assertEquals(expected, actual);
    }

    /**
     * Test si le mouvement se fait bien vers l'Est d'une case
     */
    public void testMoveEast() {
        Position actual = this.posTest.move(Direction.EAST);
        Position expected = new Position(0, 1);

        assertEquals(expected, actual);
    }

    /**
     * Test si le mouvement se fait bien vers l'Ouest d'une case
     */
    public void testMoveWest() {
        Position actual = this.posTest.move(Direction.WEST);
        Position expected = new Position(0, -1);

        assertEquals(expected, actual);
    }
}