package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

// 동철이의 일 분배
/*
 * 순열을 이용하여 하나씩 값을 곱해가면서 최댓값을 구하는 문제이다.
 * 16!은 경우의 수가 너무 많지만, 한명이라도 확률이 0인 것을 선택하면 무의미하므로
 * 0을 선택하는 경우는 무시하는 것과, 이전의 최적해보다 현재까지의 해가 더 낮으면 무시하는 경우로
 * 백트래킹기법을 이용하여 경우의 수를 많이 줄일 수 있다.
 * 여기서 0을 선택하는 경우가 초기에 나올 경우 더 많이 경우의 수를 줄일 수 있으므로,
 * 0을 가장 많이 가지고 있는 사람을 우선으로 정렬을 하여 백트래킹의 효율을 최대로 하였다.
 */
public class Solution1865_강동석 {

	static int N;
	static double result;
	static int[][] p;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			p = new int[N][N];
			visited = new boolean[N];
			
			for(int i=0; i<N; ++i) {
				str = br.readLine().split(" ");
				for(int j=0; j<N; ++j) {
					p[i][j] = Integer.parseInt(str[j]);
				}
			}
			Arrays.sort(p, new Comparator<int[]>(){
				@Override
				public int compare(int[] o1, int[] o2) {
					return numOfZero(o2)-numOfZero(o1); // 0의 갯수를 기준으로 내림차순
				}
			});
			
			result = 0.0;
			permutation(0,1.0); // 순열
			System.out.printf("#%d %.6f\n",tc,result*100);
		}
	}
	public static void permutation(int n, double percentage) { // n명에게 일을 배분완료, 현재 확률은 p
		if(percentage<result) return; // 현재까지 배분받은 사람들의 확률의 곱이 이미 기존보다 낮으면 종료
		if(n==N) { // 모든 사람에게 일을 다 배분하면
			if(result<percentage) result=percentage; // 최솟값 갱신
			return;
		}
		
		for(int i=0; i<N; ++i) {
			if(!visited[i]) {
				if(p[n][i]==0) continue; // n번쨰 사람이 i번쨰 일의 성공확률이 0이면 다음으로 넘김
				visited[i] = true;
				permutation(n+1, percentage*p[n][i]*0.01); // n번쨰 사람이 i번쨰 일을 배정 받은 후의 확률
				visited[i] = false;
			}
		}
	}
	public static int numOfZero(int[] arr) { // int[]의 0의 갯수 반환
		int cnt = 0;
		for(int i=0,len=arr.length; i<len; ++i) {
			if(arr[i]==0) cnt++;
		}
		return cnt;
	}
}
