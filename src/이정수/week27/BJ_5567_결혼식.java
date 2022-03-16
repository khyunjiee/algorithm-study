package week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 문제: 결혼식
 * 링크: https://www.acmicpc.net/problem/5567
 * 
 * 풀이:
 * 친구관계를 나타내는 이차원 boolean 배열 생성
 * 상근이와 친구인 경우 친구의 친구들 확인
 * 
 * 시간복잡도:
 * O(N^2)
 * 
 * 풀이에 걸린 시간:
 * 20m
 *
 */
public class BJ_5567_결혼식 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine());
		int m = Integer.parseInt(in.readLine());
		
		boolean[][] friendRelationTable = new boolean[n+1][n+1]; // 친구 관계 테이블
		
		for (int i = 0; i < m; i++) {
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			friendRelationTable[a][b] = true;
			friendRelationTable[b][a] = true;
			
		}
		
		
		boolean[] visited = new boolean[n+1]; // 참석 여부
		int cnt = 0; // 참석하는 동기 카운트 변수
		
		
		for (int i = 2; i <= n; i++) {
			
			if(friendRelationTable[1][i]) { // 상근이와 친구 관계이면 
				// 아직 참석자에 포함되지 않았다면 포함
				if(!visited[i]) {
					cnt++;
					visited[i] = true;
				}
				
				for (int j = 2; j <= n; j++) { // 친구의 친구들 확인
					if(friendRelationTable[i][j] && !visited[j]) {
						cnt++;
						visited[j] = true;
					}
				}
				
			}
			
		}
		
		System.out.println(cnt);
		
	}

}
