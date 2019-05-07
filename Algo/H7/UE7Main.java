import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class UE7Main
{
    public static void main(String[] args) throws IOException
    {
        int[] vlist = { 6, 10, 1, 5, 1, 4, 2, 3, 2, 6, 3, 4, 3, 5, 4, 5, 4, 6, 5, 6, 6, 4 };
        Graph g = new Graph(vlist);
        System.out.println(g);

        URL url = new URL("https://doc.itc.rwth-aachen.de/download/attachments/5800152/adjazenzlisten2.txt?version=1&modificationDate=1556880451000&api=v2");
        URLConnection con = url.openConnection();
        InputStream content = (InputStream)con.getContent();
        Graph graph = new Graph(content);
        System.out.println(graph);
    }
}
