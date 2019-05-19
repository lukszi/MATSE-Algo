import java.util.ArrayList;
import java.util.HashSet;

public class MST{

    /**
     * Gibt den Pfad des minimalen Spannbaums des übergebenen Graphens in der Konsole aus.
     * Als Wurzel wird immer Knoten 1 gewählt.
     * Übergeben wird eine valide Adjazenzmatrix eines ungerichteten zusammenhängenden Graphens.
     * Es werden des Spannbaums zurückgegeben.
     * @param edges eine valide Adjazenzmatrix eines ungerichteten zusammenhängenden Graphens.
     * @return die Kosten des minimalen Spannbaums
     * @author Lukas Szimtenings
     * @author Felix Szimtenings
     * @author Cedric Radtke
     */
    public static int getMST(int[][] edges){
        System.out.println("waehle 1 als Wurzel");

        int costs = 0;
        ArrayList<Integer>  mstNodes = new ArrayList<>(edges.length);
        HashSet<Integer> needsVisit = new HashSet<>(edges.length);  //schnelleres entfernen als ArrayList

        mstNodes.add(0);
        for (int i = 1; i < edges.length; ++i){   //0 wurde bereits besucht
            needsVisit.add(i);
        }

        while (mstNodes.size() < edges.length){ //alle Knoten wurden hinzugefügt
            int minFrom = 0;
            int minTo = 0;
            int minCost = Integer.MAX_VALUE;
            for (int mstNode : mstNodes){
                for (int nextNode : needsVisit){
                    int cost = edges[mstNode][nextNode];
                    if (cost != 0 && cost < minCost){
                        minCost = cost;
                        minFrom = mstNode;
                        minTo = nextNode;
                    }
                }
            }
            //Minimale Kante hinzufuegen
            System.out.println("Kante hinzugefuegt von "+(minFrom+1)+" zu "+(minTo+1));
            mstNodes.add(minTo);
            needsVisit.remove(minTo);
            costs += minCost;
        }
        return costs;
    }
}