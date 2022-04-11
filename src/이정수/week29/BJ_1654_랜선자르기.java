package week29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 랜선 자르기
 * 링크: https://www.acmicpc.net/problem/1654
 * 
 * 풀이:
 * upper bound
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class BJ_1654_랜선자르기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] len = new int[K];
		long max = -1;
		for (int i = 0; i < K; i++) {
			len[i] = Integer.parseInt(in.readLine());
			max = Math.max(max, len[i]);
		}
		
		long left=0, right=max+1, mid = 0; // upper bound를 구해야하므로 max값이 구하는 값일때를 위해 max + 1을 right로 설정
		
		while(left<right) {
			mid = (left + right)/2;
			// 만들어지는 랜선의 개수 구하기
			long cnt = 0;
			for (int i = 0; i < K; i++) {
				cnt += len[i]/mid;
			}
			
			if(cnt>=N) { // N개 보다 같거나 많은 랜선이 만들어지면 랜선 길이 늘리기
				left = mid+1;
			}else { // N개보다 적은 랜선이 만들어지면 랜선 길이 줄이기
				right = mid;
			}
		}
		
		System.out.println(left-1);
		
	}

}
