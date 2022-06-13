import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    DP -
 */
public class BOJ_11049 {
    static int n,MAX = Integer.MAX_VALUE;
    static int input[];
    static int dp[][];
    public static void main(String[] args) throws IOException{
        input11049();
        dp = init(dp);
        System.out.println(solve(0,n-1));
    }

    private static int[][] init(int[][] arr) {
        arr = new int[n][n];
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                arr[i][j] = MAX;
            }
        }
        return arr;
    }

    static void input11049() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        input = new int[n+1];

        for(int i = 0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            input[i] = r;
            input[i+1] = c;
        }
    }
    static int solve(int pos,int now){
        if(pos == now) return 0;
        if(dp[pos][now] != MAX) return dp[pos][now];

        for(int i = pos;i<now;i++){
            int candidateValue = solve(pos,i) + solve(i+1,now) + input[pos]*input[i+1]*input[now+1];
            dp[pos][now] = getMin(dp[pos][now],candidateValue);
        }
        return dp[pos][now];
    }

    private static int getMin(int i, int candidateValue) {
        return i>candidateValue ? candidateValue : i;
    }
}
