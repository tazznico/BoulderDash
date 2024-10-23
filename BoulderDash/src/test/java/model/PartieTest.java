package model;

import junit.framework.TestCase;
import model.level.LevelList;
import model.level.map.block.TypeBlock;

/**
 * Permet de tester les méthodes de la classe partie
 */
public class PartieTest extends TestCase {

    private final LevelList levelList = new LevelList("test");
    private Partie partie;


    /* Mouvement du joueur */

    /**
     * Test si la position du joueur après un mouvement est
     * égale à la position du joueur avant ce mouvement + le mouvement.
     */
    public void testMove() {
        this.partie = new Partie(levelList.getLevel("test 1"));

        Position posAfter = partie.getPosCurPlayer();
        this.partie.move(Direction.EAST);

        assertEquals(this.partie.getPosCurPlayer(), posAfter.move(Direction.EAST));
    }

    /**
     * Test les différentes erreurs que peut rencontrer le joueur s'il veut se déplacer sur un block qu'il ne peut pas.
     */
    public void testMoveError() {
        this.partie = new Partie(levelList.getLevel("test 6"));

        try {
            this.partie.move(Direction.EAST); // En dehors de la carte
        } catch (Exception e) {
            assertEquals("la futur nouvelle position du joueur est en dehors de la carte",
                    e.getMessage());
        }
        try {
            this.partie.move(Direction.WEST); // Contre un Mur
        } catch (Exception e) {
            assertEquals("Il y a un mur",
                    e.getMessage());
        }
        try {
            this.partie.move(Direction.NORTH); // Contre une Bordure
        } catch (Exception e) {
            assertEquals("Il y a un mur",
                    e.getMessage());
        }
        try {
            this.partie.move(Direction.SOUTH); // Contre une Pierre
        } catch (Exception e) {
            assertEquals("Un block vous bloques",
                    e.getMessage());
        }
    }

    /**
     * Test la prise d'un diamant par un joueur
     * Test si le score est bien mis à jour.
     */
    public void testPriseDiamant() {
        this.partie = new Partie(levelList.getLevel("test 1"));

        this.partie.move(Direction.EAST);

        assertEquals(1, this.partie.getCurDiamantPlayer());
        assertEquals(this.partie.getDiamondValue(), this.partie.getScore());
    }

    /**
     * Test la prise d'un diamant par un joueur
     * Test si le score est bien mis à jour.
     */
    public void testMoveBlock() {
        this.partie = new Partie(levelList.getLevel("test 2"));

        this.partie.move(Direction.EAST);

        assertEquals(TypeBlock.STONE, this.partie.getBlockType(this.partie.getPosCurPlayer().move(Direction.EAST)));
    }


    /* Les Block qui peuvent bouger tombe */

    /**
     * Test si les block qui peuvent tomber, tombe
     */
    public void testBlockUnderFall() {
        this.partie = new Partie(levelList.getLevel("test 4"));
        this.partie.fallBlock();
        Position pos = new Position(2, 3);


        assertEquals(TypeBlock.STONE, this.partie.getBlockType(pos));
    }

    /**
     * Test si les block qui peuvent tomber, tombe
     */
    public void testBlockRollFall() {
        this.partie = new Partie(levelList.getLevel("test 5"));
        this.partie.fallBlock();
        Position pos = new Position(2, 2);


        assertEquals(TypeBlock.DIAMANT, this.partie.getBlockType(pos));
    }


    /* Test state */

    /**
     * Test si quand on lance la partie, met l'état sur start
     */
    public void testStateStart() {
        this.partie = new Partie(this.levelList.getLevel("test 1"));

        assertEquals(State.START, this.partie.getState());
    }

    /**
     * Test si quand on lance la partie, met l'état sur win
     */
    public void testStateWin() {
        this.partie = new Partie(this.levelList.getLevel("test 1"));

        this.partie.move(Direction.EAST);
        this.partie.move(Direction.EAST);

        assertEquals(State.WIN, this.partie.getState());
    }

    /**
     * Test si la porte s'ouvre après avoir pris le nbr de diamant
     * requis pour l'ouvrir.
     */
    public void testOpenExitGate() {
        this.partie = new Partie(this.levelList.getLevel("test 1"));

        this.partie.move(Direction.EAST);

        assertEquals(1, this.partie.getCurDiamantPlayer());
        assertTrue(this.partie.isOpenGate());
    }

    /**
     * Test si quand on lance la partie, met l'état sur loose
     */
    public void testStateLoose() {
        this.partie = new Partie(this.levelList.getLevel("test 4"));

        this.partie.move(Direction.EAST);

        assertEquals(State.LOOSE, this.partie.getState());
    }
}