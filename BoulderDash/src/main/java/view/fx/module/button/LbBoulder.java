package view.fx.module.button;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Un label possédant la Font BoulderDash et
 * prenant en paramètre, une taille et une couleur.
 */
public class LbBoulder extends Label {

    /**
     * Constructeur d'un label personnalisé avec la Font BoulderDash
     *
     * @param s     le texte a affiché
     * @param size  la taille du texte
     * @param color la couleur du texte
     */
    public LbBoulder(String s, int size, Color color) {
        super(s);
        this.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/BoulderDash.ttf"), size));
        this.setTextFill(color);
    }


}
