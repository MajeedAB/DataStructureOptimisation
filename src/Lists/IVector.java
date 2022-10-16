package Lists;

public interface IVector<AnyType>  extends ICollection<AnyType>, IStatCollection<AnyType> {
    public void clear();
    public AnyType get(int index) throws Exception;
    public AnyType set(int index, AnyType elem) throws Exception;

    public void remove(AnyType elem);
    public AnyType insert(int index, AnyType elem) throws Exception;
}
