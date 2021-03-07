import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class VueJeton extends JPanel {
	
	private ControleurJeton controleur;
	
	public VueJeton () {
		this.controleur = new ControleurJeton();
		this.setLayout(new GridLayout(2,2));
		Icon jeton = new ImageIcon("./image.jpg");
		JButton boutonJeton = new JButton(jeton);
		this.add(boutonJeton);
		boutonJeton.addActionListener(controleur);
		for(int j = 1;j<4;j++) {
			JButton b = new JButton();
			this.add(b);
			b.addActionListener(controleur);
			
		}
		
	}
	
	public static boolean estCaseVide(JButton case1) {
		return case1.getIcon() == null;
	}
	
	public static void deplacerJeton(JButton case1, JButton case2) {
		Icon Jeton = new ImageIcon("./image.jpg");
		case1.setIcon(null);
		case2.setIcon(Jeton);
	}

}
