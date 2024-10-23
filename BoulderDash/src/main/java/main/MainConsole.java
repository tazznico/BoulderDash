package main;

import controller.ControllerConsole;
import model.Game;

/**
 * La classe MainConsole est la classe principale qui permet le lancement de la vue console du jeu Boulder Dash.
 * Elle crée une instance de la classe Game et de la classe ControllerConsole,
 * et lance la méthode start() du controllerConsole.
 */
public class MainConsole {

    /**
     * Méthode principale qui permet le lancement de la vue console du jeu Boulder Dash.
     *
     * @param args tableau de chaînes de caractères en entrée (non utilisé dans ce programme)
     */
    public static void main(String[] args) {
        Game game = new Game();
        ControllerConsole controllerConsole = new ControllerConsole(game);
        controllerConsole.start();
    }
}
