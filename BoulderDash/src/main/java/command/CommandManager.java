package command;

import java.util.Stack;

/**
 * Gère l'exécution, undo et redo des commandes effectuées
 */
public class CommandManager {
    private final Stack<Command> history = new Stack<>();
    private final Stack<Command> historyRedo = new Stack<>();


    /**
     * Permet d'exécuter la commande passée en paramètre
     *
     * @param command Command, une commande créer dans le contrôleur
     */
    public void execute(Command command) {
        command.execute();
        this.history.push(command);
        this.historyRedo.clear();
    }

    /**
     * Permet de re-exécuter une commande déjà faite
     *
     * @param command une commande à re-exécuter
     */
    public void reExecute(Command command) {
        command.execute();
        this.history.push(command);
    }

    /**
     * Reviens en arrière dans la liste des commandes
     * S'il y en a une.
     */
    public void undo() {
        if (this.history.isEmpty()) {
            throw new IllegalArgumentException("Vous ne vous êtes toujours pas déplacé");
        }
        Command toUndo = this.history.pop();
        toUndo.undo();
        this.historyRedo.push(toUndo);
    }

    /**
     * Permet de revenir en avant dans la liste des commandes
     * S'il y en a une.
     */
    public void redo() {
        if (this.historyRedo.isEmpty()) {
            throw new IllegalArgumentException("Vous n'avez annulé aucun mouvement");
        }
        Command toRedo = this.historyRedo.pop();
        reExecute(toRedo);
    }

    /**
     * @return Getter de la taille du stack des undo
     */
    public int getSizeUndo() {
        return this.history.size();
    }

    /**
     * @return Getter de la taille du stack des redo
     */
    public int getSizeRedo() {
        return this.historyRedo.size();
    }
}