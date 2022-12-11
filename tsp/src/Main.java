import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private final int N, start;
    private final double[][] distance;
    private List<Integer> tour = new ArrayList<>();
    private double minTourCost = Double.POSITIVE_INFINITY;
    private boolean ranSolver = false;

    public Main(double[][] distance) {
        this(0, distance);
    }

    public Main(int start, double[][] distance) {
        N = distance.length;

        if (N <= 2) throw new IllegalStateException("N <= 2 not yet supported.");
        if (N != distance[0].length) throw new IllegalStateException("Matrix must be square (n x n)");
        if (start < 0 || start >= N) throw new IllegalArgumentException("Invalid start node.");

        this.start = start;
        this.distance = distance;
    }

    // Returns the optimal tour for the traveling salesman problem.
    public List<Integer> getTour() {
        if (!ranSolver) solve();
        return tour;
    }

    // Returns the minimal tour cost.
    public double getTourCost() {
        if (!ranSolver) solve();
        return minTourCost;
    }

    // Solves the traveling salesman problem and caches solution.
    public void solve() {

        if (ranSolver) return;

        final int END_STATE = (1 << N) - 1;
        Double[][] memo = new Double[N][1 << N];

        // Add all outgoing edges from the starting node to memo table.
        for (int end = 0; end < N; end++) {
            if (end == start) continue;
            memo[end][(1 << start) | (1 << end)] = distance[start][end];
        }

        for (int r = 3; r <= N; r++) {
            for (int subset : combinations(r, N)) {
                if (notIn(start, subset)) continue;
                for (int next = 0; next < N; next++) {
                    if (next == start || notIn(next, subset)) continue;
                    int subsetWithoutNext = subset ^ (1 << next);
                    double minDist = Double.POSITIVE_INFINITY;
                    for (int end = 0; end < N; end++) {
                        if (end == start || end == next || notIn(end, subset)) continue;
                        double newDistance = memo[end][subsetWithoutNext] + distance[end][next];
                        if (newDistance < minDist) {
                            minDist = newDistance;
                        }
                    }
                    memo[next][subset] = minDist;
                }
            }
        }

        // Connect tour back to starting node and minimize cost.
        for (int i = 0; i < N; i++) {
            if (i == start) continue;
            double tourCost = memo[i][END_STATE] + distance[i][start];
            if (tourCost < minTourCost) {
                minTourCost = tourCost;
            }
        }

        int lastIndex = start;
        int state = END_STATE;
        tour.add(start);

        // Reconstruct TSP path from memo table.
        for (int i = 1; i < N; i++) {

            int index = -1;
            for (int j = 0; j < N; j++) {
                if (j == start || notIn(j, state)) continue;
                if (index == -1) index = j;
                double prevDist = memo[index][state] + distance[index][lastIndex];
                double newDist  = memo[j][state] + distance[j][lastIndex];
                if (newDist < prevDist) {
                    index = j;
                }
            }

            tour.add(index);
            state = state ^ (1 << index);
            lastIndex = index;
        }

        tour.add(start);
        Collections.reverse(tour);

        ranSolver = true;
    }

    private static boolean notIn(int elem, int subset) {
        return ((1 << elem) & subset) == 0;
    }

    // This method generates all bit sets of size n where r bits
    // are set to one. The result is returned as a list of integer masks.
    public static List<Integer> combinations(int r, int n) {
        List<Integer> subsets = new ArrayList<>();
        combinations(0, 0, r, n, subsets);
        return subsets;
    }

    // To find all the combinations of size r we need to recurse until we have
    // selected r elements (aka r = 0), otherwise if r != 0 then we still need to select
    // an element which is found after the position of our last selected element
    private static void combinations(int set, int at, int r, int n, List<Integer> subsets) {

        // Return early if there are more elements left to select than what is available.
        int elementsLeftToPick = n - at;
        if (elementsLeftToPick < r) return;

        // We selected 'r' elements so we found a valid subset!
        if (r == 0) {
            subsets.add(set);
        } else {
            for (int i = at; i < n; i++) {
                // Try including this element
                set |= 1 << i;

                combinations(set, i + 1, r - 1, n, subsets);

                // Backtrack and try the instance where we did not include this element
                set &= ~(1 << i);
            }
        }
    }

    public static void main(String[] args) {
        // Create adjacency matrix
        int n = 18;


        double distanceMatrix[][] =  {
                {0,     7,	10,	4,	7,	10,	6,	8,	12,	15,	8,	13,	17,	16,	14,	19,	13,	10},
                {7,	    0,	3,	5,	4,  3,	9,	7,	5,	8,	11,	6,	10,	9, 11,	13,	6,	9},
                {10,	3,	0,	8,	7,	6,	12,	10,	6,	5,	14,	9,	7,	10, 14,	10,	3,	12},
                {4,	    5,	8,	0,	3,	6,	4,	4,	8,	11,	6,	9,	13,	12, 10,	16,	9,	6},
                {7,	    4,	7,	3,	0,	3,	5,	3,	5,	8,	7,	6,	10,	9, 7,	13,	6,	5},
                {10,	3,	6,	6,	3,	0,	6,	4,	2,	5,	8,	3,	7,	6, 8,	10,	3,	6},
                {6,	    9,	12,	4,	5,	6,	0,	2,	6,	9,	2,	7,	11,	10, 8,	14,	9,	4},
                {8,	7,	10,	4,	3,	4,	2,	0,	4,	7,	4,	5,	9,	8, 6,	12,	7,	2},
                {12, 5,	6,	8,	5,	2,	6,	4,	0,	3,	8,	3,	5,	4, 8,	8,	3,	6},
                {15,	8,	5,	11,	8,	5,	9,	7,	3,	0,	11,	6,	4,	7, 11,	5,	2,	9},
                {8,	    11,	14,	6,	7,	8,  2,	4,	8,	11,	0,	5,  9,	8, 6,	12,	11,	2},
                {13,	6,	9,	9,	6,	3,	7,	5,	3,	6,	5,	0,	4,	3, 5,	7,	6,	3},
                {17,	10,	7,	13,	10,	7,	11,	9,  5,	4,	9,	4,	0,	3, 7,	3,	4,	7},
                {16,	9,	10,	12,	9,	6,	10,	8,	4,  7,	8,	3,	3,	0, 4,	3,	7,	6},
                {14,	11,	14,	10,	7,	8,	8,	6,	8,	11,	6,	5,	7,	4, 0,	6,	11,	4},
                {19,	13,	10,	16,	13,	10,	14,	12,	8,	5,	12,	7,	3,	3, 6,	0,	7,	10},
                {13,	6,	3,	9,	6,	3,	9,	7,	3,	2,	11,	6,	4,	7, 11,	7,	0,	9},
                {10,	9,	12,	6,	5,	6,	4,	2,	6,	9,	2,	3,	7,	6, 4,	10,	9,	0}};


        int startNode = 0;
        Main solver = new Main(startNode, distanceMatrix);

        // Prints: [0, 3, 2, 4, 1, 5, 0]
        System.out.println("Tour: " + solver.getTour());

        // Print: 42.0
        System.out.println("Tour cost: " + solver.getTourCost());
    }
}