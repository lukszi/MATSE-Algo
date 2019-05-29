import java.util.*;

public class Dijkstra
{
    public static void printDijkstra(int[] edges){
        Node[] graph = parseEdges(edges);
        printHeader(edges[0]);
        printHeaderLine(edges[0]);
        dijkstra(graph);
    }

    private static void dijkstra(Node[] graph)
    {
        // initialize distances with infinity and 0 to the starting element
        Integer[] distances = new Integer[graph.length];
        for(int i = 0; i<distances.length; i++){
            distances[i] = Integer.MAX_VALUE;
        }
        distances[1] = 0;

        Node[] predecessors = new Node[graph.length];
        HashSet<Node> unvisited = new HashSet<>(Arrays.asList(graph));

        // Start dijkstra
        while(unvisited.size()!=1){
            Node closest = findClosest(distances, new ArrayList<>(unvisited));
            unvisited.remove(closest);

            // Go through all neighbours that haven't been visited yet, and update their distances
            for(Node neighbour : closest.distances.keySet()){
                if(unvisited.contains(neighbour)){
                    updateDistance(closest, neighbour, distances, predecessors);
                }
            }
            // print each step
            printDijkstraStep(closest, distances, predecessors);
        }
    }

    private static void updateDistance(Node source, Node neighbour, Integer[] distances, Node[] predecessors)
    {
        // Calculate distance using a detour through source
        int distDetour = source.distances.get(neighbour) + distances[source.name];

        // If the detour is shorter than the original path, update distances and predecessors
        if(distDetour < distances[neighbour.name]){
            distances[neighbour.name] = distDetour;
            predecessors[neighbour.name] = source;
        }
    }

    private static Node findClosest(Integer[] distance, List<Node> unvisited)
    {
        Node closest = unvisited.get(1);

        // Iterate over all remaining nodes to find the closest one
        for(int i = 2; i< unvisited.size(); i++){
            Node candidate = unvisited.get(i);
            // Check if node is closer to the starting node than the current closest one
            if(distance[candidate.name]< distance[closest.name])
                closest = candidate;
        }
        return closest;
    }

    private static void printDijkstraStep(Node currentNode, Integer[] distance, Node[] predecessor){
        StringBuilder sb = new StringBuilder();

        // print vi
        sb.append(String.format("%1$2s", currentNode.name));
        sb.append("|");

        // print distances
        for(int i = 2; i< distance.length; i++){
            Integer d = distance[i];
            String printable = d==Integer.MAX_VALUE?"--":String.valueOf(d);
            sb.append(String.format("%1$3s", printable));
        }
        sb.append("|");

        // print predecessors
        for(int i = 2; i< predecessor.length; i++){
            Node p = predecessor[i];
            String printable = p==null?"--":String.valueOf(p);
            sb.append(String.format("%1$3s", printable));
        }
        sb.append("|");
        System.out.println(sb.toString());
    }

    private static void printHeaderLine(int edges)
    {
        int numLines = 3+(edges-1)*3*2+2;
        System.out.println(new String(new char[numLines]).replace("\0", "-"));
    }

    private static void printHeader(int edges)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("vi|");

        // build nodeEnumeration
        StringBuilder nodeEnum = new StringBuilder();
        for(int i = 2; i<=edges; i++){
            nodeEnum.append(String.format("%1$3s", i));
        }
        nodeEnum.append("|");

        // Append nodeEnumeration twice
        sb.append(nodeEnum).append(nodeEnum);

        System.out.println(sb.toString());
    }

    private static Node[] parseEdges(int[] edges)
    {
        // Create the graph
        Node[] graph = new Node[edges[0]+1];

        // Go over each entry in the edges field and parse it
        for(int i = 1; i< edges.length; i+=3){
            // Get all relevant data from the array
            int iFrom = edges[i];
            int iTo = edges[i+1];
            int distance = edges[i+2];

            // Make sure nodes exist
            if(graph[iFrom]==null){
                graph[iFrom] = new Node();
                graph[iFrom].name = iFrom;
            }
            if(graph[iTo]==null){
                graph[iTo] = new Node();
                graph[iTo].name = iTo;
            }

            // Add Entry to graph
            graph[iFrom].distances.put(graph[iTo], distance);

        }
        return graph;
    }

    private static class Node{
        HashMap<Node, Integer> distances;
        int name;
        Node(){
            distances = new HashMap<>();
        }

        @Override
        public String toString(){
            return String.valueOf(name);
        }
    }
}
