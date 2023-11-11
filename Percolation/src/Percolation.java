import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int gridSize;
    private final int noOfOpen;
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

        if (col == 1) {
            // Site doesn't have left neighbour
        } else if (col == this.gridSize) {
            // Site doesn't have right neighbour
        }

        if (row == 1) {
            // Site doesn't have top neighbour
        } else if (col == this.gridSize) {
            // Site doesn't have bottom neighbour
        }

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
        Percolation percolation = new Percolation(5);
        System.out.println(percolation.percolates());
    }
}