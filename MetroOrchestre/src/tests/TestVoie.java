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
public class TestVoie
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
     * Teste les méthodes setRame et getRame

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
     * Teste les methodes estRamePresente et setRame
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
     * Teste les methodes allumerFeuVert, allumerFeuRouge et estVert
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
     * Teste le cas ou on positionne deux rames sur la meme voie
     * @throws NotBoundException 
     * @throws IOException 
     */
    private void testCollision () throws IOException, NotBoundException
    {
        try
        {
            Rame rame1 = new RameImpl(1) ;
            Rame rame2 = new RameImpl(2) ;
            Voie voie = new Voie(10) ;
            
            voie.setRame(rame1);
            voie.setRame(rame2);

        }
        catch (RemoteException ex)
        {
            ex.printStackTrace() ;
        }
    }        
    
    public static final void main(final String[] argv) throws RemoteException, IOException, NotBoundException
    {
        TestVoie test = new TestVoie () ;
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
        
         System.out.println("\n\nFin tests de la classe voie...\n\n") ;
         
        System.out.println("Test de collision entre deux rames. Sortie du "
                + "main...") ;
        test.testCollision();        
    }
}
