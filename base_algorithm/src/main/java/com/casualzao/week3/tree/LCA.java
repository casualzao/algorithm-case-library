package com.casualzao.week3.tree;

import javafx.util.Pair;

/**
 * 最近公共祖先
 *
 * @author pcmd
 * @create 2022-11-01 22:09
 */
public class LCA {


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode ans = null;
    private TreeNode p;
    private TreeNode q;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.p = p;
        this.q = q;
        dfs(root);
        return ans;
    }


    /**
     * 深度优先遍历
     * @param node
     * @return
     */
    private Pair<Boolean,Boolean> dfs(TreeNode node){
        Pair<Boolean, Boolean> pair = new Pair<>(false, false);
        if(null == node){
            return pair;
        }
        // left 是否有 p  right 是否有有 q
        // 如果有任何一个，则说明 是存在子树的
        // 最大子树 的根
        Pair<Boolean,Boolean> leftResult = dfs(node.left);
        Pair<Boolean,Boolean> rightResult = dfs(node.right);

        boolean left = leftResult.getKey() || rightResult.getKey() || node.val == p.val;
        boolean right = leftResult.getValue() || rightResult.getValue() || node.val == q.val;
        pair = new Pair<>(left,right);

        if(left && right && ans == null){
            ans =node;
        }
        return pair;
    }
}
