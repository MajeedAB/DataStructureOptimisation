import Lists.MyVector;
import Trees.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        try {
            print("Hell world!\n");

//            Statistics stats = new Statistics(new MyVector<Integer>());
//            stats.testBubbleSort(10, 40, 1);
//            stats.testMergeSort(10, 40, 1);

            BinarySearchTree tree = new BinarySearchTree(5);
            tree.insert(9);
            tree.insert(2);
            tree.insert(1);
            tree.insert(6);
            tree.insert(3);
            tree.insert(11);
            tree.insert(13);
            tree.insert(4);
            tree.displayByLevel();

            tree.remove(5);
            tree.remove(4);
            tree.displayByLevel();
            tree.remove(9);
            tree.displayByLevel();
        }

        catch (Exception e) {
            print(e.getMessage());
            print("\nbravo, you got an exception!");
        }
    }

    static void print(Object e) {
        System.out.println(e);
    }
}