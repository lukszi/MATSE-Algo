import java.util.ArrayList;

/**
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class Damenproblem {

    /**
     * Stellt eine rekursive Loesung, auf Backtracking-Basis, des Damen-Problems dar.
     * Es wird die zu loesende Brettgroesse uebergeben.
     * @param brettgroesse die Brettgroesse
     */
    public void damenProblem(int brettgroesse){ //Funktionskopf als nicht statisch vorgegeben
        damenProblem(brettgroesse, new ArrayList<>(brettgroesse));
    }

    /**
     * rekursive Hilfsmethode fuer das Damenproblem.
     * @param brettgroesse die Brettgroesse
     * @param damen die aktuelle Damen-Platzierung
     */
    private static void damenProblem(int brettgroesse, ArrayList<Integer> damen){
        for (int zeile = 1; zeile <= brettgroesse; ++zeile){
            if (istvalidesDamenFeld(zeile, damen)){
                damen.add(zeile);                   //Dame platzieren
                if (damen.size() == brettgroesse){  //Loesung ausgeben
                    System.out.println(damen);
                } else {                            //naechste Spalte pruefen
                    damenProblem(brettgroesse, damen);
                }
                damen.remove(damen.size() -1);  //Dame wieder entfernen
            }
        }
    }

    /**
     * prueft ob eine Dame in die uebergebene Zeile platziert werden darf.
     * @param pruefZeile die zu pruefende Zeile
     * @param damen die aktuelle Damen-Platzierung
     * @return ob die Dame platziert werden darf.
     */
    private static boolean istvalidesDamenFeld(int pruefZeile, ArrayList<Integer> damen){
        for (int spalte = 0; spalte < damen.size(); ++spalte){
            int zeile = damen.get(spalte);
            if (zeile == pruefZeile || Math.abs(zeile - pruefZeile) == Math.abs(spalte - damen.size())){    //Dame ist schlagbar
                return false;
            }
        }
        return true;
    }
}
