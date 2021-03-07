/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author millan
 */
public interface GestionClicCase
{
    /**
     * Méthode contenant le code traitant le clic sur la case se trouvant
     * à la ligne lig et à la colonne col.<BR>
     * Cette méthode vérifie que la case est vide et que le joueur 
     * n'est pas bloqué. Si le joueur a le droit de jouer le coup est répercuté 
     * sur la grille du joueur distant. this.ihm.estBloque permet de tester une 
     * partie de l'état global du système. Si la connexion avec le joueur 
     * distant n'est pas établie la méthode l'établit.
     * @param lig numero de la ligne de la case sur laquelle on clique
     * @param col numero colonne de la case sur laquelle on clique
     * @return vrai si le joueur n'est pas bloqué et faux sinon
     */
    boolean traitementCaseCliquee(int lig, int col);
}
