import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * controleur d'une fenetre couleur � l'�coute des �v�nements de fenetre
 */
public class ControleurFenetre extends WindowAdapter {
    /**
     * traitement avant que la Fen�treCouleur ne se ferme : arr�ter
     * l'application proprement
     * 
     * @param e
     *            �v�nement produit sur la Fen�treCouleur
     */
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
