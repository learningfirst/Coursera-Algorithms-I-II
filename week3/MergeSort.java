package sort;

public class MergeSort
{
    private int aux[];
    private int mid;

    public MergeSort(int length)
    {
        aux = new int[length/2];
        mid = length/2-1;
    }
    
    public void merge(int[] a){
        for (int i = 0; i < a.length/2; i++)
        {
            aux[i] = a[i];
        }
        int i=0; int j=mid+1;
        for (int k = 0; k < a.length; k++)
        {
            if(i > mid) a[k] = a[j++];
            else if(j >a.length-1) a[k] = aux[i++];
            else  if(aux[i] <= a[j]) a[k] = aux[i++];
            else a[k] = a[j++];
        }
    }

    public static void main(String[] args)
    {
        int a[] = {0,2,3,5,7,2,4,5,8,9};
        MergeSort mergeSort = new MergeSort(10);
        mergeSort.merge(a);
        for (int i = 0; i < a.length; i++)
        {
            System.out.println(a[i]);
        }
    }
}
