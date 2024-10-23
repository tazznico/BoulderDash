package view.fx.module;

import controller.ControllerInterface;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Game;
import view.fx.module.secondaire.*;

/**
 * Permet l'affichage de toutes les informations concernant le joueur et la map
 */
public class FxInformationMap extends VBox {

    private final FxTimer fxTimer;
    private final FxDiamant fxDiamant;
    private final FxScore fxScore;
    private final FxLife fxLife;
    private final Separator spHInformations;
    private final FxOpenGate fxOpenGate;
    private final FxButtonsInformations fxButtons;
    private final FxError fxError;


    /**
     * Constructeur de l'affichage des informations du joueur.
     *
     * @param game       le jeu en cours
     * @param controller le controller du jeu
     */
    public FxInformationMap(Game game, ControllerInterface controller) {
        super();
        this.fxTimer = new FxTimer(game.getTimeMap());
        this.fxDiamant = new FxDiamant(game);
        this.fxScore = new FxScore(game);
        this.fxLife = new FxLife(game);
        this.spHInformations = new Separator(Orientation.HORIZONTAL);
        this.fxOpenGate = new FxOpenGate();
        this.fxButtons = new FxButtonsInformations(game, controller);
        this.fxError = new FxError();
        place();
        setDecoration();
    }

    /**
     * Place les éléments
     */
    private void place() {
        this.getChildren().addAll(this.fxTimer, this.fxDiamant, this.fxScore, this.fxLife,
                this.spHInformations, this.fxOpenGate, this.fxButtons, this.fxError);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(10));
    }

    /**
     * Applique la décoration
     */
    private void setDecoration() {
        this.setBorder(new Border(new BorderStroke(Color.DARKGREY, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setSpacing(10);
    }

    /**
     * Change le score du joueur
     *
     * @param score le nouveau score
     */
    public void setScore(int score) {
        this.fxScore.setScore(score);
    }

    /**
     * Permet d'afficher le message de la porte ouverte
     *
     * @param open si le joueur à assez de diamant pour sortir
     */
    public void setVisibleOpenGate(boolean open) {
        this.fxOpenGate.setOpenGate(open);
    }

    /**
     * Change le nombre de vies restantes
     *
     * @param life le nbr de vie restante
     */
    public void setLife(int life) {
        this.fxLife.setScore(life);
    }

    /**
     * Change les informations concernant les diamants
     *
     * @param curDiamant      le nbr de diamant que le joueur possède
     * @param DiamantRequired le nbr de diamant requis pour ouvrir la porte
     * @param diamantValue    la valeur d'un diamant
     */
    public void setFxDiamant(int curDiamant, int DiamantRequired, int diamantValue) {
        this.fxDiamant.setCurDiamant(curDiamant, DiamantRequired, diamantValue);
    }

    /**
     * Permet d'afficher une erreur de déplacement pendant 1 seconde
     *
     * @param msg le message à afficher
     */
    public void setDisplayError(String msg) {
        this.fxError.setDisplayError(msg);
    }

    /**
     * Met à jour le timer visuel
     *
     * @param timer le temps à afficher
     */
    public void setTimer(int timer) {
        this.fxTimer.setTimer(timer);
    }
}
