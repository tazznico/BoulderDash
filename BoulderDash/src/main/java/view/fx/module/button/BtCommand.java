package view.fx.module.button;

import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.fx.module.secondaire.FxCommand;

/**
 * Bouton permettant de créer un PopUp qui affiche les différentes commandes disponibles dans le jeu.
 */
public class BtCommand extends BtBoulder {

    private final FxCommand fxCommand;

    /**
     * Constructeur d'un bouton permettant revenir à la page du lancement du jeu
     */
    public BtCommand() {
        super("Commandes", 20, Color.BLACK);
        this.fxCommand = new FxCommand();
        action();
    }

    /**
     * Set l'action de revenir à la page d'accueil
     */
    private void action() {
        this.setOnAction((ActionEvent event) -> {
            try {
                this.fxCommand.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
