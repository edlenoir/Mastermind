import java.awt.Frame;
import java.awt.GridLayout;

/**
 * Cette classe permet de construire une <b>fenetre</b> composee d'un
 * PanneauCouleur.
 * 
 * 
 * @author Christine JULIEN
 * @version 1.1.6
 * @see java.awt.Frame
 * @see java.awt.Canvas
 * @see java.awt.GridLayout
 * @see java.awt.event.WindowAdapter
 * @see java.awt.event.WindowEvent
 * @see PanneauCouleur
 */

public class FenetreCouleur extends Frame {
    /**
     * Construit une FenetreCouleur contenant un PanneauCouleur
     */
    public FenetreCouleur() {
        // associer un titre à la FenetreCouleur
        this.setTitle("Conversions RVB");
        // selectionner le gestionnaire de mise en page de la FenetreCouleur
        this.setLayout(new GridLayout(1, 1));
        // creer une vue couleur et l'ajouter a la FenetreCouleur
        this.add(new VueCouleur());
        // associer une taille à la FenetreCouleur
        this.setSize(250, 120);
        // associer un gestionnaire de fenetre a la FenetreCouleur
        // a l'ecoute d'un evenement se produisant sur la fenetre
        this.addWindowListener(new ControleurFenetre());
        // afficher la FenetreCouleur
        this.pack();
        this.setVisible(true);
    }
}
