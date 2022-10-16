package Lists;

public class Sorter {

    public static void bubbleSort(MyVector<Comparable> vect) throws Exception {
        boolean noSwap = false;
        while(!noSwap) {
            noSwap = true;
            for(int i = 1; i < vect.getSize(); i++) {
                if(vect.get(i).compareTo(vect.get(i-1)) < 0) {
                    vect.swap(i, i-1);
                    noSwap = false;
                }
            }
        }
    }

    public static MyVector mergeSort(MyVector<Comparable> vect) throws Exception {
        return mergeSortRec(vect, 0, vect.getSize()-1);
    }

    private static MyVector mergeSortRec(MyVector<Comparable> vect, int leftBound, int rightBound) throws Exception {
        // vect = full vector, leftBound and right bounds are indexes *included* in range to be sorted.

        // Break condition, when only one element
        if(rightBound==leftBound) {
            Comparable[] arr = {vect.get(leftBound)};
            return new MyVector(arr);
        }

        //Recursive calls
        int middleBound = leftBound + (rightBound - leftBound)/2; // Euclidean division
        MyVector leftHalfSorted = mergeSortRec(vect, leftBound, middleBound);
        MyVector rightHalfSorted = mergeSortRec(vect, middleBound+1, rightBound);

        //Merge Sorted halfs
        return merge(leftHalfSorted, rightHalfSorted);
    }

    private static MyVector merge(MyVector<Comparable> leftHalf, MyVector<Comparable> rightHalf) throws Exception {
        int leftHalfSize = leftHalf.getSize();
        int rightHalfSize = rightHalf.getSize();
        int indexL = 0;
        int indexR = 0;

        MyVector mergeVect  = new MyVector<>(leftHalfSize + rightHalfSize);

        //Merge until one half is done
        while(indexL < leftHalfSize && indexR < rightHalfSize) {
            if( leftHalf.get(indexL).compareTo(rightHalf.get(indexR)) <= 0 ) {
                // If next object in leftHalf lesser than the next object in rightHalf
                mergeVect.add(leftHalf.get(indexL++));
            }
            else {
                mergeVect.add(rightHalf.get(indexR++));
            }
        }

        // Add remaining objects
        while(indexL < leftHalfSize) {
            mergeVect.add(leftHalf.get(indexL++));
        }
        while(indexR < rightHalfSize) {
            mergeVect.add(rightHalf.get(indexR++));
        }

        return mergeVect;
    }

}
