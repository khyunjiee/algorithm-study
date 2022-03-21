package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ15684 {
    /* 15684. 사다리 조작
    시뮬레이션
     */
    static int N, M, H, result = 4;
    static boolean[][] map;

    public static boolean simulate(boolean[][] map) {
        int idx;
        for (int i = 0; i < N; i++) {
            idx = i;
            for (int j = 0; j < H; j++) {
                if (idx > 0 && map[idx-1][j]) idx--;
                else if (idx < N-1 && map[idx][j]) idx++;
            }
            if (idx != i) return false;
        }
        return true;
    }

    public static void combination(int[] arr, int[] tar, int n, int r, int s, int cnt) {
        if (result == r) return;
        if (r == cnt) {
            int p, q;
            for (int i = 0; i < r; i++) {
                p = tar[i]/H;
                q = tar[i]%H;
                map[p][q] = true;
            }
            if (simulate(map)) result = r;

            for (int i = 0; i < r; i++) {
                p = tar[i]/H;
                q = tar[i]%H;
                map[p][q] = false;
            }
            return;
        }
        for (int i = s; i < n; i++) {
            tar[cnt] = arr[i];
            combination(arr, tar, n, r, i+1, cnt+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new boolean[N-1][H];
        int a, b;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken())-1;
            b = Integer.parseInt(st.nextToken())-1;
            map[b][a] = true;
            set.add(b*H+a);
        }

        int[] arr = new int[(N-1)*H-M];
        int cnt = 0;
        for (int i = 0; i < (N-1)*H; i++) {
            if (set.contains(i)) continue;
            arr[cnt++] = i;
        }
        for (int i = 0; i < 4; i++) {
            if (i > result) break;
            combination(arr, new int[i], (N-1)*H-M, i, 0, 0);
        }

        if (result > 3) System.out.println(-1);
        else System.out.println(result);
    }
}
