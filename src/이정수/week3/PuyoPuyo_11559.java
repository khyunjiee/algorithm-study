package com.ssafy.algo.study.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class PuyoPuyo_11559 {
	/*
	 * 접근: 크게 기능을 세가지로 분리했습니다. 
	 * 터뜨릴 뿌요의 위치정보와 존재여부를 알려주는 isAnyPuyoToPop
	 * 뿌요를 터뜨리는 pop
	 * 남은 뿌요를 아래로 내리는 pullDown
	 * 코드가 쓰레기 입니다.
	 * 
	 * 시간복잡도: 모름
	 */
	static char[][] map = new char[12][];
	static boolean[][] isChecked; // 터뜨려야하는 뿌요를 찾을 때 이미 확인한 칸인지 체크하는 배열
	static int[] dr = {0,0,1,-1}; 
	static int[] dc = {1,-1,0,0};
	static int[][] locToPop ; // 각 연쇄별로 터뜨려야하는 뿌요의 위치값을 갖는 배열
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<12;i++) map[i] = sc.next().toCharArray();
		int sequence=0;
		
		while(isAnyPuyoToPop()) {// 터뜨릴 뿌요가 존재하면
			sequence++;
			// 터뜨리기
			pop();
//			printMap();
			// 나머지 아래로 내리기
			pullDown();
//			printMap();
		}
		System.out.println(sequence);
	}
	
	private static void pullDown() {
		for(int j=0;j<6;j++) {// 왼쪽부터 한열씩
			for(int i = 11;i>=0;i--) { // 아래에서 부터 위로 올라가면서
				if(map[i][j]=='.') {// 비어있으면
					for(int k=i-1;k>=0;k--) {//가장 가까운 뿌요를 찾아서 가장 아래 빈칸으로 이동
						if(map[k][j]!='.') {
							map[i][j] = map[k][j];
							map[k][j] = '.';
							break;
						}
					}
				}
			}
		}
		
	}

	private static void pop() {
		int idx =0;
		while(locToPop[idx++]!=null) {
			map[locToPop[idx-1][0]][locToPop[idx-1][1]] = '.';
		}
	}

	// 터뜨릴 뿌요의 좌표 갱신 후 존재여부 반환
	private static boolean isAnyPuyoToPop() {
		isChecked = new boolean[12][6];
		int idx=0;
		locToPop =  new int[72][];
		for(int i =0;i<12;i++) {
			for(int j=0;j<6;j++) {
				if(!isChecked[i][j]) { // 확인이 아직 안된 위치에 대해서
					int[][] locs = search(i,j);
					if(locs!=null) for(int[] loc:locs) locToPop[idx++] = loc;
				}
			}
		}
		return locToPop[0]!=null; // 터뜨릴 뿌요들이 있는 경우 true 없으면 false
	}
	
	//연결된 뿌요들 확인하는 메소드 bfs방식으로 탐색
	private static int[][] search(int i, int j) {
		int cnt=0;
		Queue<int[]> queue = new LinkedList<>();
		List<int[]> list = new ArrayList<>();
		queue.offer(new int[] {i,j});
		
		char c = map[i][j];// 찾고자 하는 뿌요 종류
		while(!queue.isEmpty()) {
			int [] loc = queue.poll();
			if(!isChecked[loc[0]][loc[1]]) {
				list.add(new int[] {loc[0],loc[1]});
				isChecked[loc[0]][loc[1]] =true;
				cnt++;
			}
			for(int k=0;k<4;k++) {// 4방 탐색
				int nr = loc[0] + dr[k];
				int nc = loc[1] + dc[k];
				if(nr>=0 && nc>=0 && nr<12 && nc <6 &&map[nr][nc]==c && !isChecked[nr][nc]) // 같은 char이면
					queue.offer(new int[] {nr, nc});
			}
		}
		
		if(cnt>=4 && map[i][j]!='.') {// 4개 이상의 뿌요가 연결되어 있으면
			// list to array
			int[][] arr = list.toArray(new int[0][]);
			return arr;
		}
		return null;
	}
	
	
	// 디버깅을 위한 프린트 메소드
	private static void printMap() {
		for(int i =0;i<12;i++) {
			for(int j=0;j<6;j++) System.out.print(map[i][j]);
			System.out.println();
		}
		System.out.println();
	}

}
