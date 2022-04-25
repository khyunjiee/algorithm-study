package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14888 {
    /* 14888. 연산자 끼워넣기
    DFS
    */
    private static int N, maxRes = Integer.MIN_VALUE, minRes = Integer.MAX_VALUE;
    private static final int[] op = new int[4];

    public static void dfs(int[] arr, int cnt, int res) {
        if (cnt == N-1) {
            maxRes = Math.max(maxRes, res);
            minRes = Math.min(minRes, res);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] > 0) {
                op[i]--;
                if (i == 0) dfs(arr, cnt + 1, res + arr[cnt + 1]);
                else if (i == 1) dfs(arr, cnt + 1, res - arr[cnt + 1]);
                else if (i == 2) dfs(arr, cnt + 1, res * arr[cnt + 1]);
                else dfs(arr, cnt + 1, res / arr[cnt + 1]);
                op[i]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) op[i] = Integer.parseInt(st.nextToken());

        dfs(arr, 0, arr[0]);
        System.out.println(maxRes);
        System.out.println(minRes);
    }
}
