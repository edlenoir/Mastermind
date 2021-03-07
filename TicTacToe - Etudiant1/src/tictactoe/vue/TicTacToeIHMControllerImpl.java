/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.vue;

import tictactoe.GestionClicCase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author millan
 */
public class TicTacToeIHMControllerImpl implements Initializable
{
    private final int[] dernierCoup ;
    private final char[][] cases ;
    private char symbole  ;
    private static final int NBCASES = 3 ;
    private GridPane gridPane ;  
    private GestionClicCase gestionnaire ;
    private int nbCoup ;
    private boolean bloque ;
    
    
    public TicTacToeIHMControllerImpl ()
    {
        this.nbCoup = 0 ;
        this.dernierCoup = new int[2] ;
        this.dernierCoup[0] = -1 ;
        this.dernierCoup[1] = -1 ;
        this.cases = new char[TicTacToeIHMControllerImpl.NBCASES]
                [TicTacToeIHMControllerImpl.NBCASES] ;
        for (int idx = 0 ; idx < TicTacToeIHMControllerImpl.NBCASES ; idx++)
            for (int idy = 0 ; idy < TicTacToeIHMControllerImpl.NBCASES ; idy++)
        {
           this.cases[idx][idy] = ' ' ; 
        }
    }  
 
    @FXML
    public void caseHandler (Event evt)
    {
        boolean coupOk ;
        Button but = (Button) evt.getSource() ;        
        
        this.dernierCoup[1] = (GridPane.getRowIndex(but) == null?0:GridPane.getRowIndex(but)) ;
        this.dernierCoup[0] = (GridPane.getColumnIndex(but) == null?0:GridPane.getColumnIndex(but)) ;
        
        coupOk = this.gestionnaire.traitementCaseCliquee(this.dernierCoup[0], this.dernierCoup[1]);
        if (coupOk)
        {
            this.cases[this.dernierCoup[0]][this.dernierCoup[1]] = this.symbole ;                        
            but.setText(String.valueOf(this.symbole)) ; 
            this.bloque = true ;
            this.nbCoup++ ;
            this.partieFinie();            
        }
    }
    
    public void setEstPremierJoueur(final boolean estPremier)
    {
        this.symbole = (estPremier?'X':'O') ;
    }
    
    public void setMarque(final int ligne, final int colonne)
    {
        this.gridPane.getChildren().stream().
                filter((node) -> (node instanceof Button)).
                forEachOrdered((node) -> 
        {
            int idx = (GridPane.getColumnIndex(node) == null?0:GridPane.getColumnIndex(node)) ;
            int idy = (GridPane.getRowIndex(node) == null?0:GridPane.getRowIndex(node)) ;
            if (idx == ligne && idy == colonne)
            {   
                /**
                 * Permet de redirigé le flot de controle vers le thread dédié
                 * à java FX. Si on ne passe pas par runLater, une exception est générée.
                 **/
                this.cases[ligne][colonne] = (this.symbole == 'O'?'X':'O') ;                      
                Platform.runLater(()->((Button) node).setText((this.symbole == 'O'?"X":"O")));
            }
        });
        this.bloque = false ;
        this.nbCoup++ ;
        this.partieFinie();        
    }
    
    public char getMarque(final int ligne, final int colonne)
    {
        return this.cases[ligne][colonne] ;
    }
    
    public void setPane(final GridPane gridPane)
    {
        this.gridPane = gridPane ;
    }
    
    /**
     * @param symbole joueur dont on veut savoir s'il a gagné ou pas
     * @return vrai si le joueur a gagné faut sinon
     */
    public boolean aGagne(final char symbole)
    {    
        boolean res  = false ;
        int idx = 0 ;
        while (!res && idx <3)
        {
           res = true ;
           boolean res1 = true ;
           for (int idy = 0 ; idy <3 ; idy++)
           {
               res =    res &&  (symbole == this.cases[idx][idy]) ;
               res1 =   res1 && (symbole == this.cases[idy][idx]) ;
            }
           idx++ ;
           res = res || res1 ;
        }
        if (!res)
        {
            res = true ;
            boolean res1 = true ;
    
            for (idx = 0 ; idx <3 ; idx++)
            {
               res =    res &&  (symbole == this.cases[idx][idx]) ;
               res1 =   res1 && (symbole == this.cases[2 - idx][idx]) ;               
            }  
            res = res || res1 ;
        }
        return res ;        
    }
    
    public String getContenuCase(int ligne, int colonne)
    {
        return (this.cases[ligne][colonne] == ' '?"":String.valueOf(this.cases[ligne][colonne])) ;
    }
    
    public void setGestionnaireClicCase(final GestionClicCase gestion)
    {
        this.gestionnaire = gestion ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
    
    public void setBloque(final boolean val)
    {
        this.bloque = val ;
    }
    
    public boolean estBloque()
    {
        return this.bloque ;
    }
    
    public void setNbCoup(final int nb)
    {
        this.nbCoup = nb ;
    }
    
    public int getNbCoup()
    {
        return this.nbCoup ;
    }
    
    /**
     * Calcule le gagnant de la partie s'il y en a un et affiche un message
     */
    public void partieFinie ()
    {
        if (this.aGagne(this.symbole))
        {
            Platform.runLater(()->
            {   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Résutat...");
                alert.setHeaderText("Félicitation...");
                alert.setContentText("Vous avez gagné..."); 
                alert.show();  
            });
            this.bloque = true ;
        }
        else if (this.aGagne(this.symbole))
        {
            Platform.runLater(()->
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Résutat...");
                alert.setHeaderText("Désolé...");
                alert.setContentText("Vous avez perdu...");
                alert.show();  
            }) ;
            this.bloque = true ;
        }
        else if (this.nbCoup == 9)
        {
            Platform.runLater(()->
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Résutat...");            
                alert.setHeaderText("Match null...");
                alert.setContentText("Vous n'avez pas réussi à vous départager..."); 
                alert.show();  
            });
            this.bloque = true ;
        }
    }    
}
