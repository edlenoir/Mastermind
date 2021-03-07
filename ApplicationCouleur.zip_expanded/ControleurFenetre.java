import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * controleur d'une fenetre couleur à l'écoute des événements de fenetre
 */
public class ControleurFenetre extends WindowAdapter {
    /**
     * traitement avant que la FenêtreCouleur ne se ferme : arrêter
     * l'application proprement
     * 
     * @param e
     *            événement produit sur la FenêtreCouleur
     */
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
