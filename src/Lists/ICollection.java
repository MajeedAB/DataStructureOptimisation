package Lists;

public interface ICollection<AnyType> {
    public boolean has(AnyType elem) throws Exception;
    public AnyType add(AnyType elem) throws Exception;
    public void display();
    public void deepDisplay();

}
