package testRame;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import metroorchestre.Rame;
import metroorchestre.RameImpl;

public class RameServer {
	

	public static void main(String[] args) throws RemoteException, MalformedURLException {
        RameImpl rame = new RameImpl(1);
        java.rmi.registry.LocateRegistry.createRegistry(9000);
        try {
            java.rmi.Naming.rebind("rmi://localhost:9000/rame", (Rame) rame);
        } catch (RemoteException e) {
            e.printStackTrace();
       
    }
    
}
}
