package view.fx.module.secondaire;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.fx.module.button.BtLabel;

/**
 * Affiche la popUp permettant de voir les différentes commandes du jeu.
 */
public class FxCommand extends Application {

    private final Scene scene;
    private final VBox root;
    private final HBox hbButton;
    private final BtLabel btLabel;
    private final Label title;
    private final HBox hbCommand;
    private final GridPane gpCommandMove;
    private final VBox vbShortKey;


    /**
     * Constructeur de la popUp affichant les commandes.
     */
    public FxCommand() {
        this.root = new VBox();
        this.scene = new Scene(this.root);
        this.title = new Label("Commandes");
        this.hbButton = new HBox();
        this.btLabel = new BtLabel("X");
        this.hbCommand = new HBox();
        this.gpCommandMove = new GridPane();
        this.vbShortKey = new VBox();

        place();
        decoration();
    }

    /**
     * Place les éléments
     */
    private void place() {
        this.root.getChildren().addAll(this.hbButton, this.hbCommand);

        this.hbButton.getChildren().add(this.btLabel);

        this.hbCommand.getChildren().addAll(this.gpCommandMove, this.vbShortKey);

        this.gpCommandMove.add(new FxKey("z"), 1, 0);
        this.gpCommandMove.addRow(1, new FxKey("q"), new FxKey("s"), new FxKey("d"));

        this.vbShortKey.getChildren().addAll(new FxShortKey("CTRL-Z", "undo"),
                new FxShortKey("CTRL-Y", "redo"),
                new FxShortKey("CTRL-R", "restart"));

        this.root.setAlignment(Pos.CENTER);
        this.hbButton.setAlignment(Pos.TOP_RIGHT);
        this.root.setPadding(new Insets(10));
        this.root.setSpacing(10);
        this.gpCommandMove.setHgap(5);
        this.gpCommandMove.setVgap(5);
        this.hbCommand.setAlignment(Pos.CENTER);
        this.hbCommand.setSpacing(20);
        this.gpCommandMove.setAlignment(Pos.CENTER);
        this.vbShortKey.setSpacing(3);
    }

    /**
     * Applique la décoration
     */
    private void decoration() {
        this.root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(this.title.getText());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        this.btLabel.action(stage);

        stage.setScene(scene);
        stage.show();
    }

}
