public class WeightedQuickUnionUF {

    private int[] id;
    private int[] sz;

    // N array accesses
    public WeightedQuickUnionUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    // Depth of i accesses
    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]]; // Path compression
            i = id[i];
        }
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
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

}
