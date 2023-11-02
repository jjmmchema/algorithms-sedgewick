public class QuickFindUF {

    private int[] id;

    // N array accesses
    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    // 2 array accesses
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    // 2N + 2 array accesses, N = id.length at initialization
    public void union(int p, int q) {
        int pId = id[p];
        int qId = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId)
                id[i] = qId;
        }
    }

}
