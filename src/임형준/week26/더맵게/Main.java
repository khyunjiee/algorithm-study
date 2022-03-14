package src.프로그래머스.힙.더맵게;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int solution1 = solution.solution(new int[]{1,1,100}, 7);
        System.out.println(solution1);
    }

    static class Solution {
        public int solution(int[] scoville, int K) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for(int num : scoville) pq.offer(num);
            int cnt =0;
            boolean flag = true;
            while(true){
                if(!pq.isEmpty() && pq.peek() >= K) break;
                if(pq.size()==2) {
                    int p1 = pq.poll();
                    int p2 = pq.poll();
                    if(p1+p2*2 <= K){
                        flag = false;
                    }else{
                        cnt++;
                    }
                    break;
                }
                cnt++;
                int p1 = pq.poll();
                int p2 = pq.poll();
                pq.offer(p1 + p2*2);
            }
            if(flag){
                return cnt;
            }else{
                return -1;
            }
        }
    }
}
