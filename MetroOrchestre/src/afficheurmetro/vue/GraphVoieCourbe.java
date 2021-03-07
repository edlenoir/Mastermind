/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afficheurmetro.vue;

import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

/**
 *
 * @author millan
 */
public class GraphVoieCourbe extends GraphVoie
{
    private static final int POS_FEU_VERTH_X = 0 ;
    private static final int POS_FEU_VERTH_Y = 20 ;
    private static final int POS_FEU_VERTB_X = 0 ;
    private static final int POS_FEU_VERTB_Y = 88 ;     

    public GraphVoieCourbe(final boolean droit)
    {
        super(RacineReseau.TAILLE_VOIE +
              RacineReseau.DISTANCE_INTER_ELT * 3/2 +
              RacineReseau.LARG_STATION, 
              (GraphStation.LARG_STATION*2+RacineReseau.TAILLE_VOIE + RacineReseau.DISTANCE_INTER_ELT)*2 + 20,
              droit) ; 
        this.tracer();
    }
    
   @Override
   protected void dessiner()
   {
       this.tracer() ;
   } 
    
    private void tracer()
    {
        int Gdiametre =  RacineReseau.TAILLE_VOIE +
                         RacineReseau.DISTANCE_INTER_ELT * 3 +
                         RacineReseau.LARG_STATION * 2 ;

        int Pdiametre =  RacineReseau.TAILLE_VOIE +
                         RacineReseau.DISTANCE_INTER_ELT +
                         RacineReseau.LARG_STATION * 2 ; 
        
        int Gorigine = (this.getEstGD()?-Gdiametre/2:RacineReseau.TAILLE_VOIE/2) ;
        int Porigine = (this.getEstGD()?-Pdiametre/2:Pdiametre/2) ;
        int sens = (this.getEstGD()?-180:180) ;
        int F1x = (this.getEstGD()?
                GraphVoieCourbe.POS_FEU_VERTH_X:
                RacineReseau.TAILLE_VOIE +
                RacineReseau.DISTANCE_INTER_ELT * 3/2 +
                RacineReseau.LARG_STATION - GraphVoie.DIAM_FEU*2) ;
        int F2x = (this.getEstGD()?
                GraphVoieCourbe.POS_FEU_VERTB_X:
                RacineReseau.TAILLE_VOIE +
                RacineReseau.DISTANCE_INTER_ELT * 3/2 +
                RacineReseau.LARG_STATION - GraphVoie.DIAM_FEU*2) ; 
        
        int F1y = (this.getEstGD()?
                GraphVoieCourbe.POS_FEU_VERTH_Y:0) ;
        int F2y = (this.getEstGD()?
                GraphVoieCourbe.POS_FEU_VERTB_Y:
                Porigine + Pdiametre - 4) ;         
        
        
        this.getGraphicsContext2D().setStroke(GraphVoie.COULEUR_RAIL);
        this.getGraphicsContext2D().setLineWidth(GraphVoie.TAILLE_VOIE);
        this.getGraphicsContext2D().strokeArc(Gorigine, 
                GraphVoie.VOIEH_Y, 
                Gdiametre, 
                Gdiametre, 
                90.0, 
                sens,
                ArcType.OPEN);
        this.getGraphicsContext2D().strokeArc(
                Porigine, 
                GraphVoieDouble.VOIEH_Y + RacineReseau.DISTANCE_INTER_ELT, 
                Pdiametre, 
                Pdiametre, 
                90.0, 
                sens,
                ArcType.OPEN); 
        
        if (this.getCouleurFeux()[0]==Color.GREEN)
        {
            this.getGraphicsContext2D().setFill (this.getCouleurFeux()[0]) ;
            this.getGraphicsContext2D().fillOval(F1x,
                F1y, GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;
            this.getGraphicsContext2D().setFill (Color.BLACK) ;
            this.getGraphicsContext2D().fillOval(F1x + GraphVoieDouble.DIAM_FEU, 
                F1y, GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;
        }
        else
        {
            this.getGraphicsContext2D().setFill (Color.BLACK) ;
            this.getGraphicsContext2D().fillOval(F1x, F1y, 
                    GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;
            this.getGraphicsContext2D().setFill (this.getCouleurFeux()[0]) ;
            this.getGraphicsContext2D().fillOval(F1x + GraphVoie.DIAM_FEU, 
                F1y, GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;            
        }  
        if (this.getCouleurFeux()[1]==Color.GREEN)
        {
            this.getGraphicsContext2D().setFill (this.getCouleurFeux()[1]) ;
            this.getGraphicsContext2D().fillOval(F2x,
                F2y, GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;
            this.getGraphicsContext2D().setFill (Color.BLACK) ;
            this.getGraphicsContext2D().fillOval(F2x + GraphVoie.DIAM_FEU, 
                F2y, GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;
        }
        else
        {
            this.getGraphicsContext2D().setFill (Color.BLACK) ;
            this.getGraphicsContext2D().fillOval(F2x,
                F2y, GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;
            this.getGraphicsContext2D().setFill (this.getCouleurFeux()[1]) ;
            this.getGraphicsContext2D().fillOval(F2x + GraphVoie.DIAM_FEU, 
                F2y, GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;            
        }         
    }
    
    @Override
    public void setFeuVert(final int numVoie)
    {
        int posRelatifSensDep = ((numVoie==2)?
                                 PositionFeu.positionHaute.ordinal():
                                 PositionFeu.positionBasse.ordinal()) ;        
        this.getCouleurFeux()[posRelatifSensDep] = Color.GREEN ;
        this.dessiner();
    }

    @Override
    public void setFeuRouge(final int numVoie)
    {
        int posRelatifSensDep = ((numVoie==2)?
                                  PositionFeu.positionHaute.ordinal():
                                  PositionFeu.positionBasse.ordinal()) ;        
        
        this.getCouleurFeux()[posRelatifSensDep] = Color.RED ;
        this.dessiner() ;
    }    
}
