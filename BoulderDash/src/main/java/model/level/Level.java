package model.level;

import model.Position;
import model.level.map.Map;
import model.level.map.block.Block;

import java.util.ArrayList;

/**
 * Un level composé des différents attributs qui lui sont propres
 */
public class Level {
    private final String name;
    private final String type;
    private final int num;
    private final int timeMap;
    private final int diamondRequired;
    private final int diamondValue;
    private final int bonusDiamondValue;
    private final Map map;

    /**
     * Constructeur d'un level composé des éléments
     *
     * @param name              le nom de la carte
     * @param type              le type de niveau (cave et intermission)
     * @param num               le numéro de la carte
     * @param timeMap           le temps disponible pour s'échapper de la carte
     * @param diamondRequired   le nombre de diamants requit pour pouvoir sortir de la carte
     * @param diamondValue      la valeur normale d'un diamant
     * @param bonusDiamondValue la valeur bonus d'un diamant
     * @param map               la map où se déroule
     */
    public Level(String name, String type, int num, int timeMap, int diamondRequired,
                 int diamondValue, int bonusDiamondValue, Map map) {
        this.name = name;
        this.type = type;
        this.num = num;
        this.timeMap = timeMap;
        this.diamondRequired = diamondRequired;
        this.diamondValue = diamondValue;
        this.bonusDiamondValue = bonusDiamondValue;
        this.map = new Map(map);
    }

    /**
     * Constructeur/copie robuste d'un level
     *
     * @param other le level à copier
     */
    public Level(Level other) {
        this.name = other.name;
        this.type = other.type;
        this.num = other.num;
        this.timeMap = other.timeMap;
        this.diamondRequired = other.diamondRequired;
        this.diamondValue = other.diamondValue;
        this.bonusDiamondValue = other.bonusDiamondValue;
        this.map = new Map(other.map);
    }


    /* Getter du level */

    /**
     * @return Getter du nom du level
     */
    public String getName() {
        return name;
    }

    /**
     * @return Getter du type du level
     */
    public String getType() {
        return type;
    }

    /**
     * @return Getter du numéro du level
     */
    public int getNum() {
        return num;
    }

    /**
     * @return Getter du nom plus du niveau du level
     */
    public String getNameInGame() {
        return (this.type + " " + this.num).toLowerCase();
    }

    /**
     * @return Getter du temps total disponible sur le level
     */
    public int getTimeMap() {
        return timeMap;
    }

    /**
     * @return Getter du nombre de diamants requis pour ouvrir la porte et
     * avoir une valeur bonus aux diamants du level
     */
    public int getDiamondRequired() {
        return diamondRequired;
    }

    /**
     * @return Getter de la valeur de base pout un diamant du level
     */
    public int getDiamondValue() {
        return diamondValue;
    }

    /**
     * @return Getter de la valeur bonus pour un diamant du level
     */
    public int getBonusDiamondValue() {
        return bonusDiamondValue;
    }


    /* Getter de la map */

    /**
     * retourne le block à la position donnée
     *
     * @param pos la position où récupérer le block
     * @return le block
     */
    public Block getBlockMap(Position pos) {
        return this.map.getBlock(pos);
    }

    /**
     * @return Getter de la taille de la colonne de la map.
     */
    public int getSizeColMap() {
        return this.map.getSizeColMap();
    }

    /**
     * @return Getter de la taille de la ligne de la map.
     */
    public int getSizeRowMap() {
        return this.map.getSizeRowMap();
    }

    /**
     * @return Getter de la position de spawn du joueur
     */
    public Position getSpawnPlayer() {
        return this.map.getSpawnPlayer();
    }

    /**
     * @return Getter de la liste des positions des block bougeables
     */
    public ArrayList<Position> getMovableBlock() {
        return this.map.getMovableBlock();
    }


    /* Méthode de la map */

    /**
     * Vérifie si la position se trouve dans la carte
     *
     * @param pos La position à tester
     * @return true si oui, sinon false
     */
    public boolean isValidPos(Position pos) {
        return this.map.isValidPos(pos);
    }

    /**
     * Permet d'ouvrir la porte de sortie.
     */
    public void openGate() {
        this.map.openGate();
    }

    /**
     * Déplace le block à une position donnée.
     *
     * @param ancienPos la position du block à déplacer
     * @param newPos    la position où déplacer le block
     */
    public void moveBlock(Position ancienPos, Position newPos) {
        this.map.moveBlock(ancienPos, newPos);
    }

    /**
     * Permet de trier le tableau de position
     */
    public void sortMovableBlock() {
        this.map.sortMovableBlock();
    }
}
