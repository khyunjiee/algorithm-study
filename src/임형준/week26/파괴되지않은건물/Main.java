package src.프로그래머스.카카오기출.파괴되지않은건물;

/**
 * date: 22.03.14
 */


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int solution1 = solution.solution(new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}}, new int[][]{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}});
        System.out.println(solution1);
    }

    static class Solution{
        static int N,M;
        static int[][] sum;
        public int solution(int[][] board, int[][] skill){
            N = board.length;
            M = board[0].length;
            sum = new int[N+1][M+1];

            for(int[] s : skill){
                int r1 = s[1];
                int c1 = s[2];

                int r2 = s[3];
                int c2 = s[4];

                int degree = s[0] == 1 ? -s[5] : s[5];

                sum[r1][c1] += degree;
                sum[r2+1][c1] -= degree;
                sum[r1][c2+1] -= degree;
                sum[r2+1][c2+1] += degree;
            }

//            for(int i=0;i<N+1;i++){
//                for(int j=0;j<M+1;j++){
//                    System.out.print(sum[i][j] + " ");
//                }
//                System.out.println();
//            }

            for(int j=1;j<M;j++){
                for(int i=0;i<N;i++){
                    sum[i][j] += sum[i][j-1];
                }
            }

            for(int i=1;i<N;i++){
                for(int j=0;j<M;j++){
                    sum[i][j] += sum[i-1][j];
                }
            }

            int count = 0;

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(board[i][j] + sum[i][j] > 0) count++;
                }
            }
            return count;
        }
    }
}
