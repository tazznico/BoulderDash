package view.fx.module.button;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * classe permettant de faire un label cliquable.
 */
public class BtLabel extends LbBoulder {

    /**
     * Constructeur d'un label ayant le comportement d'un bouton
     *
     * @param text le texte à afficher
     */
    public BtLabel(String text) {
        super(text, 16, Color.FIREBRICK);
        decoration();
    }

    /**
     * Applique la décoration
     */
    private void decoration() {
        this.setBorder(new Border(
                new BorderStroke(Color.FIREBRICK, BorderStrokeStyle.SOLID, new CornerRadii(100), new BorderWidths(2))));
    }

    /**
     * Set l'action qui permet de fermer la page en cours
     */
    public void action(Stage stage) {
        this.setOnMouseClicked((evt) -> stage.close());
    }


}
