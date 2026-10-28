class Solution {
    class Node {
        int len, count;
        Node(int len, int count) {
            this.len = len;
            this.count = count;
        }
    }

    class SegTree {
        int size;
        Node[] tree;

        SegTree(int size) {
            this.size = size;
            this.tree = new Node[4 * size];
            for (int i = 0; i < tree.length; ++i)
                tree[i] = new Node(0, 0);
        }

        // Merge two segment tree nodes
        Node merge(Node a, Node b) {
            if (a.len > b.len) return a;
            if (b.len > a.len) return b;
            if (a.len == 0) return new Node(0, 0);
            return new Node(a.len, a.count + b.count);
        }

        // Query LIS info in range [l, r]
        Node query(int idx, int left, int right, int l, int r) {
            if (r < left || l > right)
                return new Node(0, 0);

            if (l <= left && right <= r)
                return tree[idx];

            int mid = left + (right - left) / 2;
            return merge(
                query(idx * 2, left, mid, l, r),
                query(idx * 2 + 1, mid + 1, right, l, r)
            );
        }

        // Update LIS info at position pos
        void update(int idx, int l, int r, int pos, Node node) {
            if (l == r) {
                if (tree[idx].len < node.len)
                    tree[idx] = node;
                else if (tree[idx].len == node.len)
                    tree[idx].count += node.count;
                return;
            }

            int m = l + (r - l) / 2;
            if (pos <= m)
                update(idx * 2, l, m, pos, node);
            else
                update(idx * 2 + 1, m + 1, r, pos, node);

            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
        }
    }

    public int findNumberOfLIS(int[] nums) {
        int[] sorted = nums.clone();
        Map<Integer, Integer> index = new HashMap<>();
        Arrays.sort(sorted);

        int size = -1;
        for (int n : sorted)
            if (!index.containsKey(n))
                index.put(n, ++size);

        SegTree st = new SegTree(size + 1);

        for (int n : nums) {
            int pos = index.get(n);
            Node best = (pos == 0)
                ? new Node(0, 0)
                : st.query(1, 0, size, 0, pos - 1);

            int len = best.len + 1;
            int count = best.count == 0 ? 1 : best.count;

            st.update(1, 0, size, pos, new Node(len, count));
        }

        return st.tree[1].count;
    }
}