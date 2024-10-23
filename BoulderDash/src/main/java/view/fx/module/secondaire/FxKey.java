package view.fx.module.secondaire;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import view.fx.module.button.LbBoulder;

/**
 * Classe représentant une touche graphique.
 */
public class FxKey extends LbBoulder {

    /**
     * Constructeur d'une touche graphique.
     *
     * @param text le texte de la touche
     */
    public FxKey(String text) {
        super(text, 32, Color.FIREBRICK);
        place();
        decoration();
    }

    /**
     * Place les éléments
     */
    private void place() {
        this.setPadding(new Insets(0, 0, 0, 5));
    }

    /**
     * Applique la décoration
     */
    private void decoration() {
        this.setBorder(new Border(
                new BorderStroke(Color.FIREBRICK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
    }


}
