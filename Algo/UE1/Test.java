import java.util.ArrayList;

public class Test
{
    public static void main(String[] args)
    {

    }

    public static ArrayList<Tripel> getTripel(int n){
        ArrayList<Tripel> ret = new ArrayList<>();
        for(int a = 0; a < n-3; ++a){
            for(int b = a+1; b < n-2; ++b){
                int c = n-a-b;
                if(a*a+b*b == c*c){
                    ret.add(new Tripel(a,b,c));
                }
            }
        }
        return ret;
    }
}
