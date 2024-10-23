package view.fx.utilitaire;

import model.Game;
import model.Position;

/**
 * classe permettant de faire les calculs de la vue et de renvoyer
 * la partie à afficher pour le plateau de jeu
 */
public class Display {

    private final Game game;
    private final Position displayValue;
    private Position displayStart;
    private Position displayEnd;

    /**
     * Constructeur du Display
     *
     * @param game         la game en cours
     * @param displayValue le nombre de block à affiché
     */
    public Display(Game game, Position displayValue) {
        this.game = game;
        this.displayValue = displayValue;
        initDisplay();
    }

    /**
     * Modifie la vue à afficher selon le positionnement du joueur
     */
    private void initDisplay() {
        Position player = this.game.getPosCurPlayer();
        int height = this.game.getSizeColMap();
        int width = this.game.getSizeRowMap();
        if (player.x() < width / 2) {
            if (player.y() < height / 2) {
                this.displayStart = new Position(0, 0);
                this.displayEnd = new Position(this.displayValue.y(), this.displayValue.x());
            } else {
                this.displayStart = new Position(height - this.displayValue.y(), 0);
                this.displayEnd = new Position(height, this.displayValue.x());
            }
        } else {
            if (player.y() < height / 2) {
                this.displayStart = new Position(0, width - this.displayValue.x());
                this.displayEnd = new Position(this.displayValue.y(), width);
            } else {
                this.displayStart = new Position(height - this.displayValue.y(), width - this.displayValue.x());
                this.displayEnd = new Position(height, width);
            }
        }
    }

    /**
     * @return Getter da la position du début de prise des éléments
     */
    public Position getStart() {
        return displayStart;
    }

    /**
     * @return Getter da la position jusqu'où prendre les éléments
     */
    public Position getEnd() {
        return displayEnd;
    }
}
