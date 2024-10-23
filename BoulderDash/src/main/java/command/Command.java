package command;

/**
 * Représente une commande
 */
public interface Command {

    /**
     * Execute la commande
     */
    void execute();

    /**
     * Défait la commande entrée
     */
    void undo();
}
