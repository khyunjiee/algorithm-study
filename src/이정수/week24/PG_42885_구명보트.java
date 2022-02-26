package week24;

import java.util.Arrays;

/**
 * 문제: 구명보트
 * 링크: https://programmers.co.kr/learn/courses/30/lessons/42885
 * 
 * 풀이:
 * 1. 오름차순 정렬
 * 2. 양쪽끝에 인덱스를 설정하여 두 위치의 합이 limit이하인 경우 둘다 태우고
 * 아닌 경우 무거운 사람만 태우기
 * 
 * 
 * 시간복잡도:
 * O(N)
 * 
 * 풀이에 걸린 시간:
 * 20m
 *
 */

public class PG_42885_구명보트 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] {70, 80, 50}, 100));
	}
	
	static public int solution(int[] people, int limit) {
		
		int left = 0;
		int right = people.length-1;
		
		Arrays.sort(people);
		
		int numberOfBoat = 0;
		
		while(left<=right) {
			if(people[left]+ people[right]<=limit) {
				left++;
				right--;
			}else {
				right--;
			}
			numberOfBoat++;
		}
		
        return numberOfBoat;
    }

}
