package view.fx.module.button;

import controller.ControllerInterface;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Bouton qui permet de revenir à la page du lancement du jeu
 */
public class BtExit extends BtBoulder {

    private final ControllerInterface controller;
    private Stage stageToClose;


    /**
     * Constructeur d'un bouton permettant revenir à la page du lancement du jeu
     *
     * @param controller le controller du jeu
     */
    public BtExit(ControllerInterface controller) {
        super("Home", 20, Color.BLACK);
        this.controller = controller;
        action();
    }

    /**
     * Constructeur d'un bouton permettant revenir à la page du lancement du jeu
     *
     * @param controller le controller du jeu
     */
    public BtExit(ControllerInterface controller, Stage stage) {
        super("Exit", 20, Color.BLACK);
        this.controller = controller;
        this.stageToClose = stage;
        action();
    }

    /**
     * Set l'action de revenir à la page d'accueil
     */
    private void action() {
        this.setOnAction((ActionEvent event) -> {
            this.controller.backToStartView();
            if (this.stageToClose != null) {
                this.stageToClose.close();
            }
        });
    }
}