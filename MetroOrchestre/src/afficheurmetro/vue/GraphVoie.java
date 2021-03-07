/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afficheurmetro.vue;

//import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author millan
 */
public abstract class GraphVoie extends RacineReseau
{
    protected static final int LONG = 100 ;
    protected static final int LARG = 40 ;

    protected static final int VOIEH_X = 0 ;
    protected static final int VOIEH_Y = 10 + RacineReseau.TAILLE_VOIE/2 ;
    protected static final int DIAM_FEU = 10 ;
    
    protected static final Color COULEUR_RAIL = Color.LIGHTBLUE ;
        
    private final Color[] couleurFeux ;
    
    private boolean estGD ;
    protected enum PositionFeu {positionHaute, positionBasse} ;
    
    public GraphVoie(final int longueur, final int largeur, final boolean droit)
    {
        super(longueur, largeur) ;
        this.estGD = droit ;
        this.couleurFeux = new Color[2] ;
        this.couleurFeux[0] = Color.BLACK ;
        this.couleurFeux[1] = Color.BLACK ; 
        double [] dou = {4.0, 10.0,};
        this.getGraphicsContext2D().setLineDashes(dou) ;        
    }
    
    public Color[] getCouleurFeux()
    {
        return this.couleurFeux ;
    }
    
    protected boolean getEstGD()
    {
        return this.estGD ;
    }
    
    protected void setEstGD(final boolean estGD)
    {
        this.estGD = estGD;
    }
    
    protected abstract void dessiner() ;
        
    public void setFeuVert(final int numVoie)
    {
//        int posRelatifSensDep = (this.estGD?numVoie-1:(numVoie==2)?0:1) ;        
        int posRelatifSensDep = this.calculPositionVoie(numVoie) ;

                
        this.getCouleurFeux()[posRelatifSensDep] = Color.GREEN ;
        this.dessiner();
    }

    public void setFeuRouge(final int numVoie)
    {
        int posRelatifSensDep = this.calculPositionVoie(numVoie) ;

        this.getCouleurFeux()[posRelatifSensDep] = Color.RED ;
        this.dessiner() ;
    }
    
    public void setFeuxEteint()
    {
        this.couleurFeux[0] = Color.BLACK ;
        this.couleurFeux[1] = Color.BLACK ; 
        this.dessiner() ;        
    }
    
    private int calculPositionVoie(final int numVoie)
    {
        int res ;
        if (this.estGD)
        {
            if (numVoie == 1)
            {
                res = PositionFeu.positionHaute.ordinal() ;
            }
            else
            {
                 res = PositionFeu.positionBasse.ordinal() ;               
            }
        }
        else
        {
            if (numVoie == 1)
            {
                res = PositionFeu.positionBasse.ordinal() ;
            }
            else
            {
                 res = PositionFeu.positionHaute.ordinal() ;               
            }            
        }
        return res ;        
    }
}
