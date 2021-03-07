package metroorchestre;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author millan
 */

public class RameImpl extends UnicastRemoteObject implements Rame
{  
    /**
     * Identifiant de la rame
     */
    private final int numRame ;
       
    /**
     * Etat da la rame
     */
    private Rame.ETAT etatRame ;
    
    /**
     * Construit une nouvelle rame dont l'identifiant est passé en 
     * paramètre.<BR>
     * <B>Pre-conditions : </B> - <BR>
     * <B>Post-conditions : </B> Etat = ETAT.DEPART_IMMINENT <BR>
     * @param numRame identifiant de la rame
     * @throws RemoteException
     */
    public RameImpl (int numRame) throws RemoteException
    {
        this.numRame = numRame ; 
        this.etatRame = Rame.ETAT.DEPART_IMMINENT ;
    }
    
    /**
     * Déclenche le processus de démarage de la rame...     
     * <B>Pre-conditions : </B> Etat = ETAT.DEPART_IMMINENT<BR>
     * <B>Post-conditions : </B> Etat = ETAT.PORTE_FERMEE <BR>
     * @throws RemoteException 
     * @throws java.lang.InterruptedException 
     */    
    @Override
    public void demarrer ()throws RemoteException, InterruptedException 
    {
        if (this.etatRame != Rame.ETAT.DEPART_IMMINENT)
        {
            throw new RuntimeException("La rame " + this.numRame + 
                    " doit etre prete a partir pour qu'elle soit demarree...") ;
        }         
        this.etatRame = Rame.ETAT.PORTE_FERMEE ;        
    }

    /**
     * Déclenche la sonnerie de la rame indiquant la fermeture des portes 
     * et ferme les portes de celle-ci..<BR>
     * Cette opération prend 500 unités de temps.
     * <B>Pre-conditions : </B> Etat = ETAT.PORTE_FERMEE<BR>
     * <B>Post-conditions : </B> Etat = ETAT.EN_ACTION <BR>
     * @throws RemoteException 
     * @throws java.lang.InterruptedException 
     */   
    @Override
    public void fermerPorte ()throws RemoteException, InterruptedException
    {
        if (this.etatRame != Rame.ETAT.PORTE_FERMEE)
        {
            throw new RuntimeException("La rame " + this.numRame + 
                    " doit etre prete a partir pour fermer ses portes...") ;
        }         
        Thread.sleep(500) ;
        System.out.println ("Rame " + this.numRame + 
                " declenche sont signal sonnore...") ;
        System.out.println ("Rame " + this.numRame + " ferme ses portes...") ;
        this.etatRame = Rame.ETAT.EN_ACTION ;        
    }    
    
    /**
     * Méthode simulant le déplacement d'une rame de métro. Pour ce faire
     * le moteur sera actionné durant durée secondes.
     * @param duree temps simulant la durée du trajet entre les deux stations.
     * Ce temps dépend de la station de départ et de la station d'arrivée.<BR>
     * <B>Pre-conditions : </B> Etat = ETAT.EN_ACTION <BR>
     * <B>Post-conditions : </B> Etat = ETAT.RAME_ARRETEE <BR>
     * @throws InterruptedException
     * @throws RemoteException 
     */  
    @Override
    public void actionnerMoteur (final int duree) 
            throws InterruptedException, RemoteException
    {
        if (this.etatRame != Rame.ETAT.EN_ACTION)
        {
            throw new RuntimeException("La rame " + this.numRame + 
                    " n'a pas les porte fermee pour partir...") ;
        }
        System.out.println ("Rame " + this.numRame + " demarree...") ;
        Thread.sleep (duree) ;
        this.etatRame = Rame.ETAT.RAME_ARRETEE ;
    }
    
    /**
     * Ouverture des portes de la rame. 
     * <B>Pre-conditions : </B>  Etat = ETAT.RAME_ARRETEE<BR>
     * <B>Post-conditions : </B> Etat = ETAT.PORTE_OUVERTE<BR>
     * @throws java.lang.InterruptedException
     * @throws RemoteException 
     */
    @Override
    public void ouvrirPorte () throws InterruptedException, RemoteException
    {
        if (this.etatRame != Rame.ETAT.RAME_ARRETEE)
        {
            throw new RuntimeException("La rame " + this.numRame + 
                    " doit etre arretee pour ouvrir les portes...") ;
        }        
        System.out.println ("Rame " + this.numRame + " ouvre ses portes...") ;
        this.etatRame = Rame.ETAT.PORTE_OUVERTE ;
    }
    
    /**
     * Indique que la rame est prète à partir dès que la voie est libre
     * @return les portes sont closes.
     * @throws RemoteException 
     */    
    @Override
    public boolean estRamePreteAPartir () throws RemoteException
    {
        return this.etatRame == Rame.ETAT.DEPART_IMMINENT ;
    }
    
    /**
     * Retourne le numéro de la rame
     * @return le numéro de la rame
     * @throws RemoteException 
     */    
    @Override
    public int getNumero () throws RemoteException
    {
        return this.numRame ;
    }
    
    /**
     * Retourne la représentation textuelle de la rame.<BR>
     * <B>Cette méthode n'est pas une méthode distribuée</B>
     * @return une chaîne de caractères représentant la rame
     */
    @Override
    public String toString()
    {
        return "Rame numero : " + this.numRame + '\n' ; 
    }
    
    /**
     * Compare de this à  l'objet o
     * @param o objet que l'on veut comparer
     * @return vrai si les this et o représentent le même objet et faux sinon
     */
    @Override
    public boolean equals(Object o)
    {
        boolean res ;
        if (o == null)
        {
            res = false ;
        }
        else if (this == o)
        {
            res = true ;
        }
        else if (o instanceof RameImpl)
        {
            res = this.numRame == ((RameImpl) o).numRame ;
        }
        else
        {
            res = false ;
        }
        return res ;
    }

    /**
     * @return code de hachage de l'objet
     */
    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 23 * hash + this.numRame;
        return hash;
    }

    /**
     * Lance la procédure de démarage de la rame. Elle fait passer
     * l'état de la rame à DEPART_IMMINENT <BR>
     * Cette opération ne gère pas le temps d'ouverture des porte. <BR>
     * <B>C'est au programmeur de fixer cette durée dans l'application.
     * </B><BR>
     * <B>Pre-conditions : </B> - <BR>
     * <B>Post-conditions : </B> Etat = ETAT.DEPART_IMMINENT<BR> 
     * @throws java.lang.InterruptedException
     * @throws java.rmi.RemoteException
     */
    @Override
    public void departImminent() throws InterruptedException, RemoteException 
    {
        System.out.println("La rame est prete a repartir...");
        this.etatRame = Rame.ETAT.DEPART_IMMINENT ;
    }
}