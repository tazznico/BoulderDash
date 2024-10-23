package view.fx.module.secondaire;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.fx.module.button.LbBoulder;

/**
 * module permettant l'affichage d'un message signalant si la porte est ouverte ou non
 */
public class FxOpenGate extends VBox {

    private final LbBoulder lbOpenGate;
    private final Separator spOpenGate;

    /**
     * Constructeur du module de l'affichage de la porte ouvert ou non
     */
    public FxOpenGate() {
        this.lbOpenGate = new LbBoulder("Porte ouverte", 16, Color.FIREBRICK);
        this.spOpenGate = new Separator(Orientation.HORIZONTAL);
        place();
        decoration();
    }

    /**
     * place les éléments
     */
    private void place() {
        this.getChildren().addAll(this.lbOpenGate, this.spOpenGate);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Applique les décorations
     */
    private void decoration() {
        this.setVisible(false);
    }

    /**
     * affiche si la porte est ouverte ou non
     *
     * @param open true si la porte est ouverte et false, sinon
     */
    public void setOpenGate(boolean open) {
        this.setVisible(open);
        if (open) {
            this.lbOpenGate.setPadding(new Insets(10));
        } else {
            this.lbOpenGate.setPadding(new Insets(0));
        }
    }
}
