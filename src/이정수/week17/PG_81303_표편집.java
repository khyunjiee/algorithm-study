package com.ssafy.algo.study.week17;

import java.util.ArrayList;
import java.util.Stack;

/*
 * 문제:표 편집
 * 링크:https://programmers.co.kr/learn/courses/30/lessons/81303
 * 
 * 접근:
 * LinkedList를 사용했으나 get() 과 remove() 메소드의 시간복잡도가 O(N)인 관계로 시간초과가 발생했습니다.
 * 구글링으로 다른 해법에 대한 아이디어를 얻어 새로 풀었습니다.
 * 
 * stack에 삭제하는 라인의 인덱스를 넣고 미리 현재 존재하는 라인의 수만큼 "OOOOOOO...O"와 같이 문자열을 생성합니다.
 * stack에서 삭제한 라인의 인덱스를 꺼내면서 해당하는 인덱스에X를 삽입해줍니다.
 * 
 * 
 * 시간복잡도:
 * O(N)
 * 
 * 
 * 풀이에 걸린 시간:
 * 2h
 * 
 */
public class PG_81303_표편집 {
	public static void main(String[] args) {
		System.out.println(solution(8,2,new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"}));
	}
	
//	static public String solution(int n, int k, String[] cmd) {
//		
//		boolean[] deleted = new boolean[n];
//		Stack<int[]> stack = new Stack<>();
//		ArrayList<Integer> table = new ArrayList<>();
//		int pointer = k;
//		
//		for (int i = 0; i < n; i++) {
//			table.add(i);
//		}
//		
//		for (String command : cmd) {
//			String[] split = command.split(" ");
//			if(split[0].equals("U")) {
//				pointer -= Integer.parseInt(split[1]);
//				
//			}
//			else if(split[0].equals("D")) {
//				pointer += Integer.parseInt(split[1]);
//			}
//			else if(split[0].equals("C")) {
//				stack.push(new int[] {pointer, table.get(pointer)});
//				deleted[table.get(pointer)] = true;
//				table.remove(pointer);
//				if(pointer==table.size()) pointer--;
//			}
//			else if(split[0].equals("Z")) {
//				int[] history = stack.pop();
//				int idx = history[0];
//				int num = history[1];
//				deleted[num] = false;
//				table.add(idx, num);
//				if(idx<=pointer) pointer ++;
//			}
//			
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < deleted.length; i++) {
//			if(deleted[i]) sb.append("X");
//			else sb.append("O");
//		}
//		
//        return sb.toString();
//    }
	
	static public String solution(int n, int k, String[] cmd) {
		
		Stack<Integer> stack = new Stack<>();
		int pointer = k;
		int size = n;
		
		for (String command : cmd) {
			String[] split = command.split(" ");
			
			if(split[0].equals("U")) { // 위로 이동
				pointer -= Integer.parseInt(split[1]);
				
			}
			else if(split[0].equals("D")) { // 아래로 이동
				pointer += Integer.parseInt(split[1]);
			}
			else if(split[0].equals("C")) { // 삭제
				stack.push(pointer);
				size--;
				if(pointer==size) pointer--;
			}
			else if(split[0].equals("Z")) { // 복구
				int history = stack.pop();
				size++;
				if(history<=pointer) pointer ++;
			}
			
		}
		
		// 결과 문자열 생성
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append("O");
		}
		
		while(!stack.isEmpty()) {
			int idx = stack.pop();
			sb.insert(idx, "X");
		}
		
        return sb.toString();
    }
	
}
