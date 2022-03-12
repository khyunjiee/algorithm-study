package SK;

import java.util.Arrays;

public class Two {

    public static void main(String[] args) {
        solution(1000, false);
//        solution(6, false);
    }

    public static int[][] solution(int n, boolean clockwise) {
        int[][] answer = new int[n][n];
        int level = 0;
        int num = 1;

        while (true) {
            int start = num;
            int i = level;
            int j = level;

            for (int k = 0; k < n - 2 * level - 1; k++) {
                if (clockwise) {
                    answer[i][j + k] = answer[i + k][n - 1 - level] = answer[n - 1 - level][n - 1
                        - level - k] = answer[n - 1 - level - k][j] = start++;
                } else {
                    answer[i + k][j] = answer[n - 1 - level][j + k] = answer[n - 1 - level - k][n
                        - 1
                        - level] = answer[i][n - 1 - level - k] = start++;
                }
            }
            if (n % 2 == 1) {
                answer[level][level] = num;
            }

            level++;

            if (level >= (n + 1) / 2) {
                break;
            }

            if (clockwise) {
                num = answer[level][level - 1] + 1;
            } else {
                num = answer[level - 1][level] + 1;
            }
        }

        for (int[] p : answer) {
            System.out.println(Arrays.toString(p));
        }

        return answer;
    }
}
