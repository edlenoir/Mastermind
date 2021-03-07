/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.vue;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tictactoe.GestionClicCase;
import tictactoe.TicTacToeIHMImpl;

/**
 *
 * @author millan
 */
public class FenetrePrincipale extends Application 
{
    private static TicTacToeIHMControllerImpl controller ;
    private static GestionClicCase gestionnaire ;
    private final boolean estPremier ;
    
    public FenetrePrincipale(final boolean estPremier) 
    {
        this.estPremier = estPremier ;
    }
    
    @Override
    public void init()
    {
        //this.estPremier = Boolean.valueOf(this.getParameters().getUnnamed().get(0)) ;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {

       try 
       {
           // Load root layout from fxml file.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(TicTacToeIHMImpl.class
                   .getResource("vue/TicTacToeIHM.fxml")); 
           AnchorPane rootLayout ;
           rootLayout = (AnchorPane) loader.load();

           // Show the scene containing the root layout.
           Scene scene = new Scene(rootLayout);
           primaryStage.setScene(scene);

           FenetrePrincipale.controller = loader.getController();
           // Permet au controleur de connaitre si le jouuer est la croix ou
           // le cercle           
           FenetrePrincipale.controller.setEstPremierJoueur(this.estPremier) ;
           FenetrePrincipale.controller.setGestionnaireClicCase(FenetrePrincipale.gestionnaire);
           if (this.estPremier)
           {
               rootLayout.getStylesheets().add("/tictactoe/vue/CouleurJoueurX.css");
           }
           else
           {
               rootLayout.getStylesheets().add("/tictactoe/vue/CouleurJoueurO.css");               
           }
           FenetrePrincipale.controller.setPane((GridPane)rootLayout.getChildren().get(0)) ;
           primaryStage.setTitle("Tic Tac Toe");
           Platform.setImplicitExit(true);
           primaryStage.setOnCloseRequest(t->System.exit(0));
           primaryStage.show();
       } 
       catch (IOException e) 
       {
           e.printStackTrace();
       }
    }  
        
    public static TicTacToeIHMControllerImpl getController()
    {
        return FenetrePrincipale.controller ;
    }
    
    public static void setGestionnaireClic(final GestionClicCase gestionnaire)
    {
       FenetrePrincipale.gestionnaire = gestionnaire ;
    }
}
