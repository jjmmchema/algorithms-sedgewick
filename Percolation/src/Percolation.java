import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int gridSize;
    private int noOfOpen;
    private final int virtualTopId;
    private final int virtualBotId;
    private final WeightedQuickUnionUF weightedQU;

    public final boolean[] state; // True for open, False for full

    public Percolation(int n) {
        if (n < 1)
            throw new IllegalArgumentException();
        this.gridSize = n;
        noOfOpen = 0;
        virtualTopId = n*n;
        virtualBotId = n*n+1;
        weightedQU = new WeightedQuickUnionUF((n*n)+2); // Add two for a 'virtual' node at the top and the bottom
        state = new boolean[n*n];
    }

    public void open(int row, int col) {
        checkValidIdx(row, col);
        if (isOpen(row, col)) return;

        int nodeId = map2dTo1d(row, col);

        if (col != 1) {
            int leftNode = map2dTo1d(row, col-1);
            this.weightedQU.union(nodeId, leftNode);
        }
        if (col != this.gridSize) {
            int rightNode = map2dTo1d(row, col-1);
            this.weightedQU.union(nodeId, rightNode);
        }

        // Assume that these are true at first
        int topNode = virtualTopId;
        int botNode = virtualBotId;

        if (row != 1) {
            topNode = map2dTo1d(row-1, col);;
        }
        if (row != this.gridSize) {
            botNode = map2dTo1d(row+1, col);;
        }

        this.weightedQU.union(nodeId, botNode);
        this.weightedQU.union(nodeId, topNode);

        this.state[nodeId-1] = true;
        this.noOfOpen++;
    }

    public boolean isOpen(int row, int col) {
        checkValidIdx(row, col);
        int id = map2dTo1d(row, col);
        return state[id-1];
    }

    public boolean isFull(int row, int col) {
        return !isOpen(row, col);
    }

    public int numberOfOpenSites() { return this.noOfOpen; }

    public boolean percolates() {
        // weightedQU.connected(virtualTopId, virtualBotId) // Deprecated??
        return weightedQU.find(virtualTopId) == weightedQU.find(virtualBotId);
    }

    private void checkValidIdx(int row, int col) {
        if (row < 1 || row > this.gridSize || col < 1 || col > this.gridSize)
            throw new IllegalArgumentException();
    }

    public int map2dTo1d(int row, int col) {
        int zeroIdxRow = row-1;
        int zeroIdxCol = col-1;
        return (zeroIdxRow*this.gridSize + zeroIdxCol) + 1;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(1);
        System.out.println(percolation.percolates());
        percolation.open(1, 1);
        System.out.println(percolation.percolates());

        percolation = new Percolation(3);
        System.out.println(percolation.noOfOpen);
        percolation.open(1, 1);
        System.out.println(percolation.noOfOpen);
        System.out.println(percolation.isOpen(1, 1));

        System.out.println(percolation.percolates());
        percolation.open(1, 2);
        percolation.open(2, 2);
        percolation.open(3, 2);
        System.out.println(percolation.percolates());

    }
}