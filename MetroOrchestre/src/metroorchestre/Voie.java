package metroorchestre;

import java.rmi.RemoteException;

public class Voie implements java.lang.Runnable{

	private int tempsParcours;
	private Station stationSuivante;
	private Rame rame;
	//private int numero;
	private int VoieSuivante;
	
	private enum ETAT_FEU  {
			VERT,ROUGE
	}
	private ETAT_FEU feu;
	
	public Voie(int tempsParcours) {
		this.tempsParcours=tempsParcours;
		this.feu = ETAT_FEU.VERT;
		this.rame = null;
	}

	public boolean estVert() {
		return this.feu.equals(ETAT_FEU.VERT);
	}

	public void setRame(Rame rame) {
		this.rame = rame;
		
	}

	public Rame getRame() {
		return this.rame;
	}

	public boolean estRamePresente(Rame rame) {
		if(this.rame != null) {
		return this.rame.equals(rame);
		}
		return false;
	}

	public void allumerFeuRouge() {
		this.feu = ETAT_FEU.ROUGE;
		
	}

	public void allumerFeuVert() {
		this.feu = ETAT_FEU.VERT;
	}

	public void ajouterStationSuivante(String url, int port, String stationSuivante, int numVoie) throws java.rmi.NotBoundException,
    java.net.MalformedURLException,
    java.rmi.RemoteException {
		
		Station s = ((Station)java.rmi.Naming.lookup("rmi://"+url+":"+port+"/"+stationSuivante));
		this.VoieSuivante = numVoie;
		this.stationSuivante = s;
	}

	public int getNumeroVoieSuivante() throws RemoteException {
		return this.VoieSuivante;
	}



	public Object getNomStationSuivante() throws RemoteException {
		return this.stationSuivante.getNom();
	}

	public void demarrerRame() {
		GestionnaireRame  g = new GestionnaireRame();
		
		g.start();
	}

	@Override
	public void run() {
		Rame r = this.getRame();
		try {
			r.demarrer();
			r.fermerPorte();
		} catch (RemoteException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.rame = null;
		try {
			r.actionnerMoteur(this.tempsParcours);
		} catch (RemoteException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.allumerFeuVert();
		//this.
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			r.departImminent();
		} catch (RemoteException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public class GestionnaireRame extends Thread {

		public void start() {
			this.run();
		}
		
        public void run() {
        	Rame r = getRame();
        	try {
    			r.demarrer();
    			r.fermerPorte();
    		} catch (RemoteException | InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		setRame(null);
    		try {
    			r.actionnerMoteur(tempsParcours);
    		} catch (RemoteException | InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		allumerFeuVert();
    		//this.
    		
    		try {
    			Thread.sleep(3000);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		try {
    			r.departImminent();
    		} catch (RemoteException | InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
    }


	public void demarrer() {
		
		
	}

}