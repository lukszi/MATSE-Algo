/**
 * Politiker der seinen Zustaende nach bestimmten auesseren Einfluessen aendert
 */
public class Context_Politiker
{
    public AggregateState state = new AggregateState();

    /**
     * Prueft ob Politiker aktiv ist
     * @return true wenn aktiv, false sonst
     */
    public boolean aktiv()
    {
        return state.aktiv();
    }

    /**
     * Spricht dem Politiker ein Lob seiner Partei aus
     */
    public void parteilob()
    {
        state.parteilob(this);
    }

    /**
     * Partei des Politikers tadelt ihn
     */
    public void parteitadel()
    {
        state.parteitadel(this);
    }

    /**
     * Politiker erhaelt Lob aus Wirtschaft
     */
    public void wirtschaftslob()
    {
        state.wirtschaftslob(this);
    }

    /**
     * Politiker wird erwischt
     */
    public void erwischt()
    {
        state.erwischt(this);
    }

    @Override
    public String toString()
    {
        return state.toString();
    }
}
