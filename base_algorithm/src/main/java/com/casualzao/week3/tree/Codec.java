package com.casualzao.week3.tree;


import javafx.util.Pair;

import java.util.*;
import java.util.zip.CRC32;

/**
 * 二叉树的序列化
 *
 * @author pcmd
 * @create 2022-10-29 11:14
 */
public class Codec {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * Encodes a tree to a single string.
     */
    public String serialize(TreeNode root) {
        List<String> seq = new ArrayList<>();
        dfs(root,seq);
        return String.join(",",seq);
    }

    int cur;
    String[] seq;
    private void dfs(TreeNode node, List<String> seq) {
        if (null == node) {
            seq.add("null");
            return;
        }
        seq.add(String.valueOf(node.val));
        dfs(node.left, seq);
        dfs(node.right, seq);

    }
    /**
     * Decodes your encoded data to tree.
     */
    public TreeNode deserialize(String data) {
        seq = data.split(",");
        cur = 0;

        return restore();

    }
    TreeNode restore(){
        if("null".equals(seq[cur])){
            cur ++;
            return  null;
        }

        TreeNode treeNode = new TreeNode(Integer.parseInt(seq[cur]));
        cur ++;
        treeNode.left = restore();
        treeNode.right = restore();

        return treeNode;
    }





//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//
//        return build(preorder,inorder,0, preorder.length -1,0,inorder.length-1);
//    }
//
//    private TreeNode build(int[] preorder, int[] inorder,int p1,int p2,int i1,int i2){
//        if(p1>=p2){
//            return null;
//        }
//        TreeNode root = new TreeNode(preorder[p1]);
//        int mid = i1;
//        while (inorder[mid] != root.val){
//            mid ++;
//        }
//        // 前序 【 p1 + 1 , p1+(mid-1 - i1+1)]
//        // 中序 【i1,mid -1 】
//        root.left= build(preorder,inorder,p1+1 , p1+(mid-1 - i1 +1),i1,mid-1);
//
//        // 前序 【 p1+(mid-1 - i1+1)+1 , p2]
//        // 中序 【mid+1 ,i2 】
//        root.right= build(preorder,inorder,p1+(mid-1 -i1+1) +1,p2,mid+1,i2);
//        return root;
//    }
//




    public static void main(String[] args) {
        Codec codec = new Codec();
        String decode = "[1,2,3,null,null,4,5]";
        TreeNode deserialize = codec.deserialize(decode);
        String serialize = codec.serialize(deserialize);
//        int[] preorder = new int[]{3,9,20,15,7};
//        int[] inorder = new int[]{9,3,15,20,7};
//        TreeNode treeNode = codec.buildTree(preorder, inorder);
        System.out.println(serialize);
    }
}
