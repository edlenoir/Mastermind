/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afficheurmetro.vue;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author millan
 */
public class GraphStation extends RacineReseau
{
    protected static final int LONG_STATION = 50 ; 
    private final boolean estGD ;
    
    public GraphStation (final boolean estGD)
    {
        super (GraphStation.LONG_STATION, 
                RacineReseau.LARG_STATION*2+RacineReseau.TAILLE_VOIE + RacineReseau.DISTANCE_INTER_ELT) ;
        this.estGD = estGD ;
        this.tracer() ;
    }
    
    protected void Dessiner()
    {
        this.tracer();
    }
    
    private void tracer()
    {
        this.getGraphicsContext2D().setFill (Color.DARKBLUE) ;        
        this.getGraphicsContext2D().fillRect(0, 0, 
            GraphStation.LONG_STATION, 
            RacineReseau.LARG_STATION + RacineReseau.TAILLE_VOIE) ;
        
        this.getGraphicsContext2D().fillRect(0, RacineReseau.LARG_STATION + RacineReseau.DISTANCE_INTER_ELT, 
            GraphStation.LONG_STATION, 
            RacineReseau.LARG_STATION + RacineReseau.TAILLE_VOIE) ;         
    }
    
    public void setStation(final String nom)
    {
        this.getGraphicsContext2D().setTextAlign(TextAlignment.LEFT);
        this.getGraphicsContext2D().fillText(nom, 0, 
                RacineReseau.LARG_STATION + RacineReseau.DISTANCE_INTER_ELT - 2 ) ;        
    }
    
    public void setRame(final int numVoie, final String rame)
    {
        if (rame.isEmpty())
        {
            this.getGraphicsContext2D().setFill (Color.DARKBLUE) ;     
        }
        else
        {
            this.getGraphicsContext2D().setFill (Color.BURLYWOOD) ;                      
        }
        
        int posRelatifSensDep = (this.estGD?numVoie-1:(numVoie==2)?0:1) ;
        this.getGraphicsContext2D().fillRect(0, 
                    RacineReseau.LARG_STATION + 
                            RacineReseau.DISTANCE_INTER_ELT * posRelatifSensDep, 
                    GraphStation.LONG_STATION, RacineReseau.TAILLE_VOIE) ;
            
        this.getGraphicsContext2D().setTextAlign(TextAlignment.CENTER);        
        this.getGraphicsContext2D().setFill(Color.DARKBLUE) ;
        this.getGraphicsContext2D().setFont(Font.font("Times", 
                FontWeight.BOLD, Canvas.BASELINE_OFFSET_SAME_AS_HEIGHT));
        this.getGraphicsContext2D().fillText(rame, 
                0 + GraphStation.LONG_STATION/2, 
                RacineReseau.LARG_STATION + RacineReseau.TAILLE_VOIE + 
                        RacineReseau.DISTANCE_INTER_ELT * posRelatifSensDep) ;        
    }    
}
