/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import tests.MetroSuperviseurBouchon;
import afficheurmetro.MetroSuperviseurIHM;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import metroorchestre.Station;
import metroorchestre.StationImpl;

/**
 *
 * @author millan
 */
public class creationObjetsDistribuesTests
{
    public static void testInitialisation()
    {
        try 
        {
            java.rmi.registry.LocateRegistry.createRegistry(9999) ;
            Station station = new StationImpl("C'est la station 1", 2) ;
                java.rmi.Naming.bind("rmi://localhost:9999/St1", 
                        (Station) station) ; 
            Station station1 = new StationImpl("C'est la station 2", 2) ;
                java.rmi.Naming.bind("rmi://localhost:9999/St2", 
                        (Station) station1) ; 
            Station station2 = new StationImpl("C'est la station 3", 1) ;
                java.rmi.Naming.bind("rmi://localhost:9999/St3", 
                        (Station) station2) ; 
                
//                Rame rame = new RameImpl(0) ;
//                java.rmi.Naming.bind("rmi://localhost:9999/Rame0", 
//                        (Rame) rame) ;

        MetroSuperviseurIHM ihm = new MetroSuperviseurBouchon () ;       
        java.rmi.Naming.bind("rmi://localhost:9999/moniteur", ihm) ;
                
        }
        catch (RemoteException | AlreadyBoundException | MalformedURLException ex)
        {
            ex.printStackTrace() ;
        }

    }
    
    public static String getNomStation(final String nomStation)
    {
        String res ;
        if ("St1".equals(nomStation))
        {
            res = "C'est la station 1" ;
        }
        else  if ("St2".equals(nomStation))
        {
            res = "C'est la station 2" ;
        }
        else
        {
            res = "C'est la station 3" ;            
        }
        return res ;
    }
    
    public static void nettoyageRegistre()
    {
        try 
        {
            java.rmi.Naming.unbind("rmi://localhost:9999/St1") ; 
            java.rmi.Naming.unbind("rmi://localhost:9999/St2") ; 
            java.rmi.Naming.unbind("rmi://localhost:9999/St3") ; 

                
        }
        catch ( RemoteException | MalformedURLException | NotBoundException ex)
        {
            ex.printStackTrace() ;
        }        
    }
    
}
