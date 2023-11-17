import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final int trials;
    private final double[] thresholds;

    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1)
            throw new IllegalArgumentException();

        this.trials = trials;
        thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int randRow = StdRandom.uniformInt(1, n + 1);
                int randCol = StdRandom.uniformInt(1, n + 1);
                p.open(randRow, randCol);
            }
            thresholds[i] = p.numberOfOpenSites() / ((double) (n * n));
        }

    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo() {
        double b = (CONFIDENCE_95 * stddev()) / Math.sqrt(trials);
        return mean() - b;
    }

    public double confidenceHi() {
        double b = (CONFIDENCE_95 * stddev()) / Math.sqrt(trials);
        return mean() + b;
    }

    private String printResults() {
        return String.format("mean \t\t\t\t\t = %f \nstddev \t\t\t\t\t = %f \n95%% confidence interval  = [%f, %f]",
                mean(),
                stddev(),
                confidenceLo(),
                confidenceHi());
    }

    // ALT+SHIFT+F10 to change cli arguments
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
//        ps.runMonteCarloSim();
        System.out.println(ps.printResults());
    }
}
