package com.ssafy.algo.study.week15;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class 보물상자비밀번호_5658 {
	/*
	 * 접근:
	 *1. 인덱스로 rotate
	 *2. rotate 할때마다 생성한 숫자들은 Set로 관리하여 중복으로 새지 않도록 함
	 *3. Hexadecimal => decimal로 변환하여 리스르로 정렬
	 * 
	 * 시간복잡도:
	 * ?
	 * 
	 * 풀이에 걸린 시간:
	 * 1h
	 * 
	 */

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			int N = sc.nextInt();
			int K = sc.nextInt();
			sc.nextLine(); // 빈 문자열 처리
			
			String numbers = sc.nextLine().trim();
			
			int rotation = N/4; // 회전 횟수
			int digits = rotation; // 숫자 하나의 자릿수
			
			
			Set<String> numberSet = new HashSet<>(); // 중복 숫자 관리를 위한 set
			
			for (int startIdx = 0; startIdx < rotation; startIdx++) { // 첫 숫자 시작 인덱스(회전 횟수만큼 한칸씩 뒤로)
				
				for (int j = 0; j < 4; j++) { // 보물 상자의 네변에 대하여
					
					int firstDigitIdx = startIdx + (digits * j); // 각 수의 시작 인덱스
					
					if(firstDigitIdx+digits>=numbers.length()) {
						numberSet.add(numbers.substring(firstDigitIdx, numbers.length())+numbers.substring(0, ((firstDigitIdx+digits)%numbers.length())));
					}else {
						numberSet.add(numbers.substring(firstDigitIdx, (firstDigitIdx+digits)%numbers.length()));
					}
					
				}
			}
			
			LinkedList<Integer> list = new LinkedList<>(); // 정렬하기 위한 리스트
			
			// Set의 내용들을 리스트로 변환
			for (String createdNumber : numberSet) {
				list.add(Integer.parseUnsignedInt(createdNumber, 16));
			}
			
			// 내림차순 정렬
			Collections.sort(list);
			Collections.reverse(list);
			
			System.out.println("#" + tc + " " + list.get(K-1));
				
			
		}
	}

}
