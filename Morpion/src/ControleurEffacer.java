import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

public class ControleurEffacer implements ActionListener {
	
	private VueMorpion vue;
	private ModeleMorpion modele;
	
	public ControleurEffacer(VueMorpion vue) {
		this.vue = vue;
		this.modele = new ModeleMorpion();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.vue.initialiser();
		this.modele.initialiser();
	}

}
