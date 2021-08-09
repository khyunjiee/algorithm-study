package com.ssafy.algoritm.study.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 후위표기식_1918 {
	/*
	 * 접근: 피연산자는 바로 출력, ')' 인 경우 스택에서 '('가 나올때까지 팝하고 출력, 
	 * 스택에 자신보다 우선순위가 높은 연산자가 있는 경우 팝하여 출력
	 * 
	 * 시간복잡도:O(N)?  N이 작으므로 고려할 필요 없음 
	 */

	public static void main(String[] args) throws IOException {
		
		BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();
		Stack<Character> tempStack = new Stack<>();
		char[] expression = bin.readLine().toCharArray();
		for(char e: expression) {
			if(e>='A' && e<='Z') { // 피연산자일 경우 출력
				System.out.print(e);
			}
			
			else if(e=='(') stack.push(e);
			
			else if(e==')') { // ')' 인 경우 스택에서 '('가 나올때까지 팝하고 출력
				while(stack.peek()!='(') System.out.print(stack.pop());
				stack.pop();
			}
			
			else {
				// 스택에 자신보다 우선순위가 높은 연산자가 있는 경우 팝하여 출력
				while(!stack.isEmpty()&&(priority(stack.peek())>=priority(e))) System.out.print(stack.pop());
				stack.push(e);
			}
			
		}
		while(!stack.isEmpty()) System.out.print(stack.pop());
	}
	
	static int priority(char c) {
		if(c=='(') return 0;
		else if(c=='+'||c=='-') return 1;
		else if(c=='*'||c=='/') return 2;
		return 0;
	}
	
}
