package view.fx.module.secondaire;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.Game;
import model.level.map.block.TypeBlock;
import view.fx.module.button.LbBoulder;

/**
 * Module permettant d'afficher le score du joueur
 */
public class FxScore extends HBox {

    private final ImageView imageDiamantLeft;
    private final ImageView imageDiamantRight;
    private final LbBoulder score;

    /**
     * Constructeur de l'affichage du score du joueur
     *
     * @param game le jeu en cours
     */
    public FxScore(Game game) {
        Image imageSprite = new Image("Sprites/" + game.getNameInGame() + " sprite.png");
        this.imageDiamantLeft = new FxImageView(imageSprite, TypeBlock.DIAMANT.getAnimation());
        this.score = new LbBoulder("" + game.getScore(), 32, Color.FIREBRICK);
        this.imageDiamantRight = new FxImageView(imageSprite, TypeBlock.DIAMANT.getAnimation());
        place();
        decoration();
    }

    /**
     * place les éléments
     */
    private void place() {
        this.getChildren().addAll(this.imageDiamantLeft, this.score, this.imageDiamantRight);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Applique les décorations
     */
    private void decoration() {
        this.setSpacing(10);
    }

    /**
     * change le score du joueur
     *
     * @param score le nouveau score du joueur
     */
    public void setScore(int score) {
        this.score.setText("" + score);
    }
}
