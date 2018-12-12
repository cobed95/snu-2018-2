package ds.queue;

import java.util.NoSuchElementException;

public class DistanceQueue {
    private int maxN;        // maximum number of elements on PQ
    private int n;           // number of elements on PQ
    private int[] pq;        // binary heap using 1-based indexing
    private int[] qp;
    private double[] dist;      // dist[i] = priority of i

    @SuppressWarnings("unchecked")
    public DistanceQueue(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        n = 0;
        dist = new double[maxN + 1];    // make this of length maxN??
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];                   // make this of length maxN??
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(int i) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        return qp[i] != -1;
    }

    public int size() {
        return n;
    }

    public void insert(int i, double dist) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
        n++;
        qp[i] = n;
        pq[n] = i;
        this.dist[i] = dist;
        siftUp(n);
    }

    public int minIndex() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    public int removeMin() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        int min = pq[1];
        swap(1, n--);
        siftDown(1);
        assert min == pq[n + 1];
        qp[min] = -1;        // delete
        pq[n + 1] = -1;        // not needed
        return min;
    }

    public double distanceOf(int i) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        else return dist[i];
    }

    public void decreaseDistance(int i, double dist) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (this.dist[i] <= dist)
            throw new IllegalArgumentException("Calling decreaseDistance() with given argument would not strictly decrease the key");
        this.dist[i] = dist;
        siftUp(qp[i]);
    }

    private boolean greater(int i, int j) {
        return dist[pq[i]] > dist[pq[j]];
    }

    private void swap(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }


    private void siftUp(int k) {
        while (k > 1 && greater(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void siftDown(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            swap(k, j);
            k = j;
        }
    }
}
