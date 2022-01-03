package com.ssafy.algo.study.week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * 문제:괄호 추가하기
 * 링크:https://www.acmicpc.net/problem/16637
 * 
 * 접근:
 * 괄호를 중첩하지 않으면서 완탐을 하는 방법을 찾지 못해 결국 구글링을 했습니다.
 * 연산자와 피연산자를 나누어 리스트로 관리합니다.
 * 순서에 맞춰 연산자와 피연산자를 하나씩 꺼내어 연산을 수행합니다.
 * 괄호를 사용하지 않는 경우와 사용하는 경우 두가지로 나누어 dfs로 수행하여 brute force로 해결합니다.
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 소요 시간:
 * 4h
 * 
 */
public class BJ_16637_괄호추가하기 {
	static List<Character> operator = new LinkedList<>();
	static List<Integer> operand = new LinkedList<>();
	static int result = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		String input = in.readLine();
		
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if(ch>='0' && ch<='9') {
				operand.add(ch-'0');
			}else {
				operator.add(ch);
			}
		}
		
		dfs(operand.get(0), 0);
		
		System.out.println(result);
	}
	private static void dfs(int sum, int idx) {
		if(idx==operator.size()) {
			result = Math.max(result, sum);
			return;
		}
		
		// 괄호 사용 x
		int newSum = calc(operator.get(idx), sum, operand.get(idx+1));
		
		dfs(newSum, idx+1);
		
		// 괄호 사용
		if(idx<operator.size()-1) {
			newSum = calc(operator.get(idx+1), operand.get(idx+1), operand.get(idx+2));
			newSum = calc(operator.get(idx), sum, newSum);
			dfs(newSum, idx+2);
		}
		
	}
	private static int calc(Character op, int a, int b) {
		switch(op) {
			case '+':
				return a+b;
			case '-':
				return a-b;
			case '*':
				return a*b;
		}
		return 0;
	}

}
