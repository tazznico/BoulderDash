package view.console;

import java.util.List;
import java.util.Scanner;

/**
 * vue de la console
 */
public class ViewConsole {


    /* Demande de partie */

    /**
     * Demande à l'utilisateur de rentrer une valeur valide
     *
     * @return l'entier
     */
    public String askStart() {
        displayAsk("Rentrez une commande");
        displayListSetting();
        String ask = askLine();
        return switch (ask) {
            case "niveau", "exit" -> ask;
            default -> "";
        };
    }

    /**
     * Permet la sélection d'un level, si la personne se trompe, lui redemande
     *
     * @param levelList Liste des nomes des levels
     * @return le nom du level choisi.
     */
    public String askSelectLevel(List<String> levelList) {
        String askLevel = askLine();
        for (String nameMap : levelList) {
            if (askLevel.equals(nameMap.toLowerCase())) {
                return nameMap;
            }
        }
        displayLevelNameOfList(levelList);
        return "";
    }

    /**
     * Demande à l'utilisateur de rentrer une commande valide
     *
     * @return la commande valide
     */
    public String askMove() {
        String command = askLine();
        return switch (command) {
            case "z", "s", "q", "d", "undo", "redo", "exit" -> command;
            default -> "";
        };
    }


    /* <<< Méthodes de display >>> */

    /**
     * Affiche la liste des commandes pour les settings
     */
    public void displayListSetting() {
        System.out.println("""
                Liste de Commandes:
                     - niveau
                     - exit
                """);
    }

    /**
     * Affiche le nom des différentes cartes disponibles
     */
    public void displayLevelNameOfList(List<String> levelList) {
        StringBuilder displayListNameMap = new StringBuilder("Entrez un des noms de niveaux:\n");
        for (String nameLevel : levelList) {
            displayListNameMap.append("     - ").append(nameLevel).append("\n");
        }
        System.out.println(displayListNameMap);
    }

    /**
     * Affiche la totalité d'une partie
     *
     * @param name             le nom de la carte
     * @param diamantRequired  le nbr de diamants requit
     * @param diamantCurPlayer le nombre de diamants que possède le joueur
     * @param score            score du joueur
     * @param openGate         si la porte est ouverte
     * @param map              la map au complet
     */
    public void displayPartie(String name, int diamantCurPlayer, int diamantRequired, int score, boolean openGate, String map) {
        displayNameOfMap(name);
        displayDiamant(diamantCurPlayer, diamantRequired, score);
        if (openGate) displayOpenGate();
        displayMap(map);
        displayListTouches();
    }

    /**
     * Affiche toutes les commandes possibles
     */
    public void displayListTouches() {
        System.out.println("Touches: Z | Q | S | D | undo | redo | exit\n");
    }

    /**
     * Affiche la carte
     */
    public void displayMap(String carte) {
        System.out.println(carte);
    }

    /**
     * Affiche le nombre de diamants
     */
    public void displayDiamant(int diamantCurPlayer, int diamantRequired, int score) {
        System.out.println("<<< Diamant: " + diamantCurPlayer + " | " + diamantRequired + " -> * " + score + " * >>>");
    }

    /**
     * Affiche si la porte de sortie est ouverte
     */
    public void displayOpenGate() {
        System.out.println("<<< La porte est ouverte >>>");
    }

    /**
     * Affiche une phrase pour prévenir que le joueur a perdu
     */
    public void displayLoose() {
        System.out.println("<<< VOUS AVEZ PERDU >>>");
    }

    /**
     * Affiche une phrase pour prévenir que le joueur a gagné
     */
    public void displayWin() {
        System.out.println("<<< VOUS AVEZ GAGNE >>>");
    }

    /**
     * Affiche le nom de carte courante
     */
    public void displayNameOfMap(String nameOfLevel) {
        System.out.println("<<< " + nameOfLevel + " >>>");
    }

    /**
     * Affiche l'erreur qui est survenue
     *
     * @param error l'erreur à afficher
     */
    public void displayError(String error) {
        System.out.println("\u001B[31m" + "!!! " + error + " !!!" + "\u001B[0m");
    }

    /**
     * Affiche le titre du jeu
     */
    public void displayStart() {
        System.out.println("---     Début du jeu Boulder Dash     ---");
    }

    /**
     * Affiche la fin du jeu
     */
    public void displayEnd() {
        System.out.println("--- Merci d'avoir joué à Boulder Dash ---");
    }

    /**
     * Affiche la demande
     *
     * @param ask la demande
     */
    public void displayAsk(String ask) {
        System.out.println("<<< " + ask + " >>>");
    }

    /**
     * Demande à l'utilisateur de rentrer une valeur (String)
     *
     * @return String, ce qu'a rentré l'utilisateur
     */
    public String askLine() {
        Scanner clavier = new Scanner(System.in);
        String command = clavier.nextLine();
        return command.toLowerCase();
    }
}
