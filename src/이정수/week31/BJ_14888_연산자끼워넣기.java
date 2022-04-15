package week31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 연산자 끼워넣기
 * 링크: https://www.acmicpc.net/problem/14888
 * 
 * 풀이:
 * 중복순열(?)
 * 
 * 
 * 시간복잡도:
 * O(N!)보다 작음
 * 
 * 풀이에 걸린 시간:
 * 30m
 *
 */
public class BJ_14888_연산자끼워넣기 {
	
	static final char[] operator = {'+','-','*','/'};
	static int[] operatorCnt = {0,0,0,0};
	static int[] A;
	static char[] opertorSequence;
	static int N, max = Integer.MIN_VALUE, min=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		A = new int[N];
		opertorSequence = new char[N-1];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < 4; i++) {
			operatorCnt[i] = Integer.parseInt(st.nextToken());
		}
		
		permutation(0);
		
		System.out.println(max);
		System.out.println(min);
	}

	private static void permutation(int depth) {
		if(depth==N-1) {
			int result = runOperation();
			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if(operatorCnt[i]!=0) {
				opertorSequence[depth] = operator[i];
				operatorCnt[i]--;
				permutation(depth+1);
				operatorCnt[i]++;
			}
		}
	}

	private static int runOperation() {
		
		int result = A[0];
		
		for (int i = 1; i < N; i++) {
			switch(opertorSequence[i-1]) {
				case '+':
					result += A[i];
					break;
				case '-':
					result -= A[i];
					break;
				case '*':
					result *= A[i];
					break;
				case '/':
					result /= A[i];
					break;
			}
		}
		
		return result;
	}

}
