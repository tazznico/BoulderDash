package view.fx.module.secondaire;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.Game;
import view.fx.module.button.LbBoulder;

/**
 * Module permettant d'afficher les vies du joueur
 */
public class FxLife extends HBox {

    private final ImageView imageLifeLeft;
    private final ImageView imageLifeRight;
    private final LbBoulder life;

    /**
     * Constructeur de l'affichage des vies du joueur
     *
     * @param game le jeu en cours
     */
    public FxLife(Game game) {
        Image imageSprite = new Image("Sprites/" + game.getNameInGame() + " sprite.png");
        this.imageLifeLeft = new ImageView(imageSprite);
        this.life = new LbBoulder("0", 32, Color.FIREBRICK);
        this.imageLifeRight = new ImageView(imageSprite);
        place();
        decoration();
    }

    /**
     * place les éléments
     */
    private void place() {
        this.getChildren().addAll(this.imageLifeLeft, this.life, this.imageLifeRight);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Applique les décorations
     */
    private void decoration() {
        this.setSpacing(10);
        this.imageLifeLeft.setViewport(new Rectangle2D(0, 5 * 32, 32, 32));
        this.imageLifeRight.setViewport(new Rectangle2D(0, 4 * 32, 32, 32));
    }

    /**
     * change le nombre de vies du joueur
     *
     * @param life le nbr de vies du joueur
     */
    public void setScore(int life) {
        this.life.setText("" + life);
    }
}
