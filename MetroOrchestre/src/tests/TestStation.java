/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.rmi.RemoteException;
import metroorchestre.Rame;
import metroorchestre.RameImpl;
import metroorchestre.Station;
import metroorchestre.StationImpl;

/**
 *
 * @author millan
 */
public class TestStation
{
    /**
     * Teste le constructeur de la classe Station et la methode getNom
     */
    private void testStationSuivante ()
    {
        try
        {
            Station st = new StationImpl("Essai1", 2) ;
            System.out.println("Ok creation de la classe StationImpl");
            if (!"Essai1".equals(st.getNom()))
            {
                throw new RuntimeException ("La methode getNom ne retourne "
                        + "pas la bonne valeur.") ;   
            }
            System.out.println("Ok methode getNom");            
        }
        catch (Throwable ex)
        {
            System.out.println("NOk");                           
            ex.printStackTrace() ;
        }
    }
        
    /**
     * Teste l'ajout d'une nouvelle voie au travers des methodes suivantes :
     * ajouterStationSuivante, getNomStationSuivante, getNumeroVoieSuivante
     */
    private void testAjouterStationSuivante () 
    {
       try
        {
            Station st = new StationImpl("Test", 2) ;
            System.out.println("Test ajouterStationSuivante...");
            st.ajouterStationSuivante("localhost", 9999, 1, "St2", 1);
            System.out.println("Ok methode ajouterStationSuivante \n"
                    + "Test nomStationSuivante...");        
            
            if (!creationObjetsDistribuesTests.getNomStation("St2").equals(
                    st.getNomStationSuivante(1)))
            {
                throw new RuntimeException ("Le nom de la station suivante "
                        + "n'est pas celui attendu") ;                  
                 
            }
            System.out.println("Ok methode nomStationSuivante \n"
                    + "Test numeroVoieSuivante...");   
            
            if (st.getNumeroVoieSuivante(1) != 1)
            {
                throw new RuntimeException ("Le numero de la voie suivante "
                        + "n'est pas le même que celui attendu") ;                
            }  
            System.out.println("Ok methode numeroVoieSuivante");                                    
        }
        catch (Throwable ex)
        {
            System.out.println("NOk");                                                
            ex.printStackTrace() ;
        }   
    }  
    
    /**
     * Teste la methode allumerFeuRouge et estFeuVert
     */
    private void testAllumerFeuRouge () 
    {
        try
        {
            Station st = new StationImpl("Essai1", 2) ;
            System.out.println("Ok methode allumerFeuRouge");                                                

            if (!st.estFeuVert(1))
            {
                throw new RuntimeException ("Le feu devrait être vert et là "
                        + "il est rouge.") ;                  
            }
            System.out.println("Ok methode estFeuVert"); 
            st.allumerFeuRouge(1);
            System.out.println("Ok methode allumerFeuRouge"); 
            
            if (st.estFeuVert(1))
            {
                throw new RuntimeException ("Le feu devrait être rouge et là "
                        + "il est vert.") ;                  
            }
            System.out.println("Ok methode estFeuVert");   
        }
        catch (Throwable ex)
        {
            System.out.println("NOk");               
            ex.printStackTrace() ;
        }
    }

    /**
     * Teste des methodes setRame et getNumeroVoie
     */   
    private void testSetRame ()
    {
        try
        {
            Station st = new StationImpl("Essai1", 2) ;
            System.out.println("Ok methode allumerFeuRouge"); 
            Rame r = new RameImpl(0) ;
            
            st.setRame(1, r);
            System.out.println("Ok methode setRame"); 

            if (st.getNumeroVoie(r) != 1)
            {
                throw new RuntimeException ("Le feu devrait être vert et là "
                        + "il est rouge.") ;                  
            }

            System.out.println("Ok methode getNumeroVoie");     
        }
        catch (Throwable ex)
        {
            System.out.println("NOk");                           
            ex.printStackTrace() ;
        }        
    }
    
    /**
     * Teste les methodes demarrerRame
     */    
    private void testDemarrerRame (final Rame r) 
    {
       try
        {
            Station st = new StationImpl("Test", 2) ;
            System.out.println("Test ajouterStationSuivante...");
            st.ajouterStationSuivante("localhost", 9999, 1, "St2", 1);
            
            st.setRame(1, r) ;
            st.allumerFeuRouge(1);
            st.demarrerRame(1) ; 
            while (!st.estFeuVert(1))
            {
                Thread.yield() ;
            }
            if (st.getNumeroVoie(r) != -1)
            {
                throw new RuntimeException ("La rame n'a pas bougé de sa voie "
                        + "de depart") ;                 
            }
            System.out.println("Ok methode demarrerRame");                                                            
        }
        catch (Throwable ex)
        {
            System.out.println("NOk");                                                
            ex.printStackTrace() ;
        }        
    }    
    
    public static final void main(final String[] argv)
    {
        creationObjetsDistribuesTests.testInitialisation() ;
            
        TestStation test = new TestStation () ;
        System.out.println("Tests de la classe Station...\n\n") ;
         
        System.out.println("Test de du constructeur et de la methode getNom") ;
        test.testStationSuivante() ;
        
        System.out.println("Test des methodes ajouterStationSuivante, "
                + "getNomStationSuivante, getNumeroVoieSuivante") ;        
        test.testAjouterStationSuivante() ;        
        
        System.out.println("Test des methodes setRame et getRame") ;
        test.testAllumerFeuRouge() ; 
        
        System.out.println("des methodes setRame et getNumeroVoie") ;
        test.testSetRame();
           
        System.out.println("Test de la methode demarreRame") ; 
        try
        {
            Rame rame = new RameImpl(0) ;
            
            test.testDemarrerRame(rame);
            while (!rame.estRamePreteAPartir())
            {
                Thread.yield() ;
            }
        }
        catch (RemoteException ex)
        {
            ex.printStackTrace();
        }
        System.out.println("\n\nFin tests de la classe voie...\n\n") ;
        System.exit (0) ;             
    }    
           
}
