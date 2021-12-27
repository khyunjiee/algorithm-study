package src.알고리즘스터디.날짜12월3째주.보행자천국;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(3,6,new int[][]{
                {0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}
        });
    }
    static class Solution {
        static int M,N;

        static int[][] count;

        static int[][][] dp;

        int MOD = 20170805;
        public int solution(int m, int n, int[][] cityMap) {
            M=n;
            N=m;

            //  세로0 가로1
            dp = new int[N][M][2];

            // 세로
            for(int i=0;i<N;i++){
                if(cityMap[i][0] != 1){
                    dp[i][0][0]=1;
                }
            }

            // 가로
            for(int j=0;j<N;j++){
                if(cityMap[0][j] != 1){
                    dp[0][j][1]=1;
                }
            }

            for(int i=1;i<N;i++){
                for(int j=1;j<N;j++){
                    if(cityMap[i][j]==1)continue;

                    // 세로 방향
                    if(cityMap[i-1][j]==0){
                        dp[i][j][0] = (dp[i][j][0] + dp[i-1][j][0] + dp[i-1][j][1])%MOD;
                    }else if(cityMap[i-1][j]==1){
                    }else if(cityMap[i-1][j]==2){
                        dp[i][j][0] = (dp[i][j][0] + dp[i-1][j][0]) % MOD;
                    }

                    if(cityMap[i][j-1]==0){
                        dp[i][j-1][1] = (dp[i][j][1] + dp[i][j-1][0] + dp[i][j-1][1])%MOD;
                    }else if(cityMap[i-1][j]==1){
                    }else if(cityMap[i-1][j]==2){
                        dp[i][j][1] = (dp[i][j][1] + dp[i][j-1][1]) % MOD;
                    }
                }
            }

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    System.out.print(dp[i][j][0] + " ");
                }
                System.out.println();
            }

            System.out.println();

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    System.out.print(dp[i][j][1] + " ");
                }
                System.out.println();
            }

            System.out.println(dp[N-1][M-1][0] + dp[N-1][M-1][1]);


            int answer = 0;
            return answer;
        }
    }
}
