package pg;

import java.util.Arrays;

public class PG92342 {
    /* Level 2. 양궁대회
    DFS
    */
    public static void main(String[] args) {
        PG92342.Solution test = new PG92342.Solution();
        System.out.println(Arrays.toString(test.solution(5, new int[] {2,1,1,1,0,0,0,0,0,0,0})));
        System.out.println(Arrays.toString(test.solution(1, new int[] {1,0,0,0,0,0,0,0,0,0,0})));
        System.out.println(Arrays.toString(test.solution(9, new int[] {0,0,1,2,0,1,1,1,1,1,1})));
        System.out.println(Arrays.toString(test.solution(10, new int[] {0,0,0,0,0,0,0,0,3,4,3})));
    }

    static class Solution {
        static int N = 10;
        static int M = 0;
        static int[] answer;

        public void dfs(int[] info, int dep, int cnt, int[] arr) {
            if (cnt == 0) {
                int ryon = 0;
                int apeach = 0;
                for (int i = 0; i < N; i++) {
                    if (info[i] == arr[i] && info[i] == 0) continue;
                    if (info[i] < arr[i]) ryon += 10-i;
                    else apeach += 10-i;
                }
                if (ryon > apeach && ryon-apeach >= M) {
                    if (answer.length == N+1 && ryon-apeach == M) {
                        for (int i = N; i >= 0; i--) {
                            if (answer[i] > arr[i]) return;
                            else if (answer[i] < arr[i]) break;
                        }
                    }
                    answer = arr.clone();
                    M = ryon-apeach;
                }
                return;
            }
            if (dep > N) return;

            for (int i = 0; i <= cnt; i++) {
                arr[dep] = i;
                dfs(info, dep+1, cnt-i, arr);
                arr[dep] = 0;
            }
        }

        public int[] solution(int n, int[] info) {
            answer = new int[] {-1};
            dfs(info, 0, n, new int[N+1]);
            return answer;
        }
    }
}
