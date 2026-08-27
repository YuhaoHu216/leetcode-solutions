package question207;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 * ACM 模式：
 * 输入：第一行为课程总数 numCourses；第二行为先修课程对的数量 len；接下来 len 行，每行两个整数 ai bi，表示学习 ai 前必须先学 bi。例如：
 * 2
 * 1
 * 1 0
 * 输出：是否可能完成所有课程，例如：true
 */
class Solution {

    // 拓扑排序 先要获取到每节课的入度和其后续课程 利用队列存入度为0的课 看是否能正常修完所有课程
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // 存储每节课的入度
        int[] inDegree = new int[numCourses];
        // 存储每节课具体的后置课程
        List<List<Integer>> courses = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            courses.add(new ArrayList<>());
        }

        for(int[] prerequisite : prerequisites){
            int course = prerequisite[0];
            int preCourse = prerequisite[1];

            inDegree[course]++;
            courses.get(preCourse).add(course);
        }

        // 将入度为0的课程加入队列
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }

        // 开始将课程弹出队列，并且将课程后面的课程的入度减一，入度为0的课程继续加入队列
        int count = 0;  // 记录课程正常学习数
        while(!queue.isEmpty()){
            int poll = queue.poll();
            List<Integer> list = courses.get(poll);
            for(int course : list){
                inDegree[course]--;
                if(inDegree[course] == 0){
                    queue.offer(course);
                }
            }
            count++;
        }

        // 循环结束后如果学的课程数(count的值)和课程总数不同则表示有死循环,无法完成所有课程的学习
        return numCourses == count;


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 第一行：课程总数 numCourses
        int numCourses = scanner.nextInt();
        // 第二行：先修课程对的数量 len
        int len = scanner.nextInt();
        // 接下来 len 行，每行两个整数 ai bi，表示学习 ai 前必须先学 bi
        int[][] prerequisites = new int[len][2];
        for (int i = 0; i < len; i++) {
            prerequisites[i][0] = scanner.nextInt();
            prerequisites[i][1] = scanner.nextInt();
        }
        scanner.close();

        boolean result = new Solution().canFinish(numCourses, prerequisites);
        System.out.println(result);
    }
}