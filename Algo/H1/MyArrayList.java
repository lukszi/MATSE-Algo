public class MyArrayList<T>
{
    private Object[] feld;
    private int position;

    public MyArrayList(){clear();}

    public void clear(){
        position = 0;
        feld = new Object[10];
    }

    public T get(int index){
        if(position <= index){
            throw new ArrayIndexOutOfBoundsException();
        }
        else return (T)feld[index];
    }

    public void addFirst(T elem){
        if(feld.length == position+1){
            resize();
        }
        Object[] temp = new Object[feld.length];
        System.arraycopy(feld,0,temp,1, feld.length-1);
        this.feld = temp;
        this.feld[0] = elem;
        ++position;
    }

    public void addLast(T elem){
        if(feld.length == position+1){
            resize();
        }
        this.feld[this.position] = elem;
        this.position++;
    }

    private void resize()
    {
        System.out.println("resizing");
        Object[] temp = new Object[feld.length*2];
        System.arraycopy(feld,0,temp,0, feld.length);
        this.feld = temp;
    }

    public int size(){ return position;}
}
