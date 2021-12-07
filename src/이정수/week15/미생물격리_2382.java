package com.ssafy.algo.study.week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 미생물격리_2382 {
	/*
	 * 접근:
	 * 1. 이차원 배열을 사용하지 않고 key, value를 각각 row*N+col, {미생물수, 방향}로 하는 해쉬맵 자료구조 사용(시간복잡도 이득)
	 * 2. map을 2개 사용해서 매시간 미생물의 이동을 map1에서 map2 or map2에서 map1에 기록(여러 미생물 군집이 합쳐지는 것을 처리하기 위함)
	 * 
	 * 
	 * 시간복잡도:
	 * O(KM)
	 * 
	 * 풀이에 걸린 시간:
	 * 1h
	 * 
	 */
	
	static int N, reverseDirection[] = {0,2,1,4,3};
	static int[][] delta = {{0,-1,1,0,0},{0,0,0,-1,1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			Map<Integer, int[]> map1 = new HashMap<>(); // 미생물 군집을 관리하는 맵
			Map<Integer, int[]> map2 = new HashMap<>(); // 미생물 군집을 관리하는 맵
			
			// 최초의 미생물 군집의 위치와 군집당 미생물 수, 방향을 맵에 저장
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(in.readLine());
				
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				int numberOfMicrobe = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				
				map1.put(row*N+col, new int[] {numberOfMicrobe, direction});
			}
			
			
			// map1에 기록한 경우 이동 후 정보를 map2에 쓰고 map2에 기록되어있는 경우 map1에 이동 후 정보를 기록
			for (int j = 0; j < M; j++) {
				if(j%2==0) {
					moveMicrobe(map1, map2);
					map1 = new HashMap<>();
					
				}else {
					moveMicrobe(map2, map1);
					map2 = new HashMap<>();
				}
			}
			
			int answer = 0; // 총 미생물 수
			
			// 남아있는 미생물 수 모두 합치기
			for (int[] value : map1.values()) {
				answer += value[0];
			}
			for (int[] value : map2.values()) {
				answer += value[0];
			}
			
			System.out.println("#"+tc+" " + answer);
		}
	}
	
	
	// map1에 기록된 미생물 군집의 이동후 상태를 map2에 기록
	private static void moveMicrobe(Map<Integer, int[]> map1, Map<Integer, int[]> map2) {

		for (int key : map1.keySet()) { // 각각의 미생물 군집에 대해서
			
			int[] value = map1.get(key);
			int row = key/N;				// 현재 행
			int col = key%N;				// 현재 열
			int numberOfMicrobe =value[0];	// 현재 미생물 수
			int direction = value[1];		// 군집 이동 방향
			
			// 이동
			row += delta[0][direction];
			col += delta[1][direction];
			
			// 약품 구역에 도달한 경우
			if(row==0 || col==0 || row==N-1 || col==N-1) {
				
				if(numberOfMicrobe==1) continue;// 남은 미생물이 1개일 경우 없어짐
				
				numberOfMicrobe /= 2;	// 미생물 수 절반으로 감소
				direction  = reverseDirection[direction]; // 방향 180도 전환
				
				map2.put(row*N+col, new int[] {numberOfMicrobe, direction}); // map2에 기록
				continue;
			}
			
			// 다른 미생물 군집과 합쳐진 경우
			if(map2.containsKey(row*N+col)) {
				
				int[] value2 = map2.get(row*N+col);
				int numberOfAccumulatedMicrobe = value2[0]; // 현재까지 해당 셀에 합쳐진 총 미생물 수
				int direction2 = value2[1];					// 군집의 방향
				int numberOfMicrobe2 = value2[2];			// 군집의 방향에 해당하는 미생물 수
				
				numberOfAccumulatedMicrobe += numberOfMicrobe; // 총 미생물 수 추가
				
				if(numberOfMicrobe> numberOfMicrobe2) { // 새로 들어온 미생물들의 수가 이미 셀에 들어온 미생물 수가 가장 많은 군집의 미생물 수보다 크면
					map2.replace(row*N+col, new int[] {numberOfAccumulatedMicrobe, direction, numberOfMicrobe}); // 방향 변경
				}else { // 아닌 경우 방향 그대로
					map2.replace(row*N+col, new int[] {numberOfAccumulatedMicrobe, direction2, numberOfMicrobe2});
				}
				continue;
				
			}
			
			
			// 일반적인 이동
			map2.put(row*N+col, new int[] {numberOfMicrobe, direction, numberOfMicrobe});
			
		}
	}

}
