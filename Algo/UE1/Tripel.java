public class Tripel
{
    private int a, b, c;
    public Tripel(int a, int b, int c){
        this.c = c;
        this.b = b;
        this.a = a;
    }

    public int getA()
    {
        return a;
    }

    public void setA(int a)
    {
        this.a = a;
    }

    public int getB()
    {
        return b;
    }

    public void setB(int b)
    {
        this.b = b;
    }

    public int getC()
    {
        return c;
    }

    public void setC(int c)
    {
        this.c = c;
    }

    @Override
    public String toString(){
        return "(" + a + ", " + b + ", " + c + ")";
    }
}
