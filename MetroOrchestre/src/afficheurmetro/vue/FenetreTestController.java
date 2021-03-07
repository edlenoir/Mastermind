/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afficheurmetro.vue;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
/**
 * FXML Controller class
 *
 * @author millan
 */
public class FenetreTestController implements Initializable
{
    @FXML
    private ListView stationDebut ;   

    @FXML
    private ListView quaiDebut ;     

    @FXML
    private ListView rameDebut ;
    
    @FXML
    private ListView stationSuivante ;   

    @FXML
    private ListView quaiSuivant ; 
    
    private AfficheurMetro afficheur ;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.quaiDebut.setItems(FXCollections.observableArrayList("1", "2")) ;
        this.quaiSuivant.setItems(FXCollections.observableArrayList("1", "2")) ;
        this.rameDebut.setItems(FXCollections.observableArrayList("", "R1")) ;
    }
    
    public void setListeStation(final String[] stations)
    {
        ObservableList<String> listeStations = FXCollections.observableArrayList(stations) ;
        listeStations.add(0, "Depot");
        this.stationDebut.setItems(listeStations) ;
        this.stationSuivante.setItems(listeStations);
    }
    
    public void actionHandler (Event evt)
    {
        Button bt = (Button) evt.getTarget() ;
        if ("pos".equals(bt.getId()))            
        {
            this.afficheur.positionnerRame(
                    this.stationDebut.getSelectionModel().getSelectedItem().toString(), 
                    Integer.valueOf(this.quaiDebut.getSelectionModel().getSelectedItem().toString()), 
                    this.rameDebut.getSelectionModel().getSelectedItem().toString()) ; 
        }
        else
        {
            this.afficheur.modifierAffichage(
                    this.stationDebut.getSelectionModel().getSelectedItem().toString(), 
                    Integer.valueOf(this.quaiDebut.getSelectionModel().getSelectedItem().toString()), 
                    this.stationSuivante.getSelectionModel().getSelectedItem().toString(), 
                    Integer.valueOf(this.quaiSuivant.getSelectionModel().getSelectedItem().toString()), 
                    this.rameDebut.getSelectionModel().getSelectedItem().toString());
        }
    }
    
    public void setAfficheur(final AfficheurMetro aff)
    {
        this.afficheur = aff ;
    }
}
