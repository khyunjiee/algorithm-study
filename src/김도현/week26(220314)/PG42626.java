package pg;

import java.util.PriorityQueue;

public class PG42626 {
    /* Level 2. 더맵게
    우선순위 큐
    */
    public static void main(String[] args) {
        PG42626.Solution test = new PG42626.Solution();
        System.out.println(test.solution(new int[] {1, 2, 3, 9, 10, 12}, 7));
    }

    static class Solution {
        public int solution(int[] scoville, int K) {
            int answer = 0;
            int N = scoville.length;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int elem:scoville) pq.offer(elem);

            int p, q;
            for (int i = 0; i < N-1; i++) {
                if (pq.peek() >= K) break;
                p = pq.poll();
                q = pq.poll();
                pq.add(p + 2 * q);
                answer++;
            }
            if (pq.peek() < K) answer = -1;
            return answer;
        }
    }
}
