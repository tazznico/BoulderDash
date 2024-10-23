package view.fx;

import controller.ControllerInterface;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Direction;
import model.Game;
import oo.Observer;
import view.fx.module.FxInformationMap;
import view.fx.module.FxMap;
import view.fx.module.secondaire.FxPopUp;
import view.fx.module.button.BtExit;
import view.fx.module.button.BtNextLevel;
import view.fx.module.button.BtRestart;
import view.fx.module.button.BtUndo;


/**
 * Affiche et permet le déplacement du joueur dans le level choisi
 */
public class ViewMap extends Application implements Observer {
    private final Game game;
    private final ControllerInterface controller;
    private Scene scene;
    private final GridPane root;
    private final FxMap fxMap;
    private final FxInformationMap fxInformationMap;
    private final Stage stage;

    /**
     * Constructeur de la vue du jeu
     *
     * @param game       le jeu en cours
     * @param controller le controller du jeu en cours
     * @param stage      le stage ou placer la scene du jeu
     */
    public ViewMap(Game game, ControllerInterface controller, Stage stage) {
        this.game = game;
        this.game.addObserver(this);
        this.stage = stage;
        this.controller = controller;
        this.root = new GridPane();
        this.fxMap = new FxMap(this.game);
        this.fxInformationMap = new FxInformationMap(this.game, this.controller);
        decoration();

    }

    /**
     * Applique la décoration
     */
    private void decoration() {
        this.root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }


    @Override
    public void start(Stage stage) {
        stage.getIcons().add(new Image("Icons/icon level.png"));
        this.stage.setTitle("Boulder Dash - " + this.game.getName());

        this.root.add(this.fxMap, 0, 0);
        this.root.add(this.fxInformationMap, 1, 0);
        this.scene = new Scene(this.root);

        actionMap();

        stage.setScene(scene);
        stage.show();
    }


    /**
     * Permet de modifier les informations du joueur
     */
    private void modifyInformation() {
        this.fxInformationMap.setScore(this.game.getScore());
        this.fxInformationMap.setFxDiamant(this.game.getCurDiamantPlayer(),
                this.game.getDiamondRequired(), this.game.getDiamondValue());
        this.fxInformationMap.setVisibleOpenGate(this.game.getCurDiamantPlayer() >= this.game.getDiamondRequired());
    }

    /**
     * Permet de rassembler toutes les informations à changer à chaque notification du modèle (Partie)
     */
    private void modifyAllInformation() {
        this.fxMap.modifyMap();
        modifyInformation();
    }

    /**
     * Permet de savoir l'état du jeu prévois un affichage en conséquence
     */
    private void switchState() {
        modifyAllInformation();
        switch (this.game.getState()) {
            case WIN -> {
                if (this.game.getNum() == this.game.allNameOfLevel().size()) {
                    FxPopUp popWin = new FxPopUp("Vous avez fini Boulder Dash !");
                    popWin.addButton(new BtRestart(this.game, this.controller,
                            popWin.getDialog()), new BtExit(this.controller, popWin.getDialog()));
                    popWin.start(this.stage);
                } else {
                    FxPopUp popWin = new FxPopUp("Vous avez gagne !");
                    popWin.addButton(new BtRestart
                                    (this.game, this.controller, popWin.getDialog()),
                            new BtExit(this.controller, popWin.getDialog()),
                            new BtNextLevel(this.game, this.controller, popWin.getDialog()));
                    popWin.start(this.stage);
                }
            }
            case LOOSE -> {
                FxPopUp popLoose = new FxPopUp("Vous avez perdu !");
                popLoose.addButton(new BtRestart(this.game, this.controller, popLoose.getDialog()),
                        new BtExit(this.controller, popLoose.getDialog()), new BtUndo(this.controller, popLoose.getDialog()));
                popLoose.start(this.stage);
            }
        }
    }

    /**
     * Permet de controller la touche pressée et d'effectuer le mouvement.
     */
    private void actionMap() {
        KeyCombination keyUndo = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
        KeyCombination keyRedo = new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN);
        KeyCombination keyRestart = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);


        this.scene.setOnKeyPressed((KeyEvent event) -> {
            if (keyUndo.match(event)) {
                this.controller.undo();
            } else {
                if (keyRedo.match(event)) {
                    this.controller.redo();
                } else {
                    if (keyRestart.match(event)) {
                        try {
                            this.controller.startLevel(this.game.getNameInGame());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        switch (event.getCode()) {
                            case Z, UP -> moveTest(Direction.NORTH);
                            case S, DOWN -> moveTest(Direction.SOUTH);
                            case Q, LEFT -> moveTest(Direction.WEST);
                            case D, RIGHT -> moveTest(Direction.EAST);
                            case R -> {
                                try {
                                    this.controller.startLevel(this.game.getNameInGame());
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Permet d'effectuer un mouvement s'il est valide
     *
     * @param dir la direction où se déplacer
     */
    private void moveTest(Direction dir) {
        try {
            this.controller.move(dir);
        } catch (Exception e) {
            this.fxInformationMap.setDisplayError(e.getMessage());
        }
    }

    @Override
    public void update() {
        switchState();
    }
}
