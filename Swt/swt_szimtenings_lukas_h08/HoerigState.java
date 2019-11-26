public class HoerigState implements PolitikerState
{
    @Override
    public boolean aktiv()
    {
        return true;
    }

    @Override
    public void parteilob(Context_Politiker context){}
    @Override
    public void parteitadel(Context_Politiker context){}

    @Override
    public void wirtschaftslob(Context_Politiker context)
    {
        context.state.wirtschaftsState = new AufsichtsratState();
        context.state.politikState = new InactiveState();
    }

    @Override
    public void wirtschaftskritik(Context_Politiker context)
    {
        context.state.wirtschaftsState = new NeutralState();
    }

    @Override
    public void erwischt(Context_Politiker context)
    {
        context.state.wirtschaftsState = new InactiveState();
    }

    @Override
    public String toString()
    {
        return "PROTEGIERT HÖRIG";
    }
}
