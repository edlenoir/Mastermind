package metroorchestre;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * interface simulant les rames de métro. Chaque rame est un objet distribué.
 * 
 * @see RameImpl
 * @author millan
 */
public interface Rame extends Remote {
	/**
	 * Enumératif permettant de gérer l'automate qui régit le fonctionnement des
	 * rames
	 */
	enum ETAT {
		/**
		 * La rame est à l'arrêt
		 */
		RAME_ARRETEE,

		/**
		 * Les portes de la rame sont ouvertes
		 */
		PORTE_OUVERTE,

		/**
		 * Les portes de la rame sont fermées
		 */
		PORTE_FERMEE,

		/**
		 * Le signal sonnore retenti et les portes de la rame sont en train de se fermer
		 */
		DEPART_IMMINENT,

		/**
		 * La rame est en mouvement
		 */
		EN_ACTION
	};

	/**
	 * Declenche le processus de démarage de la rame... <B>Pre-conditions : </B>
	 * Etat = ETAT.DEPART_IMMINENT<BR>
	 * <B>Post-conditions : </B> Etat = ETAT.PORTE_FERMEE <BR>
	 * 
	 * @throws RemoteException
	 * @throws java.lang.InterruptedException
	 */
	public void demarrer() throws RemoteException, InterruptedException;

	/**
	 * Declenche la sonnerie de la rame indiquant la fermeture des portes et ferme
	 * les portes de celle-ci..<BR>
	 * Cette opération prend 500 unités de temps. <B>Pre-conditions : </B> Etat =
	 * ETAT.PORTE_FERMEE<BR>
	 * <B>Post-conditions : </B> Etat = ETAT.EN_ACTION <BR>
	 * 
	 * @throws RemoteException
	 * @throws java.lang.InterruptedException
	 */
	void fermerPorte() throws RemoteException, InterruptedException;

	/**
	 * Méthode simulant le déplacement d'une rame de métro. Pour ce faire le
	 * moteur sera actionné durant durée secondes.
	 * 
	 * @param duree temps simulant la durée du trajet entre les deux stations. Ce
	 *              temps dépend de la station de départ et de la station
	 *              d'arrivée.<BR>
	 *              <B>Pre-conditions : </B> Etat = ETAT.EN_ACTION <BR>
	 *              <B>Post-conditions : </B> Etat = ETAT.RAME_ARRETEE <BR>
	 * @throws InterruptedException
	 * @throws RemoteException
	 */
	void actionnerMoteur(final int duree) throws InterruptedException, RemoteException;

	/**
	 * Ouverture des portes de la rame. <B>Pre-conditions : </B> Etat =
	 * ETAT.RAME_ARRETEE<BR>
	 * <B>Post-conditions : </B> Etat = ETAT.PORTE_OUVERTE<BR>
	 * 
	 * @throws java.lang.InterruptedException
	 * @throws RemoteException
	 */
	void ouvrirPorte() throws InterruptedException, RemoteException;

	/**
	 * Lance la procédure de démarage de la rame. Elle fait passer l'état de la
	 * rame à DEPART_IMMINENT <BR>
	 * Cette opération ne gère pas le temps d'ouverture des porte. <BR>
	 * <B>C'est au programmeur de fixer cette durée dans l'application. </B> <BR>
	 * <B>Pre-conditions : </B> - <BR>
	 * <B>Post-conditions : </B> Etat = ETAT.DEPART_IMMINENT<BR>
	 * 
	 * @throws java.lang.InterruptedException
	 * @throws java.rmi.RemoteException
	 */
	void departImminent() throws InterruptedException, RemoteException;

	/**
	 * Retourne le numéro de la rame
	 * 
	 * @return le numéro de la rame
	 * @throws RemoteException
	 */
	int getNumero() throws RemoteException;

	/**
	 * Indique que la rame est prête à partir dès que la voie est libre
	 * 
	 * @return les portes sont closes.
	 * @throws RemoteException
	 */
	boolean estRamePreteAPartir() throws RemoteException;

}
