/**
 *
 */
public class AggregateState implements PolitikerState
{
    public PolitikerState politikState = new LoyalState();
    public PolitikerState wirtschaftsState = new ErgebenState();

    @Override
    public boolean aktiv()
    {
        return politikState.aktiv()||wirtschaftsState.aktiv();
    }

    @Override
    public void parteilob(Context_Politiker context)
    {
        politikState.parteilob(context);
        wirtschaftsState.parteilob(context);
    }

    @Override
    public void parteitadel(Context_Politiker context)
    {
        politikState.parteitadel(context);
        wirtschaftsState.parteitadel(context);
    }

    @Override
    public void wirtschaftslob(Context_Politiker context)
    {
        politikState.wirtschaftslob(context);
        wirtschaftsState.wirtschaftslob(context);
    }

    @Override
    public void wirtschaftskritik(Context_Politiker context)
    {
        politikState.wirtschaftskritik(context);
        wirtschaftsState.wirtschaftskritik(context);
    }

    @Override
    public void erwischt(Context_Politiker context)
    {
        politikState.erwischt(context);
        wirtschaftsState.erwischt(context);
    }

    @Override
    public String toString()
    {
        return politikState.toString() + " " + wirtschaftsState.toString();
    }
}
