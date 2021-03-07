package communicationJoueur;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.AlreadyBoundException;

public class TicTacToeServer {
    
    public static void main(String[] args) throws RemoteException {

        TicTacToeImpl objetDist = new TicTacToeImpl(args[2], args[3], Boolean.valueOf(args[4]));
        try {
            java.rmi.registry.LocateRegistry.createRegistry(Integer.valueOf(args[0]));
            java.rmi.Naming.bind("rmi://"+args[1]+":"+args[0]+"/joueur", (TicTacToe)objetDist);
        } catch (NumberFormatException | RemoteException | AlreadyBoundException | MalformedURLException ex) {
                ex.printStackTrace();
        }
    }
}