import java.util.Arrays;

public class Sort
{
    // Insertionsort
    public static void main(String[] args)
    {
        int[] num = {3,8,7,5,4,1};

        int j;

        int key;
        int i;
        for(j=1; j< num.length; j++){
            key = num[j];
            for(i=j-1; i>=0&&num[i]>key; i--){
                num[i+1] = num[i];
            }
            num[i+1] = key;
            System.out.println(Arrays.toString(num));
        }
    }
}
