package model.level.map;

import model.Position;
import model.level.map.block.Block;
import model.level.map.block.DoubleBlock;
import model.level.map.block.TypeBlock;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * une classe comportant certains attributs qui sont générés à la création de la map
 * dans la class LevelList.
 */
public class Map {
    private final Block[][] map;
    private final int diamondTotal;
    private final Position spawnPlayer;
    private final Position exitGate;
    private final ArrayList<Position> movableBlock;

    /**
     * Constructeur de la map ayant les différents paramètres charger.
     *
     * @param map          la carte composée des block de type.
     * @param diamondTotal le nrb de diamants sur la carte.
     * @param spawnPlayer  la position où le joueur spawn.
     * @param exitGate     la position de la porte de sortie.
     * @param movableBlock la liste des blocks bougeables.
     */
    public Map(Block[][] map, int diamondTotal, Position spawnPlayer,
               Position exitGate, ArrayList<Position> movableBlock) {
        this.map = copyMap(map);
        this.diamondTotal = diamondTotal;
        this.spawnPlayer = spawnPlayer;
        this.exitGate = exitGate;
        this.movableBlock = (ArrayList<Position>) movableBlock.clone();
    }

    /**
     * Constructeur robust de la map avec une autre map.
     *
     * @param other la map à copier.
     */
    public Map(Map other) {
        this(other.copyMap(other.map), other.diamondTotal, other.spawnPlayer,
                other.exitGate, (ArrayList<Position>) other.movableBlock.clone());
    }

    /**
     * Fait une copie robuste de la carte de la classe courante
     *
     * @param toCopie la map à copier
     * @return la copie robuste de la carte
     */
    private Block[][] copyMap(Block[][] toCopie) {
        Block[][] newMap = new Block[toCopie.length][toCopie[0].length];
        for (int y = 0; y < toCopie.length; y++) {
            for (int x = 0; x < toCopie[y].length; x++) {
                newMap[y][x] = toCopie[y][x].clone();
            }
        }
        return newMap;
    }


    /* Getter de la map */

    /**
     * retourne le block à la position donnée
     *
     * @param pos la position où récupérer le block
     * @return le block
     */
    public Block getBlock(Position pos) {
        return this.map[pos.y()][pos.x()];
    }

    /**
     * @return Getter de la taille de la colonne de la map.
     */
    public int getSizeColMap() {
        return this.map.length;
    }

    /**
     * @return Getter de la taille de la ligne de la map.
     */
    public int getSizeRowMap() {
        return this.map[0].length;
    }

    /**
     * @return Getter de la carte complète
     */
    public Block[][] getMap() {
        return this.map;
    }

    /**
     * @return Getter de la position de spawn du joueur
     */
    public Position getSpawnPlayer() {
        return this.spawnPlayer;
    }

    /**
     * @return Getter de la liste des positions des block bougeables
     */
    public ArrayList<Position> getMovableBlock() {
        return this.movableBlock;
    }


    /* Méthode de la map */

    /**
     * Vérifie si la position se trouve dans la carte
     *
     * @param pos La position à tester
     * @return true si oui, sinon false
     */
    public boolean isValidPos(Position pos) {
        if (pos.y() < 0 || pos.y() >= this.map.length) {
            return false;
        }
        return pos.x() >= 0 && pos.x() < this.map[0].length;
    }

    /**
     * Permet d'ouvrir la porte de sortie.
     */
    public void openGate() {
        if (this.getBlock(this.exitGate) instanceof DoubleBlock) {
            ((DoubleBlock) this.getBlock(this.exitGate)).changeTypeDefault();
        } else {
            throw new IllegalArgumentException("Le block donné à la pos " + this.exitGate + " n'est pas un double block");
        }
    }

    /**
     * Déplace le block à une position donnée.
     *
     * @param ancienPos la position du block à déplacer
     * @param newPos    la position où déplacer le block
     */
    public void moveBlock(Position ancienPos, Position newPos) {
        TypeBlock typeBlock = getBlock(ancienPos).getTypeDefault();
        getBlock(ancienPos).setDefaultType(TypeBlock.VOID);
        getBlock(newPos).setDefaultType(typeBlock);
    }

    /**
     * Permet de trier le tableau de position
     * Ce code a été trouvé sur :
     * <a href="https://stackoverflow.com/questions/29624497/java-sorting-a-set-of-points-based-on-both-x-and-y-coordinates">...</a>
     */
    public void sortMovableBlock() {
        this.movableBlock.sort(Comparator.comparingInt(Position::y).thenComparingInt(Position::x));
    }
}
