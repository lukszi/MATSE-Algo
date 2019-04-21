import java.util.ArrayList;

/**
 * Repräsentiert einen B-Baum der Ordnung 2, welcher Integer-Werte enthält.
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class BTree{
    /**
     * innere Klasse zum darstellen eines Nodes. Die Attribute sind public, um Code zu vereinfachen und da von aussen
     * nicht auf die Klasse zugegriffen werden kann.
     */
    private static class BTreeNode{
        ArrayList<Integer> values = new ArrayList<>();
        ArrayList<BTreeNode> children = new ArrayList<>();
        BTreeNode parent;
    }
    private BTreeNode root = new BTreeNode();

    /**
     * Gibt wieder, ob der Wert x bereits im Baum vorhanden ist.
     * @param x der zu suchende Wert
     * @return ob der Wert bereits vorhanden ist
     */
    public boolean search(int x){
        BTreeNode currentNode = root;
        while (true){
            if (currentNode.values.contains(x)){
                return true;
            }
            currentNode = getFollowingNode(x, currentNode);
            if (currentNode == null){
                return false;
            }
        }
    }

    /**
     * Fuegt einen Wert x dem Baum hinzu. Gibt 0 wieder, wenn der Wert einfach eingefuegt werden konnte, 1, wenn eine neue Node erstellt wurde
     * und 2, wenn eine neue Ebene erstellt wurde.
     * @param x der hinzuzufuegende Wert
     * @return 0, 1, oder 2
     * @throws ArithmeticException , wenn der Wert bereits vorhanden ist.
     */
    public int insert(int x){
        BTreeNode currentNode = root;
        while (true){   //determine insertionNode
            if (currentNode.values.contains(x)){
                throw new ArithmeticException("Wert war bereits vorhanden.");
            }
            BTreeNode nextNode = getFollowingNode(x, currentNode);
            if (nextNode == null){
                break;
            } else {
                currentNode = nextNode;
            }
        }
        //Element einfuegen
        einfuegen(x, currentNode);
        //wenn nötig ausbalancieren
        if (currentNode.values.size() <= 4){ //maximale Anzahl im B-Baumcdes der Ordnung 2
            return 0;
        } else {
            return spalten(currentNode);
        }
    }

    /**
     * Gibt die nachfolgende Node, nach Richtung x wieder
     * @param x der Wert zudem die Node passen soll
     * @param currentNode die aktuelle Node
     * @return die naechste Node, oder null, wenn keine darauffolgende existiert
     */
    private static BTreeNode getFollowingNode(int x, BTreeNode currentNode){
        if (currentNode.children.isEmpty()){
            return null;
        }
        for (int index = 0; index < currentNode.values.size(); ++index){
            int value = currentNode.values.get(index);
            if (x < value){
                return currentNode.children.get(index);
            } else if (index == currentNode.values.size()-1){  //last value has to be tested to get last child
                return currentNode.children.get(index + 1);
            }
        }
        return null;
    }

    /**
     * fuegt einen Wert x sortiert in eine Node ein
     * @param x der Wert
     * @param node die Node
     * @return der Index, andem eingefuegt wurde
     */
    private static int einfuegen(int x, BTreeNode node){
        if (node.values.size() == 0){   //erstes Element gesondert betrachten
            node.values.add(x);
            return 0;
        }
        for (int index = 0; index < node.values.size(); ++index){
            int value = node.values.get(index);
            if (x < value){
                node.values.add(index, x);
                return index;
            } else if (index == node.values.size()-1){
                node.values.add(x);
                return index+1;
            }
        }
        return -1;
    }

    /**
     * Rekursiver Aufruf zu Spalten einer zu gross gewordenen Node
     * @param node die Node
     * @return 1, wenn eine neue Node erstellt wurde, 2, wenn eine neue Ebene erstellt wurde
     */
    private int spalten(BTreeNode node){
        if (node.parent != null){   //Element hochziehen
            subSpaltung(node, node.parent);
            if (node.parent.values.size() > 4) {
                return spalten(node.parent);
            } else {
                return 1;
            }
        } else {    //neue Wurzel erstellen
            root = new BTreeNode();
            subSpaltung(node, root);
            return 2;
        }
    }

    /**
     * Spaltet eine Node in zwei Nodes und setzt noetige Attribute und die der Eltern-Node neu
     * @param node die zu spaltende Node
     * @param parent die Eltern-Node
     */
    private static void subSpaltung(BTreeNode node, BTreeNode parent){
        int index = einfuegen(node.values.get(2), parent);
        //linke Seite erstellen
        BTreeNode leftSide = new BTreeNode();
        leftSide.values.add(node.values.get(0));
        leftSide.values.add(node.values.get(1));
        leftSide.parent = parent;
        if (!parent.children.isEmpty()){ //linke Seite nur als Kind aktualisieren, wenn eine neue Wurzel erzeugt wurde(noch keine Kind-Elemente gesetzt wurden)
            parent.children.remove(index);
        }
        parent.children.add(index, leftSide);
        //rechte Seite erstellen
        BTreeNode rightSide = new BTreeNode();
        rightSide.values.add(node.values.get(3));
        rightSide.values.add(node.values.get(4));
        rightSide.parent = parent;
        parent.children.add(index + 1, rightSide);
        if (!node.children.isEmpty()){  //Kinder aktualisieren
            for (int i = 0; i < 6; ++i){
                if (i < 3){ //linke Kinder
                    leftSide.children.add(node.children.get(i));
                } else {
                    rightSide.children.add(node.children.get(i));
                }
            }
        }
    }

    /**
     * Testet die Klasse BTree anhand der uebergebenen Testfunktion
     */
    public static void main(String[] args) {
        BTree btree = new BTree();
        int [] toInsert = {5,15,10,20,25,30,3,4,29,28,2,21,26,22,8,12,13};
        String solution = "00002000011001002";

        String statusOfInsertion = "";
        for(int x : toInsert){
            statusOfInsertion += btree.insert(x);
        }
        System.out.println("Das Einfuegen in den Baum hat richtig funktioniert: " + solution.equals(statusOfInsertion));
        System.out.println(btree.search(26));  //true
        System.out.println(btree.search(100));  //false
    }
}