package model;

import command.CommandManager;
import command.Move;
import model.level.LevelList;
import model.level.map.block.TypeBlock;
import oo.Observable;
import oo.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Une game est composé des levels jouable et d'une partie où jouer.
 * Est une façade de la classe Partie.
 */
public class Game implements Observable {
    private final LevelList levelList;
    private final List<Observer> observerList;
    private CommandManager commandManager;
    private Partie partie;


    /**
     * Constructeur d'une game par défaut
     */
    public Game() {
        this.levelList = new LevelList("main");
        this.observerList = new ArrayList<>();
    }

    /**
     * Permet de lancer une partie grâce au nom du level
     *
     * @param name le nom de level
     */
    public void setPartie(String name) {
        this.partie = new Partie(this.levelList.getLevel(name));
        this.commandManager = new CommandManager();
        this.observerList.clear();
        notifyObserver();
    }

    /**
     * Permet de rejouer une instance d'une partie
     *
     * @param partie la partie à reprendre
     */
    public void setPartie(Partie partie) {
        this.partie = new Partie(partie);
        notifyObserver();
    }


    /* Getter du Game */

    /**
     * @return la partie en cours
     */
    public Partie getPartie() {
        return new Partie(partie);
    }


    /* Méthode du level */

    /**
     * Permet de déplacer le joueur.
     *
     * @param direction la direction où va se déplacer le joueur.
     */
    public void move(Direction direction) {
        this.partie.move(direction);
        notifyObserver();
    }


    /* Méthode commandeManager */

    /**
     * Exécute un mouvement grâce à une direction et le jeu en cours
     *
     * @param dir la direction àu va se déplacer le joueur
     */
    public void executeMove(Direction dir) {
        this.commandManager.execute(new Move(this, dir));
    }

    /**
     * Permet d'annuler un mouvement que l'on a déjà exécuté
     */
    public void undoMove() {
        this.commandManager.undo();
    }

    /**
     * Permet d'exécuter à nouveau un mouvement qui a été annulé.
     */
    public void redoMove() {
        this.commandManager.redo();
    }

    /* Getter du level */

    /**
     * retourne le type du block à la position donnée
     *
     * @param pos la position où récupérer le type du block
     * @return le type du block
     */
    public TypeBlock getBlockType(Position pos) {
        return this.partie.getBlockType(pos);
    }

    /**
     * @return Getter de la taille de la colonne de la map.
     */
    public int getSizeColMap() {
        return this.partie.getSizeColMap();
    }

    /**
     * @return Getter de la taille de la ligne de la map.
     */
    public int getSizeRowMap() {
        return this.partie.getSizeRowMap();
    }

    /**
     * @return getter de la position du joueur
     */
    public Position getPosCurPlayer() {
        return this.partie.getPosCurPlayer();
    }

    /**
     * @return Getter du score du joueur
     */
    public int getScore() {
        return this.partie.getScore();
    }

    /**
     * @return Getter du nombre de diamants que possède le joueur
     */
    public int getCurDiamantPlayer() {
        return this.partie.getCurDiamantPlayer();
    }

    /**
     * @return Getter du type du level
     */
    public String getType() {
        return this.partie.getType();
    }

    /**
     * @return Getter du numéro du level
     */
    public int getNum() {
        return this.partie.getNum();
    }

    /**
     * @return Getter de l'état du jeu
     */
    public State getState() {
        return this.partie.getState();
    }

    /**
     * @return Getter de si la porte de sortie est ouverte.
     */
    public boolean isOpenGate() {
        return this.partie.isOpenGate();
    }

    /**
     * @return Getter de la carte du level.
     */
    public String viewMap() {
        return this.partie.createMap();
    }


    /* Getter du level */

    /**
     * @return Getter du nom du level
     */
    public String getName() {
        return this.partie.getName();
    }

    /**
     * @return Getter du nom plus du niveau du level
     */
    public String getNameInGame() {
        return this.partie.getNameInGame();
    }

    /**
     * @return Getter du temps total disponible sur le level
     */
    public int getTimeMap() {
        return this.partie.getTimeMap();
    }

    /**
     * @return Getter du nombre de diamants requis pour ouvrir la porte et
     * avoir une valeur bonus aux diamants du level
     */
    public int getDiamondRequired() {
        return this.partie.getDiamondRequired();
    }

    /**
     * @return Getter de la valeur de base pout un diamant du level
     */
    public int getDiamondValue() {
        return this.partie.getDiamondValue();
    }

    /* Getter de LevelList */

    /**
     * @return Getter de la liste des noms des levels disponibles
     */
    public List<String> allNameOfLevel() {
        return this.levelList.allNameOfLevel();
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
