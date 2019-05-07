import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class Graph
{
    private ArrayList<Integer>[] adjacencyList;
    private int edgeCount;

    /**
     * Erstellt einen neuen Graphen mit v Knoten
     * @param v anzahl der Knoten
     */
    @SuppressWarnings("unchecked")
    public Graph(int v){
        // Erster Platz bleibt frei
        adjacencyList = new ArrayList[v+1];
        for(int i = 1; i<=v; i++){
            adjacencyList[i] = new ArrayList<>();
        }
    }

    /**
     * Erstellt einen neuen Graphen aus einer Kantenliste
     * @param list Kantenliste des Graphen
     */
    public Graph(int[] list){
        this(list[0]);
        for(int i = 2; i< list.length; i+=2){
            addEdge(list[i], list[i+1]);
        }
    }

    /**
     * Erstellt einen neuen Graphen aus einer Adjazenzliste
     * @param in Stream einer Adjazenzliste
     */
    public Graph(InputStream in){
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
        (new BufferedReader(new InputStreamReader(in))).lines().forEach(line -> {
            ArrayList<Integer> adjacencies = new ArrayList<>();
            for (String entry : line.split(";")) {
                if(line.trim().length()==0)
                    continue;
                adjacencies.add(Integer.parseInt(entry));
                this.edgeCount++;
            }
            adjacencyList.add(adjacencies);
        });
        ArrayList<Integer>[] ret = new ArrayList[0];
        ret = adjacencyList.toArray(ret);
        this.adjacencyList = ret;
    }

    /**
     *
     * @return die Menge der Knoten in dem Graphen
     */
    public int getVertexCount(){
        return adjacencyList.length-1;
    }

    /**
     *
     * @return die Menge der Kanten in dem Graphen
     */
    public int getEdgeCount(){
        return edgeCount;
    }

    /**
     * Fügt eine gerichtete Kante zwischen zwei Knoten hinzu
     * @param from Ursprung der Kante
     * @param to Ziel der Kante
     */
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

    /**
     *
     * @param v Knoten zu dem die ausgehenden Kanten gefunden werden sollen
     * @return Knoten die von v erreichbar sind
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Integer> getAdjacent(int v){
        if(v<1)
            throw new IndexOutOfBoundsException();
        return (ArrayList<Integer>) adjacencyList[v].clone();
    }

    /**
     *
     * @return String Repräsentation des Graphen
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i = 1; i<adjacencyList.length; i++){
            sb.append(adjacencyList[i].toString());
        }
        sb.append(']');
        return sb.toString();
    }

    /**
     * Führt eine Breitensuche aus
     * @param start Startknoten der Breitensuche
     * @return Liste der Knoten sortiert nach Entdeckungsreihenfolge
     */
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

    /**
     * Führt eine Tiefensuche aus
     * @param start Startknoten der Tiefensuche
     * @return Liste der Knoten sortiert nach Entdeckungsreihenfolge
     */
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
