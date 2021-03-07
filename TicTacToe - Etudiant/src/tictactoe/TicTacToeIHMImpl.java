/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

//import java.awt.GridLayout;
import tictactoe.vue.FenetrePrincipale;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author millan
 */
public class TicTacToeIHMImpl implements TicTacToeIHM
{    
     public TicTacToeIHMImpl(final boolean estPremier)
     {         
        this.lancerIHM(estPremier);
     }  
     
    @Override
     public void setGestionnaireClic(final GestionClicCase gest)
     {
         FenetrePrincipale.setGestionnaireClic(gest) ;
     }
     
    
    /**
     * @return vrai si le joueur est bloqué c'est-à-dire si c'est au joueur
     * distant de jouer.
     */
    @Override
    public boolean estBloque()
    {
        return  FenetrePrincipale.getController().estBloque() ;
    }
    
    /**
     * Méthode permettant de propager la case cochée par le joueur distant
     * @param ligne numéro de la ligne cochée par le joueur distant
     * @param colonne numéro de la colonne cochée par le joueur distant
     */
    @Override
    public void jouerDistant(final int ligne, final int colonne)
    {
        FenetrePrincipale.getController().setMarque(ligne, colonne);
    }

    @Override
    public String getContenuCase(int ligne, int colonne)
    {
        return FenetrePrincipale.getController().getContenuCase(ligne, colonne) ;
    }
    
    private void lancerIHM(final boolean estPremier)
    {
        try 
        {
            // Seule manière publique actuelle de lancer les runtimes a la main.
            // Une autre manière de faire sera disponible dans le JDK 9.
            new JFXPanel();
            //
            final FenetrePrincipale app = new FenetrePrincipale(estPremier);
            app.init();
            Platform.runLater(() -> 
            {
                try {
                    final Stage stage = new Stage();
                    stage.setResizable(false);
                    app.start(stage);
                } catch (Exception ex) 
                {
                    ex.printStackTrace() ;
                }
            });
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace() ;
        }
        //    FenetrePrincipale.initialiserIHM(estPremier);  
    }

	@Override
	public void propagerCoup(int lig, int col) {
		// TODO Auto-generated method stub
		
	} 
}
