import java.util.TreeSet;

class RangeModule {

    private static class Interval implements Comparable<Interval> {
        int left;
        int right;

        Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Interval other) {
            if (this.right != other.right) {
                return Integer.compare(this.right, other.right);
            }
            return Integer.compare(this.left, other.left);
        }
    }

    private final TreeSet<Interval> intervals;

    public RangeModule() {
        intervals = new TreeSet<>();
    }

    public void addRange(int left, int right) {
        // Find intervals that overlap or touch [left, right)
        // We use a dummy interval with right = left to find floor/subsets
        Interval dummy = new Interval(0, left);
        var tailSet = intervals.tailSet(dummy, true);
        
        java.util.List<Interval> toRemove = new java.util.ArrayList<>();
        
        for (Interval interval : tailSet) {
            if (interval.left > right) {
                break; // No more overlapping intervals
            }
            // Overlapping condition: interval.left <= right && interval.right >= left
            left = Math.min(left, interval.left);
            right = Math.max(right, interval.right);
            toRemove.add(interval);
        }
        
        for (Interval interval : toRemove) {
            intervals.remove(interval);
        }
        
        intervals.add(new Interval(left, right));
    }

    public boolean queryRange(int left, int right) {
        // Find the interval that ends at or after `left` and has the smallest right bound
        Interval dummy = new Interval(0, left);
        Interval match = intervals.ceiling(dummy);
        
        // Check if `match` covers the entire [left, right) range
        return match != null && match.left <= left && match.right >= right;
    }

    public void removeRange(int left, int right) {
        Interval dummy = new Interval(0, left);
        var tailSet = intervals.tailSet(dummy, true);
        
        java.util.List<Interval> toRemove = new java.util.ArrayList<>();
        java.util.List<Interval> toAdd = new java.util.ArrayList<>();
        
        for (Interval interval : tailSet) {
            if (interval.left >= right) {
                break;
            }
            if (interval.right <= left) {
                continue;
            }
            
            toRemove.add(interval);
            
            // If the interval extends to the left of the removal range, keep the left part
            if (interval.left < left) {
                toAdd.add(new Interval(interval.left, left));
            }
            // If the interval extends to the right of the removal range, keep the right part
            if (interval.right > right) {
                toAdd.add(new Interval(right, interval.right));
            }
        }
        
        for (Interval interval : toRemove) {
            intervals.remove(interval);
        }
        for (Interval interval : toAdd) {
            intervals.add(interval);
        }
    }
}