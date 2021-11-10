package com.ssafy.algo.study.week14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class 기둥과보설치_60061 {
	/*
	 * 접근:
	 * 못풀었습니다
	 * 처음에 풀다 실패해서 답안을 봤는데도
	 * 디버깅이 안되네요
	 * 
	 * 시간복잡도:
	 * 
	 * 
	 * 풀이에 걸린 시간:
	 * 5h..
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(Arrays.deepToString(solution(5,new int[][] {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}})));
	}
	
	
	static final int PILLAR=0, BEAM=1, INSTALL=1, REMOVE=0;
	static int N;
	static Map<Integer, int[]> answerMap;
	static boolean[][] pillar,beam;
	public static int[][] solution(int n, int[][] build_frame) {
		answerMap = new HashMap<>();
		N = n;
		pillar = new boolean[n+3][n+3];
		beam = new boolean[n+3][n+3];
		
		for (int i = 0; i < build_frame.length; i++) {
			int x = build_frame[i][0]+1;
			int y = build_frame[i][1]+1;
			int a = build_frame[i][2];
			int b = build_frame[i][3];
			
			if(b==INSTALL) { // 설치
				if(a==PILLAR) { // 기둥
					if(canInstallPillar(x,y)) 
						install(x,y,a);
				}else if(a==BEAM) { // 설치
					if(canInstallBeam(x,y))
						install(x,y,a);
				}
			}else if(b==REMOVE) { // 삭제
				if(a==PILLAR) pillar[y][x] = false;
				else if(a==BEAM) beam[y][x] = false;
				
				if(canRemove()) {
					remove(x, y, a);
				}else {
					if(a==PILLAR) pillar[y][x] = true;
					else if(a==BEAM) beam[y][x] = true;
				}
			}
		}
		
		// Map -> Array
        int[][] answer = answerMap.values().toArray(new int[0][]);
        
        // 정렬
        Arrays.sort(answer, new Comparator<int[]>(){
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0] && o1[1]==o2[1]) {
					return o1[2] - o2[2];
				}else if(o1[0]==o2[0]) {
					return o1[1]-o2[1];
				}
				return o1[0]-o2[0];
			}
		});
        
        return answer;
    }
	
	
	private static boolean canRemove() {
		for (int i = 1; i < N+2; i++) {
			for (int j = 1; j < N+2; j++) {
				if(pillar[j][i] && !canInstallPillar(i,j))
					return false;
				if(beam[j][i] && !canInstallBeam(i, j))
					return false;
			}
		}
		return true;
	}


	// 설치
	private static void install(int x, int y, int a) {
		if(a==PILLAR) pillar[y][x] = true;
		else beam[y][x] = true;
		answerMap.put(x*(N+3)+y, new int[] {x-1,y-1,a});
	}
	
	// 삭제
	private static void remove(int x, int y, int a) {
		if(a==PILLAR) pillar[y][x] = false;
		else beam[y][x] = false;
		answerMap.remove(x*(N+3)+y);
	}

	// 기둥 설치 가능 여부 확인
	private static boolean canInstallPillar(int x, int y) {
		// 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있거나
		return y==1 || beam[y][x-1] || beam[y][x] || pillar[y-1][x];
		
	}
	
	// 보 설치 가능 여부 확인
	private static boolean canInstallBeam(int x, int y) {
		// 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결
		return pillar[y-1][x] || pillar[y-1][x+1] || (beam[y][x-1] && beam[y][x+1]);
	}
	
	
}
