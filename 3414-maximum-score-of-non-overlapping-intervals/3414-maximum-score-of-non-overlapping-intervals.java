import java.util.*;

class Solution {
    // A compact representation of each interval
    private static class Interval {
        int left, right, weight, originalIndex;
        
        Interval(int left, int right, int weight, int originalIndex) {
            this.left = left;
            this.right = right;
            this.weight = weight;
            this.originalIndex = originalIndex;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] indexedIntervals = new Interval[n];
        
        for (int i = 0; i < n; ++i) {
            List<Integer> row = intervals.get(i);
            indexedIntervals[i] = new Interval(row.get(0), row.get(1), row.get(2), i);
        }
        
        // Sort intervals strictly by their left boundary
        Arrays.sort(indexedIntervals, Comparator.comparingInt(a -> a.left));
        
        // dp[i][j] stores the maximum weight starting from index 'i' with 'j' intervals left to pick
        long[][] dpWeight = new long[n + 1][5];
        // dpAns[i][j] stores the best selected original indices
        List<Integer>[][] dpAns = new ArrayList[n + 1][5];
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 4; j++) {
                dpAns[i][j] = new ArrayList<>();
            }
        }
        
        // Process backward (Bottom-Up)
        for (int i = n - 1; i >= 0; i--) {
            Interval curr = indexedIntervals[i];
            int nextValidIdx = findFirstGreater(indexedIntervals, i + 1, curr.right);
            
            for (int k = 1; k <= 4; k++) {
                // Option 1: Skip the current interval
                long skipWeight = dpWeight[i + 1][k];
                List<Integer> skipList = dpAns[i + 1][k];
                
                // Option 2: Pick the current interval
                long pickWeight = curr.weight + dpWeight[nextValidIdx][k - 1];
                List<Integer> pickList = new ArrayList<>(dpAns[nextValidIdx][k - 1]);
                pickList.add(curr.originalIndex);
                Collections.sort(pickList); // Keep indices sorted for lexicographical checking
                
                // Decision making with lexicographical tie-breaking
                if (pickWeight > skipWeight || (pickWeight == skipWeight && isLexicographicallySmaller(pickList, skipList))) {
                    dpWeight[i][k] = pickWeight;
                    dpAns[i][k] = pickList;
                } else {
                    dpWeight[i][k] = skipWeight;
                    dpAns[i][k] = skipList;
                }
            }
        }
        
        // Convert the best resulting list from state [0][4] into the primitive output array
        List<Integer> bestResult = dpAns[0][4];
        int[] ans = new int[bestResult.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = bestResult.get(i);
        }
        return ans;
    }

    // Binary search to find the first interval whose left boundary > rightBoundary
    private int findFirstGreater(Interval[] intervals, int startFrom, int rightBoundary) {
        int l = startFrom;
        int r = intervals.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (intervals[m].left > rightBoundary) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    // Standard structural comparison for lexicographical ordering
    private boolean isLexicographicallySmaller(List<Integer> a, List<Integer> b) {
        int lenA = a.size();
        int lenB = b.size();
        int minLen = Math.min(lenA, lenB);
        for (int i = 0; i < minLen; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return lenA < lenB;
    }
}
