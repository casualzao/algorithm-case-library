package com.casualzao.week3.tree;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.org.apache.xpath.internal.operations.Variable;
import javafx.util.Pair;

/**
 * @author pcmd
 * @create 2022-10-29 15:50
 */
public class Restore {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int[] inorder;
    private int[] postorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        return build(0,inorder.length -1 ,0,postorder.length -1);
    }


    public TreeNode build(int i1,int i2,int p1, int p2){

        if(i1>i2){
            return  null;
        }
        TreeNode root = new TreeNode(postorder[p2]);
        int mid = i1;
        while (inorder[mid] != root.val){
            mid ++;
        }

        root.left = build(i1,mid-1,p1,p1 +mid - i1 - 1);
        root.right = build(mid+1,i2,p2 + mid - i2 ,p2-1);
        return root;
    }


    public static void main(String[] args) {
        Restore restore = new Restore();
        int[] inorder = new int[] {9,3,15,20,7};
        int[] postorder =new int[] {9,15,7,20,3};
        TreeNode treeNode = restore.buildTree(inorder, postorder);
        System.out.println(treeNode);
    }
}
