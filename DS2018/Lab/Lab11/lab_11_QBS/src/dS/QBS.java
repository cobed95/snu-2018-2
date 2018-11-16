package dS;

public class QBS<E extends Comparable<? super E>> {
	public QBS() {}
	public int qbs(E[] arr, E e, int len) {
		// fill your code
        if (len <= 10)
            return binarySearch(arr, e, 0, len - 1);
        else {
            double p = getP(arr, e, len);
            int pn = getPN(p, len);
            int sqrt = sqrt(1.0, len);
            if (e.compareTo(arr[pn]) == 0) return pn;
            else if (e.compareTo(arr[pn]) < 0)
                return probeDown(arr, e, len, pn, sqrt);
            else return probeUp(arr, e, len, pn, sqrt);
        }

	}

	public double getP(E[] arr, E target, int len) {
	    if (target instanceof Integer) {
            Integer targetInt = (Integer) target;
            Integer arr1 = (Integer) arr[0];
            Integer arrN = (Integer) arr[len - 1];
            if (len == 0 || len == 1) return 0;
            return (double) (targetInt - arr1) / (arrN - arr1);
        }
        return -1;
    }

    public int getPN(double p, int len) {
	    return (int) (p * len);
    }

    public int probeDown(E[] arr, E target, int len, int curr, int jump) {
	    if (curr <= 0) return 0;
        if (target.compareTo(arr[curr]) >= 0)
            return linearSearch(arr, target, curr, curr + jump, false);
        else return probeDown(arr, target, len, curr - jump, jump);
    }

    public int probeUp(E[] arr, E target, int len, int curr, int jump) {
	    if (curr >= len) return len - 1;
	    if (target.compareTo(arr[curr]) <= 0)
	        return linearSearch(arr, target, curr, curr - jump, true);
	    else return probeUp(arr, target, len, curr + jump, jump);
    }

    public int linearSearch(E[] arr, E target, int from, int to, boolean reverse) {
	    if (reverse) {
	        for (int i = from; i > to; i--) {
                if (target.compareTo(arr[i]) == 0)
                    return i;
                else if (target.compareTo(arr[i]) > 0)
                    return i + 1;
            }
            return to + 1;
        } else {
            for (int i = from; i < to; i++) {
                if (target.compareTo(arr[i]) == 0)
                    return i;
                else if (target.compareTo(arr[i]) < 0)
                    return i + 1;
            }
            return to;
        }
    }
	// Maybe you need to create the helper methods.
    public int sqrt(double guess, int n) {
        if (isGoodEnough(guess, n)) return (int) guess;
        else return sqrt(improve(guess, n), n);
    }

    public boolean isGoodEnough(double guess, int n) {
	    double diff = (guess * guess) - n;
	    return diff <= 0.1 && diff >= -0.1;
    }

    public double improve(double guess, int n) {
        return (guess + n / guess) / 2;
    }

    public int binarySearch(E[] arr, E target, int from, int to) {
        if (to - from == 1) {
            if (target.compareTo(arr[from]) == 0) return from;
            else if (target.compareTo(arr[from]) < 0) return from;
            else return from + 1;
        } else {
            int mid = (to + from) / 2;
            if (target.compareTo(arr[mid]) == 0) return mid;
            else if (target.compareTo(arr[mid]) < 0)
                return binarySearch(arr, target, from, mid);
            else
                return binarySearch(arr, target, mid, to);
        }
    }

}
