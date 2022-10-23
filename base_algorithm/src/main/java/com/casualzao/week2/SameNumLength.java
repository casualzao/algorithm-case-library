package com.casualzao.week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 想同数组的长度
 *
 * @author pcmd
 * @create 2022-10-23 17:28
 */
public class SameNumLength {


    public int findShortestSubArray(int[] nums) {
        // int[]  0 == 出现次数 1 == 初始位置 3 == 最后一次位置
        Map<Integer, int[]> numCount = new HashMap<>(nums.length);
        int maxCount = 0;

        for (int i = 0; i < nums.length; i++) {

            int[] record = numCount.getOrDefault(nums[i], new int[]{0, i, i});

            record[0]++;
            record[2] = i;
            if (record[0] > maxCount) {
                maxCount = record[0];
            }
            numCount.put(nums[i], record);
        }
        int ans = nums.length;
        int max = 0;

        for (Map.Entry<Integer, int[]> integerEntry : numCount.entrySet()) {
            int[] cur = integerEntry.getValue();
            if (cur[0] > max) {
                max = cur[0];
                ans = cur[2] - cur[1] + 1;
            } else if (cur[0] == max) {
                ans = Math.min(ans, cur[2] - cur[1] + 1);
            }

        }


        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 3, 1};
        SameNumLength length = new SameNumLength();
        System.out.println(length.findShortestSubArray(nums));
    }
}
