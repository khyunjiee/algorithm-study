import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main12026 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static final long MAX = Integer.MAX_VALUE;
    private static int N;
    private static char[] street;
    private static long[] dp;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(bufferedReader.readLine());
        street = new char[N];
        street = bufferedReader.readLine().toCharArray();
        dp = new long[N];
        Arrays.fill(dp, MAX);
        solve();
        if (dp[N - 1] == MAX) {
            System.out.println("-1\n");
        } else {
            System.out.println(dp[N - 1]);
        }
    }

    private static void solve() {
        dp[0] = 0;
        for(int i = 0; i < N-1; i++) {
            int now = i;
            int next = 0;
            char curBlock = street[now];
            char nextBlock = '\0';
            if(curBlock == 'B') {
                for(int j = i +1; j < N; j++) {
                    next = j;
                    nextBlock = street[next];
                    if(nextBlock == 'O') {
                        dp[next] = Math.min(dp[next], dp[now] + (next-now) * (next-now));
                    }
                }
            } else if(curBlock == 'O') {
                for(int j = i +1; j < N; j++) {
                    next = j;
                    nextBlock = street[next];
                    if(nextBlock == 'J') {
                        dp[next] = Math.min(dp[next], dp[now] + (next-now) * (next-now));
                    }
                }
            } else {
                for(int j = i +1; j < N; j++) {
                    next = j;
                    nextBlock = street[next];
                    if(nextBlock == 'B') {
                        dp[next] = Math.min(dp[next], dp[now] + (next-now) * (next-now));
                    }
                }
            }
        }

    }


}
