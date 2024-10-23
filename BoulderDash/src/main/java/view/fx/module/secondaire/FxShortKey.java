package view.fx.module.secondaire;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.fx.module.secondaire.FxKey;

/**
 * Classe représentant une touche de raccourci graphique et son action dans le jeu.
 */
public class FxShortKey extends HBox {
    private final HBox hbKey;
    private final FxKey fxKey;
    private final Label key;

    /**
     * Constructeur d'une touche de raccourci graphique composé
     * du nom de son action dans le jeu.
     *
     * @param shortKey le nom de la touche de raccourci
     * @param key      le nom de son action dans le jeu
     */
    public FxShortKey(String shortKey, String key) {
        super();
        this.hbKey = new HBox();
        this.fxKey = new FxKey(shortKey);
        this.key = new Label(key);

        place();
        decoration();
    }

    /**
     * Place les éléments de la touche de raccourci
     */
    private void place() {
        this.getChildren().addAll(this.hbKey, this.key);
        this.hbKey.getChildren().add(this.fxKey);

        this.setSpacing(20);
        this.hbKey.setAlignment(Pos.CENTER_LEFT);
        this.key.setAlignment(Pos.CENTER_RIGHT);
    }

    /**
     * Applique la décoration à la touche de raccourci
     */
    private void decoration() {
        this.key.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BoulderDash.ttf"), 32));
        this.key.setTextFill(Color.WHITE);
    }
}
