import java.util.Arrays;

public class H6Main
{
    public static void main(String[] args)
    {
        Integer[] values = {1, 6, 8, 18, 23, 5, 17, 20, 26, 21, 9};
        Heap heap = new Heap();
        Arrays.stream(values).forEach(value -> {
            System.out.println(heap);
            heap.add(value);
        });
        System.out.println(heap);

        while(!heap.isEmpty()){
            System.out.println(heap.getMax());
            System.out.println(heap);
        }
    }
}
