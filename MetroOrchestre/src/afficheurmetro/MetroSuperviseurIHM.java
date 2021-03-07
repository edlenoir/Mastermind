package afficheurmetro;



import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Cette interface permet d'indiquer les déplacements des rames entre les 
 * différentes stations.
 * La machine qui supporte cette classe se trouve sur la même machine que 
 * celle supportant la classe Superviseur. L'objet distribué s'appelle 
 * <I>moniteur</I> et est enregistré sur le port <I>9999</I>.
 * @see MetroSuperviseurIHMImpl
 * @author millan
 */
public interface MetroSuperviseurIHM extends Remote
{
    /**
     * Cette interface permet de fournir les informations pour visualiser
     * le déplacement des rames de métro sur la ligne. Cette opération
     * pouvant être appelée depuis toutes les stations elle est synchronisée.
     * @param nomStationDepart nom de la station d'où part la rame
     * @param voieDepart numéro de la voie où se trouve la rame au départ
     * @param nomStationArrivee nom de la station vers laquelle la rame se
     * déplace
     * @param voieArrivee voie sur laquelle la rame se trouvera une 
     * fois arrivée dans la nouvelle station
     * @param numRame numéro de la rame que l'on veut afficher
     * @throws java.rmi.RemoteException
     */
    void modifierAffichage(
            final String nomStationDepart, 
            final int voieDepart, 
            final String nomStationArrivee, 
            final int voieArrivee,             
            final String numRame) throws RemoteException ;
   
    /**
     * Méthode permettant l'arrêt des feux. Cette méthode doit être la
     * dernière instruction exécutée une fois toutes les rames rentrées au
     * dépôt.
     * @throws RemoteException 
     */
    void eteindreFeux ()  throws RemoteException ;
}
