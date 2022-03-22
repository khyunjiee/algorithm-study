package week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제: 용액
 * 링크: https://www.acmicpc.net/problem/2473
 * 
 * 풀이:
 * 용액의 특성값에 따라 소팅
 * 두 개의 용액 고르기는 조합으로 완탐
 * 나머지 하나는 이분탐색 시도
 * 
 * 
 * 시간복잡도:
 * O(NlogN)
 * 
 * 풀이에 걸린 시간:
 * 2h
 *
 */
public class BJ_2473_용액 {
	
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		int[] solutions = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		for (int i = 0; i < N; i++) {
			solutions[i] = Integer.parseInt(st.nextToken());
		}
		
		// 오름차순 소팅
		Arrays.parallelSort(solutions);
		
		int[] ans = {0,0,0};
		long min = Long.MAX_VALUE; // 세 용액의 특성값의 합의 절대값의 최소값
		
		int[] delta = {-2,-1,1,2};
		int[] delta2 = {-2,-1,0,1,2};
		
		label: for (int i = 0; i < N-1; i++) {// 용액 a 선택
			int a = solutions[i]; 
			for (int j = i+1; j < N; j++) {// 용액 b 선택
				
				int b = solutions[j]; 
				
				int target = -a-b; // 합쳤을때 0이되는 용액의 특성값
				
				// 이분탐색으로 최대한 가까운 용액 찾기
				int targetIdx = Arrays.binarySearch(solutions, target);
				
				// 존재하는 경우
				if(targetIdx>=0) {
					
					if(targetIdx==i || targetIdx==j) { // 이미 사용중인 경우
						
						// 양옆으로 두칸 확인
						for (int d = 0; d < 4; d++) {
							
							int nextIdx = targetIdx + delta[d];
							
							if(!isValid(nextIdx, i, j)) {
								continue;
							}
							
							long sum  = (long)a+b+solutions[nextIdx];
							if(sum<0) {
								sum *=-1;
							}
							
							if(min>sum) {
								min = sum;
								ans = new int[] {a,b,solutions[nextIdx]};
							}
							
						}
					}else { // 특성값의 합이 0이 되는 경우
						
						min = 0;
						ans = new int[] {a,b,target};
						break label;
						
					}
					
				}else {// 없는 경우
					
					// 양옆으로 두칸 확인
					for (int d = 0; d < 5; d++) {
						
						int nextIdx = (targetIdx*-1) - 1 + delta2[d];
						
						if(!isValid(nextIdx, i, j)) {
							continue;
						}
						
						long sum  = (long)a+b+solutions[nextIdx];
						if(sum<0) {
							sum *=-1;
						}
						
						if(min>sum) {
							min = sum;
							ans = new int[] {a,b,solutions[nextIdx]};
						}
						
					}
					
				}
			}
		}
		
		Arrays.sort(ans);
		
		System.out.println(ans[0]+" "+ans[1]+" "+ans[2]);
		
	}

	
	// 사용가능한 위치의 용액인지 확인
	private static boolean isValid(int idx, int i, int j) {
		return idx>=0 && idx<N && idx!=i && idx!=j;
	}

}
