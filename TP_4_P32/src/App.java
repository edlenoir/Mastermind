import java.awt.GridLayout;

import javax.swing.JFrame;

public class App {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setLayout(new GridLayout(1,1));
		f.add(new VueMastermind());
		f.setTitle("App_Mastermind");
		f.pack();
		f.setVisible(true);
		f.setSize(200,200);	
	}

}