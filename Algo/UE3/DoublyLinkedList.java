import java.util.NoSuchElementException;

public class DoublyLinkedList implements IList
{
    private ListNode first = new ListNode();
    private int count;
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
            count++;
            return;
        }
        while(node!=null && node.value!=null){
            if(position == pos-1){
                ListNode temp = node.next;
                node.next = new ListNode();
                node.next.value = val;
                node.next.next = temp;
                count++;
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
        while(node!=null && node.value!=null){
            if(position == pos){
                if(node.next!=null&&node.prev!=null){
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }
                else if(node.prev!=null){
                    node.prev.next = null;
                }
                else{
                    node.value = null;
                }
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
        return count;
    }

    private class ListNode{
        ListNode prev;
        ListNode next;
        Integer value;
    }
}
