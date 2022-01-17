package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ17281 {
    /* 17281. 야구
    시뮬레이션
    [2 <= N <= 50, 선수 8명의 타순] => 8! * 50 => 순열
     */
    final static int MAX_M = 9;
    static int[][] hitter;
    static int N, result = 0;

    // 야구 시뮬레이션
    public static int calcScore(int[] arr) {
        int idx = 0, cnt, score = 0;
        int[] base = new int[MAX_M];    // 주자의 상태
        for (int i = 0; i < N; i++) {
            cnt = 0;
            Arrays.fill(base, 0);
            while (cnt < 3) {   // 3아웃 체인지
                switch (hitter[i][arr[idx]]) {
                    case 0: // 아웃
                        cnt++;
                        break;
                    case 1: case 2: case 3: // 1,2,3루타
                        int hit = hitter[i][arr[idx]];
                        for (int j = 0; j < MAX_M; j++) {
                            if (base[j] > 0) {
                                if (base[j] + hit > 3) {
                                    score++;
                                    base[j] = 0;
                                } else base[j] += hit;
                            }
                        }
                        base[idx] = hit;
                        break;
                    case 4: // 홈련
                        int runner = 1;
                        for (int j = 0; j < MAX_M; j++) if (base[j] > 0) runner++;
                        score += runner;
                        Arrays.fill(base, 0);
                        break;
                }
                idx = (idx+1)%MAX_M;    // cyclic index
            }
        }
        return score;
    }

    // swap을 이용한 순열
    public static void simulate(int[] arr, int r) {
        if (r == MAX_M) {
            // 4번 타자 1번으로 고정
            swap(arr, 0, 3);
            result = Math.max(result, calcScore(arr));
            swap(arr, 0, 3);
        }
        for (int i = r; i < MAX_M; i++) {
            swap(arr, r, i);
            simulate(arr, r+1);
            swap(arr, r, i);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        hitter = new int[N][MAX_M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < MAX_M; j++) hitter[i][j] = Integer.parseInt(st.nextToken());
        }
        int[] arr = new int[MAX_M];
        for (int i = 0; i < MAX_M; i++) arr[i] = i;
        simulate(arr, 1);
        System.out.println(result);
    }
}
