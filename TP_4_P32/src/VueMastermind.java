import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueMastermind extends JPanel {
	
	private JTextField[] bPIHM;
	private JTextField[] combinaisonOrdiIHM;
	private JButton[][] combinaisonsJoueurIHM;
	private JTextField[] mPIHM;
	private int nbCouleurs;
	private static int NBMAX_COMBINAISONS = 10;
	private JButton[] paletteIHM;
	private static long serialVersionUID;
	private int taille;
	
	public VueMastermind(int nbCouleurs, int taille) {
		this.bPIHM = new JTextField[10];
		this.mPIHM = new JTextField[10];
		this.combinaisonOrdiIHM = new JTextField[10];
		this.combinaisonsJoueurIHM = new JButton[10][4];
		this.nbCouleurs = nbCouleurs;
		this.paletteIHM = new JButton[this.nbCouleurs];
		this.taille = taille;
		
		// Panneau principal
		this.setLayout(new BorderLayout());
		
		// Panneau Nord
		JPanel pNord = new JPanel();
		pNord.setLayout(new FlowLayout());
		this.add(pNord, BorderLayout.NORTH);
		
			// Libelle
			JLabel libelleCouleur = new JLabel("Couleurs :");
			pNord.add(libelleCouleur);
			
			// Grille
			JPanel grilleNord = new JPanel();
			grilleNord.setLayout(new GridLayout(1,6));
			pNord.add(grilleNord);
			
				// Boutons grille 
				for (int i = 0 ; i < this.nbCouleurs ; i++) {
					JButton bGrilleNord = new JButton();
					bGrilleNord.setEnabled(false);
					this.paletteIHM[i] = bGrilleNord;
					grilleNord.add(bGrilleNord);
				}
		
		// Panneau Centre
		JPanel pCentre = new JPanel();
		pCentre.setLayout(new GridLayout(10,2));
		this.add(pCentre, BorderLayout.CENTER);
		
			// Repetition de 10 fois Grille et resultat 
			for (int i = 0 ; i < NBMAX_COMBINAISONS ; i++) {
				
				// Grille
				JPanel grilleCentre = new JPanel();
				grilleCentre.setLayout(new GridLayout(1,4));
				
				pCentre.add(grilleCentre);
				for (int j = 0 ; j < this.taille ; j++) {
					JButton bGrilleCentre = new JButton();
					if (i != 0) {
						bGrilleCentre.setEnabled(false);
					}
					combinaisonsJoueurIHM[i][j] = bGrilleCentre;
					grilleCentre.add(bGrilleCentre);
				}
				
				// Grille (resultat)
				JPanel grilleResultat = new JPanel();
				grilleResultat.setLayout(new GridLayout(2,2));
				pCentre.add(grilleResultat);
				grilleResultat.add(new JLabel("BP", JLabel.CENTER));
				grilleResultat.add(new JLabel("NP", JLabel.CENTER));
				for (int k = 0 ; k < 2 ; k++) {
					JTextField textField = new JTextField();
					textField.setEnabled(false);
					if (k == 0) {
						bPIHM[i] = textField;
					} else { 
						mPIHM[i] = textField;
					}
					grilleResultat.add(textField);
				}
			}
			
		// Panneau Sud
		JPanel pSud = new JPanel();
		pSud.setLayout(new GridLayout(1,2));
		this.add(pSud, BorderLayout.SOUTH);
		
			// Grille 
			JPanel grilleSud = new JPanel();
			grilleSud.setLayout(new GridLayout(1,4));
			pSud.add(grilleSud);
			for (int i = 0 ; i < this.taille ; i++) {
				JTextField textField = new JTextField();
				textField.setEnabled(false);
				this.combinaisonOrdiIHM[i] = textField;
				grilleSud.add(textField);
			}
			// Bouton
			JButton bValider = new JButton("Valider");
			pSud.add(bValider);
	}
	
	public void activerCombinaison(int i) {
		for (int j = 0 ; j < this.taille ; j++) {
			combinaisonsJoueurIHM[i][j].setEnabled(true);
		}
	}
	
	public void afficherBP(int i, int nbBP) {
		this.bPIHM[i].setText(String.valueOf(nbBP));
	}
	
	public void afficherCombinaisonOrdinateur(int [] tableauCouleurs) {
		for (int i = 0 ; i < this.taille ; i++) {
			this.combinaisonOrdiIHM[i].setBackground(VueMastermind.chiffreEnCouleur(tableauCouleurs[i]));
		}
	}
	
	public void afficherMP(int i, int nbMP) {
		this.mPIHM[i].setText(String.valueOf(nbMP));
	}
	
	public boolean appartientCombinaison(JButton b, int i) {
		for (int j = 0 ; j < this.taille ; j++) {
			if (b == this.combinaisonsJoueurIHM[i][j]) {
				return true;
			}
		}
		return false;
	}
	
	public boolean appartientPalette(JButton b) {
		for (int i = 0 ; i < this.nbCouleurs ; i++) {
			if (b == this.paletteIHM[i]) { //.getComponent(0)) {
				return true;
			}
		}
		return false;
	}
	
	public static Color chiffreEnCouleur(int i) {
		Color couleur;
		switch(i) {
			case 0:
				couleur = Color.BLUE;
				break;
			case 1:
				couleur = Color.RED;
				break;
			case 2:
				couleur = Color.GREEN;
				break;
			case 3:
				couleur = Color.YELLOW;
				break;
			case 4:
				couleur = Color.CYAN;
				break;
			case 5:
				couleur = Color.MAGENTA;
				break;
			default:
				couleur = Color.BLUE;
				break;
		}
		return couleur;
	}
	
	public boolean combinaisonComplete(int i) {
        for (int j = 0 ; j < this.taille ; j++) {
            if (this.combinaisonsJoueurIHM[i][j].getBackground() != this.combinaisonOrdiIHM[j].getBackground()) {
                return false;
            }
        }
        return true;
    }
	
	public int[] combinaisonEnEntiers(int i) {
        int[] combinaison = new int[this.taille];
        for (int j = 0 ; j < this.taille ; j++) {
            Color couleur = this.combinaisonsJoueurIHM[i][j].getBackground();
            if (couleur == Color.BLUE) {
                combinaison[j] = 0;
            } else if (couleur == Color.RED) {
                combinaison[j] = 1;
            } else if (couleur == Color.GREEN) {
                combinaison[j] = 2;
            } else if (couleur == Color.YELLOW) {
                combinaison[j] = 3;
            } else if (couleur == Color.CYAN) {
                combinaison[j] = 4;
            } else if (couleur == Color.MAGENTA) {
                combinaison[j] = 5;
            }
        }
        return combinaison;
    }
	
	
	
	public void desactiverCombinaison(int i) {
		for (int j = 0 ; j < this.taille ; j++) {
			combinaisonsJoueurIHM[i][j].setEnabled(false);
		}
	}
	
	public int getnbCouleurs() {
		return this.nbCouleurs;
	}
	
	public int getTaille() {
		return this.taille;
	}
}
