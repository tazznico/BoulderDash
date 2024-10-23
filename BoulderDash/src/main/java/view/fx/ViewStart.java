package view.fx;

import controller.ControllerInterface;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Game;
import view.fx.module.secondaire.FxSelectLevel;
import view.fx.module.button.BtBoulder;
import view.fx.module.button.LbBoulder;


/**
 * La vue interface principale qui affiche la
 * sélection du niveau et le bouton play
 */
public class ViewStart extends Application {

    private final ControllerInterface controller;

    private final Scene scene;
    private final BorderPane root;
    private final HBox hbTitle;
    private final LbBoulder titleStage;
    private final VBox vbPlay;
    private final BtBoulder btPlay;
    private final FxSelectLevel fxSelectlevel;


    /**
     * Constructeur de la vue du start
     *
     * @param game       le jeu
     * @param controller le controller du jeu
     */
    public ViewStart(Game game, ControllerInterface controller) {
        this.controller = controller;
        this.root = new BorderPane();
        this.hbTitle = new HBox();
        this.titleStage = new LbBoulder("Boulder Dash", 48, Color.FIREBRICK);
        this.vbPlay = new VBox();
        this.btPlay = new BtBoulder("PLAY", 24, Color.BLACK);
        this.fxSelectlevel = new FxSelectLevel(game.allNameOfLevel());
        this.scene = new Scene(this.root);
        place();
        decoration();
    }

    /**
     * Place les éléments
     */
    private void place() {
        this.root.setTop(this.hbTitle);
        this.root.setCenter(this.vbPlay);
        this.hbTitle.getChildren().add(this.titleStage);
        this.vbPlay.getChildren().addAll(this.btPlay, this.fxSelectlevel);

        this.hbTitle.setAlignment(Pos.CENTER);
        this.titleStage.setAlignment(Pos.CENTER);
        this.vbPlay.setAlignment(Pos.CENTER);

        this.hbTitle.setPadding(new Insets(25));
        this.vbPlay.setSpacing(10);
    }

    /**
     * Applique la décoration
     */
    private void decoration() {
        this.root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @Override
    public void start(Stage stage) {
        stage.getIcons().add(new Image("Icons/icon home.png"));
        stage.setTitle(this.titleStage.getText());

        actionPlay();

        stage.setScene(this.scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * lors du click du bouton play, lance une nouvelle partie.
     */
    private void actionPlay() {
        this.btPlay.setOnAction((ActionEvent event) -> this.controller.startLevel(this.fxSelectlevel.getLbLevelText()));
    }
}
