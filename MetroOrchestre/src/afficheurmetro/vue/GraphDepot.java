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
public class GraphDepot extends GraphStation
{        
    public GraphDepot (final boolean estGD)
    {
        super(estGD) ;
        this.tracer() ;
    }
    
    @Override
    public void Dessiner()
    {
        this.tracer() ;
    }
    
    private void tracer()
    {        
        this.getGraphicsContext2D().setFill (Color.DARKBLUE) ;

        this.getGraphicsContext2D().fillRect(0, 0, 
            GraphStation.LONG_STATION, 
            (RacineReseau.LARG_STATION + RacineReseau.TAILLE_VOIE)*2) ; 
        this.getGraphicsContext2D().setFill (Color.WHITE) ;        
    }  
    
    @Override
    public void setRame(final int numVoie, final String rame)
    {

    }    
}
