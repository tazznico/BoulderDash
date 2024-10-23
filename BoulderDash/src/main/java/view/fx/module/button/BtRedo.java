package view.fx.module.button;

import controller.ControllerInterface;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Bouton permettant de rejouer un coup qui a été annuler précédemment.
 */
public class BtRedo extends BtBoulder {

    private final ControllerInterface controller;
    private Stage stageToClose;


    /**
     * Constructeur d'un bouton permettant de faire un redo
     *
     * @param controller le controller du jeu
     */
    public BtRedo(ControllerInterface controller) {
        super("Redo", 20, Color.BLACK);
        this.controller = controller;
        action();
    }

    /**
     * Constructeur d'un bouton permettant de faire un redo
     *
     * @param controller le controller du jeu
     */
    public BtRedo(ControllerInterface controller, Stage stage) {
        super("Redo", 20, Color.BLACK);
        this.controller = controller;
        this.stageToClose = stage;
        action();
    }

    /**
     * Set l'action undo sur level en cours, pour pouvoir recommencer la commande d'avant
     */
    private void action() {
        this.setOnAction((ActionEvent event) -> {
            this.controller.redo();
            if (this.stageToClose != null) {
                this.stageToClose.close();
            }
        });
    }
}