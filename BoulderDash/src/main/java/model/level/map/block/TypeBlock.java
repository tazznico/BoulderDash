package model.level.map.block;

import model.Position;
import view.fx.utilitaire.Animation;

/**
 * Définit les différents types de block disponibles dans le jeu
 * ayant comme attributs un char représentant le caractère dans le paterne de la map
 * et un attribut animation qui permet de savoir quelle partie du sprite afficher
 */
public enum TypeBlock {
    WALL('W', new Animation(false, new Position(6, 3), 0)),
    BORDER('B', new Animation(false, new Position(6, 1), 0)),
    DIRT('D', new Animation(false, new Position(7, 1), 0)),
    DIAMANT('*', new Animation(true, new Position(10, 0), 7)),
    STONE('S', new Animation(false, new Position(7, 0), 0)),
    PLAYER('+', new Animation(true, new Position(1, 0), 7)),
    GATE('-', new Animation(false, new Position(6, 2), 0)),
    VOID('V', new Animation(false, new Position(6, 0), 0));

    private final Animation animation;

    /**
     * Constructeur du type d'un block
     *
     * @param charPatternBlock le char fourni dans le paterne de la map
     * @param animation        l'animation a joué pour un type de block'
     */
    TypeBlock(char charPatternBlock, Animation animation) {
        this.animation = animation;
    }

    /**
     * Renvoie la couleur du block en fonction de son type
     *
     * @return le block avec un espace et un fond de couleur
     */
    public String getColorBlock() {
        return switch (this) {
            case WALL -> "\u001B[47m" + "   " + "\u001B[0m";
            case BORDER -> "\u001B[45m" + "   " + "\u001B[0m";
            case DIRT -> "\u001B[42m" + "   " + "\u001B[0m";
            case DIAMANT -> "\u001B[46m" + "   " + "\u001B[0m";
            case STONE -> "\u001B[43m" + "   " + "\u001B[0m";
            case PLAYER -> "\u001B[40m" + " P " + "\u001B[0m";
            case VOID -> "\u001B[40m" + "   " + "\u001B[0m";
            case GATE -> "\u001B[40m" + "[ ]" + "\u001B[0m";
        };
    }

    /**
     * @return getter de l'animation d'un type de block
     */
    public Animation getAnimation() {
        return this.animation;
    }
}
