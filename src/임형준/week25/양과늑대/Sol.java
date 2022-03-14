package src.프로그래머스.카카오기출.양과늑대;

import java.util.ArrayList;
import java.util.List;

/**
 * date: 22.03.07
 * 못풀어서 링크 참조했습니다 : https://jangcenter.tistory.com/120
 */

public class Sol{
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[]{0,0,1,1,1,0,1,0,1,0,1,1},new int[][]{{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}});
    }

    static class Solution {
        // 해당 IDX의 자식은 누가 있는지
        static ArrayList<Integer>[] childs;
        static int[] Info;
        static int maxSheepCnt = 0;

        public int solution(int[] info, int[][] edges) {
            Info = info;
            childs = new ArrayList[info.length];
            for (int[] l : edges) {
                int parent = l[0];
                int child = l[1];
                if (childs[parent] == null) {
                    childs[parent] = new ArrayList<>();
                }
                childs[parent].add(child);
            }

            List<Integer> list = new ArrayList<>();
            list.add(0);
            dfs(0, 0, 0, list);
            return maxSheepCnt;
        }

        private static void dfs(int idx, int sheepCnt, int wolfCnt, List<Integer> nextPos) {
            // 늑대/양 수, 양의 최대값 최신화
            if (Info[idx] == 0) sheepCnt++;
            else wolfCnt++;

            if (wolfCnt >= sheepCnt) return;
            maxSheepCnt = Math.max(sheepCnt, maxSheepCnt);

            // 다음 탐색 위치 갱신
            List<Integer> list = new ArrayList<>(nextPos);
            // 다음 탐색 목록중 현재 위치제외
            list.remove(Integer.valueOf(idx));
            if (childs[idx] != null) {
                list.addAll(childs[idx]);
            }

            // 갈수 있는 모든 Node Dfs
            for (int next : list) {
                dfs(next, sheepCnt, wolfCnt, list);
            }
        }
    }
}


