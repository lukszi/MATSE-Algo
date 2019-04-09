public interface IList
{
    public void insertAt(Integer val, int pos);
    public void removeAt(int pos);
    public Integer getAt(int pos);
    public int search(Integer val);
    public void clear();
    public int getCount();
}
