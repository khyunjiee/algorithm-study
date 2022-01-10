package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2212 {
    /* 2212. 센서
    센서의 위치를 정렬 후 센서 사이의 거리가 큰 순으로 집중국의 수 - 1개의 좌표의
    차이의 합을 구한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        if (N <= K) {
            System.out.println(0);
            return;
        }
        Arrays.sort(arr);   // 센서의 위치 정렬
        int result = arr[N-1] - arr[0];
        int[] diff = new int[N-1];
        for (int i = 0; i < N-1; i++) diff[i] = arr[i+1] - arr[i];
        Arrays.sort(diff);  // 센서 사이의 거리 정렬
        for (int i = 0; i < K-1; i++) result -= diff[N-2-i];
        System.out.println(result);
    }
}
