import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class H12Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int gewicht = sc.nextInt();

        Balkenwaage waage = new Balkenwaage(new ArrayList<>(Arrays.asList(1,3,8,20)));
        ArrayList<ArrayList<Integer>> solutions = waage.getSolutions(gewicht);

        // print results
        for(ArrayList<Integer> solution : solutions){
            StringBuilder sb = new StringBuilder();
            solution.forEach(num -> sb.append(num).append(","));
            sb.deleteCharAt(sb.length()-1);
            sb.append(" = ").append(gewicht);
            System.out.println(sb.toString());
        }
    }
}
