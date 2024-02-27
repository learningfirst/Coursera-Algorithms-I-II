package sort;

public class Nuts
{
    int nuts[];
    int bolts[];
    public Nuts(int a[], int b[]){
        nuts = new int[a.length];
        bolts = new int[b.length];
        for (int i = 0; i < a.length; i++)
        {
            nuts[i] = a[i];
            bolts[i] = b[i];
        }
    }

    public void quickSort( int i, int low, int high)
    {
        if(low >= high)  return;
        int index1 = sort(nuts, i, low, high);
        sort(bolts, nuts[index1], low, high);
        quickSort(bolts[low], low, index1-1);
        quickSort(bolts[index1+1], index1+1, high);
    }

    private void swap(int a[], int x,int y){
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    public int sort(int a[], int i, int low, int high){
        int index = 0;
        while (low<high)
        {
            while (a[low] <= i && low<a.length) {
                if(a[low] == i) index = low;
                low++;
            }
            while (a[high] > i) {
                high--;
            }
            if(high <= low) {
                int temp = a[high];
                a[high] = a[index];
                a[index] = temp;
                index = high;
            }else
                swap(a, high, low);
        }
        return index;
    }


    public static void main(String[] args)
    {
        int nuts[] = {3,1,5,2,4,6,0,8};
        int bolts[] =  {3,1,5,2,4,6,0,8};
        Nuts nut = new Nuts(nuts, bolts);
        nut.quickSort(nuts[0], 0, 7);
        for (int i = 0; i < nuts.length; i++)
        {
            System.out.println(nut.nuts[i]);
        }
    }
}
