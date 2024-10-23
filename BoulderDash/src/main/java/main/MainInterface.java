package main;

import controller.ControllerInterface;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Game;

/**
 * La classe MainInterface est la classe principale qui permet le lancement de la vue interface du jeu Boulder Dash.
 * Elle hérite de la classe Application de JavaFX et surcharge la méthode start().
 */
public class MainInterface extends Application {

    @Override
    public void start(Stage stage) {
        Game game = new Game();
        ControllerInterface controller = new ControllerInterface(game, stage);
    }

    /**
     * Méthode principale qui permet le lancement de l'application JavaFX.
     *
     * @param args tableau de chaînes de caractères en entrée (non utilisé dans ce programme)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
