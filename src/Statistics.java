import Lists.*;

import java.util.Random;
public class Statistics {

    private IStatCollection collection;

    public Statistics(IStatCollection coll) {
        this.collection = coll;
    }

    public void setCollection(IStatCollection coll) {
        this.collection = coll;
    }

    public void testCommandLoop(int nEnd) throws Exception {
        testCommandLoop(0, nEnd, 1);
    }

    public void testCommandLoop(int nStart, int nEnd) throws Exception {
        testCommandLoop(nStart, nEnd, 1);
    }

    public void testCommandLoop(int nStart, int nEnd, int increment) throws Exception {

        MyVector<Integer> setCount = new MyVector<>();
        MyVector<Integer> getCount = new MyVector<>();

        for(int n = nStart; n <= nEnd; n+=increment)
        {
            for (int i = 0; i < n; i++) {
                collection.add(11 * i);
            }
            System.out.println(IStatCollection.getStatistics());
            collection.deepDisplay();
            setCount.add(IStatCollection.getStatistics().get("set"));
            getCount.add(IStatCollection.getStatistics().get("get"));
            collection = new MyVector<>();
            IStatCollection.resetStatistics();
        }
        setCount.display();
        getCount.display();
    }

    public void testMergeSort(int nStart, int nEnd, int increment) throws Exception
    {
        MyVector setCount = new MyVector<>();
        MyVector getCount = new MyVector<>();

        for (int n = nStart; n < nEnd; n+=increment) {
//            System.out.println("========== N  = " + n + " ============");
            MyVector vect = generateRandomVector(n);
            MyVector.resetStatistics();
            vect = Sorter.mergeSort(vect);
//            System.out.println(Lists.MyVector.getStatistics());
//            vect.display();
            setCount.add(MyVector.getStatistics().get("set"));
            getCount.add(MyVector.getStatistics().get("get"));
        }
        System.out.print(" set() calls ");
        setCount.display();
        System.out.print(" get() calls ");
        getCount.display();

    }

    public void testBubbleSort(int nStart, int nEnd, int increment) throws Exception
    {
        MyVector setCount = new MyVector<>();
        MyVector getCount = new MyVector<>();

        for (int n = nStart; n < nEnd; n+=increment) {
//            System.out.println("========== N  = " + n + " ============");
            MyVector vect = generateRandomVector(n);
            MyVector.resetStatistics();
            Sorter.bubbleSort(vect);
//            System.out.println(Lists.MyVector.getStatistics());
//            vect.display();
            setCount.add(MyVector.getStatistics().get("set"));
            getCount.add(MyVector.getStatistics().get("get"));
        }
        System.out.print(" set() calls ");
        setCount.display();
        System.out.print(" get() calls ");
        getCount.display();

    }


    private MyVector generateRandomVector(int size) throws Exception{
        Random random = new Random();

        MyVector vect = new MyVector<>();
        for (int i = 0; i < size; i++) {
            vect.add(random.nextInt(-100, 100));
        }

        return vect;
    }

}
