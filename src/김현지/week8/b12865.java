package baekjoon.sep.sep27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 12865 평범한 배낭 [G5]
 *
 * 접근 방식:
 * DP 0-1 knapsack 알고리즘으로 풀었습니다.
 * 일차원 배열을 활용해 공간 복잡도를 작게 설정했습니다.
 * 배열의 끝부터 가치를 계산했습니다.
 *
 * 시간 복잡도:
 * O(N*K)
 * 100 * 100,000 = 10,000,000 -> 천만으로 시간이 터지지 않는다고 판단했습니다.
 *
 * 소요 시간:
 * 10분
 *
 * 12484KB 132ms
 */

public class b12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());   // 물건의 수
        int K = Integer.parseInt(st.nextToken());   // 배낭의 총 무게

        int[][] products = new int[N][2];   // 물건의 무게와 가치를 담을 배열
        int[] backpack = new int[K+1];      // 배낭의 무게마다 최적의 가치를 담을 배열

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            products[i][0] = Integer.parseInt(st.nextToken());  // 무게
            products[i][1] = Integer.parseInt(st.nextToken());  // 가치
        }

        for (int i = 0; i < N; i++) {
            int w = products[i][0]; // 현재 고려할 물건의 무게
            int v = products[i][1]; // 현재 고려할 가치의 무게
            for (int j = K; j >= w; j--) {  // 일차원을 사용하므로 배열의 끝부터 적재 && 현재 고려할 물건을 담을 수 있는 범위까지만 고려
                // 현재 배낭 무게에 물건을 담는 것이 최적이라면 업데이트
                if (backpack[j] < backpack[j-w] + v) backpack[j] = backpack[j-w] + v;
            }
        }

        System.out.println(backpack[K]);
    }
}
