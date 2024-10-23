package command;

import junit.framework.TestCase;
import model.Direction;
import model.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Test les différentes méthodes du command manager (undo, redo, execute et re execute)
 */
public class CommandManagerTest extends TestCase {

    private final CommandManager commandManager = new CommandManager();
    private final Game game = new Game();

    /**
     * Test si après l'exécution d'une commande, elle est bien mise dans la stack undo.
     */
    public void testStackUndo() {
        this.game.setPartie("cave 1");
        Move move1 = new Move(this.game, Direction.NORTH);
        Move move2 = new Move(this.game, Direction.EAST);
        List<Move> listMoves = new ArrayList<>();
        listMoves.add(move1);
        listMoves.add(move2);

        for (Move move : listMoves) {
            this.commandManager.execute(move);
        }

        assertEquals(listMoves.size(), this.commandManager.getSizeUndo());
    }

    /**
     * Test si je vide bien ma stack undo quand j'exécute la commande
     * et que je passe bien mes commandes à ma stack redo.
     */
    public void testStackUndoToRedo() {
        testStackUndo();
        int size = 2;
        for (int i = 0; i < size; i++) {
            this.commandManager.undo();
        }

        assertEquals(0, this.commandManager.getSizeUndo());
        assertEquals(size, this.commandManager.getSizeRedo());
    }

    /**
     * Test si je vide bien ma stack redo quand j'exécute la commande
     * et que je passe bien mes commandes à ma stack undo.
     */
    public void testStackRedoToUndo() {
        testStackUndoToRedo();
        int size = 2;
        for (int i = 0; i < size; i++) {
            this.commandManager.redo();
        }

        assertEquals(0, this.commandManager.getSizeRedo());
        assertEquals(size, this.commandManager.getSizeUndo());

    }

    /**
     * Test qu'après un déplacement la stack redo est bien vidé, même s'il y a des commandes dedans.
     * Et regarde si on rajoute bien la commande dans la commande dans la stack undo
     */
    public void testVoidRedoAfterMove() {
        testStackUndoToRedo();
        Move move1 = new Move(this.game, Direction.NORTH);


        this.commandManager.execute(move1);

        assertEquals(0, this.commandManager.getSizeRedo());
        assertEquals(1, this.commandManager.getSizeUndo());
    }
}