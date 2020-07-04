import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int size;
    private final boolean[] grid;
    private final int virtualTopId;
    private final int virtualBtmId;
    private int numOfOpenSite = 0;
    private final WeightedQuickUnionUF wqf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.size = n;
        this.grid = new boolean[size * size + 2];
        this.virtualTopId = 0;
        this.virtualBtmId = size * size + 1;
        wqf = new WeightedQuickUnionUF(size * size + 2);
//        for (int i = 1; i <= size; i++) {
//            wqf.union(i, virtualTopId);
//            wqf.union(size * (size - 1) + i, virtualBtmId);
//        }
    }

    private int serialize(int row, int col) {
        return (row - 1) * size + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (serialize(row, col) < 1 || serialize(row, col) > size * size)
            throw new IllegalArgumentException("error in open");
        if (!isOpen(row, col)) {
            grid[serialize(row, col)] = true;
            if (row == 1) wqf.union(serialize(row, col), virtualTopId);
            else if (row == size) wqf.union(serialize(row, col), virtualBtmId);
            numOfOpenSite++;
            if (row != 1 && isOpen(row - 1, col))
                wqf.union(serialize(row, col), serialize(row - 1, col)); // up
            if (row != size && isOpen(row + 1, col))
                wqf.union(serialize(row, col), serialize(row + 1, col)); // down
            if (col != 1 && isOpen(row, col - 1))
                wqf.union(serialize(row, col), serialize(row, col - 1)); // left
            if (col != size && isOpen(row, col + 1))
                wqf.union(serialize(row, col), serialize(row, col + 1)); // right
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (serialize(row, col) < 1 || serialize(row, col) > size * size)
            throw new IllegalArgumentException("error in open");
        return grid[serialize(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (serialize(row, col) < 1 || serialize(row, col) > size * size)
            throw new IllegalArgumentException("error in open");
        return wqf.find(serialize(row, col)) == wqf.find(virtualTopId);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOfOpenSite;
    }

    // does the system percolate?
    public boolean percolates() {
        return wqf.find(virtualTopId) == wqf.find(virtualBtmId);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 1);
        p.open(2, 1);
        p.open(3, 1);
        System.out.println("isFUll ? " + p.isFull(1, 1));
        System.out.println("result = " + p.percolates());
        System.out.println("numOfOpen = " + p.numberOfOpenSites());
    }
}
