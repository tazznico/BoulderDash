package controller;

import model.Direction;
import model.Game;
import view.console.ViewConsole;

/**
 * Controller de la vue console
 */
public class ControllerConsole {

    private final Game game;
    private final ViewConsole view;

    public ControllerConsole(Game game) {
        this.game = game;
        this.view = new ViewConsole();
    }

    /**
     * La méthode qui lance le jeu.
     */
    public void start() {
        boolean start = true;
        this.view.displayStart();
        while (start) {
            switch (askStart()) {
                case "niveau" -> {
                    askLevel();
                    play();
                }
                case "exit" -> {
                    start = false;
                    this.view.displayEnd();
                }
            }
        }
    }

    /**
     * Méthode qui lance la partie
     */
    public void play() {
        boolean retryGame = true;
        while (retryGame) {
            switch (this.game.getState()) {
                case START -> {
                    displayLevel();
                    if (!askMove()) {
                        retryGame = false;
                    }
                }
                case WIN -> {
                    this.view.displayWin();
                    retryGame = false;
                }
                case LOOSE -> {
                    this.view.displayLoose();
                    retryGame = false;
                }
            }
        }
    }

    /**
     * Demande d'un mouvement valide robuste
     */
    private boolean askMove() {
        String strMove = this.view.askMove();
        while (strMove.isEmpty()) {
            strMove = this.view.askMove();
        }
        try {
            Direction dir = null;
            switch (strMove) {
                case "z":
                    dir = Direction.NORTH;
                    break;
                case "s":
                    dir = Direction.SOUTH;
                    break;
                case "d":
                    dir = Direction.EAST;
                    break;
                case "q":
                    dir = Direction.WEST;
                    break;
                case "undo":
                    this.game.undoMove();
                    return true;
                case "redo":
                    this.game.redoMove();
                    return true;
                case "exit":
                    return false;
                default:
                    this.view.displayError("Commande de mouvement non valide");
                    return true;
            }
            this.game.executeMove(dir);
            return true;
        } catch (Exception e) {
            this.view.displayError(e.getMessage());
            return true;
        }
    }

    /**
     * Méthode demandant de choisir un level et l'initialise dans la partie.
     */
    private void askLevel() {
        this.view.displayLevelNameOfList(this.game.allNameOfLevel());
        String nameLevel = this.view.askSelectLevel(this.game.allNameOfLevel());
        while (nameLevel.isEmpty()) {
            nameLevel = this.view.askSelectLevel(this.game.allNameOfLevel());
        }
        this.game.setPartie(nameLevel);
    }

    /**
     * Demande robust au joueur pour savoir s'il veut lancer une partie
     *
     * @return le string robust d'un des possibilités
     */
    private String askStart() {
        String select = this.view.askStart();
        while (select.isEmpty()) {
            select = this.view.askStart();
        }
        return select.toLowerCase();
    }

    /**
     * affiche le bon affichage selon si c'est un bonus de diamant normal ou bonus
     */
    private void displayLevel() {
        this.view.displayPartie(this.game.getName(), this.game.getCurDiamantPlayer(), this.game.getDiamondRequired(),
                this.game.getScore(), this.game.isOpenGate(), this.game.viewMap());
    }
}
