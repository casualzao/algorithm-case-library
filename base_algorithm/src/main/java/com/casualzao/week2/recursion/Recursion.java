package com.casualzao.week2.recursion;


import java.util.ArrayList;
import java.util.List;

/**
 * 递归
 *
 * 还需要再次练习
 * template 模版
 * void recursion(int level, int param){
 *     if(level > max_level){
 *         // process result;
 *         return;
 *     }
 *
 *     process logic in current level
 *     process(level,param)
 *
 *     //drill down
 *     recur(level + 1, new_param);
 *
 *     restore the current level status
 * }
 *
 * @author pcmd
 * @create 2022-10-25 21:48
 */
public class Recursion {


    List<Integer> t = new ArrayList<Integer>();
    List<Integer> chose = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    boolean[] isUsed = new boolean[]{};
    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return ans;
    }

    public void dfs(int cur, int[] nums) {
        // 递归 边界条件
        if (cur == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        // 走下一个分支
        dfs(cur + 1, nums);
        // 当前的值加进去
        t.add(nums[cur]);
        // 下一个分支
        dfs(cur + 1, nums);
        // 移除当前的
        t.remove(t.size() - 1);

    }


    public List<List<Integer>> combine(int n, int k) {
        recur(1,n,k);
        return ans;
    }

    private void recur(int cur ,int n,int k){
        if(chose.size() > k || chose.size() + (n-cur +1) < k){
            return;
        }

        if(cur == n+1){
            if(chose.size() ==k){
                ans.add(new ArrayList<>(chose));
            }

            return;
        }
        chose.add(cur);
        recur(cur+1,n,k);
        chose.remove(chose.size()-1);
        recur(cur+1,n,k);
    }


    /**
     * 全排列
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {

        isUsed = new boolean[nums.length];
        recure(nums,0);
        return ans;
    }



    void recure(int[] num,int pos){
        if(pos == num.length){
            ans.add(new ArrayList<>(t));
            return;
        }

        for (int i = 0; i < num.length; i++) {
            if(!isUsed[i]){
                t.add(num[i]);
                isUsed[i] = true;
                recure(num,pos+1);

                isUsed[i] = false;
                t.remove(t.size() -1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        Recursion recursion = new Recursion();
        List<List<Integer>> subsets = recursion.permute(nums);
        System.out.println(subsets);
    }



}
