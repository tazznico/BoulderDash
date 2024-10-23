package view.fx.module.secondaire;

import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.fx.module.button.LbBoulder;

/**
 * Module contenant les informations du temps restant de la partie
 */
public class FxTimer extends VBox {

    private final LbBoulder lbTime;
    private final ProgressBar testProgress;
    private final LbBoulder lbNbTime;


    /**
     * Constructeur d'un module ayant un timer
     *
     * @param timer le temps de la partie
     */
    public FxTimer(int timer) {
        this.lbTime = new LbBoulder("Time", 40, Color.FIREBRICK);
        this.lbNbTime = new LbBoulder("" + timer, 24, Color.FIREBRICK);
        this.testProgress = new ProgressBar();
        place();
    }

    /**
     * place les éléments
     */
    private void place() {
        this.getChildren().addAll(this.lbTime, lbNbTime, testProgress);
        this.setAlignment(Pos.TOP_CENTER);
    }

    /**
     * Met à jour le timer visuel
     *
     * @param timer le temps à afficher
     */
    public void setTimer(int timer) {
        this.lbTime.setText("" + timer);
    }
}
