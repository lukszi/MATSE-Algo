/**
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class H8Main {

    public static void main(String[] args){
        int[][] adjazenzmatrix = { { 0, 3, 0, 2, 0, 0 },
                { 3, 0, 2, 0, 3, 0 },
                { 0, 2, 0, 1, 6, 0 },
                { 2, 0, 1, 0, 0, 0 },
                { 0, 3, 6, 0, 0, 5 },
                { 0, 0, 0, 0, 5, 0 } };
        System.out.println("resultierende kosten: " + MST.getMST(adjazenzmatrix));
    }
}
