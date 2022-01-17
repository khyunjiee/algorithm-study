package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj1300 {
    /* 1300. K번째 수
    K번째 수를 어떤 수로 가정하고 그 수를 기준으로 이분 탐색
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        long lef = 1, rig = K, result = 0;
        while (lef <= rig) {
            long mid = (lef + rig) / 2;
            long cnt = 0;
            cnt += mid / N * N;
            for (long i = mid / N + 1; i <= N; i++) {
                cnt += (Math.min(mid / i, N));
                if (cnt > K) break;
            }
            if (cnt < K) lef = mid + 1;
            else {
                result = mid;
                rig = mid - 1;
            }
        }
        System.out.println(result);
    }
}
