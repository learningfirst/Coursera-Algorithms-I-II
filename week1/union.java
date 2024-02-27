package union;


class unionNetwork
{
    private int[] net;
    private  int[] size;
    public int count=0;
    private int[] max;

    //≥ı ºªØ
    public unionNetwork(int n)
    {
        net=new int[n];
        size=new int[n];
        max=new int[n];
        for(int i=0;i<n;i++)
        {
            net[i]=i;
            size[i]=1;
            max[i]=i;
        }
    }

    public int root(int i)
    {
        while(i!=net[i])
        {
            i=net[i];
        }
        return i;
    }

    public boolean isConnected(int a,int b)
    {
        if(root(a)==root(b))
           return true;
        return false;
    }

    public void union(int p,int q)
    {
        int i=root(p);
        int j=root(q);
        if(i!=j)
        {
            if(size[i]>size[j])   {net[j]=i;size[i]+=size[j]; max[i]=j;}
            else  {net[i]=j;size[j]+=size[i];}
        }
    }

    public void delete(int n)
    {
         union(n,n+1);

    }

    public int successor(int n)
    {
        return max[root(n+1)];
    }

    public static void main(String[] args)
    {
        unionNetwork unionNetwork1=new unionNetwork(10);
        unionNetwork1.delete(2);
        unionNetwork1.delete(3);
        unionNetwork1.delete(4);
        unionNetwork1.delete(6);
        System.out.println(unionNetwork1.successor(2));
        System.out.println(unionNetwork1.successor(1));
        System.out.println(unionNetwork1.successor(0));
        System.out.println(unionNetwork1.successor(5));
        unionNetwork1.delete(5);
        System.out.println(unionNetwork1.successor(1));
    }
}

