import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution437 {
    public static int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] cnt = new int[] { 0 };
        helper(root, sum, 0, cnt, map);
        return cnt[0];
    }

    public static void helper(TreeNode root, int sum, int presum, int[] cnt, Map<Integer, Integer> map) {
        if (root == null)
            return;
        presum += root.val;
        if (presum == sum)
            cnt[0]++;
        cnt[0] += map.getOrDefault(presum - sum, 0);
        map.put(presum, map.getOrDefault(presum, 0) + 1);
        helper(root.left, sum, presum, cnt, map);
        helper(root.right, sum, presum, cnt, map);
        map.put(presum, map.get(presum) - 1);
    }

    public static TreeNode buildTree(Integer[] array) {
        if (array == null || array.length == 0)
            return new TreeNode();
        Deque<TreeNode> q = new ArrayDeque<>();
        int index = 0;
        TreeNode root = new TreeNode(array[index++]);
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur, left, right;
                cur = q.poll();
                if (index < array.length && array[index] != null) {
                    left = new TreeNode(array[index]);
                    q.offer(left);
                    cur.left = left;
                }
                index++;
                if (index < array.length && array[index] != null) {
                    right = new TreeNode(array[index]);
                    q.offer(right);
                    cur.right = right;
                }
                index++;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        // Integer[] tree = {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
        Integer[] tree = { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1 };
        int sum = 22;
        TreeNode root = Solution437.buildTree(tree);
        System.out.println(Solution437.pathSum(root, sum));
    }
}

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode() {
    }
}