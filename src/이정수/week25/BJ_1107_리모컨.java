package week25;

import java.util.Scanner;

/**
 * 문제: 리모컨
 * 링크: https://www.acmicpc.net/problem/1107
 * 
 * 풀이:
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class BJ_1107_리모컨 {
	
	static int min, N, digitsCnt;
	static int[] buttons;
	static boolean[] isBroke = new boolean[10];
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		int M = sc.nextInt();
		
		// 총 자릿수 구하기
		digitsCnt = Integer.toString(N).length();
		
		// 고장난 버튼 구하기
		for (int i = 0; i < M; i++) {
			
			isBroke[sc.nextInt()] = true;
			
		}
		
		// 100번 채널에서 +- 버튼으로 변경하는 경우
		min = Math.abs(100 - N);
		
		// 중복순열
		permutationWithRepition(0, 0);
		
		System.out.println(min);
	}

	
	// 중복순열
	private static void permutationWithRepition(int depth, int result) {
		
		if(depth>=digitsCnt-1) {
			min = Math.min(min, Integer.toString(result).length() + Math.abs(N - result)); // 최소 횟수 갱신
			if(depth==digitsCnt+1) return;
		}
		
		for (int i = 0; i < 10; i++) {
			if(isBroke[i]) continue;
			permutationWithRepition(depth+1, (result*10)+i);
		}
		
	}

}
