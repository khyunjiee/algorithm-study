package pg;

public class PG92343 {
    /* Level 3. 양과 늑대
    완전탐색
    */
    public static void main(String[] args) {
        PG92343.Solution test = new PG92343.Solution();
        System.out.println(test.solution(new int[] {0,0,1,1,1,0,1,0,1,0,1,1}, new int[][] {
                {0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}
        }));
        System.out.println(test.solution(new int[] {0,1,0,1,1,0,1,0,0,1,0}, new int[][] {
                {0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}
        }));
    }

    static class Solution {

        private static int N;
        private static int result = 0;

        public void dfs(int[] info, int[][] tree, int parent, int sheep, int wolf, boolean[] visited) {
            if (info[parent] == 0) sheep++;
            else wolf++;
            if (sheep <= wolf) return;
//            System.out.println(parent+" "+sheep+" "+wolf);
            result = Math.max(result, sheep);
            visited[parent] = false;
            if (tree[parent][0] != 0) {
                visited[tree[parent][0]] = true;
                if (tree[parent][1] != 0)
                    visited[tree[parent][1]] = true;
            }
            for (int i = 1; i < N; i++) {
                if (visited[i]) {
                    dfs(info, tree, i, sheep, wolf, visited.clone());
                }
            }
        }

        public int solution(int[] info, int[][] edges) {
            N = info.length;
            int[][] tree = new int[N][2];
            int p, c;
            for (int[] edge : edges) {
                p = edge[0]; c = edge[1];
                if (tree[p][0] == 0) tree[p][0] = c;
                else tree[p][1] = c;
            }
            dfs(info, tree, 0, 0, 0, new boolean[N]);
            return result;
        }
    }
}
