/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import afficheurmetro.MetroSuperviseurIHM;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author millan
 */
public class MetroSuperviseurBouchon extends UnicastRemoteObject 
            implements MetroSuperviseurIHM
{
    /**
     * @param args the command line arguments
     */
 
    /** Pour éviter un warning venant du JFrame */
    private static final long serialVersionUID = -8123406571694511514L;          
    
    public MetroSuperviseurBouchon () throws RemoteException
    {
        
    }

    @Override
    public void modifierAffichage(String nomStationDepart, int voieDepart, 
            String nomStationArrivee, int voieArrivee, String numRame) 
            throws RemoteException
    {

    }

    @Override
    public void eteindreFeux() throws RemoteException
    {

    }
    
}
