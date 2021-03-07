package afficheurmetro.vue;

import afficheurmetro.EcouteurBoutons;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author millan
 */
public class AfficheurMetro extends Application
{
//    static final String[] STATIONS = {"Ramonville", "Paul Sab", "Fac Medecine", 
//        "Rangueil", "Souselong", "St Agne", "Empalot", 
//        "Marcel Langet", "St Michel"};
//    
////    static final String[] STATIONS = {"Ramonville", "Paul Sab", "Fac Medecine", 
////        "Rangueil", "Souselong", "St Agne","Empalot"};
        
    private Stage stagePrincipal ;
    private final List<GraphVoie> Voies ;
    private final Map<String, Noeud> topologie ;
    private int indiceVoie ;
    private Button demarrer ;
    private Button arreter ;
    private Button tester ;
    private final String [] stations ;
    private final EcouteurBoutons ecouteurs ;
    
    
    private class Noeud
    {
        private final GraphStation st ;
        private int prec ;
        private int suiv ;
        
        public Noeud(final GraphStation st)
        {
            this.prec = -1 ;
            this.suiv = -1 ;
            this.st = st ;
        }
        
        public void setSuivant(final int suiv)
        {
            this.suiv = suiv ;            
        }
        
        public void setPrecedent(final int prec)
        {
            this.prec = prec ;            
        }        
        
        public GraphStation getStation()
        {
            return this.st ;
        }
        
        public int getSuivant()
        {
            return this.suiv;
        }
        
        public int getPrecedent()
        {
            return this.prec;
        }        
    }
        
    public AfficheurMetro(
            final EcouteurBoutons ecouteurs, 
            final String[] stations)
    {
        this.stations = stations ;
        this.topologie = new HashMap<>() ;
        this.Voies = new ArrayList<>() ;
        this.indiceVoie = 0 ;
        this.ecouteurs = ecouteurs ;

    }
    
    @Override
    public void start(Stage stage) throws Exception
    {
        GridPane root = new GridPane();
        root.setVgap(RacineReseau.DISTANCE_INTER_ELT); 
        this.stagePrincipal = stage ;
        int nbStations ;
        /*
         * Il ne faut pas que la dernière station se trouve en premier ou 
         * dernier donc on met 5 stations par ligne ou 4 si le nombre de 
         * stations est un multiple de 4 ou 6 si c'est aussi un multiple de 4
         */
        int nbStationsLigne = (((this.stations.length + 1) % 5 == 0)?
                               (((this.stations.length + 1) % 4 == 0)?6:4):5) ;
        int ligne ;
        boolean estGD = true ;

        GraphVoieChangement v1 = new GraphVoieChangement(true, true) ;        
        GraphDepot dep = new GraphDepot(true) ;
        dep.setStation("Depot");
        this.topologie.put("Depot", new Noeud(dep)) ;
        
        int prec ;
        nbStations = 1 ;
        
        this.topologie.get("Depot").setSuivant(this.indiceVoie);
        this.Voies.add(v1) ;
        prec = this.indiceVoie ;
        this.indiceVoie++ ;
        
        for (int idx=0 ; idx < this.stations.length ; idx++)
        {
            GraphStation st = new GraphStation(estGD) ;
            this.topologie.put(this.stations[idx], new Noeud(st)) ;
            this.topologie.get(this.stations[idx]).setPrecedent(prec);            
            st.setStation(this.stations[idx]);
            nbStations++ ;
            
            if (nbStations==nbStationsLigne)
            {
                GraphVoieCourbe vc = new GraphVoieCourbe(estGD) ;
                this.topologie.get(this.stations[idx]).
                        setSuivant(this.indiceVoie);
                this.Voies.add(vc) ;
                prec = this.indiceVoie ;
                this.indiceVoie++ ;
                estGD = !estGD ;  
                nbStations = 0 ;
            }
            else if (idx<this.stations.length-1)
            {
                GraphVoieDouble vd = new GraphVoieDouble(estGD) ;               
                this.topologie.get(this.stations[idx]).setPrecedent(prec);
                this.topologie.get(this.stations[idx]).
                        setSuivant(this.indiceVoie); 
                this.Voies.add(vd) ;                
                prec = this.indiceVoie ; 
                this.indiceVoie++ ;
            }
        }
        v1 = new GraphVoieChangement(!estGD, false) ;
        this.topologie.get(this.stations[this.stations.length - 1]).
                setPrecedent(prec);
        this.topologie.get(this.stations[this.stations.length - 1]).
                setSuivant(this.indiceVoie); 
        this.Voies.add(v1) ;               
//        prec = this.indiceVoie ;
        this.indiceVoie++ ;        
        
        
//for (int idx = 0 ; idx < this.Voies.size() ; idx++)
//{
//    System.out.println("GraphVoie : " + idx + " - " + 
//            this.Voies.get(idx).getEstGD() + " - " + 
//            this.Voies.get(idx).getClass().getName());
//}
        int colonne ;

        /*
         * Création du dépot
        */
        GridPane.setConstraints(dep, 1, 0); 
        root.getChildren().add(dep) ;    
        GridPane.setConstraints(this.Voies.get(0), 2, 0);   
        root.getChildren().add(this.Voies.get(0));
//    System.out.println("\n\nVoie : 0 - " + this.Voies.get(0).getEstGD() + " - " + this.Voies.get(0).getClass().getName());
        
        colonne = 3 ;        
        estGD = true ;
        ligne = 0 ;
        
        
        for (int station=0 ;station < this.stations.length ; station++)
        {
            GraphStation st = this.topologie.get(this.stations[station]).getStation() ;
            GridPane.setConstraints(st, colonne, ligne);
            root.getChildren().add(st) ;
            colonne = (estGD?colonne+1:colonne-1) ;            
            
            GraphVoie voie = this.Voies.get(station+1);
//    System.out.println("GraphVoie : " + (station+1) + " - " + 
//            this.Voies.get(station+1).getEstGD() + " - " + 
//            this.Voies.get(station+1).getClass().getName());
            
            GridPane.setConstraints(voie, colonne, ligne);
            root.getChildren().add(voie) ;
            colonne = (estGD?colonne+1:colonne-1) ;            
            
            if (colonne > nbStationsLigne*2)
            {
                estGD = false ;
                ligne++ ;
                colonne = colonne-2 ;            
         
            }
            else if (colonne < 0)
            {
                estGD = true ;
                ligne++ ;
                colonne = colonne+2 ;                            
            }
            
        }       

        double longueurCadre ;
        double largeurCadre ;
        
        // Création de la contrainte sur la première colonne contenant les virages
        root.getColumnConstraints().add( 
                new ColumnConstraints(dep.getWidth(), dep.getWidth(), 
                        dep.getWidth())) ;
        root.getColumnConstraints().get(0).setHgrow(Priority.NEVER); 
        root.getColumnConstraints().get(0).setHalignment(HPos.RIGHT);         
        longueurCadre = dep.getWidth() ;
        
        for (int idx = 1 ; idx < nbStationsLigne*2 ; idx += 2)
        {
            // Création de la contrainte sur les stations
            root.getColumnConstraints().add( 
                new ColumnConstraints(dep.getWidth(), dep.getWidth(), 
                        dep.getWidth())) ;
            root.getColumnConstraints().get(idx).setHgrow(Priority.NEVER); 
            root.getColumnConstraints().get(idx).setHalignment(HPos.LEFT);
            longueurCadre += dep.getWidth() ;            
            
            // Création de la contrainte sur les voies
            root.getColumnConstraints().add( 
                new ColumnConstraints(v1.getWidth(), v1.getWidth(), 
                        v1.getWidth())) ;
            root.getColumnConstraints().get(idx + 1).setHgrow(Priority.NEVER); 
            root.getColumnConstraints().get(idx + 1).setHalignment(HPos.LEFT); 
            longueurCadre += v1.getWidth() ;            
        }                   
        
        for (int idx = 0 ; idx <= ligne ; idx++)
        {
            root.getRowConstraints().add( 
                    new RowConstraints(dep.getHeight(), dep.getHeight(),
                            dep.getHeight())) ;
            root.getRowConstraints().get(idx).setVgrow(Priority.NEVER); 
            root.getRowConstraints().get(idx).setValignment(VPos.TOP);            
        }        
        
        largeurCadre = dep.getHeight()*(ligne + 3) ;
        ButtonBar bts = new ButtonBar() ;
        this.demarrer = new Button("Demarrer...") ;
        this.demarrer.setId("dem");
        this.arreter = new Button("Arreter...") ;
        this.arreter.setId("arr");
        this.tester = new Button("Tester...") ;
        this.tester.setId("tes");
        
//        bts.
        bts.getButtons().setAll(this.demarrer, this.arreter) ;
        GridPane.setConstraints(bts, 1 , ligne +1);
        root.getChildren().add(bts);
        this.demarrer.setOnAction(this::caseHandler) ;
        this.arreter.setOnAction(this::caseHandler) ;
        
        GridPane.setConstraints(this.tester, nbStationsLigne*2, ligne +1);        
        root.getChildren().add(this.tester);        
        this.tester.setOnAction(this::caseHandler) ;
//        largeurCadre += bts.getHeight() ;
        
//root.setGridLinesVisible(true);   
               
        Scene scene = new Scene(root, longueurCadre, largeurCadre);
        stage.setTitle("Metro");
        stage.setResizable(false);
        stage.setScene(scene);
        Platform.setImplicitExit(true);
        stage.setOnCloseRequest(t->System.exit(0));        
        stage.show();
    }
    
    public void caseHandler (Event evt)
    {
        if (evt.getTarget() instanceof Button)
        {
            Button bt = (Button) evt.getTarget() ;
            if ("dem".equals(bt.getId()))            
            {
                this.demarrer.setDisable(true);
                this.tester.setDisable(true);
                this.Voies.stream().map((voie) ->
                {
                    voie.setFeuVert(1);
                    return voie;
                }).forEachOrdered((voie) ->
                {
                    voie.setFeuVert(2);
                });
                this.ecouteurs.actionEcouteurDemarrer() ;
                System.out.println("Démarrer...");
            }
            else if ("arr".equals(bt.getId()))
            {
                this.ecouteurs.actionEcouteurArreter();
                this.arreter.setDisable(true); 
            }
            else
            {
                this.demarrer.setDisable(true);
                this.tester.setDisable(true);
                this.arreter.setDisable(true);                
                this.Voies.stream().map((voie) ->
                {
                    voie.setFeuVert(1);
                    return voie;
                }).forEachOrdered((voie) -> voie.setFeuVert(2));
                System.out.println("Tester...");
                bt.setDisable(true);
                this.afficherTester() ;             
            }
        }
        else if (evt.getTarget() instanceof Stage)
        {
            this.demarrer.setDisable(false);
            this.tester.setDisable(false);
            this.arreter.setDisable(false);            
        }
    }
    
    public void afficherTester() {
        try 
        {
           // Load person overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(AfficheurMetro.class.
                   getResource("FenetreTest.fxml"));
           AnchorPane testeur = (AnchorPane) loader.load();
           Stage dialogStage = new Stage();
           dialogStage.setTitle("Outil de test");
           dialogStage.initModality(Modality.NONE);
           dialogStage.initOwner(this.stagePrincipal);
           Scene scene = new Scene(testeur);
           dialogStage.setScene(scene);

           // Set the persons into the controller.
           FenetreTestController controller = loader.getController();
           controller.setListeStation(this.stations);
           controller.setAfficheur(this);
           dialogStage.setOnCloseRequest(this::caseHandler);
           dialogStage.setResizable(false);
           dialogStage.show();
//            controller.setMainApp(this);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void modifierAffichage(
            final String nomStationDepart, 
            final int voieDepart, 
            final String nomStationArrivee, 
            final int voieArrivee,             
            final String numRame)
    {
        Platform.runLater(() ->
        {
            AfficheurMetro.this.positionnerRame(
                    nomStationDepart, voieDepart, "");
            AfficheurMetro.this.positionnerRame(
                    nomStationArrivee, voieArrivee, numRame);
        }) ;
    }
    
    public void positionnerRame(
            final String nomStation, final int voie, final String nomRame)
    {
        Noeud noeud = this.topologie.get(nomStation) ;
        int numVoieRouge = voie ;
        
        int voieCourante = (voie == 1?
                            (noeud.getPrecedent() == -1?noeud.getSuivant():
                                                        noeud.getPrecedent()):
                            (noeud.getSuivant() == -1?noeud.getPrecedent():
                                                      noeud.getSuivant())) ;
        
        if (voieCourante != -1)
        {
            if (nomRame.isEmpty()) 
            {
                this.Voies.get(voieCourante).setFeuVert(numVoieRouge);
            }
            else
            {
                this.Voies.get(voieCourante).setFeuRouge(numVoieRouge);
            }
        }
        noeud.getStation().setRame(voie, nomRame);
    }
    
    public void eteindreFeux()
    {
        this.demarrer.setDisable(false);
        this.tester.setDisable(false);
        this.arreter.setDisable(false); 
        this.Voies.stream().forEachOrdered((voie) -> voie.setFeuxEteint()) ;        
    }

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args)
//    {
//        launch(args);
//    }
}
