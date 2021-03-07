/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afficheurmetro.vue;

import javafx.scene.paint.Color;

/**
 *
 * @author millan
 */
public class GraphVoieDouble extends GraphVoie        
{
    private static final int POS_FEU_VERTH_X = 80 ;
    private static final int POS_FEU_VERTH_Y = 0 ;
    private static final int POS_FEU_VERTB_X = 0 ;
    private static final int POS_FEU_VERTB_Y = 20 ; 
       
    public GraphVoieDouble(final boolean droit)
    {
        super(GraphVoie.LONG, GraphVoie.LARG, droit) ;  
        this.tracer();        
    }
    
    /**
     *
     */
    @Override
    protected void dessiner()
    {
        this.tracer() ;
    }
    
    private void tracer()
    {
        this.getGraphicsContext2D().setStroke(GraphVoie.COULEUR_RAIL) ;
        this.getGraphicsContext2D().setLineWidth(GraphVoie.TAILLE_VOIE);        
        this.getGraphicsContext2D().strokeLine(GraphVoieDouble.VOIEH_X, GraphVoieDouble.VOIEH_Y, 
                GraphVoie.LONG, GraphVoieDouble.VOIEH_Y);
        
        this.getGraphicsContext2D().strokeLine(GraphVoieDouble.VOIEH_X, GraphVoieDouble.VOIEH_Y + RacineReseau.DISTANCE_INTER_ELT, 
                GraphVoie.LONG, GraphVoieDouble.VOIEH_Y + RacineReseau.DISTANCE_INTER_ELT);            
        
        if (this.getCouleurFeux()[0]==Color.GREEN)
        {
            this.getGraphicsContext2D().setFill (this.getCouleurFeux()[0]) ;
            this.getGraphicsContext2D().fillOval(GraphVoieDouble.POS_FEU_VERTH_X,
                GraphVoieDouble.POS_FEU_VERTH_Y, 
                GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;
            
            this.getGraphicsContext2D().setFill (Color.BLACK) ;
            this.getGraphicsContext2D().fillOval(GraphVoieDouble.POS_FEU_VERTH_X + GraphVoie.DIAM_FEU, 
                GraphVoieDouble.POS_FEU_VERTH_Y, 
                GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;
        }
        else
        {
            this.getGraphicsContext2D().setFill (Color.BLACK) ;
            this.getGraphicsContext2D().fillOval(GraphVoieDouble.POS_FEU_VERTH_X,
                GraphVoieDouble.POS_FEU_VERTH_Y, 
                GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;
            
            this.getGraphicsContext2D().setFill (this.getCouleurFeux()[0]) ;
            this.getGraphicsContext2D().fillOval(GraphVoieDouble.POS_FEU_VERTH_X + GraphVoie.DIAM_FEU, 
                GraphVoieDouble.POS_FEU_VERTH_Y, GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;            
        }  
        
        if (this.getCouleurFeux()[1]==Color.GREEN)
        {
            this.getGraphicsContext2D().setFill (this.getCouleurFeux()[1]) ;
            this.getGraphicsContext2D().fillOval(GraphVoieDouble.POS_FEU_VERTB_X,
                GraphVoieDouble.POS_FEU_VERTB_Y, 
                GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;
            
            this.getGraphicsContext2D().setFill (Color.BLACK) ;
            this.getGraphicsContext2D().fillOval(GraphVoieDouble.POS_FEU_VERTB_X + GraphVoie.DIAM_FEU, 
                GraphVoieDouble.POS_FEU_VERTB_Y, 
                GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;
        }
        else
        {
            this.getGraphicsContext2D().setFill (Color.BLACK) ;
            this.getGraphicsContext2D().fillOval(GraphVoieDouble.POS_FEU_VERTB_X,
                GraphVoieDouble.POS_FEU_VERTB_Y, 
                GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;
            
            this.getGraphicsContext2D().setFill (this.getCouleurFeux()[1]) ;
            this.getGraphicsContext2D().fillOval(GraphVoieDouble.POS_FEU_VERTB_X + GraphVoie.DIAM_FEU, 
                GraphVoieDouble.POS_FEU_VERTB_Y, 
                GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;            
        }         
    }
}
