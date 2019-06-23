import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class NFA
{
    private List<Character>[][] adj;
    private int target;
    int edges;

    public NFA(String x){
        String[] edgeList = x.split(",");
        edges = Integer.parseInt(edgeList[0]);
        target = Integer.parseInt(edgeList[1]);
        // init adjacency list
        adj = new ArrayList[edges+1][edges+1];

        // put values in adjacency list
        for(int i = 2; i<edgeList.length; i+=3){

            int from = Integer.parseInt(edgeList[i]);
            int to = Integer.parseInt(edgeList[i+1]);
            char with = edgeList[i+2].charAt(0);

            List<Character> waysToGetThere = adj[from][to];
            if(waysToGetThere == null){
                waysToGetThere = new ArrayList<>();
                adj[from][to] = waysToGetThere;
            }
            waysToGetThere.add(with);
        }
    }

    public boolean testString(String s){
        HashSet<Integer> reachableStates = new HashSet<>();
        reachableStates.add(1);
        for(int i = 0; i<s.length();i++)
        {
            char letter = s.charAt(i);
            HashSet<Integer> newReachableStates = new HashSet<>();

            for(Integer reachableState : reachableStates)
            {
                for(int targetState = 1; targetState<=edges; targetState++)
                {
                    List<Character> actions = adj[reachableState][targetState];
                    if(actions==null){
                        continue;
                    }
                    if(actions.contains(letter)){
                        newReachableStates.add(targetState);
                    }
                }
            }
            reachableStates = newReachableStates;
            if(reachableStates.size()==0)
                return false;
        }
        return reachableStates.contains(target);
    }
}

