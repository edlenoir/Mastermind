package metroorchestre;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import afficheurmetro.MetroSuperviseurIHM;

public class StationImpl extends UnicastRemoteObject implements Station {

	private String nom;
	private Voie[] nbVoie;

	public StationImpl(String nom, int nb) throws IOException, NotBoundException {
		this.nom = nom;
		this.nbVoie = new Voie[nb];
		for(int i = 1; i< nb ; i++) {
			this.nbVoie[i] = new Voie(10);
		} 

	}

	@Override
	public String afficher() throws RemoteException {
		return "La station" + this.nom;
	}

	@Override
	public String getNom() throws RemoteException {
		return this.nom;
	}

	@Override
	public void ajouterStationSuivante(String machine, int port, int numeroVoieDepart, String stationSuivante,
			int voieSuivante) throws RemoteException, NotBoundException, MalformedURLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean estFeuVert(int numeroVoie) throws RemoteException {
		return this.nbVoie[numeroVoie].estVert();
	}

	@Override
    public void demarrerRame(int numeroVoie) throws RemoteException {
        Voie voieSuivante = this.getVoie(numeroVoie);
        int numRame = voieSuivante.getRame().getNumero();
        try {
            voieSuivante.demarrer();
            MetroSuperviseurIHM ihm = (MetroSuperviseurIHM) java.rmi.Naming.lookup("rmi://localhost:9999/moniteur");
            ihm.modifierAffichage(this.getNom(), numeroVoie, voieSuivante.getNomStationSuivante(), voieSuivante.getNumeroVoieSuivante(), Integer.toString(numRame));
            
        } catch (MalformedURLException | NotBoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

	private Voie getVoie(int numeroVoie) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getNomStationSuivante(int numeroVoie) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumeroVoieSuivante(int numeroVoie) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRame(int numeroVoie, Rame rame) throws RemoteException {
		this.nbVoie[numeroVoie].setRame(rame);
	}

	@Override
	public void allumerFeuRouge(int numeroVoie) throws RemoteException {
		this.nbVoie[numeroVoie].allumerFeuRouge();

	}

	@Override
	public int getNumeroVoie(Rame rame) throws RemoteException {
		return 0;
	}

}
