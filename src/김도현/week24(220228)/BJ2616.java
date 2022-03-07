package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2616 {
    /* 2616. 소형기관차
    DP
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());

        int[] train = new int[N-M+1];
        int temp = 0;
        for (int i = 0; i < M; i++) temp += arr[i];
        train[0] = temp;
        for (int i = 0; i < N-M; i++) {
            temp -= arr[i];
            temp += arr[i+M];
            train[i+1] = temp;
        }

        int[][] DP = new int[3][N-M+1];
        for (int i = 0; i < 3; i++) {
            for (int j = i*M; j < N+1+(i-3)*M; j++) {
                if (i > 0) {
                    DP[i][j] = Math.max(DP[i][j-1], DP[i-1][j-M] + train[j]);
                }else if (j > 0) {
                    DP[i][j] = Math.max(DP[i][j-1], train[j]);
                }else DP[i][j] = train[0];
            }
        }
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < N-M+1; j++) {
//                System.out.print(DP[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println(DP[2][N-M]);
    }
}
