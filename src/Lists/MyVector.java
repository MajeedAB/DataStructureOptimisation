package Lists;

import java.util.HashMap;

public class MyVector<AnyType> implements IVector<AnyType> {

    private final int BASE_ALLOCATED_SIZE = 4;
    private AnyType elems[];
    private int allocatedSize = BASE_ALLOCATED_SIZE;
    private int size = 0;
    private static HashMap<String, Integer> stats = new HashMap<>();

    public MyVector() {
        elems = (AnyType[]) new Object[allocatedSize];
        if(stats.isEmpty()) {
            resetStatistics();
        }
    }

    public MyVector(int baseSize) {
        allocatedSize = Math.max(baseSize, BASE_ALLOCATED_SIZE);
        elems = (AnyType[]) new Object[allocatedSize];
        if(stats.isEmpty()) {
            resetStatistics();
        }
    }

    public MyVector(AnyType[] elems) {
        this.elems = elems;
        allocatedSize = elems.length;
        this.size = elems.length;
        if(stats.isEmpty()) {
            resetStatistics();
        }
    }

    public void clear() {
        size = 0;
    }

    public boolean has(AnyType elem) throws Exception{
        for (int i = 0; i < size; i++) {
            if (get(i) == elem) {
                return true;
            }
        }
        return false;
    }

    public AnyType add(AnyType elem) throws Exception {
        if(size >= allocatedSize) {
            doubleAllocation();
        }
        return set(size++, elem);
    }

    public AnyType get(int index) throws Exception {
        if(index < 0 || index >= size)
            throw new Exception("no no *finger wiggle*, respect vector size");
        incrementStats("get");
        return elems[index];
    }

    private void incrementStats(String method) {
        stats.put(method, stats.get(method)+1);
    }

    public AnyType set(int index, AnyType elem) throws Exception {
        if(index < 0 || index >= size)
            throw new Exception("no no *finger wiggle*, respect vector size");

        AnyType old = elems[index];
        elems[index] = elem;

        incrementStats("set");
        return old;
    }

    public void swap(int i, int j) throws Exception {
        AnyType tmp = get(i);
        set(i, get(j));
        set(j, tmp);
    }

    public void remove(AnyType elem) {
        // Does NOT necessarily retain element order

        int index = 0;
        while(index < size) {
            // Element found
            if(elems[index].equals(elem))
                elems[index] = elems[--size];
            else
                index++;
        }
    }

    public void removeOrdered(AnyType elem) {
        // Retains element order

        int searchIndex = 0;

        //Find first element to remove, if present
        while(searchIndex < size) {
            if(elems[searchIndex].equals(elem)) // Element found
                break;
            searchIndex++;
        }

        // If no elements to remove found, nothing to do.
        if(searchIndex == size)
            return;

        int emptyIndex = searchIndex;
        searchIndex++;

        while (searchIndex < size) {
            if(!elems[searchIndex].equals(elem)) {
                // Copy element on search index to first "empty" index (i.e. elements to be removed)
                elems[emptyIndex] = elems[searchIndex];
                emptyIndex++;
            }
            searchIndex++;
        }
        // Reduce size to "non-empty" indexes (i.e. elements to be kept)
        size = emptyIndex;
    }

    public void removeOrderedNaive(AnyType elem) {
        for(int i = 0; i < size; i++) {
            if(elems[i].equals(elem)) {
                for (int j = i+1; j < size; j++) {
                    elems[j-1] = elems[j];
                }
                size--;
            }
        }
    }

    public AnyType insert(int index, AnyType elem) throws Exception {
        if(index < 0 || index > size)
            throw new Exception("no no *finger wiggle*, respect vector size");
        if(size == allocatedSize)
            doubleAllocation();

        size++; // Add empty element at end of vector
        for (int i = size-1; i > index; i--) {
            // Iterate through vector backwards, moving elements towards end
            set(i, get(i-1));
        }

        return set(index, elem);
    }

    public void deepDisplay() {
        String lengthDisp = "-- " + size + " / " + allocatedSize + " --";
        String disp = "";
        for (int i = 0; i < allocatedSize; i++) {
            disp += elems[i] + "\t";
        }
        System.out.println(lengthDisp + "\n" + disp);
    }

    public void display() {
        String lengthDisp = "-- " + size + " --";
        String disp = "";
        for(int i = 0; i < size; i++) {
            disp += elems[i]+"\t";
        }
        System.out.println(lengthDisp + "\n" + disp);
    }

    private void doubleAllocation() throws Exception {
        AnyType[] oldElems = elems;
        // Double allocated size +1 in case of 0 allocated size
        allocatedSize = 2 * allocatedSize + 1;
        elems = (AnyType[]) new Object[allocatedSize];

        // Copy old array to new
        for(int i = 0; i < size; i++) {
            set(i, oldElems[i]);
        }
        incrementStats("doubleAllocation");
    }

    public int getSize() {
        return size;
    }

    public static HashMap<String, Integer> getStatistics() {
        return stats;
    }

    public static void resetStatistics() {
        stats.put("get", 0);
        stats.put("set", 0);
        stats.put("doubleAllocation", 0);
    }
}
