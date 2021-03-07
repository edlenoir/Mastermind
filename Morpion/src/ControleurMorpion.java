import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ControleurMorpion implements ActionListener {
	private enum EtatControleur {
		JOUEUR1, JOUEUR2, PARTI_FINI
	}

	private VueMorpion vue;
	private ModeleMorpion modele;
	private EtatControleur etat;

	public ControleurMorpion(VueMorpion vue) {
		this.vue = vue;
		this.modele = new ModeleMorpion();
		this.etat = EtatControleur.JOUEUR1;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Couple couple;
		int i;
		int j;
		JButton b = (JButton) evt.getSource();
		if (b.getText().equals("Effacer")) {
			this.vue.initialiser();
			this.modele.initialiser();
			this.etat = EtatControleur.JOUEUR1;
		} else {
			switch (this.etat) {
			case JOUEUR1:
				b.setText("X");
				b.setEnabled(false);
				couple = this.vue.coordonneesBTCaseGrille(b);
				i = couple.getPremier();
				j = couple.getSecond();
				this.modele.setValeurCase(i, j, TypeCase.JOUEUR1);
				if (this.modele.casesAlignees(i, j)) {
					this.vue.afficherResusltat(1);
					this.etat = EtatControleur.PARTI_FINI;
				} else {
					this.vue.afficherJoueurCourant(2);
					this.etat = EtatControleur.JOUEUR2;
				}
				System.out.println(this.modele);
				break;
			case JOUEUR2:
				b.setText("O");
				b.setEnabled(false);
				couple = this.vue.coordonneesBTCaseGrille(b);
				i = couple.getPremier();
				j = couple.getSecond();
				this.modele.setValeurCase(i, j, TypeCase.JOUEUR2);
				if (this.modele.casesAlignees(i, j)) {
					this.vue.afficherResusltat(2);
					this.etat = EtatControleur.PARTI_FINI;
				} else {
					this.vue.afficherJoueurCourant(1);
					this.etat = EtatControleur.JOUEUR1;
				}
				System.out.println(this.modele);
				break;
			}
		}

	}
}
