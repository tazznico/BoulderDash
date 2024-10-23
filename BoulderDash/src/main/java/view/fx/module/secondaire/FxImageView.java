package view.fx.module.secondaire;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import view.fx.utilitaire.Animation;

/**
 * Permet l'affichage du block et de l'animation si le block l'autorise.
 */
public class FxImageView extends ImageView {

    private final double speedAnime;
    private Rectangle2D rectangle2D;
    private int posX;
    private int posY;
    private int fin;
    private Timeline timeline;
    private Animation animation;

    /**
     * Constructeur de l'affichage/animation du block correspondant
     *
     * @param image     l'image contenant tout les sprites en fonctions du niveau joué
     * @param animation l'animation du block
     */
    public FxImageView(Image image, Animation animation) {
        super();
        this.timeline = new Timeline();
        this.animation = animation;
        this.speedAnime = 1;
        this.posX = this.animation.posStart().x();
        this.posY = this.animation.posStart().y();
        this.fin = this.animation.posEnd();
        rectangle2D = new Rectangle2D(this.posX * 32, this.posY * 32, 32, 32);
        this.setImage(image);
        this.setViewport(this.rectangle2D);
        if (this.animation.play()) {
            animation();
        }
    }

    /**
     * Lance l'animation du block en changent juste la vue de l'image grâce au paramètre de la classe animation
     * définit un temps de passage à la vue suivant de 100 millis seconde, modifiable grâce à la variable speedAnime.
     */
    private void animation() {
        this.timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        this.timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(100 / speedAnime), t -> {
                    setViewport(new Rectangle2D(posX * 32, posY * 32, 32, 32));
                    if (posX == fin) {
                        posX = 0;
                    }
                    posX++;
                })
        );
        this.timeline.play();
    }

    /**
     * Permet change la vue du block par un autre et stop l'ancienne animation avant de possiblement
     * créer uen nouvelle animation, si le block le permet.
     *
     * @param animation l'animation du block
     */
    public void setAnimation(Animation animation) {
        this.timeline.stop();
        this.animation = animation;
        this.posX = animation.posStart().x();
        this.posY = animation.posStart().y();
        this.fin = animation.posEnd();
        rectangle2D = new Rectangle2D(this.posX * 32, this.posY * 32, 32, 32);
        this.setViewport(this.rectangle2D);
        if (animation.play()) {
            timeline = new Timeline();
            animation();
        }
    }

    public Animation getPosition() {
        return this.animation;
    }
}
