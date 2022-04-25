package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1007 {
    /* 1007. 벡터 매칭
    조합
    */
    private static double result = Double.MAX_VALUE;
    private static Point[] points;
    private static int M, N;
    public static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double calculate(int[] arr) {
        int vecX = 0, vecY = 0;
        for (int i = 0; i < N; i++) {
            vecX += points[i].x;
            vecY += points[i].y;
        }
        for (int i = 0; i < M; i++) {
            vecX -= 2 * points[arr[i]].x;
            vecY -= 2 * points[arr[i]].y;
        }
        return Math.sqrt((double) vecX * vecX + (double) vecY * vecY);
    }

    private static void combination(int[] arr, int n, int r, int cnt, int s) {
        if (cnt == r) {
            result = Math.min(result, calculate(arr));
            return;
        }
        for (int i = s; i < n; i++) {
            arr[cnt] = i;
            combination(arr, n, r, cnt + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            result = Double.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            points = new Point[N];
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                points[j] = new Point(Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()));
            }
            M = N / 2;
            int[] arr = new int[M];
            combination(arr, N, M, 0, 0);
            System.out.println(result);
        }
    }
}
