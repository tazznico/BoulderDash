package view.fx.module.secondaire;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Separator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Game;
import view.fx.module.button.LbBoulder;

/**
 * Module composé des différents affichages des diamants
 */
public class FxDiamant extends VBox {
    private final HBox hbDiamants;
    private final LbBoulder diamantTotal;
    private final Separator spDiamant;
    private final LbBoulder diamantValue;


    /**
     * Constructeur d'un module gérant l'affichage des diamants
     *
     * @param game la partie en cours
     */
    public FxDiamant(Game game) {
        this.hbDiamants = new HBox();
        this.diamantTotal = new LbBoulder(game.getCurDiamantPlayer() + " / " + game.getDiamondRequired(), 24, Color.FIREBRICK);
        this.spDiamant = new Separator(Orientation.VERTICAL);
        this.diamantValue = new LbBoulder("" + game.getDiamondValue(), 24, Color.FIREBRICK);

        place();
        decoration();
    }

    /**
     * place les éléments
     */
    private void place() {
        this.getChildren().addAll(this.hbDiamants);

        this.hbDiamants.getChildren().addAll(this.diamantTotal, this.spDiamant, this.diamantValue);

        this.setAlignment(Pos.TOP_CENTER);
        this.hbDiamants.setAlignment(Pos.TOP_CENTER);

    }

    /**
     * Applique la décoration
     */
    private void decoration() {
        this.hbDiamants.setSpacing(10);
        this.spDiamant.setBackground(new Background(new BackgroundFill(Color.FIREBRICK, null, Insets.EMPTY)));
        this.spDiamant.setValignment(VPos.TOP);
    }

    /**
     * Change les informations concernant les diamants
     *
     * @param curDiamant      le nbr de diamant que le joueur possède
     * @param DiamantRequired le nbr de diamant requis pour ouvrir la porte
     * @param diamantValue    la valeur d'un diamant
     */
    public void setCurDiamant(int curDiamant, int DiamantRequired, int diamantValue) {
        this.diamantTotal.setText(curDiamant + " / " + DiamantRequired);
        this.diamantValue.setText("" + diamantValue);
    }
}
