package week26;

import java.util.PriorityQueue;

/**
 * 문제: 더 맵게
 * 링크: https://programmers.co.kr/learn/courses/30/lessons/42626
 * 
 * 풀이:
 * 힙자료구조인 pq로 음식을 스코빌 오름차순으로 정렬
 * 모든 음식의 스코빌 지수가 k이상이 될때까지
 * pq에서 두개의 음식을 꺼내서 새로운 음식 더하기
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class PG_42626_더맵게 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] {1, 2, 3, 9, 10, 12}, 7));
	}
	
	static public int solution(int[] scoville, int K) {
		
		int numberOfFoodBelowK = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		// pq 초기화
		for (int i = 0; i < scoville.length; i++) {
			int scovilleOfFood = scoville[i];
			if(scovilleOfFood<K) {
				numberOfFoodBelowK++;
			}
			pq.add(scovilleOfFood);
		}
		
		int cnt = 0; // 섞는 횟수
		
		while(numberOfFoodBelowK>0 && pq.size()>1) { // k이하인 음식이 있고 음식의 개수가 1개보다 많을 때
			int sum = pq.poll() + pq.poll()*2;
			if(sum>=K) {
				numberOfFoodBelowK -=2;
			}else {
				numberOfFoodBelowK--;
			}
			
			pq.add(sum);
			cnt++;
		}
		
		if(numberOfFoodBelowK>0) return -1;
		
        return cnt;
    }
}
