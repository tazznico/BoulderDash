package view.fx.module.button;

import controller.ControllerInterface;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Game;

/**
 * Bouton permettant de passer au niveau suivant
 */
public class BtNextLevel extends BtBoulder {

    private final Game game;
    private final ControllerInterface controller;
    private Stage stageToClose;

    /**
     * Constructeur d'un bouton permettant de restart le jeu en cours
     *
     * @param game       le jeu en cours
     * @param controller le controller du jeu
     */
    public BtNextLevel(Game game, ControllerInterface controller) {
        super("Next Level", 20, Color.BLACK);
        this.game = game;
        this.controller = controller;
        action();
    }

    /**
     * Constructeur d'un bouton permettant de restart le jeu en cours
     *
     * @param game       le jeu en cours
     * @param controller le controller du jeu
     */
    public BtNextLevel(Game game, ControllerInterface controller, Stage stage) {
        super("Next Level", 20, Color.BLACK);
        this.game = game;
        this.controller = controller;
        this.stageToClose = stage;
        action();
    }

    /**
     * Set l'action du restart du level en cours, pour pouvoir recommencer de zéro
     */
    private void action() {
        this.setOnAction((ActionEvent event) -> {
            int num = this.game.getNum();
            this.controller.startLevel(this.game.getType() + " " + (++num));
            if (this.stageToClose != null) {
                this.stageToClose.close();
            }
        });
    }
}
