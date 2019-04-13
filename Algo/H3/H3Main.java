import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class H3Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        GamesHash hash = new GamesHash();
        Scanner sc = new Scanner(new File("C:\\Users\\CRA\\Documents\\Uni\\MATSE\\Algo\\H3\\games20.txt"));
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] splitLine = line.split("\t");
            hash.add(splitLine[1]);
        }
        //sc.forEachRemaining(s -> hash.add(s.split("\t")[1]));
        System.out.println(hash.contains("Nioh"));
        System.out.println(hash.contains("Cuphead"));
        System.out.println(hash.contains("WOW"));
    }
}
