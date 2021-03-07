package communicationJoueur ;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TicTacToeImpl extends java.rmi.server.UnicastRemoteObject implements TicTacToe, tictactoe.GestionClicCase {

    private String adresseDistante;
    private String portDistant;
    private tictactoe.TicTacToeIHM ihm;
    
    public TicTacToeImpl(String adresseDistante, String portDistant, boolean estPremier) throws RemoteException {
        this.adresseDistante = adresseDistante;
        this.portDistant = portDistant;
        this.ihm = new tictactoe.TicTacToeIHMImpl(estPremier);
        this.ihm.setGestionnaireClic(this);
    }

    @Override
    public boolean traitementCaseCliquee(int lig, int col) {
        boolean res = false;
        if (!this.ihm.estBloque() && this.ihm.getContenuCase(lig, col).isEmpty()) {
            try {
                ((TicTacToe)java.rmi.Naming.lookup("rmi://"+this.adresseDistante+':'+this.portDistant+"/joueur")).propagerCoup(lig, col);
                res = true;
            } catch (RemoteException | MalformedURLException | NotBoundException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    @Override
    public void propagerCoup(int ligne, int colonne) throws RemoteException {
        this.ihm.jouerDistant(ligne, colonne);
        
    }
    
}