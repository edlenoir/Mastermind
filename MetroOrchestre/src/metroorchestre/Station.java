package metroorchestre;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author millan
 */
public interface Station extends Remote
{   
    /**
     * Constante fournissant le nom des stations de métro
     */
    static final String[] STATIONS = {"Ramonville", "Paul Sab", "Fac Medecine", 
        "Rangueil", "Souselong", "St Agne", "Empalot", 
        "Marcel Langet", "St Michel"};
    /**
     * Retourne une chaîne contenant une représentation de la station. Cette
     * opération est similaire à toString mais elle est distante.
     * @return une représentation de la station
     * @throws RemoteException 
     */
    String  afficher () throws RemoteException ;
    
    /**
     * Retourne le nom de la station
     * @return le nom de la station
     * @throws RemoteException 
     */
    String getNom () throws RemoteException ;
        
    /**
     * Connecte deux stations entre elles.
     * @param machine nom de la machine utilisée pour simuler la station
     * @param port numéro de port où est enregistré la station sur la machine
     * <I>machine</I>
     * @param numeroVoieDepart numéro de la voie de la station repr�sent�e
     * par this que l'on va connecter à une nouvelle station
     * @param stationSuivante nom de la station à laquelle on veut 
     * connecter la station courante
     * @param voieSuivante numéro de la voie dans la station suivante à laquelle 
     * est connectée la voie de départ
     * @throws RemoteException
     * @throws NotBoundException
     * @throws MalformedURLException 
     */
    void ajouterStationSuivante (
            final String machine,
            final int port,             
            int numeroVoieDepart,
            String stationSuivante,
            int voieSuivante) 
            throws RemoteException, NotBoundException, MalformedURLException ;  
     
    /**
     * Permet de savoir si le feu de la voie dont le numéro est passé en 
     * paramètre est vert ou pas
     * @param numeroVoie numéro de la voie dont on veut savoir si le feu est vert
     * @return vrai si le feu est vert faux sinon
     * @throws RemoteException 
     */
    boolean estFeuVert (final int numeroVoie) throws RemoteException ;

    /**
     * Démarre la rame se trouvant sur la voie dont le numéro est passé en 
     * paramètre
     * @param numeroVoie numéro de la voie dont on veut démarrer la rame
     * @throws RemoteException 
     */    
    void demarrerRame (final int numeroVoie) throws RemoteException ;

    /**
     * Permet de savoir le nom de la station se trouvant à l'extrémité de la 
     * voie dont le numéro est passé en paramètre
     * @param numeroVoie numéro de la voie dont on veut connaitre la station
     * à l'extrèmité
     * @return le nom de la prochaine station
     * @throws RemoteException 
     */    
    String getNomStationSuivante(final int numeroVoie) throws RemoteException ;
    
    /**
     * Permet de savoir le numéro de la voie suivante se trouvant à l'extrémité  
     * de la voie dont le numéro est passé en paramètre. 
     * Par exemple, lorsqu'une rame se trouve à une extrémité de la ligne, elle
     * change de numéro de voie. Par exemple, la voie suivant la voie 1 est 
     * la voie 2.
     * @param numeroVoie numéro de la voie dont on veut connaitre le numéro de la 
     * voie suivante.
     * @return le numéro de la voie suivante
     * @throws RemoteException 
     */     
    int getNumeroVoieSuivante(final int numeroVoie) throws RemoteException ;

    /**
     * Permet de positionner une rame sur la voie dont le numéro est passé
     * en paramètre.
     * @param numeroVoie num�ro de la voie où on veut positionner la rame
     * @param rame rame que l'on veut positionner sur la voie numéro 
     * <I>numeroVoie</I>
     * @throws RemoteException 
     */    
    void setRame (final int numeroVoie, final Rame rame) 
            throws RemoteException ;

    /**
     * Positionne le feu se trouvant sur la voie passée en paramètre à rouge
     * @param numeroVoie numéro de la voie dont on veut passer le feu à rouge
     * @throws RemoteException 
     */
    void allumerFeuRouge (final int numeroVoie) throws RemoteException ;
        
    /**
     * Retourne le numéro de la voie où se trouve la rame passée en paramètre.
     * @param rame rame que l'on recherche
     * @return le numéro de la voie ; si la rame est dans la station ou -1 si la
     * rame n'y est pas.
     * @throws RemoteException 
     */
    int getNumeroVoie (final Rame rame) throws RemoteException ;
        
}