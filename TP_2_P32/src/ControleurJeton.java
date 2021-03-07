import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ControleurJeton implements ActionListener {
	
	public enum Etat {ATTENTE_CLIC_DEPART, ATTENTE_CLIC_ARRIVEE};
	private Etat etat;
	private JButton bImage;
	

	public ControleurJeton() {
		this.etat = Etat.ATTENTE_CLIC_DEPART;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		switch(this.etat) {
		case ATTENTE_CLIC_DEPART : 
			if(!VueJeton.estCaseVide(button)) {
				this.bImage = button;
				this.etat = Etat.ATTENTE_CLIC_ARRIVEE;
			}
			break;
			
		case ATTENTE_CLIC_ARRIVEE : 
			VueJeton.deplacerJeton(this.bImage, button);
			this.etat = Etat.ATTENTE_CLIC_DEPART;
			break;
		}
	}

}
