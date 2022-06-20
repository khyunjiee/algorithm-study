import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2631 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int max = -1;
        int[] arr = new int[n];
        int[] dp = new int[n];

        for(int i = 0;i<n;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 0;i<n;i++){
            dp[i] = 1;
            for(int j = 0;j<i;j++){
                if(arr[i] > arr[j] && dp[i]<dp[j]+1) dp[i] = dp[j]+1;
            }
        }
        for(int i = 0;i<n;i++){
            max = Math.max(max,dp[i]);
        }
        System.out.println(n - max);
    }
}
