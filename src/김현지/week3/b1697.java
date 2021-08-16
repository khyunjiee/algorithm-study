package baekjoon.aug.aug15;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 1697 숨바꼭질
 *
 * 접근 방법:
 * bfs와 메모이제이션을 활용했다.
 * N이 K일 경우는 이미 같은 위치이므로 0을 리턴했고,
 * 이외의 경우에는 memo[N]에 1을 저장하고 시작했다.
 * 큐가 빌 때까지 반복하면서 K에 도달할 때를 완전탐색했다.
 *
 * 시간 복잡도:
 * O(N^2) -> BFS
 */

public class b1697 {

    static int N, K;
    static int[] memo;  // 해당 위치를 몇 번 째로 갔는지 저장

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        memo = new int[100001];

        if (N == K) {
            System.out.println(0);
        } else {
            bfs(N);
        }

    }

    private static void bfs(int num) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(num);
        memo[num] = 1;  // 현재 위치는 1로 저장

        while (!queue.isEmpty()) {  // 큐가 빌 때까지 반복
            int current = queue.poll();

            for (int i = 0; i < 3; ++i) {
                int next;

                // 세 가지 경우를 모두 표현
                if (i == 0) next = current+1;
                else if (i == 1) next = current-1;
                else next = current*2;

                // 다음 위치가 K(목적지)일 경우 현재 위치에 저장된 숫자를 리턴한다
                if (next == K) {
                    System.out.println(memo[current]);
                    return;
                }

                // 위치의 범위는 0~100000까지이며, 한 번도 가지 않은 곳만 갈 수 있다.
                if (next >= 0 && next < memo.length && memo[next] == 0) {
                    queue.offer(next);
                    memo[next] = memo[current] + 1;
                }
            }
        }
    }
}
