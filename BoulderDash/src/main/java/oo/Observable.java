package oo;

/**
 * la classe interface d'une classe observer
 */
public interface Observable {


    /**
     * Permet de rajouter un observateur à l'objet observer
     *
     * @param observer observateur à rajouter
     */
    void addObserver(Observer observer);

    /**
     * Permet de retirer un observateur à l'objet observer
     *
     * @param observer observateur à retirer
     */
    void removeObserver(Observer observer);

    /**
     * Permet de notifier tous les observateurs
     */
    void notifyObserver();
}
