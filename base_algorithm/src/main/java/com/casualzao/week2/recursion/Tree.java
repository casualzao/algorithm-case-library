package com.casualzao.week2.recursion;

/**
 *
 * 边界值尤其重要
 * @author pcmd
 * @create 2022-10-27 00:10
 */
public class Tree {



    public boolean isValidBST(TreeNode root) {

        return checkIsBST(root,-(1L  << 31) ,(1L << 31) -1);
    }

    /**
     * 注重边界值
     * @param root
     * @param leftRange
     * @param rightRange
     * @return
     */
    public boolean checkIsBST(TreeNode root,long leftRange,long rightRange){
        if(null == root){
            return true;
        }

        if(root.val > rightRange || root.val < leftRange){
            return false;
        }
        return checkIsBST(root.left,leftRange,(long)root.val -1) &&
                checkIsBST(root.right,(long)root.val + 1,rightRange);

    }



    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
