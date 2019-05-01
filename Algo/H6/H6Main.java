import java.util.Arrays;

/**
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class H6Main
{
    public static void main(String[] args)
    {
        Integer[] values = {1, 6, 8, 18, 23, 5, 17, 20, 26, 21, 9};

        // Add elements
        Heap heap = new Heap();
        Arrays.stream(values).forEach(value -> {
            System.out.println(heap);
            heap.add(value);
        });
        System.out.println(heap);

        // Remove elements
        while (!heap.isEmpty()) {
            System.out.println(heap.getMax());
            System.out.println(heap);
        }
    }
}
