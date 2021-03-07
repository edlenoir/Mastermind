/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import metroorchestre.Rame;
import metroorchestre.RameImpl;
import metroorchestre.Voie;

/**
 *
 * @author millan
 */
public class TestVoie1
{  
    /**
     * Teste le constructeur de la classe Voie et la methode estVert()
     * @throws NotBoundException 
     * @throws IOException 
     * @throws RemoteException 
     */
    private void testVoie() throws RemoteException, IOException, NotBoundException
    {
        Voie voie = new Voie(20) ;
        System.out.println("Ok creation voie");                                                                    
        
        if (!voie.estVert())
        {
            throw new RuntimeException ("Le feu n'est pas initialise à vert") ;
        }  
        System.out.println("Ok methode estVert");                                                                    
    }
        
    /**
     * Teste des méthodes setRame et getRame

     */
    private void testSetGetRame ()
    {
        try
        {
            Rame rame = new RameImpl(1) ;
            Voie voie = new Voie(10) ;
            
            voie.setRame(rame);
            System.out.println("Ok methode setRame");                                                                    

            if (voie.getRame()!=rame)
            {
                throw new RuntimeException ("La rame passee en parametre de la "
                        + "methode setRame n'est pas la même que celle "
                        + "retournee par la methode getRame") ;                
            }
            System.out.println("Ok methode getRame");                                                            
        }        
        catch (Throwable ex)
        {
            System.out.println("NOk");                                                            
            ex.printStackTrace() ;
        }
    }
    
    /**
     * Teste les methodes setRame et estRamePresente
     */
    private void testEstRamePresente()
    {
        try
        {
            Rame rame = new RameImpl(1) ;
            Voie voie = new Voie(10) ;
            if (voie.estRamePresente(rame))
            {
                throw new RuntimeException ("le resultat de la methode "
                        + "estRamePresente ne retourne pas la bonne valeur "
                        + "La valeur attendue est faux et la valeur retournee "
                        + "est vrai") ;                  
            }
            System.out.println("Ok methode estRamePresente");                                                
            
            voie.setRame(rame);
            System.out.println("Ok methode setRame");                                                
            
            if (!voie.estRamePresente(rame))
            {
                throw new RuntimeException ("le resultat de la methode "
                        + "estRamePresente ne retourne pas la bonne valeur "
                        + "La valeur attendue est vrai et la valeur retournee "
                        + "est faux") ;                  
            }
            System.out.println("Ok methode estRamePresente");                                                            
        }
        catch (Throwable ex)
        {
            System.out.println("NOk");                                                            
            ex.printStackTrace() ;
        }
    }    

    /**
     * Teste les methodes suivantes allumerFeuVert, allumerFeuRouge et estVert
     * @throws NotBoundException 
     * @throws IOException 
     * @throws RemoteException 
     */
    private void testAllumerFeu () throws RemoteException, IOException, NotBoundException
    {
        Voie voie = new Voie(10) ;
        voie.allumerFeuRouge();
        System.out.println("Ok methode allumerFeuRouge");                                                

        if (voie.estVert())
        {
            throw new RuntimeException ("Le feu devrait être rouge et là "
                    + "il est vert.") ;                  
        }
        System.out.println("Ok methode estVert");                                                            
        voie.allumerFeuVert();
        System.out.println("Ok methode allumerFeuVert");                                                            
        
        if (!voie.estVert())
        {
            throw new RuntimeException ("Le feu devrait être vert et là "
                    + "il est rouge.") ;                  
        }
        System.out.println("Ok methode estVert");                                                            
    }
    
    /**
     * Teste les methodes ajouterStationSuivante, getNomStationSuivante, 
     * getNumeroVoieSuivante
     */    
    private void testAjouterStationSuivante ()
    {
        try
        {
            Voie voie = new Voie(10) ;
            System.out.println("Test ajouterStationSuivante...");
            voie.ajouterStationSuivante("localhost", 9999, "St2", 2);
            System.out.println("Ok methode ajouterStationSuivante \n"
                    + "Test getNomStationSuivante...");            
            if (!creationObjetsDistribuesTests.getNomStation("St2").equals(
                    voie.getNomStationSuivante()))
            {
                throw new RuntimeException ("Le nom de la station suivante "
                        + "n'est pas celui attendu") ;                  
            }
            System.out.println("Ok methode getNomStationSuivante \n"
                    + "Test getNumeroVoieSuivante...");                        
            if (voie.getNumeroVoieSuivante()!=2)
            {
                throw new RuntimeException ("Le numero de la voie suivante "
                        + "n'est pas le même que celui attendu") ;                  
            }  
            System.out.println("Ok methode getNumeroVoieSuivante");                                    
        }
        catch (Throwable ex)
        {
            System.out.println("NOk");                                                
            ex.printStackTrace() ;
        }        
    }
    
    /**
     * Teste la methode demarrerRame
     */
    private void testDemarrerRame (final Rame r)
    {
        try
        {
            Voie voie = new Voie(10) ;
            voie.ajouterStationSuivante("localhost", 9999, "St2", 1);            
            voie.setRame(r) ;
            voie.allumerFeuRouge();
            voie.demarrerRame() ; 
            while (!voie.estVert())
            {
                Thread.yield() ;
            }
            if (voie.getRame() != null)
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
           
    public static final void main(final String[] argv) throws RemoteException, IOException, NotBoundException
    {
        creationObjetsDistribuesTests.testInitialisation() ;
            
        TestVoie1 test = new TestVoie1 () ;
        System.out.println("Tests de la classe voie...\n\n") ;
         
        System.out.println("Test de du constructeur et de la methode estVert") ;
        test.testVoie() ;
        
        System.out.println("Test des methodes setRame et getRame") ;
        test.testSetGetRame() ; 
        
        System.out.println("Test de la methode estRamePresente") ;
        test.testEstRamePresente();
        
        System.out.println("Test des methodes alumerFeuRouge, alumerFeuVert et "
                + "estVert") ;
        test.testAllumerFeu();
        
        System.out.println("Test des methodes ajouterStationSuivante, "
                + "getNomStationSuivante, getNumeroVoieSuivante") ;        
        test.testAjouterStationSuivante() ;
        
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
