package programmers;

import java.util.PriorityQueue;

// 더 맵게
/*
 * PriorityQueue를 이용하여 힙정렬을 한 후 최솟값 2개를 계속 계산하여 해결한다.
 * 최솟값이 K보다 작으면서 pq에 값이 1개밖에 없으면 -1을 리턴하며 종료한다.
 */
public class Programmers_42626 {
	
	public static void main(String[] args) {
		int[] scoville = {1, 2, 3, 9, 10, 12};
		int K = 150;
		System.out.println(solution(scoville, K));
	}
	
	public static int solution(int[] scoville, int K) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i=0,len=scoville.length; i<len; ++i) {
			pq.offer(scoville[i]);
		}
		int times = 0; // 섞은 횟수
		while(true) {
			if(pq.peek()<K) { // 최솟값이 K보다 작으면
				int num1 = pq.poll(); // 최솟값 뽑고
				if(!pq.isEmpty()) { // 아직 남아있으면 그다음 최솟값 뽑고
					int num2 = pq.poll();
					pq.offer(num1+num2*2); // 새로 만들어서 삽입
					times++; // 횟수 1증가
				}else { // 2번째 최솟값이 남아있지 않으면
					times = -1; // -1로 종료
					break;
				}
			}else { // 최솟값이 K이상이면
				break;
			}
		}
        return times;
    }
}