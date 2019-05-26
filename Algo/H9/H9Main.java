/**
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class H9Main {

    /**
     * Testmethode fuer das Damenproblem.
     * Getestet wird ein Brett der Groesse 6.
     */
    public static void main(String[] args){
        Damenproblem test = new Damenproblem();
        int brettgroesse = 6;
        System.out.println("Brettgroesse = "+ brettgroesse + ":");
        test.damenProblem(brettgroesse);
    }
}
