package programmers;

import java.util.Arrays;

// 구명보트
public class Programmers_42885 {
	
	public static void main(String[] args) {
		int[] people = {70,50,80,50};
		int limit = 100;
		System.out.println(solution(people, limit));
	}
	
	public static int solution(int[] people, int limit) {
		if(people.length==1) return 1; // 사람이 1명이면 정답 1
        int answer = 0;
        Arrays.sort(people); // 오름차순 정렬
        int start = 0;
        int end = people.length-1;
        while(start<end) {
        	if(people[start]+people[end]<=limit) start++;
        	end--;
        	answer++;
        }
        if(start==end) answer++;
        
        return answer;
    }
}