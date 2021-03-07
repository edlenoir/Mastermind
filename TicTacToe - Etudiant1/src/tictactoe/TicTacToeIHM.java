package tictactoe;

/**
 * Interface exposant les méthodes permettant de dialoguer avec l'interface
 * graphique du jeu TicTacToc.
 * @author millan
 */
public interface TicTacToeIHM 
{

    /**
     * @return vrai si le joueur est bloqué c'est-à-dire si c'est au joueur
     * distant de jouer.
     */
    boolean estBloque();

    /**
     * Méthode permettant de propager la case cliquée par le joueur distant au
     * joueur local
     * @param ligne numéro de la ligne cochée par le joueur distant
     * @param colonne numéro de la colonne cochée par le joueur distant
     */
    void jouerDistant(final int ligne, final int colonne);    
    
    /**
     * Methode retournant la valeur contenu dans la case
     * @param ligne numero de la ligne de la case
     * @param colonne numéro de la colonne de la case
     * @return contenu de la case. Dans le cas du Tic Tac Toe on aura :
     * <ul>
     * <li> X pour le joueur qui joue avec les croix </li>
     * <li> O pour le joueur qui joue avec les ronds </li>
     * <li> la chaine vide si la case est vide </li>
     * </ul>
     */
    String getContenuCase(final int ligne, final int colonne);

    /**
     * Methode permettant d'associer a l'IHM son gestionnaire de clic sur
     * une case.
     * @param gest gestionnaire que l'on veut associer a l'interface graphique
     */    
    void setGestionnaireClic(final GestionClicCase gest);      
}
