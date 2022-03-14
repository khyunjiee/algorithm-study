package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2075 {
    /* 2075. N번째 큰 수
    우선순위 큐
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++)
            pq.offer(map[N-1][i]);
        int result = pq.peek();


        for (int i = N-2; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                if (pq.peek() < map[i][j]) {
                    pq.poll();
                    pq.offer(map[i][j]);
                }
            }
            if (result == pq.peek()) break;
            result = pq.peek();
        }
        System.out.println(result);
    }
}