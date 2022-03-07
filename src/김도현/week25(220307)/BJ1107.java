package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1107 {
    /* 1107. 리모컨
    백트래킹
    */
    private static int N;
    private static int M;
    private static int K;
    private static int[] units;
    private static boolean[] num = new boolean[10];
    private static int result;

    public static void dfs(int depth, int count, int channel) {
        if (depth == 0) {
            result = Math.min(result, count + Math.abs(N - channel));
            return;
        }
        for (int i = 0; i < 10; i++) {
            // 리모컨의 아무 버튼도 누르지 않고 자리수를 내림
            if (depth != 1 && channel == 0) dfs(depth-1, count, channel);
            if (!num[i]) {
                if (i != 0 || depth == 1 || channel != 0) {
                    // 백트래킹
                    if (channel > N && count + channel - N >= result) continue;
                    dfs(depth-1, count+1, channel+i*units[depth]);
                }else dfs(depth-1, count, channel); // 0을 누를 수 있을 때 아무 버튼도 누르지 않고 자리수를 내림
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String str = br.readLine();
        K = str.length() + 1;
        units = new int[K+1];
        units[1] = 1;
        for (int i = 2; i < K+1; i++) units[i] = units[i-1] * 10;
        N = Integer.parseInt(str);
        result = Math.abs(N-100);
        M = Integer.parseInt(br.readLine());
        if (M != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) num[Integer.parseInt(st.nextToken())] = true;
        }
        dfs(K, 0, 0);
        System.out.println(result);
    }
}
