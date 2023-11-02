public class QuickUnionUF {

    private int[] id;

    // N array accesses
    public QuickUnionUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    // Depth of i accesses
    private int root(int i) {
        while (i != id[i])
            i = id[i];
        return i;
    }

    // // Depth of p and q accesses
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // // Depth of p and q accesses
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        id[i] = j;
    }

}
