package com.ssafy.algo.study.week3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class 단어수학_1339 {
	/*
	 * 접근: 가장 높은 자리글자부터 큰수를 대입하려고 했으나 같은 자리에 위치한 서로 다른
	 * 알파벳에 어떻게 숫자를 부여할지를 결정할 수 없습니다.
	 * 최대 10개의 알파벳에 숫자를 부여하는 순열의 가짓수는 10!입니다. 1초가 조금 안걸리므로
	 * 시간기준인 2초를 넘기지 않을 수 있습니다.
	 * 
	 * 시간복잡도:모름
	 */
	static List<Character> chars;
	static char[] perm;
	static boolean[] isSelected;
	static int R , max = 0, N;
	static char[][] words;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		words = new char[N][];
		chars = new ArrayList<>();
		
		
		for(int i=0; i<N; i++) {
			words[i] = sc.next().toCharArray(); // 단어를 저장하는 이차원 배열
			// 단어들에 포함된 문자 배열 만들기
			for(char c:words[i]) if(!chars.contains(c)) chars.add(c); 
		}
		
		isSelected = new boolean[chars.size()];
		R = chars.size();
		perm = new char[chars.size()]; // 생성된 순열 담는 배열
		
		// 순열 생성
		permutation(0);
		
		
		// 합 구하기
		System.out.println(max);
	}
	
	
	private static void permutation(int cnt) {
		if(cnt==R) {// 순열 완성
			int total = sum();
			if(total>max) max = total;
			return;
		}
		for(int i=0;i<R;i++) {
			// 알파벳 선택
			if(isSelected[i]) continue;
			perm[cnt] = chars.get(i);
			isSelected[i] = true;
			permutation(cnt+1);
			isSelected[i]= false;
		}
	}
	
	private static int sum() {
		int sum = 0; 
		for(int i=0; i<N; i++) {
			int number = 0;
			for(int j=0;j<words[i].length;j++) {// 자리에 가중치 곱해주면서 더해주기
				int idx=0;
				for(int k=0;k<perm.length;k++) if(perm[k]==words[i][j]) {
					idx = k; break;
				}
				number = (9-idx) +  number*10;
			}
			sum += number;
		}
		return sum;
	}
	
}
