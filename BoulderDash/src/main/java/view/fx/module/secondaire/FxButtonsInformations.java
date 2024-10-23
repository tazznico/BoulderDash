package view.fx.module.secondaire;

import controller.ControllerInterface;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Game;
import view.fx.module.button.*;

/**
 * Module comprenant tous les boutons se retrouvant dans l'affichage du module principal
 * FxInformations
 */
public class FxButtonsInformations extends VBox {
    private final HBox hbUndoRedo;
    private final BtUndo btUndo;
    private final BtRedo btRedo;
    private final BtExit btExit;
    private final BtRestart btRestart;
    private final BtCommand btCommand;

    /**
     * Constructeur d'un module comprenant les boutons pour le fx informations
     *
     * @param game       le jeu en cours
     * @param controller le controller du jeu en cours
     */
    public FxButtonsInformations(Game game, ControllerInterface controller) {
        this.hbUndoRedo = new HBox();
        this.btUndo = new BtUndo(controller);
        this.btRedo = new BtRedo(controller);
        this.btExit = new BtExit(controller);
        this.btRestart = new BtRestart(game, controller);
        this.btCommand = new BtCommand();
        place();
        decoration();
    }

    /**
     * place les éléments
     */
    private void place() {
        this.getChildren().addAll(this.hbUndoRedo, this.btRestart, this.btCommand, this.btExit);
        this.hbUndoRedo.getChildren().addAll(this.btUndo, this.btRedo);
        this.setAlignment(Pos.BOTTOM_CENTER);
        this.hbUndoRedo.setAlignment(Pos.CENTER);
    }

    /**
     * Applique les décorations
     */
    private void decoration() {
        this.setSpacing(10);
        this.hbUndoRedo.setSpacing(5);
    }
}
