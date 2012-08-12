package lb.kollect.containers;

public class ArrContainer<T>
{

    @SuppressWarnings("unchecked")
    protected T[] items = (T[]) new Object[16];

    protected int count = 0;


    protected void ensureCapacityForOneItemMore()
    {
        ensureCapacity(count+1);
    }


    public void ensureCapacity(int desiredCapacity)
    {
        if (desiredCapacity <= items.length)
            return;

        int newCapacity = items.length << 1;
        while (desiredCapacity < newCapacity)
            newCapacity <<= 1;

        @SuppressWarnings("unchecked")
        T[] newItems = (T[]) new Object[newCapacity];

        if (count > 0)
            System.arraycopy(items, 0, newItems, 0, count);

        items = newItems;
    }


    public int getCapacity()
    {
        return items.length;
    }


    T[] takeAllItems()
    {
        T[] takenItems = items;
        count = 0;
        //noinspection unchecked
        items = (T[]) new Object[16];
        return takenItems;
    }


}
