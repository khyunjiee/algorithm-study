package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2805 {
    /* 2805. 나무 자르기
    이분탐색
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int lef = 0;
        int rig = Integer.MAX_VALUE;
        int mid;
        long sum = 0;
        int answer = 0;
        while(lef <= rig) {
            mid = (lef + rig) / 2;
            sum = 0;
            for (int i = 0; i < N; i++) sum += Math.max(0, arr[i] - mid);
            if (sum >= M) {
                lef = mid + 1;
                answer = mid;
            }else {
                rig = mid - 1;
            }
        }
        System.out.println(answer);
    }
}
