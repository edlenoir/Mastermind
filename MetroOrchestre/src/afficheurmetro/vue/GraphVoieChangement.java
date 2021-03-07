package afficheurmetro.vue;

import javafx.scene.paint.Color;

/**
 *
 * @author millan
 */
public class GraphVoieChangement extends GraphVoie        
{
    private static final int POS_FEU_VERTH_X = 80 ;
    private static final int POS_FEU_VERTH_Y = 0 ;
    private static final int POS_FEU_VERTB_X = 0 ;
    private static final int POS_FEU_VERTB_Y = 20 ; 
    
    private static final double POINTSG_X[] = {
                    GraphVoieChangement.VOIEH_X, 
                    GraphVoie.LONG, 
                    GraphVoie.LONG/2, 
                    GraphVoie.LONG*3/4, 
                    GraphVoie.LONG};
    private static final double POINTSG_Y[] = {
                    GraphVoieChangement.VOIEH_Y, 
                    GraphVoieChangement.VOIEH_Y, 
                    GraphVoieChangement.VOIEH_Y, 
                    GraphVoieChangement.VOIEH_Y + RacineReseau.DISTANCE_INTER_ELT,
                    GraphVoieChangement.VOIEH_Y + RacineReseau.DISTANCE_INTER_ELT};
    
        private static final double POINTSD_X[] = {
                    GraphVoieChangement.VOIEH_X, 
                    GraphVoie.LONG, 
                    GraphVoie.LONG/2, 
                    GraphVoie.LONG/4, 
                    GraphVoieChangement.VOIEH_X};
private static final double POINTSD_Y[] = {
                    GraphVoieChangement.VOIEH_Y, 
                    GraphVoieChangement.VOIEH_Y, 
                    GraphVoieChangement.VOIEH_Y, 
                    GraphVoieChangement.VOIEH_Y + RacineReseau.DISTANCE_INTER_ELT,
                    GraphVoieChangement.VOIEH_Y + RacineReseau.DISTANCE_INTER_ELT};

    private final boolean estDepot ;
    private Color couleur ;
     
    public GraphVoieChangement(
            final boolean droit, 
            final boolean estDepot)
    {
        super(GraphVoie.LONG, GraphVoie.LARG, droit) ; 
        this.estDepot = estDepot ;
        this.tracer();        
    }
    
    @Override
    protected void dessiner()
    {
        this.tracer() ; 
    }
    
    private void tracer()
    {
        this.getGraphicsContext2D().setStroke(GraphVoie.COULEUR_RAIL) ;
        this.getGraphicsContext2D().setLineWidth(GraphVoie.TAILLE_VOIE);   
          
        this.getGraphicsContext2D().strokePolyline((!this.getEstGD()?GraphVoieChangement.POINTSD_X:
                              GraphVoieChangement.POINTSG_X), 
            (!this.getEstGD()?GraphVoieChangement.POINTSD_Y:
                              GraphVoieChangement.POINTSG_Y), 
            5);
        this.couleur = 
            (this.getCouleurFeux()[0]==Color.RED || 
             this.getCouleurFeux()[1]==Color.RED?Color.RED:
            (this.getCouleurFeux()[0]==Color.GREEN || 
             this.getCouleurFeux()[1]==Color.GREEN?Color.GREEN:Color.BLACK)) ;
        /*
         * Pas de dépot donc on n'a qu'un feu sur la atation
        */
        if (this.estDepot)
        {    
            Color couleurDep = (this.couleur == Color.BLACK?
                                Color.BLACK:Color.GREEN) ;
            if (this.getEstGD())
            {
                this.gestionnaireFeux(GraphVoieChangement.POS_FEU_VERTB_X, 
                                      GraphVoieChangement.POS_FEU_VERTB_Y,
                                      couleurDep);
            }
            else 
            {
                this.gestionnaireFeux(GraphVoieChangement.POS_FEU_VERTH_X, 
                                      GraphVoieChangement.POS_FEU_VERTH_Y, 
                                      couleurDep);
            }
        }
        
        if (this.getEstGD())
        {
            this.gestionnaireFeux(GraphVoieChangement.POS_FEU_VERTH_X, 
                                  GraphVoieChangement.POS_FEU_VERTH_Y, this.couleur);
        }
        else 
        {
            this.gestionnaireFeux(GraphVoieChangement.POS_FEU_VERTB_X, 
                    GraphVoieChangement.POS_FEU_VERTB_Y, this.couleur);
        }
    }

    private void gestionnaireFeux(final int x, final int y, final Color c)
    {
        if (c == Color.GREEN)
        {
            this.tracerFeux(x, y, c, Color.BLACK) ;
        }
        else
        {
            this.tracerFeux(
                    x, y, Color.BLACK, c) ;
        }
    }
    
    private void tracerFeux(final int x, final int y, final Color c)
    {
        this.getGraphicsContext2D().setFill (c) ;
        this.getGraphicsContext2D().fillOval(x, y, GraphVoie.DIAM_FEU, GraphVoie.DIAM_FEU) ;        
    }
    
    private void tracerFeux(
            final int x, final int y, final Color cF1, final Color cF2)
    {
        this.tracerFeux(x, y, cF1) ;
        this.tracerFeux(x + GraphVoie.DIAM_FEU, y, cF2) ;  
    }    
}
