import javax.swing.UIManager;

/**
 * Cette classe permet de tester la classe FenetreCouleur
 * 
 * @author Christine JULIEN
 * @version 1.1.6
 * @see FenetreCouleur
 */

public class ApplicationCouleur {
	public static void main(String args[]) {
		{
			UIManager.LookAndFeelInfo rendusGraphiques[];
			rendusGraphiques = UIManager.getInstalledLookAndFeels();
			try {
				for (int i = 0; i < rendusGraphiques.length; i++) {
					String nomRenduGraphique = rendusGraphiques[i].getClassName();
					System.out.println(nomRenduGraphique);
					UIManager.setLookAndFeel(nomRenduGraphique);
					new FenetreCouleur();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		new FenetreCouleur();
	}
}
