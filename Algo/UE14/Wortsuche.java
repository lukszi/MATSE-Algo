import java.util.HashMap;

public class Wortsuche
{
    private String text;
    private HashMap<Character, Integer> lastTable;

    public Wortsuche(String text){
        this.text = text;
    }

    private static HashMap<Character, Integer> getLastTable(String pattern){
        HashMap<Character, Integer> ret = new HashMap<>();
        for(int i = 0;i<pattern.length();i++){
            ret.put(pattern.charAt(i), i);
        }
        return ret;
    }

    private int fits(String pattern, int pos){
        if(pos>text.length()-pattern.length()){
            return -2;
        }
        int ret = -1;

        for(int i = 0; i<pattern.length();i++){
            if(pattern.charAt(i)!=text.charAt(i+pos))
                return lastTable.getOrDefault(text.charAt(pos+pattern.length()),0);
        }
        return ret;
    }

    public int findFirst(String pattern){
        this.lastTable = getLastTable(pattern);
        for(int pos = 0; pos< text.length();){
            int fits = fits(pattern, pos);
            if(fits == -2){
                return -1;
            }
            if(fits == -1){
                return pos;
            }
            else{
                pos+=pattern.length()-fits;
            }
        }
        return -1;
    }

    public static void main(String[] args)
    {
        Wortsuche meinWort = new Wortsuche("DieguteBabanane123Lecker");
        int ergebnis=meinWort.findFirst("Banane");
        if(ergebnis==-1){
            System.out.println("pattern not found");
        }
        else{
            System.out.println("pattern found at: " + ergebnis);
        }
    }
}
