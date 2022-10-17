import Lists.MyVector;
import Trees.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        try {
            print("Hell world!\n");

            Statistics stats = new Statistics(new MyVector<Integer>());
            stats.testBubbleSort(10, 40, 1);
            stats.testMergeSort(10, 40, 1);
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