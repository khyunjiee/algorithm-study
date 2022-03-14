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
public class BJ_1107_리모컨2 {
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		// 총 자릿수 구하기
		int digitsCnt = Integer.toString(N).length();
		
		boolean[] isBroke = new boolean[10];
		
		// 고장난 버튼 구하기
		for (int i = 0; i < M; i++) {
			
			isBroke[sc.nextInt()] = true;
			
		}
		
		// 100번 채널에서 +- 버튼으로 변경하는 경우
		int min = Math.abs(100 - N);
		
		String[] buttons = {"0","1","2","3","4","5","6","7","8","9"};
		
		for (int i = 0; i < 999999; i++) {
			boolean flag = false;
			String numberInStr = Integer.toString(i);
			for (int j = 0; j < 10; j++) {
				if(isBroke[j] && numberInStr.contains(buttons[j])) {
					flag= true;
					break;
				}
			}
			if(flag) continue;
			
			min = Math.min(min, numberInStr.length() + Math.abs(N-i));
		}
		
		
		System.out.println(min);
	}

	
}
