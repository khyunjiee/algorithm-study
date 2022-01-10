package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2110 {
    /* 2110. 공유기 설치
    인접한 두 공유기 사이의 최대 거리를 기준으로 이분 탐색
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        int lef = 1, rig = arr[N-1] - arr[0], mid;
        int cnt, prev, answer = 0;
        while (lef <= rig) {
            mid = (lef + rig) / 2;
            cnt = 1;
            // 현재 지정한 공유기 사이의 최대 거리가 주어진 집의 좌표에서 만족하는지 확인
            prev = arr[0];
            for (int i = 1; i < N; i++) {
                if (arr[i] - prev >= mid) {
                    prev = arr[i];
                    cnt++;
                }
            }
            if (cnt >= C) { // 만족할 경우 lef를 현재 지정한 최대 거리 + 1로 설정
                lef = mid + 1;
                answer = mid;   // 현재 최대 거리가 만족하므로 저장
            } else rig = mid - 1;   // 만족하지 않을 경우 rig를 현재 지정한 최대 거리 -1로 설정
        }

        System.out.println(answer);
    }
}
