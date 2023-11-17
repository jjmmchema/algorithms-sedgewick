import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int gridSize;
    private int noOfOpen;
    private final int virtualTopId;
    private final int virtualBotId;
    private final WeightedQuickUnionUF weightedQU;

    private final boolean[] state; // True for open, False for full

    public Percolation(int n) {
        if (n < 1)
            throw new IllegalArgumentException();
        this.gridSize = n;
        noOfOpen = 0;
        virtualTopId = n * n;
        virtualBotId = n * n + 1;
        weightedQU = new WeightedQuickUnionUF((n * n) + 2); // Add two for a 'virtual' node at the top and the bottom
        state = new boolean[n * n + 2];
        state[virtualTopId] = true;
        state[virtualBotId] = true;
    }

    public void open(int row, int col) {
        checkValidIdx(row, col);
        if (isOpen(row, col)) return;

        int nodeId = map2dTo1d(row, col);

        if (col != 1) {
            int leftNode = map2dTo1d(row, col - 1);
            if (state[leftNode])
                this.weightedQU.union(nodeId, leftNode);
        }
        if (col != this.gridSize) {
            int rightNode = map2dTo1d(row, col + 1);
            if (state[rightNode])
                this.weightedQU.union(nodeId, rightNode);
        }

        // Assume that these are true at first
        int topNode = virtualTopId;
        int botNode = virtualBotId;

        if (row != 1) {
            topNode = map2dTo1d(row - 1, col);
        }
        if (row != this.gridSize) {
            botNode = map2dTo1d(row + 1, col);
        }

        if (state[botNode])
            this.weightedQU.union(nodeId, botNode);
        if (state[topNode])
            this.weightedQU.union(nodeId, topNode);

        this.state[nodeId] = true;
        this.noOfOpen++;
    }

    public boolean isOpen(int row, int col) {
        checkValidIdx(row, col);
        int id = map2dTo1d(row, col);
        return state[id];
    }

    public boolean isFull(int row, int col) {
        checkValidIdx(row, col);
        return weightedQU.find(map2dTo1d(row, col)) == weightedQU.find(virtualTopId);
    }

    public int numberOfOpenSites() {
        return this.noOfOpen;
    }

    public boolean percolates() {
        return weightedQU.find(virtualTopId) == weightedQU.find(virtualBotId);
    }

    private void checkValidIdx(int row, int col) {
        if (row < 1 || row > this.gridSize || col < 1 || col > this.gridSize)
            throw new IllegalArgumentException();
    }

    private int map2dTo1d(int row, int col) {
        int zeroIdxRow = row - 1;
        int zeroIdxCol = col - 1;
        return (zeroIdxRow *  this.gridSize + zeroIdxCol);
    }

    public static void main(String[] args) {  }
}