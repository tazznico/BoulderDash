package view.fx.module.secondaire;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import view.fx.module.button.LbBoulder;

/**
 * Module secondaire d'affichage d'erreur
 */
public class FxError extends VBox {
    private final LbBoulder lbError;
    private final Timeline timeline;

    /**
     * Constructeur d'un module d'affichage d'erreur
     */
    public FxError() {
        this.lbError = new LbBoulder("", 16, Color.FIREBRICK);
        this.timeline = new Timeline();
        place();
    }

    /**
     * place les éléments
     */
    private void place() {
        this.getChildren().add(this.lbError);
        this.setAlignment(Pos.BASELINE_CENTER);
        this.lbError.setPadding(new Insets(5));
    }

    /**
     * Permet d'afficher une erreur de déplacement pendant 1 seconde
     *
     * @param msg le message à afficher
     */
    public void setDisplayError(String msg) {
        this.timeline.stop();
        this.lbError.setVisible(true);
        this.lbError.setText("! " + msg + " !");
        this.timeline.setCycleCount(1);
        this.timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000), t -> this.lbError.setVisible(false))
        );
        this.timeline.play();
    }
}
