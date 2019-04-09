import java.util.NoSuchElementException;

public class LinkedList implements IList
{
    private ListNode first = new ListNode();
    @Override
    public void insertAt(Integer val, int pos)
    {
        int position = 0;
        ListNode node = first;
        if(pos == 0){
            ListNode temp = first;
            first = new ListNode();
            first.value = val;
            first.next = temp;
            return;
        }
        while(node!=null && node.value!=null){
            if(position == pos-1){
                ListNode temp = node.next;
                node.next = new ListNode();
                node.next.value = val;
                node.next.next = temp;
                return;
            }
            position++;
            node = node.next;
        }

        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void removeAt(int pos)
    {
        int position = 0;
        ListNode node = first;
        if(pos == 0){
            ListNode temp = first.next;
            if(temp == null){
                first = new ListNode();
                return;
            }
            first = temp;
            return;
        }
        while(node!=null && node.value!=null){
            if(position == pos-1){
                node.next = (node.next==null ? null : node.next.next);
                return;
            }
            position++;
            node = node.next;
        }

        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public Integer getAt(int pos)
    {
        int position = 0;
        ListNode node = first;

        while(node!=null && node.value!=null){
            if(position == pos)
                return node.value;
            position++;
            node = node.next;
        }

        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int search(Integer val)
    {
        int position = 0;
        ListNode node = first;

        while(node!=null && node.value!=null){
            if(node.value.equals(val))
                return position;
            position++;
            node = node.next;
        }

        throw new NoSuchElementException();
    }

    @Override
    public void clear()
    {
        first = new ListNode();
    }

    @Override
    public int getCount()
    {
        int count = 0;
        ListNode node = first;
        while(node!=null && node.value!=null){
            node = node.next;
            count++;
        }
        return count;
    }

    private class ListNode
    {
        ListNode next;
        Integer value;
    }

}
