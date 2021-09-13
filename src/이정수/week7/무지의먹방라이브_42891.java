package com.ssafy.algo.study.week7;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class 무지의먹방라이브_42891 {
	/*
	 * 접근:
	 * 단순 반복문으로 구현하면 최대 2 x 10^13 번 반복하여 시간초과 발생
	 * 정렬 후 음식을 최대한 한번에 없애는 방식으로 접근
	 * 
	 * 시간복잡도: 정렬에 O(NlogN) N은 음식의 개수, 반복문 수행 횟수는 알 수 없음
	 * 
	 * 
	 */

	public static void main(String[] args) {
		System.out.println(solution(new int[] {3, 1, 2}, 5));
	}
	
	// 푸드 클래스
	static class Food implements Comparable<Food>{ 
		int idx; // 음식 번호
		int time; // 걸리는 시간
		public Food(int idx, int time) {
			super();
			this.idx = idx;
			this.time = time;
		}
		@Override
		public int compareTo(Food o) {
			return this.time - o.time;
		}
	}
	
	static Comparator<Food> compIdx = new Comparator<Food>() { // 음식 번호로 정렬하기 위한 comparator
		@Override
		public int compare(Food o1, Food o2) {
			return o1.idx - o2.idx;
		}
	};
	public static int solution(int[] food_times, long k) {
		List<Food> foodList = new LinkedList<>();
		for (int i = 0; i < food_times.length; i++) foodList.add(new Food(i+1, food_times[i])); // 푸드리스트 생성
		
		Collections.sort(foodList); // 시간순 오름차순 정렬
		
		long preTime = 0;
		int remainFoodSize = food_times.length;
		int i=0;
		for(Food food:foodList) {
			long diff = food.time - preTime; // 시간 차이 (시간 크기 고려하여 long형 사용)
			if(diff!=0) {
				if(k<diff*remainFoodSize) {
					k %= remainFoodSize;
					foodList.subList(i, foodList.size()).sort(compIdx); // 뒤에 남은 부분만 정렬
					return foodList.get(i+(int)k).idx; // 이미 다 먹은 음식이 리스트에 남아있으므로 i 더 해주기
				}
				k -= diff*remainFoodSize; // 방송 중지까지 남은 시간
				preTime = food.time;
			}
			i++;
			remainFoodSize--;
		}
		return -1;
		
		
    }
}
