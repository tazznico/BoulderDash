package view.fx.module.button;

import controller.ControllerInterface;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Bouton permettant le retour en arrière dans le jeu
 */
public class BtUndo extends BtBoulder {

    private final ControllerInterface controller;
    private Stage stageToClose;


    /**
     * Constructeur d'un bouton permettant de faire un undo, s'il y a minimum une commande qui peut être undo
     *
     * @param controller le controller du jeu
     */
    public BtUndo(ControllerInterface controller) {
        super("Undo", 20, Color.BLACK);
        this.controller = controller;
        action();
    }

    /**
     * Constructeur d'un bouton permettant de faire un undo, s'il y a minimum une commande qui peut être undo
     *
     * @param controller le controller du jeu
     */
    public BtUndo(ControllerInterface controller, Stage stage) {
        super("Undo", 20, Color.BLACK);
        this.controller = controller;
        this.stageToClose = stage;
        action();
    }

    /**
     * Set l'action undo sur level en cours, pour pouvoir recommencer la commande d'avant
     */
    private void action() {
        this.setOnAction((ActionEvent event) -> {
            this.controller.undo();
            if (this.stageToClose != null) {
                this.stageToClose.close();
            }
        });
    }
}
