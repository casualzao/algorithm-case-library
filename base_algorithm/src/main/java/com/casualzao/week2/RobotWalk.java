package com.casualzao.week2;

import java.util.HashSet;
import java.util.Set;

/**
 * 机器人行走
 * 1、 二元组 转换为 1 元组 hash
 * 2.
 方向定义， 简单运算就能替代 N 北 ==0，东 E ==1 ，南 S ==2 西 W == 3

  * 3. // 网格中行走，
 int[] dx = new int[]{0, 1, 0, -1};
 int[] dy = new int[]{1, 0, -1, 0};
 * @author pcmd
 * @create 2022-10-23 18:56
 */
public class RobotWalk {

    /**
     * 模拟机器人行走
     *
     * @param commands  命令
     * @param obstacles 障碍物
     * @return
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        int ans = 0;
        Set<Long> obsSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            long obsHash = convertArr(obstacle);
            obsSet.add(obsHash);
        }

        //x，y 坐标 ，当前坐标
        int x = 0, y = 0;
        //<方向数组>
        // 方向定义， 简单运算就能替代 N 北 ==0，东 E ==1 ，南 S ==2 西 W == 3
        // （dir + 1 ）%4 转一圈，有回归到当前为止 巧妙的设计
        // 右转  （dir + 1 ） % 4
        // 左转 （dir +3 ）%4
        int dir = 0;

        // 网格中行走，方向数组
        // (-1,0)_|_(1.0)
        //        |

        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        //<方向数组>

        for (int command : commands) {
            if (command == -1) {
                dir = (dir + 1) % 4;
            } else if (command == -2) {
                dir = (dir + 3) % 4;
            } else {
                // 执行前进命令
                for (int i = 0; i < command; i++) {
                    int cx = x + dx[dir], cy = y + dy[dir];
                    // 判断是否存在障碍物
                    if (obsSet.contains(convertArr(new int[]{cx, cy}))) {
                        break;
                    } else {
                        x = cx;
                        y = cy;
                    }
                }
            }
            ans = Math.max(ans,x*x + y*y);
        }
        return ans;
    }

    private String convertHash(int[] obstacles) {
        return obstacles[0] + "," + obstacles[1];
    }

    /**
     * hash 二元组 转换为一元组
     * @param obstacles
     * @return
     */
    private long convertArr(int[] obstacles){
        return (obstacles[0] + 30000 )* 600001L + obstacles[1];
    }

    public static void main(String[] args) {
        int[] commands = new int[]{4,-1,4,-2,4};
        int[][] obstacles =new int[][] {{2,4}};

        RobotWalk walk = new RobotWalk();
        int ans = walk.robotSim(commands, obstacles);
        System.out.println(ans);
    }
}
