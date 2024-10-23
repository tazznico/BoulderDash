package view.fx.module.secondaire;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.fx.module.button.LbBoulder;

/**
 * permet d'afficher une popUp personnalisée
 */
public class FxPopUp extends Application {

    private final VBox vbPopUp;
    private final LbBoulder titlePopUp;
    private final HBox hbButton;
    private final Stage dialog;


    /**
     * Constructeur d'un PopUp
     *
     * @param title le titre du popup
     */
    public FxPopUp(String title) {
        this.dialog = new Stage();
        this.vbPopUp = new VBox();
        this.titlePopUp = new LbBoulder(title, 32, Color.FIREBRICK);
        this.hbButton = new HBox();
        place();
        decoration();
    }

    /**
     * Place les éléments
     */
    private void place() {
        this.vbPopUp.getChildren().addAll(this.titlePopUp, this.hbButton);

        this.vbPopUp.setPadding(new Insets(10, 10, 10, 10));
        this.titlePopUp.setPadding(new Insets(10, 10, 10, 10));
        this.vbPopUp.setSpacing(10);
        this.hbButton.setSpacing(10);

        this.vbPopUp.setAlignment(Pos.CENTER);
        this.hbButton.setAlignment(Pos.CENTER);
    }

    /**
     * Applique la décoration
     */
    private void decoration() {
        this.vbPopUp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        this.titlePopUp.setBorder(new Border(new BorderStroke(Color.FIREBRICK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 3, 0))));
    }

    /**
     * Place tous les boutons dans le Hbox des Buttons
     *
     * @param buttons les différents boutons à placer
     */
    public void addButton(Button... buttons) {
        this.hbButton.getChildren().addAll(buttons);
    }

    @Override
    public void start(Stage stage) {
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.initOwner(stage);


        Scene scene = new Scene(this.vbPopUp);
        scene.setFill(Color.TRANSPARENT);
        scene.setFill(Color.color(0, 0, 0, 0));
        dialog.setScene(scene);
        dialog.show();
    }

    /**
     * @return Getter de boîte de dialogue, utilisé pour le fermer.
     */
    public Stage getDialog() {
        return dialog;
    }
}
