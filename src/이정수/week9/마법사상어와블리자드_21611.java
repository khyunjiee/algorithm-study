package com.ssafy.algo.study.week9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 마법사상어와블리자드_21611 {
	
	
	static int[][] map;
	static int destroyed[] = new int[4], N;
	static int[][] delta = {{0,-1,1,0,0},{0,0,0,-1,1}};
	static boolean emptyFlag;
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc =new Scanner(System.in);
		N = sc.nextInt();
		int M = sc.nextInt();
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				map[i][j] = sc.nextInt();
		}
		
		for (int i = 0; i < M; i++) {
			int d = sc.nextInt();
			int s = sc.nextInt();
			// 블리자드 시전
			blizzard(d, s);
			// 후처리
			while(emptyFlag) {// 빈공간이 있으면
				moveMarble(); // 구슬 이동
				boom(); // 구슬 폭발
				
			}
			
			// 구슬 변화
			transform();
		}
		
		System.out.println(destroyed[1]+ destroyed[2]*2 + destroyed[3]*3);
	}
	

	private static void transform() {
		int[][] delt = {{0,1,0,-1},{-1,0,1,0}};
		Queue<Integer> queue = new LinkedList<>();
		int r = N/2, c = N/2;
		int d=0;
		int sequenceCnt = 0;
		int postMarble = -1;
		
		label: for(int i = 1; i < N+1; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < i; k++) {
					r += delt[0][d];
					c += delt[1][d];
					if(map[r][c]==0) break label;
					if(map[r][c]==postMarble) { // 앞 구슬과 동일한 구슬이면
						sequenceCnt++; 
					}else { // 앞 구슬과 다른 구슬이면
						queue.add(sequenceCnt); // 그룹 구슬 개수 기록
						queue.add(postMarble); // 구슬 번호 기록
						
						// 연속성 확인할 구슬 교체
						postMarble = map[r][c]; 
						sequenceCnt = 1;
					}
				}
				d = (d+1)%4;
			}
		}
		// 마지막 구슬 정보 추가
		queue.add(sequenceCnt); 
		queue.add(postMarble); 
		
		// 새로운 맵 채우기
		int[][] newMap = new int[N][N]; // 변화한 구슬을 기록하는 이차원 배열
		d=0;
		r = N/2;
		c = N/2;
		// 더미 데이터 없애기
		queue.poll();
		queue.poll();
		label: for(int i=1; i<N+1;i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < i; k++) {
					r += delt[0][d];
					c += delt[1][d];
					if(!isValid(r,c) || queue.isEmpty()) break label;
					newMap[r][c] = queue.poll();
				}
				d = (d+1)%4;
			}
		}
		
		//맵 교체
		map = newMap;
	}


	private static void boom() {
		int[][] delt = {{0,1,0,-1},{-1,0,1,0}};
		Queue<int[]> queue = new LinkedList<>();
		int r = N/2, c = N/2;
		int d=0;
		int sequenceCnt = 1;
		int postMarble = -1;
		
		label: for(int i=1; i<N+1;i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < i; k++) {
					r += delt[0][d];
					c += delt[1][d];
					if(map[r][c]==0) break label;
					if(map[r][c]==postMarble) { // 앞 구슬과 동일한 구슬이면
						queue.add(new int[] {r,c,map[r][c]}); // 큐에 추가
						sequenceCnt++; 
					}else { // 앞 구슬과 다른 구슬이면
						if(sequenceCnt>=4) {// 앞에서 4개 이상의 구슬이 연속했을 경우
							
							while(!queue.isEmpty()) { // 모두 터뜨리기
								int[] marble = queue.poll();
								int row = marble[0];
								int col = marble[1];
								destroyed[marble[2]]++;
								map[row][col] = 0;
							}
							emptyFlag = true;
						}
						
						// 연속성 확인할 구슬 교체
						queue= new LinkedList<>();
						queue.add(new int[] {r,c,map[r][c]});
						postMarble = map[r][c]; 
						sequenceCnt = 1;
					}
				}
				d = (d+1)%4;
			}
		}
		
		// 마지막 처리
		if(sequenceCnt>=4) {// 앞에서 4개 이상의 구슬이 연속했을 경우
			
			while(!queue.isEmpty()) { // 모두 터뜨리기
				int[] marble = queue.poll();
				int row = marble[0];
				int col = marble[1];
				destroyed[marble[2]]++;
				map[row][col] = 0;
			}
			emptyFlag = true;
		}
	}


	// 빈공간 채우기
	private static void moveMarble() {
		emptyFlag = false;
		int[][] delt = {{0,1,0,-1},{-1,0,1,0}};
		Queue<int[]> queue = new LinkedList<>();
		int r = N/2, c = N/2;
		int d=0;
		
		for(int i=1; i<N+1;i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < i; k++) {
					r += delt[0][d];
					c += delt[1][d];
					if(!isValid(r,c)) return;
					if(!queue.isEmpty() && map[r][c]>0) { // 앞에 빈공간이 있고 현재 위치가 구슬이면
						int[] emptyLoc = queue.poll();
						map[emptyLoc[0]][emptyLoc[1]] = map[r][c]; // 구슬 이동
						map[r][c] = 0;// 현재자리 빈자리
					}
					
					if(map[r][c]==0) // 빈공간 위치 기억
						queue.add(new int[] {r,c});
				}
				d = (d+1)%4;
			}
		}
		
	}

	private static void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// 블리자드 시전
	private static void blizzard(int d, int s) {
		int nr = N/2;
		int nc = N/2;
		
		for (int i = 0; i < s; i++) {
			nr += delta[0][d];
			nc += delta[1][d];
			if(isValid(nr, nc) && map[nr][nc]>0) {
				emptyFlag= true;
				map[nr][nc] = 0; //빈공간 표시
			}
				
		}
	}
	
	// 범위가 유효한지 확인
	private static boolean isValid(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<N && nc<N;
	}

}
