import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GamesHash
{
    private String[] hashTable = new String[31];

    private int hashIndex(String game){
        return game.hashCode()%100;
    }

    private int hashIncrement(String game){
        return (game.hashCode()%1000)/100+1;
    }

    public void add(String game){
        int h0 = hashIndex(game);
        if(hashTable[h0%31]!=null) {
            h0 += hashIncrement(game);
            if(hashTable[h0%31]!=null){
                throw new RuntimeException("Value can't be placed");
            }
        }
        hashTable[h0%31] = game;
    }

    public boolean contains(String game){
        int h0 = hashIndex(game);
        if(hashTable[h0%31]!=null) {
            if(hashTable[h0%31].equals(game))
                return true;
            h0 += hashIncrement(game);
            if(hashTable[h0%31]!=null){
                return hashTable[h0 % 31].equals(game);
            }
        }
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        GamesHash hash = new GamesHash();
        Scanner sc = new Scanner(new File("C:\\Users\\Lukas\\Documents\\Uni\\MATSE\\Algo\\H3\\games20.txt"));
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
