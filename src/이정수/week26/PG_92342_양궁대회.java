package week26;

import java.util.Arrays;

/**
 * 문제: 양궁대회
 * 링크: https://programmers.co.kr/learn/courses/30/lessons/92342
 * 
 * 풀이:
 * 완탐
 * 
 * 
 * 시간복잡도:
 * ?
 * 
 * 풀이에 걸린 시간:
 * 4h
 *
 */
public class PG_92342_양궁대회 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(5, new int[] {2,1,1,1,0,0,0,0,0,0,0})));
	}
	
	static int[] result = {-1};
	static int N, maxScoreDiff = 0;
	static int[] Info;
	public static int[] solution(int n, int[] info) {
		
		Info = info;
		N = n; // 화살 n발
		
		dfs(0,0, new int[] {0,0,0,0,0,0,0,0,0,0,0}); // 10점, 9점, 8점 ..., 0점
		
		return result;
        
    }
	
	private static void dfs(int depth, int arrowCnt, int[] record) {
		
		if(depth==10) { // 모든 과녁에 대한 화살 명중 횟수 계산이 끝나면
			record[10] = N - arrowCnt;
			if(result[0]==4) {
				System.out.println(Arrays.toString(result));
			}
			// 점수 계산
			int[] scores = calcScore(record);
			int scoreDiff = scores[1]-scores[0];
			
			if(scoreDiff>0) {// 이겼고
				
				if(maxScoreDiff<scoreDiff) { // 기존 점수차이보다 더 큰 점수차이로 이긴 경우
					result = Arrays.copyOf(record, record.length);
					maxScoreDiff = scoreDiff;
				}
				else if(maxScoreDiff==scoreDiff) { // 기존 점수차와 동일하면
					// 가장 낮은 점수를 더 많이 맞힌 경우 구하기
					for (int i = 10; i >= 0; i--) {
						if(record[i]>result[i]) {
							result = Arrays.copyOf(record, record.length);
							break;
						}else if(record[i]<result[i]) {
							break;
						}
					}
				}
				
			}
			
			return;
		}
		
		int availableArrows = N-arrowCnt;
		
		for (int arrows = 0; arrows <= availableArrows; arrows++) {
			record[depth] = arrows;
			dfs(depth+1, arrowCnt+arrows, record);
		}
		
		
	}
	private static int[] calcScore(int[] record) {
		
		int scores[] = {0,0}; // {어피치 점수, 라이언 점수}
		
		for (int i = 0; i < 11; i++) {
			if(Info[i]==0 && record[i]==0) continue;
			if(Info[i]<record[i]) {
				scores[1] += 10-i;
			}else {
				scores[0] += 10-i;
			}
		}
		
		return scores;
		
	}

}
