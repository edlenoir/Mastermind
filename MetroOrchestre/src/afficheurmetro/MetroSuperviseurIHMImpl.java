package afficheurmetro;


import afficheurmetro.vue.AfficheurMetro;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

/**
 *
 * @author millan
 */
public class MetroSuperviseurIHMImpl extends UnicastRemoteObject 
            implements MetroSuperviseurIHM
{
    /**
     * @param args the command line arguments
     */
 
    /** Pour éviter un warning venant du JFrame */
    private static final long serialVersionUID = -8123406571694511514L;
    
    private final AfficheurMetro fenetreGraphique ;
    
    /**
     * Permet à partir d'une liste de noms de station d'initialiser l'interface
     * graphique représentant le réseau.
     * <B>Attention : </B> le d�pot ne doit pas faire partie de cette liste. 
     * Pour simplifier on considèrera que le dépôt est toujours positionné
     * avant la première station.
     * @param ecouteurs ecouteur pemettant une action sur les boutons Demarrer
     * et arreter
     * @param stations liste des noms de station triés dans l'ordre où
     * elles doivent être connectées. 
     * @throws java.rmi.RemoteException 
     */
    public MetroSuperviseurIHMImpl(
            final EcouteurBoutons ecouteurs, 
            final String... stations) throws RemoteException
    {
        this.fenetreGraphique = new AfficheurMetro(ecouteurs, stations);   
        try 
        {
            // Seule manière publique actuelle de lancer les runtimes a la main.
            // Une autre manière de faire sera disponible dans le JDK 9.
            new JFXPanel();
            //
            this.fenetreGraphique.init();
            Platform.runLater(() -> 
            {
                try {
                    final Stage stage = new Stage();
                    stage.setResizable(false);
                    this.fenetreGraphique.start(stage);
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

    /**
     * Cette interface permet de fournir les informations pour visualiser
     * le déplacement des rames de métro sur la ligne. Cette opération
     * pouvant être appelée depuis toutes les stations elle est synchronisée.
     * @param nomStationDepart nom de la station d'où part la rame
     * @param voieDepart numéro de la voie où se trouve la rame au départ
     * @param nomStationArrivee nom de la station vers laquelle la rame se
     * déplace
     * @param voieArrivee voie sur laquelle la rame se trouvera la rame une 
     * fois arrivée dans la station d'arrivée
     * @param numRame numéro de la rame que l'on veut afficher
     * @throws java.rmi.RemoteException
     */
    @Override
    public void modifierAffichage(
            final String nomStationDepart, 
            final int voieDepart, 
            final String nomStationArrivee, 
            final int voieArrivee,             
            final String numRame) throws RemoteException
    {
        this.fenetreGraphique.modifierAffichage(
                nomStationDepart, voieDepart, nomStationArrivee, 
                voieArrivee, numRame);
    }

    /**
     * Méthode permettant de demander l'arrêt des feu lorsque le service
     * du métro est terminé. Cette méthode doit être la dernière instruction
     * exécuté
     * @throws RemoteException 
     */
    @Override
    public void eteindreFeux ()  throws RemoteException
    {
        this.fenetreGraphique.eteindreFeux () ;
    }
    
}
