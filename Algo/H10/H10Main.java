/**
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class H10Main
{
    /**
     * Testet den Dijkstra-Algorithmus
     */
    public static void main(String[] args)
    {
        int[] testCase1 = {4,1,2,2,1,4,5,2,4,1,2,3,4,3,1,1,4,3,1};
        Dijkstra.printDijkstra(testCase1);
        int[] testCase2 = {10,1,2,30,1,3,10,2,5,15,2,8,55,3,4,5,3,9,35,4,2,10,4,5,45,4,6,10,5,3,20,5,7,15,5,9,25,6,7,5,7,10,20,8,10,15,9,8,10,9,10,30};
        Dijkstra.printDijkstra(testCase2);
    }
}
