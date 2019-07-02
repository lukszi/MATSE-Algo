import java.util.ArrayList;

public class HashSet
{
    private ArrayList<String>[] vals;

    public HashSet(){
        this.vals = new ArrayList[26];
        for(int i = 0; i<=26; i++){
            this.vals[i] = new ArrayList<>();
        }
    }

    public void add(String s){
        vals[getHash(s)-1].add(s);
    }

    public boolean contains(String s){
        if(vals[getHash(s)-1].contains(s))
            return true;
        return false;
    }

    private int getHash(String s){
        return s.charAt(0)-65;
    }
}
