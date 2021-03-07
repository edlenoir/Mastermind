package communicationJoueur;

public interface TicTacToe extends java.rmi.Remote{
    public void propagerCoup(int ligne,int colonne) throws java.rmi.RemoteException;
}