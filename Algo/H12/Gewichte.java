import java.util.ArrayList;

/**
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class Gewichte {

    /**
     * Testfunktion des Wiegens.
     * Testfaelle vom Aufgabenblatt uebernommen.
     */
    public static void main(String[] args){
        int[] possibleWeights = {1, 3, 8, 20};
        weigh(16, possibleWeights);
    }

    /**
     * Testet, ob das gesuchte Gewichte als Kombination der uebergebenen Gewichte dargestellt werden kann.
     * Die Kombinationen werden auf den Bildschirm ausgegeben.
     * @param desiredWeight das gesuchte Gewicht
     * @param weights die moeglichen Gewichte
     */
    static void weigh(int desiredWeight, int[] weights){
        weigh(desiredWeight, new ArrayList<>(), 0, weights, 0);
    }

    /**
     * rekursive Hilfsfunktion des Wiegens
     * @param desiredWeight das gesuchte Gewicht
     * @param path die aktuell platzierten Gewichte
     * @param currentSum die aktuelle Summe
     * @param weights die moeglichen Gewichte
     * @param currentIndex der Index des aktuellen Gewichts
     */
    static void weigh(int desiredWeight, ArrayList<Integer> path, int currentSum, int[] weights, int currentIndex){
        if (desiredWeight == Math.abs(currentSum)){       //wenn gefunden Gewicht ausgeben
            System.out.println(path);
        }
        if (weights.length == currentIndex){    //alle Gewichte wurden platziert
            return;
        }
        //Gewicht links platzieren
        int currentWeight = weights[currentIndex];
        path.add(currentWeight);
        weigh(desiredWeight, path, currentSum + currentWeight, weights, currentIndex + 1);
        path.remove(path.size() - 1);
        //Gewicht nicht platzieren
        weigh(desiredWeight, path, currentSum, weights, currentIndex + 1);
        //Gewicht rechts platzieren
        path.add(-currentWeight);
        weigh(desiredWeight, path, currentSum - currentWeight, weights, currentIndex + 1);
        path.remove(path.size() - 1);
    }
}
