package model.level.map;

import junit.framework.TestCase;
import model.Position;
import model.level.map.block.Block;
import model.level.map.block.DoubleBlock;
import model.level.map.block.TypeBlock;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Permet de tester les méthodes qui modifient la map ou renvoient une valeur selon des certains critères
 * (Les getters basiques ne sont pas testés)
 */
public class MapTest extends TestCase {

    private final Map map1;
    private final Map map2;
    private final Map mapGate;

    /**
     * Constructeur de différentes map pour faire des tests
     */
    public MapTest() {
        Block[][] toCopy = new Block[3][3];
        for (int y = 0; y < toCopy.length; y++) {
            for (int x = 0; x < toCopy[y].length; x++) {
                toCopy[y][x] = new Block(TypeBlock.WALL);
            }
        }
        ArrayList<Position> movableBlock = new ArrayList<>();
        movableBlock.add(new Position(1, 2));
        movableBlock.add(new Position(2, 1));
        movableBlock.add(new Position(3, 3));
        movableBlock.add(new Position(0, 0));
        this.map1 = new Map(toCopy, 5, new Position(0, 0), new Position(1, 1), movableBlock);
        this.map2 = new Map(map1);

        Block[][] copy = new Block[3][3];
        for (int y = 0; y < copy.length; y++) {
            for (int x = 0; x < copy[y].length; x++) {
                copy[y][x] = new DoubleBlock(TypeBlock.WALL, TypeBlock.GATE);
            }
        }
        this.mapGate = new Map(copy, 5, new Position(0, 0), new Position(1, 1), new ArrayList<>());
    }

    /**
     * Test si c'est bien uen copie robuste
     */
    public void testCopyMap() {
        assertNotSame(this.map1.getMap(), this.map2.getMap());
        assertArrayEquals(this.map1.getMap(), this.map2.getMap());

        this.map1.moveBlock(new Position(1, 1), new Position(1, 2));

        assertNotEquals(this.map1.getBlock(new Position(1, 1)),
                this.map2.getBlock(new Position(1, 1)));
    }

    /**
     * Test si la position se trouve bien dans le tableau
     */
    public void testIsValidPos() {
        Position posInMap = new Position(2, 1);
        Position posOutMap = new Position(-5, 7);

        assertTrue(this.map1.isValidPos(posInMap));
        assertFalse(this.map1.isValidPos(posOutMap));
    }

    /**
     * Test si la porte est ouverte et existe
     */
    public void testOpenGate() {
        assertNotEquals(TypeBlock.GATE, this.mapGate.getBlock(new Position(1, 1)).getTypeDefault());
        try {
            this.map1.openGate();
        } catch (Exception e) {
            assertEquals("Le block donné à la pos " + new Position(1, 1) +
                    " n'est pas un double block", e.getMessage());
        }
        this.mapGate.openGate();
        assertEquals(TypeBlock.GATE, this.mapGate.getBlock(new Position(1, 1)).getTypeDefault());
    }

    /**
     * Test si le sortMovableBlock trie bien dans plus petit au plus grand.
     */
    public void testSortMovableBlock() {
        Position[] expected = new Position[]{
                new Position(0, 0),
                new Position(1, 2),
                new Position(2, 1),
                new Position(3, 3)
        };
        this.map1.sortMovableBlock();
        assertArrayEquals(expected, this.map1.getMovableBlock().toArray());
    }

}