
import java.io.*;
import java.util.*;

/**
 * 
 */
public class PoubelleIntelligente extends Corbeille {

    /**
     * Default constructor
     */
    public PoubelleIntelligente() {
    }

    /**
     * 
     */
    private int idPoubelle;

    /**
     * 
     */
    private String type;

    /**
     * 
     */
    private double capacitéMaximale;

    /**
     * 
     */
    private String emplacement;

    /**
     * @return
     */
    public boolean estPlein() {
        // TODO implement here
        return false;
    }

    /**
     * @param utilisateur  
     * @return
     */
    public boolean identifierUtilisateur(Menage utilisateur ) {
        // TODO implement here
        return false;
    }

    /**
     * @param corbeille 
     * @return
     */
    public double calculerQuantitéDéchets(Corbeille corbeille) {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param utilisateur  
     * @param quantite  
     * @return
     */
    public void attribuerPointsFidelité(Menage utilisateur , double quantite ) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void envoyerNotification() {
        // TODO implement here
        return null;
    }

}