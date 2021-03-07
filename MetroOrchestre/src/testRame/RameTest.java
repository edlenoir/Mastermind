package testRame;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import metroorchestre.Rame;

public class RameTest {
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException, InterruptedException {
		Rame rame =(Rame) Naming.lookup("rmi://localhost:9000/rame");
		rame.departImminent();
		System.out.println("Est prete à partir : " + rame.estRamePreteAPartir());
		rame.demarrer();
		System.out.println("A demarrer");
		rame.fermerPorte();
		System.out.println("A Fermer porte");
		rame.actionnerMoteur(500);
		System.out.println("Ouvrir porte");
		rame.ouvrirPorte();
		Thread.sleep(300);
		System.out.println("Depart iminant de : "+rame.getNumero());
		rame.departImminent();
	}
}
