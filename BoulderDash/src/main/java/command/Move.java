package command;

import model.Direction;
import model.Game;
import model.Partie;

/**
 * class définissant un déplacement
 */
public class Move implements Command {

    protected final Game game;
    private final Partie savePartie;
    private final Direction dir;

    /**
     * Constructeur d'une commande de mouvement
     *
     * @param game Le jeu en cours
     */
    public Move(Game game, Direction dir) {
        this.game = game;
        this.savePartie = game.getPartie();
        this.dir = dir;
    }

    @Override
    public void execute() {
        this.game.move(this.dir);
    }

    @Override
    public void undo() {
        this.game.setPartie(this.savePartie);
    }
}
