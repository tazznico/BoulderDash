package controller;

import javafx.stage.Stage;
import model.Direction;
import model.Game;
import view.fx.ViewMap;
import view.fx.ViewStart;

/**
 * controller de la vue interface, elle met en relation les différentes vues a affiché avec le game
 */
public class ControllerInterface {
    private final Game game;
    private final Stage stage;

    /**
     * Constructeur du controller de la vue interface du jeu
     *
     * @param game  le jeu en cours
     * @param stage fenêtre principale de mon application
     */
    public ControllerInterface(Game game, Stage stage) {
        this.game = game;
        this.stage = stage;
        ViewStart viewStart = new ViewStart(this.game, this);
        viewStart.start(this.stage);
    }

    /**
     * Permet de placer un nouveau level dans le game.
     * @param level le level à sélectionner
     */
    public void startLevel(String level) {
        this.game.setPartie(level);
        ViewMap viewMap = new ViewMap(this.game, this, this.stage);
        this.game.addObserver(viewMap);
        try {
            this.stage.close();
            viewMap.start(this.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Permet de déplacer le joueur si le joueur peut l'effectuer.
     *
     * @param dir la direction où se déplacer
     */
    public void move(Direction dir) {
        this.game.executeMove(dir);
    }

    /**
     * Permet d'effectuer un undo sur le level en cours
     */
    public void undo() {
        try {
            this.game.undoMove();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Permet d'effectuer un redo sur le level en cours
     */
    public void redo() {
        try {
            this.game.redoMove();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Permet de revenir à la page du lancement du jeu
     */
    public void backToStartView() {
        this.stage.close();
        ViewStart viewStart = new ViewStart(this.game, this);
        viewStart.start(this.stage);
    }
}
