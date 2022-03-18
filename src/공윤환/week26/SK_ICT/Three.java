package SK;

public class Three {

    public static void main(String[] args) {
        System.out.println(solution(51, 37, new int[][]{{17,19}}));
    }

    static final int MOD = 10000019;

    public static int solution(int width, int height, int[][] diagonals) {
        int[][] dp = new int[height + 1][width + 1];
        int answer = 0;

        for (int i = 0; i < height + 1; i++) {
            dp[i][0] = 1;
        }

        for (int j = 0; j < width + 1; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < height + 1; i++) {
            for (int j = 1; j < width + 1; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
            }
        }

        for (int[] diagonal : diagonals) {
            int[][] tmp = new int[height + 1][width + 1];

            int leftR = diagonal[1];
            int leftC = diagonal[0] - 1;
            int rightR = diagonal[1] - 1;
            int rightC = diagonal[0];

            tmp[leftR][leftC] = dp[rightR][rightC];
            tmp[rightR][rightC] = dp[leftR][leftC];

            for (int i = leftR + 1; i < height + 1; i++) {
                tmp[i][leftC] = tmp[leftR][leftC];
            }

            for (int j = rightC + 1; j < width + 1; j++) {
                tmp[rightR][j] = tmp[rightR][rightC];
            }

            for (int i = leftR; i < height + 1; i++) {
                for (int j = rightC; j < width + 1; j++) {
                    tmp[i][j] = (tmp[i - 1][j] + tmp[i][j - 1]) % MOD;
                }
            }

            answer += tmp[height][width] % MOD;
        }

        return answer;
    }
}
