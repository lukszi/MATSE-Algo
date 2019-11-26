public class ErgebenState implements PolitikerState
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
        context.state.wirtschaftsState = new HoerigState();
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

    public String toString(){
        return "PROTEGIERT ERGEBEN";
    }
}
