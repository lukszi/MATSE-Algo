/**
 * Interface das den Status eines Politikers und die dazugehörigen Transitionen implementiert
 */
public interface PolitikerState
{
    /**
     * @return true wenn der Politiker aktiv ist, ansonsten false
     */
    boolean aktiv();

    /**
     * Fuehrt die Transition zum neuen Zustand aus wenn ein Politiker ein Lob von seiner Partei erhaelt
     * @param context Politiker an dem die Transition vorgenommen wird
     */
    void parteilob(Context_Politiker context);

    /**
     * Fuehrt die Transition zum neuen Zustand aus wenn ein Politiker einen Tadel von seiner Partei erhaelt
     * @param context Politiker an dem die Transition vorgenommen wird
     */
    void parteitadel(Context_Politiker context);
    /**
     * Fuehrt die Transition zum neuen Zustand aus wenn ein Politiker ein Lob von der Wirtschaft erhaelt
     * @param context Politiker an dem die Transition vorgenommen wird
     */
    void wirtschaftslob(Context_Politiker context);
    /**
     * Fuehrt die Transition zum neuen Zustand aus wenn ein Politiker Kritik aus der Wirtschaft erhaelt
     * @param context Politiker an dem die Transition vorgenommen wird
     */
    void wirtschaftskritik(Context_Politiker context);
    /**
     * Fuehrt die Transition zum neuen Zustand aus (In der Regel InactiveState) wenn ein Politiker erwischt wird
     * @param context Politiker an dem die Transition vorgenommen wird
     */
    void erwischt(Context_Politiker context);
}
