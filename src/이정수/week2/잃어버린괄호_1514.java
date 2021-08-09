package com.ssafy.algoritm.study.week2;

import java.util.Arrays;
import java.util.Scanner;


public class 잃어버린괄호_1514 {
	/*
	 * 접근: +와 - 연산자로 이루어진 수식의 최소값은 - 하는 값을 최대로 함으로써
	 * 구할 수 있습니다. + 연산들을 먼저 수행합니다.
	 * 
	 * 시간 복잡도: 식의 길이가 50이하로 최대 25개 정도의 연산자가 존재할 수 있고 연산자의 수만큼
	 * for문으로 돌기 때문에 시간복잡도를 고려하지 않아도 됩니다.
	 */

	public static void main(String[] args) {
		
		
		
		Scanner sc = new Scanner(System.in);
		String originExpression = sc.next();
		
		// 더하기 연산들만 먼저 수행
		String[] expressions = originExpression.split("\\-");
		int[] numbers= new int[expressions.length];
		for(int i=0;i<expressions.length;i++) {
			int[] nums = Arrays.stream(expressions[i].split("\\+")).mapToInt(Integer::parseInt).toArray(); 
			for(int num: nums) {
				numbers[i] += num;
			}
			
		}
		
		// 첫번째 요소로부터 나머지 요소 모두 빼주기
		int result = numbers[0];
		for(int i=1;i<numbers.length;i++) {
			result -= numbers[i];
		}
		
		
		System.out.println(result);
		
	}

}
