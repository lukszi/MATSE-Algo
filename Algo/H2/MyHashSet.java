import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * HashSet of Type K
 * @param <K> the Type that should be contained in this HashSet
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class MyHashSet<K>
{
    private ArrayList<K>[] hashTable;
    private int n = 0;

    /**
     * Creates a new MyHashSet with a length of 10
     */
    public MyHashSet(){
        createHashtable(10);
    }

    @SuppressWarnings("unchecked")
    private void createHashtable(int length)
    {
        hashTable = new ArrayList[length];
        for(int i = 0; i< length; i++){
            hashTable[i] = new ArrayList<>();
        }
    }

    private MyHashSet(int length){
        createHashtable(length);
    }

    /**
     * Adds an element to this set
     * @param elem element to be added to this set
     * @return true if the element already existed, false otherwise
     */
    public boolean add(K elem){
        ArrayList<K> list = hashTable[elem.hashCode() % hashTable.length];
        if(list.contains(elem))
            return true;
        else{
            list.add(elem);
            n++;
        }
        resizeIfNecessary();
        return false;
    }

    /**
     * checks if an element is contained in the set
     * @param elem element to be looked for
     * @return true if elem is contained in Set
     */
    public boolean contains(K elem){
        return hashTable[elem.hashCode()%hashTable.length].contains(elem);
    }

    /**
     *
     * @param elem element to be deleted
     * @return true if element existed, false otherwise
     */
    public boolean delete(K elem){
        return hashTable[elem.hashCode()%hashTable.length].remove(elem);
    }

    /**
     * Checks if the set needs to be resized, if so resizes it
     */
    private void resizeIfNecessary()
    {
        if(n/hashTable.length > 2){
            resize();
        }
    }

    /**
     * resizes the set by creating a new one, putting the existing values in it and then taking the created hashTable
     */
    private void resize()
    {
        MyHashSet<K> tempSet = new MyHashSet<>(hashTable.length*2);
        iterator().forEachRemaining(tempSet::add);
        this.hashTable = tempSet.hashTable;
    }

    /**
     *
     * @return ArrayList of all elements
     */
    public ArrayList<K> getElements(){
        ArrayList<K> ret = new ArrayList<>();
        iterator().forEachRemaining(ret::add);
        return ret;
    }

    private Iterator<K> iterator(){
        return new Iterator<K>()
        {
            int tablePosition = 0;
            Iterator<K> currentIterator = hashTable[tablePosition].iterator();

            @Override
            public boolean hasNext()
            {
                // Return true if current Iterator has next
                if(currentIterator.hasNext())
                    return true;
                // Check if we can find another iterator
                else if(tablePosition<hashTable.length-1){
                    setNextIterator();
                }
                // No other iterator found
                else{
                    return false;
                }

                // Other iterator found,
                return currentIterator.hasNext();
            }

            /**
             * Points the Iterator to the next List with elements
             */
            private void setNextIterator(){
                if(this.tablePosition < hashTable.length-1){
                    this.tablePosition++;
                    this.currentIterator = hashTable[tablePosition].iterator();
                }
                else{
                    return;
                }
                if(!currentIterator.hasNext()){
                    setNextIterator();
                }
            }

            @Override
            public K next()
            {
                if(this.currentIterator.hasNext()){
                    return this.currentIterator.next();
                }
                setNextIterator();
                return this.currentIterator.next();
            }
        };
    }

    /**
     * Main method, starts when the class is executed
     * @param args arguments passed by the command line
     */
    public static void main(String[] args)
    {
        MyHashSet<Integer> myHash = new MyHashSet<>();
        for (int i = 0; i < 30; i++) {
            myHash.add(i);
        }
        System.out.println(myHash.contains(5)); // true
        myHash.delete(5);
        System.out.println(myHash.contains(5)); // false
        ArrayList<Integer> el = myHash.getElements();
        System.out.println(el); // Zahlen 0..29 ausser der 5 unsortiert
        Collections.sort(el);
        System.out.println(el); // 0,1,2,3,4,6,7,....,29
    }
}
