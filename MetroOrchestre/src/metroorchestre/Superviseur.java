package metroorchestre;

import afficheurmetro.EcouteurBoutons;
import afficheurmetro.MetroSuperviseurIHM;
import afficheurmetro.MetroSuperviseurIHMImpl;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


/**
 *
 * @author millan
 */
public class Superviseur implements EcouteurBoutons
{
   private enum ETAT {DEMARRAGE_METRO, EN_SERVICE, FIN_DE_SERVICE} ;
 
    
    /**
     * actionEcouteurDemarrer et actionEcouteurArreter. 
	 * <B>Ces méthodes sont 
     * fournies aux étudiants.</B>
     */    
    @Override
    public void actionEcouteurDemarrer()
    {
        Superviseur.this.gererLigne() ;
    }

    @Override
    public void actionEcouteurArreter()
    {
        Superviseur.this.arreterMetro() ;
    }    
    
}