import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * sol)
 *
 * 1개부터 bottom-up 방식으로 3개까지 구성한다.
 *
 */

public class Main {

    static int N;
    static int max;
    static int[] arr;
    static int[] sum;
    static int[][] dp;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N];

        for(int i=0;i<N;i++){
            arr[i] = sc.nextInt();
        }
        max = sc.nextInt();

        sum = new int[N+1];
        dp = new int[3+1][N+1];

        for(int i=0;i<N;i++){
            sum[i+1] = sum[i] +  arr[i];
        }

        // 소형 기관차는 max 단위로 움직이므로, i*max부터 시작해야 한다.
        for(int i=1;i<3+1;i++){
            for(int j=i*max;j<N+1;j++){
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-max] + (sum[j] - sum[j-max]));
            }
        }

        System.out.println(dp[3][N]);
    }
}
