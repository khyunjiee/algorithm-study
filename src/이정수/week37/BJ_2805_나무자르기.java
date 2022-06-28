package week37;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 나무 자르기
 * 링크: https://www.acmicpc.net/problem/2805
 * 
 * 풀이: 
 * 이분탐색 Lower Bound
 * 
 * 
 * 시간복잡도:
 * O(NlogM)
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class BJ_2805_나무자르기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] trees = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 1, right = 1000000000;
		int mid= 0;
		
		while(left<right) {
			mid = (left + right) / 2;
			
			long totalLen = 0;
			for (int i = 0; i < trees.length; i++) {
				totalLen += trees[i]>mid?trees[i]-mid:0;
			}
			
			if(totalLen<M) {
				right = mid;
			}else {
				left = mid+1;
			}
		}
		
		System.out.println(left-1);
		
	}

}
