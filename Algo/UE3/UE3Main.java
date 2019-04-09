public class UE3Main
{
    public static void main(String[] args)
    {
        LinkedList list = new LinkedList();
        testList(list);
    }

    private static void testList(IList list)
    {
        for(int i = 0; i<10;i++){
            list.insertAt(i, i);
        }
        printList(list);
        list.insertAt(100, 2);
        printList(list);
        list.removeAt(2);
        list.removeAt(0);
        printList(list);
        System.out.println(list.getCount());
        System.out.println(list.search(3));
        list.clear();
        printList(list);
    }

    private static void printList(IList list){
        for(int i = 0; i<list.getCount(); i++){
            System.out.print(list.getAt(i) + ", ");
        }
        System.out.print("\n");
    }
}
