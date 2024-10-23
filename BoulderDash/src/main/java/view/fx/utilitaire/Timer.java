package view.fx.utilitaire;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import oo.Observable;
import oo.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de jouer un compte à rebours
 */
public class Timer implements Observable {

    private int timer;
    private final Timeline timeline;
    private final List<Observer> observerList;


    /**
     * Constructeur d'un timer
     *
     * @param timer le temps à jouer
     */
    public Timer(int timer) {
        this.timer = timer;
        this.timeline = new Timeline();
        this.observerList = new ArrayList<>();
        animation();
        timerPlay();
    }

    /* Animation */

    /**
     * L'animation à jouer
     */
    private void animation() {
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            --this.timer;
            if (this.timer == 0) timerPause();
        }));
    }

    /* Action */

    /**
     * lancer/relancer le timer
     */
    public void timerPlay() {
        this.timeline.play();
    }

    /**
     * Arrête le timer
     */
    public void timerPause() {
        this.timeline.stop();
    }

    @Override
    public void addObserver(Observer observer) {
        if (!this.observerList.contains(observer)) {
            this.observerList.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        this.observerList.forEach(
                Observer::update
        );
    }
}
