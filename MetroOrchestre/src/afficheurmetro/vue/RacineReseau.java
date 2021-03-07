/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afficheurmetro.vue;

import javafx.scene.canvas.Canvas;

/**
 *
 * @author millan
 */
public class RacineReseau extends Canvas
{    
    public static final int DISTANCE_INTER_ELT = 20 ;
    protected static final int TAILLE_VOIE = 8 ;
    protected static final int LARG_STATION = 10 ;        
    
    public RacineReseau(final int longueur, final int largeur)
    {
        super(longueur, largeur) ;
    }   
}
