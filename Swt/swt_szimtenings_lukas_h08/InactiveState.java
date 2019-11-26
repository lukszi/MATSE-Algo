public class InactiveState implements PolitikerState
{
    @Override
    public boolean aktiv()
    {
        return false;
    }

    @Override
    public void parteilob(Context_Politiker context){}
    @Override
    public void parteitadel(Context_Politiker context){}
    @Override
    public void wirtschaftslob(Context_Politiker context){}
    @Override
    public void wirtschaftskritik(Context_Politiker context){}
    @Override
    public void erwischt(Context_Politiker context){}

    @Override
    public String toString()
    {
        return "";
    }
}
