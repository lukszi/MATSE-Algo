public class BinTree
{
    private TreeNode root;

    public BinTree(){

    }

    private TreeNode getNode(int x){
        TreeNode temp = root;
        while(temp != null){
            if(temp.getData()==x)
                break;
            else if(temp.getData()> x)
                temp = temp.getLeft();
            else if(temp.getData()<x)
                temp = temp.getRight();
        }
        return temp;
    }

    private TreeNode getParentNode(int x){
        TreeNode parentNode, temp;
        temp = parentNode = root;
        boolean found = false;
        while(temp != null){
            if(temp.getData()==x){
                found = true;
                break;
            }

            parentNode = temp;
            if(temp.getData()> x){
                temp = temp.getLeft();
            }
            else if(temp.getData()<x){
                temp = temp.getRight();
            }
        }
        if(!found)
            return null;
        if(temp == root)
            return null;
        return parentNode;
    }

    public void clear(){
        root = null;
    }

    public void insert(int x){
        TreeNode parentNode, temp, insertNode;
        insertNode = new TreeNode();
        insertNode.setData(x);

        // If tree is empty set root
        if(root == null){
            root = insertNode;
            return;
        }
        temp = parentNode = root;

        boolean found = false;
        while(temp != null){
            parentNode = temp;
            if(temp.getData()==x){
                found = true;
                break;
            }
            else if(temp.getData()> x){
                temp = temp.getLeft();
            }
            else if(temp.getData()<x){
                temp = temp.getRight();
            }
        }
        if(found)
            throw new ArithmeticException("Element: " + x + " already in tree!");

        if(parentNode.getData()>x)
            parentNode.setLeft(insertNode);
        else
            parentNode.setRight(insertNode);
    }

    public void remove(int x){
        TreeNode node = getNode(x);
        TreeNode parent = getParentNode(x);

        // Only delete yourself if leaf
        if(node.getLeft() == null && node.getRight() == null){
            if(parent.getData() < x){
                parent.setRight(null);
            }
            else
                parent.setLeft(null);
            return;
        }

        TreeNode replacement = getReplacement(node);
        if(replacement != null)
            remove(replacement.getData());

        if(parent != null){
            int parentVal = parent.getData();
            if(parentVal < x){
                parent.setRight(replacement);
            }
            else
                parent.setLeft(replacement);
        }
    }

    private TreeNode getReplacement(TreeNode node)
    {
        if(node.getRight() != null){
            TreeNode ret = node.getRight();
            while(ret.getLeft() != null){
                ret = ret.getLeft();
            }
            return ret;
        }
        else if(node.getLeft() != null){
            TreeNode ret = node.getLeft();
            while(ret.getRight() != null){
                ret = ret.getRight();
            }
            return ret;
        }
        return null;
    }

    public static void main(String[] args) {
        BinTree tree = new BinTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(50);
        int[] testcases = { 30, 35, 50 };
        for (int testcase : testcases) {
            TreeNode node = tree.getNode(testcase);
            if (node == null) {
                System.out.println("Knoten " + testcase + " nicht gefunden.");
            } else {
                System.out.println("Knoten " + testcase + " gefunden: " + node.getData());
            }
        }
        tree.remove(30);
        System.out.println("Knoten geloescht: 30");
        System.out.println("Elternknoten von 50: " + tree.getParentNode(50).getData());// 20
    }
}
