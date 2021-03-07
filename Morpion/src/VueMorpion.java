import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueMorpion extends JPanel {
	private JButton[] btCasesGrille;
	private JLabel resultat;
	private JLabel numeroJoueur;

	public VueMorpion() {
		ControleurMorpion controleur = new ControleurMorpion(this);
		this.setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 2));
		this.add(p1, BorderLayout.NORTH);
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(3, 3));
		this.add(p2, BorderLayout.CENTER);
		this.resultat = new JLabel(" ");
		this.add(this.resultat, BorderLayout.SOUTH);
		JButton effacer = new JButton("Effacer");
		effacer.addActionListener(controleur);
		p1.add(effacer);
		this.numeroJoueur = new JLabel(" ");
		p1.add(this.numeroJoueur);
		this.btCasesGrille = new JButton[9];
		for (int i = 0; i < 9; i++) {
			this.btCasesGrille[i] = new JButton(" ");
			p2.add(this.btCasesGrille[i]);
			this.btCasesGrille[i].addActionListener(controleur);
		}
	}

	public Couple coordonneesBTCaseGrille(JButton b) {
		for (int i = 0; i < 9; i++) {
			if (this.btCasesGrille[i] == b) {
				return new Couple(i / 3, i % 3);
			}
		}
		return null;
	}

	public void initialiser() {
		for (JButton b : this.btCasesGrille) {
			b.setText(" ");
			b.setEnabled(true);
		}
		this.resultat.setText(" ");
		this.numeroJoueur.setText("Joueur 1");
	}

	public void afficherResusltat(int no) {
		this.resultat.setText("Le joueur" + no + "a gagné la partie");
	}

	public void afficherJoueurCourant(int no) {
		this.numeroJoueur.setText("Le joueur" + no);
	}

}
