package Lists;

import java.util.HashMap;

public interface IStatCollection<AnyType> extends ICollection<AnyType> {

        public void clear();
        public AnyType get(int index) throws Exception;
        public AnyType set(int index, AnyType elem) throws Exception;

        public void remove(AnyType elem);
        public AnyType insert(int index, AnyType elem) throws Exception;

        public static HashMap<String, Integer> getStatistics() {return null;}
        public static void resetStatistics() {}
}
