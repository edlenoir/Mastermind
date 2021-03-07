import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 * controleur a l'ecoute des changements de focus sur les champs de texte
 * contenant les valeurs des nuances d'une couleur
 */
public class ControleurCouleur implements CaretListener {
	/**
	 * vue associee au controleur
	 */
	private VueCouleur vue;

	/**
	 * cree un controleur en lui associant sa vue
	 * 
	 * @param vue : vue associee au controleur
	 */
	public ControleurCouleur(VueCouleur vue) {
		this.vue = vue;
	}

	/**
	 * si on perd le focus sur un champ de texte alors recalculer la nouvelle valeur
	 * RVB de la couleur en fonction des trois nuances RVB et afficher cette couleur
	 * dans le canevas cCouleur de la vue
	 * 
	 * @param e evenement de focus
	 */
	public void caretUpdate(CaretEvent e) {
		Color c;
		String rouge = this.vue.getTRouge().getText();
		String vert = this.vue.getTVert().getText();
		String bleu = this.vue.getTBleu().getText();
		// convertir le contenu du premier champ de texte
		// en un entier pour obtenir la nuance rouge
		int v1 = 0;
		if (!rouge.equals(null) && !rouge.isEmpty()) {
			v1 = Integer.parseInt(rouge);
		}
		// convertir le contenu du deuxieme champ de texte
		// en un entier pour obtenir la nuance verte
		int v2 = 0;
		if (!vert.equals(null) && !vert.isEmpty()) {
			v2 = Integer.parseInt(vert);
		}
		// convertir le contenu du troisieme champ de texte
		// en un entier pour obtenir la nuance bleue
		int v3 = 0;
		if (!bleu.equals(null) && !bleu.isEmpty()) {
			v3 = Integer.parseInt(bleu);
		}
		// creer une couleur a partir des 3 nuances obtenues
		c = new Color(v1, v2, v3);
		// changer la couleur de fond du canevas couleurRVB
		// a partir de la nouvelle couleur obtenue
		this.vue.setCouleurCanvas(c);
	}
}
