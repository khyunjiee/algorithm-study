package com.ssafy.algo.study.week4;

import java.util.Arrays;
import java.util.Scanner;

public class 암호만들기_1759 {
	/*
	 * 접근:
	 * 알파벳 선택 조합 모두 만들어서 각각의 경우 조건확인
	 * 
	 * 시간 복잡도:
	 * ?
	 * 조합 15C8 = 6435
	 * 각 조합마다 L번의 비교 연산 수행
	 */
	
	static int L;
	static char[] chars, result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		L = sc.nextInt();
		int C = sc.nextInt();
		
		chars = new char[C];
		
		for (int i = 0; i < C; i++) chars[i] = sc.next().charAt(0);
		
		Arrays.sort(chars);
		
		result  = new char[L];
		combination(0, 0);
	}

	private static void combination(int cnt, int start) {
		if(cnt==L) {
			if(check()) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < result.length; i++) {
					sb.append(result[i]);
				}
				System.out.println(sb.toString());
			}
			return;
		}
		
		for (int i = start; i < chars.length; i++) {
			result[cnt] = chars[i];
			combination(cnt+1, i+1);
		}
	}

	private static boolean check() {
		int vowels = 0; // 모음
		int consonants = 0; // 자음
		char[] vowel = {'a', 'e', 'i', 'o', 'u'};
		for (int i = 0; i < result.length; i++) {
			if(result[i]=='a' || result[i]=='e' ||result[i]=='i' ||result[i]=='o' ||result[i]=='u') vowels++;
			else consonants++;
		}
		
		if(vowels==0 || consonants<2) return false;
		return true;
	}

}
