package pg;

import java.util.PriorityQueue;

public class PG92344 {
    /* Level 3. 파괴되지 않은 건물
    imos법
    [1] https://driip.me/65d9b58c-bf02-44bf-8fba-54d394ed21e0
    [2] https://imoz.jp/algorithms/imos_method.html
    */
    public static void main(String[] args) {
        PG92344.Solution test = new PG92344.Solution();
        System.out.println(test.solution(new int[][] {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}},
                new int[][] {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}}));
        System.out.println(test.solution(new int[][] {{1,2,3},{4,5,6},{7,8,9}},
                new int[][] {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}}));
    }

    static class Solution {
        public int solution(int[][] board, int[][] skill) {
            int answer = 0;
            int M = board.length+1;
            int N = board[0].length+1;
            int[][] map = new int[M][N];
            for (int[] info:skill) {
                if (info[0] == 1) {
                    map[info[1]][info[2]] -= info[5];
                    map[info[1]][info[4]+1] += info[5];
                    map[info[3]+1][info[2]] += info[5];
                    map[info[3]+1][info[4]+1] -= info[5];
                }else {
                    map[info[1]][info[2]] += info[5];
                    map[info[1]][info[4]+1] -= info[5];
                    map[info[3]+1][info[2]] -= info[5];
                    map[info[3]+1][info[4]+1] += info[5];
                }
            }

            for (int i = 0; i < M; i++)
                for (int j = 1; j < N; j++)
                    map[i][j] += map[i][j-1];
            for (int i = 1; i < M; i++)
                for (int j = 0; j < N; j++)
                    map[i][j] += map[i-1][j];
            for (int i = 0; i < M-1; i++)
                for (int j = 0; j < N-1; j++)
                    if (board[i][j]+map[i][j] > 0) answer++;
            return answer;
        }
    }
}
