/*
 * Cette interface contient deux méthodes qui servent à associer aux boutons
 * de l'interface graphique les actions associées au démarrage et à l'arrêt du 
 * service.
 */
package afficheurmetro;

/**
 *
 * @author millan
 */
public interface EcouteurBoutons
{
    /**
     * Méthode fournissant l'action associée au clic du bouton démarrer de
     * l'interface graphique.
     */
    void actionEcouteurDemarrer() ;
    
    /**
     * Méthode fournissant l'action associée au clic du bouton arrêter de
     * l'interface graphique.
     */    
    void actionEcouteurArreter() ;
}
