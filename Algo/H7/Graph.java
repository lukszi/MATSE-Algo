import java.util.*;
import java.util.stream.Stream;

public class Graph
{
    private ArrayList<Integer>[] adjacencyList;
    private int edgeCount;

    public Graph(int v){
        // Erster Platz bleibt frei
        adjacencyList = new ArrayList[v+1];
        for(int i = 1; i<=v; i++){
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public Graph(int[] list){
        this(list[0]);
        edgeCount = list[1];
        for(int i = 2; i< list.length; i+=2){
            addEdge(list[i], list[i+1]);
        }
    }

    public int getVertexCount(){
        return adjacencyList.length-1;
    }

    public int getEdgeCount(){
        return edgeCount;
    }

    public void addEdge(int from, int to){
        if(from<1|| to<1){
            throw new IllegalArgumentException();
        }
        if(adjacencyList[from] == null){
            adjacencyList[from] = new ArrayList<>();
        }
        adjacencyList[from].add(to);
        edgeCount++;
    }

    public ArrayList<Integer> getAdjacent(int v){
        return (ArrayList<Integer>) adjacencyList[v].clone();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i = 1; i<adjacencyList.length; i++){
            sb.append(adjacencyList[i].toString());
        }
        sb.append(']');
        return sb.toString();
    }

    public ArrayList<Integer> bfs(int start){
        ArrayDeque<Integer> deq = new ArrayDeque<>();
        ArrayList<Integer> ret = new ArrayList<>();
        boolean[] found = new boolean[this.adjacencyList.length];

        deq.push(start);
        found[start] = true;
        while(!deq.isEmpty()){
            Integer vertex = deq.pop();
            ret.add(vertex);

            adjacencyList[vertex].forEach(adjacentVertex -> {
                if(!found[adjacentVertex]){
                    deq.add(adjacentVertex);
                    found[adjacentVertex] = true;
                }
            });
        }
        return ret;
    }

    public ArrayList<Integer> dfs(int start){
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> ret = new ArrayList<>();
        boolean[] found = new boolean[this.adjacencyList.length];

        stack.push(start);
        ret.add(start);
        found[start] = true;

        while(!stack.isEmpty()){
            Integer vertex = stack.peek();

            boolean newPushed = false;

            Collections.sort(adjacencyList[vertex]);
            for (Integer adjacentVertex : adjacencyList[vertex]) {
                if(!found[adjacentVertex]){
                    stack.push(adjacentVertex);
                    ret.add(adjacentVertex);
                    found[adjacentVertex] = true;
                    newPushed = true;
                    break;
                }
            }

            if (!newPushed){
                stack.pop();
            }
        }
        return ret;
    }
}
