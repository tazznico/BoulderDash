package view.fx.module.button;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Un Bouton possédant la Font BoulderDash et
 * prenant en paramètre, une taille et une couleur.
 */
public class BtBoulder extends Button {

    /**
     * Constructeur d'un Bouton personnalisé avec la Font BoulderDash
     *
     * @param s     le texte a affiché
     * @param size  la taille du texte
     * @param color la couleur du texte
     */
    public BtBoulder(String s, int size, Color color) {
        super(s);
        this.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BoulderDash.ttf"), size));
        this.setTextFill(color);
    }
}
