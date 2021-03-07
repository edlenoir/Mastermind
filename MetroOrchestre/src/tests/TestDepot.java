package tests;

import java.rmi.RemoteException;
import metroorchestre.DepotImpl;
import metroorchestre.Rame;
import metroorchestre.RameImpl;
import metroorchestre.Station;

/**
 *
 * @author millan
 */
public class TestDepot
{
    /**
     * Teste le constructeur de la classe Depot et la methode getNom
     */    
    private void testDepotImpl() 
    {
        try
        {
            Station st = new DepotImpl("Depot") ;
            System.out.println("Ok creation de la classe DepotImpl");
            if (!"Depot".equals(st.getNom()))
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
     * Teste les methodes demarrerRame
     */ 
    private void testDemarrerRame (final Rame r) 
    {
        try
        {
            Station dp = new DepotImpl("Test") ;
            System.out.println("Test ajouterStationSuivante...");
            dp.ajouterStationSuivante("localhost", 9999, 1, "St2", 1);
            
            dp.setRame(1, r) ;
            System.out.println("Ok methode setRame");                                                            
            
            dp.allumerFeuRouge(1);
            System.out.println("Ok methode allumerFeuRouge");                                                            
            
            dp.demarrerRame(1) ; 
            while (dp.getNumeroVoie(r) != -1)
            {
                Thread.yield() ;
            }
            System.out.println("Ok methode demarrerRame");                                                            
        }
        catch (Throwable ex)
        {
            System.out.println("NOk");                                                
            ex.printStackTrace() ;
        }          
    }    

     /**
     * Teste la methodesetRame 
     */   
    private void testSetRame() 
    { 
        try
        {
            Station st = new DepotImpl("Depot") ;
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
     * Teste la methode getNumeroVoie
     */       
    private void testGetNumeroVoie ()
    {
        try
        {
            Station dp = new DepotImpl("Depot") ;
            Rame r0 = new RameImpl(0) ;
            Rame r1 = new RameImpl(1) ;
            Rame r2 = new RameImpl(2) ;
            dp.setRame(1, r0);
            System.out.println("Ok methode setRame");                                                            
            dp.setRame(1, r1);
            System.out.println("Ok methode setRame");                                                                        
            if (dp.getNumeroVoie(r0) != 1)
            {
                throw new RuntimeException ("La rame r0 devrait etre sur "
                        + "la voie 1") ;  
            }
            System.out.println("Ok methode getNumeroVoie");                                                                        
            if (dp.getNumeroVoie(r1) != 1)
            {
                throw new RuntimeException ("La rame r1 devrait etre sur "
                        + "la voie 1") ;  
            }            
            System.out.println("Ok methode getNumeroVoie");                                                                        

            if (dp.getNumeroVoie(r2) != -1)
            {
                throw new RuntimeException ("La rame r2 ne devrait pas être "
                        + "sur une voie") ;  
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
     * Teste la methode estFeuVert 
     */    
    private void testEstFeuVert() 
    {
        try
        {
            Station dp = new DepotImpl("Depot") ;        
            if (!dp.estFeuVert(1))
            {
                throw new RuntimeException ("Le feu doit toujorus etre vert.") ;             
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace() ;
        }
    }  

    public static final void main(final String[] argv)
    {
        creationObjetsDistribuesTests.testInitialisation() ;
                    
        TestDepot test = new TestDepot () ;
        System.out.println("Tests de la classe Station...\n\n") ;
         
        System.out.println("Test de du constructeur et de la methode getNom") ;
        test.testDepotImpl() ;
        
        System.out.println("Test de la methode getNumeroVoie") ;        
        test.testGetNumeroVoie() ;        
        
        System.out.println("Test de la methode testEstFeuVert") ;
        test.testEstFeuVert() ; 
        
        System.out.println("Test des methodes setRame et getRame") ;
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
