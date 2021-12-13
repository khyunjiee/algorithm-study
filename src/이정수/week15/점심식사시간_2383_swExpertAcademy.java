package com.ssafy.algo.study.week15;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class 점심식사시간_2383_swExpertAcademy {
	/*
	 * 접근:
	 * 1. Person클래스와 Stair클래스 구성
	 * 2. 어느 계단을 사용할지 정하기는 brute force
	 * 3. 계단이 정해지면 시뮬
	 * 
	 * 시간복잡도:
	 * 계단 선택 => 2^P(사람수)  최대  1024
	 * 시뮬 =>?
	 * 
	 * 
	 * 공간복잡도:
	 * 
	 * 풀이에 걸린 시간:
	 * 2h
	 * 
	 */
	
	static class Person implements Comparable<Person>{
		
		int row, col, arrivalTime, exitTime, stair; // 행, 열, 계단 도착시간, 계단 탈출 시간, 배정받은 계단

		public Person(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

		@Override
		public int compareTo(Person o) {
			return this.arrivalTime- o.arrivalTime;
		}

	}
	
	static class Stair{
		
		int row, col, requiredTime;		// 행, 열, 탈출에 필요한 시간
		Queue<Person> movingQueue;		// 계단을 타고 있는 사람들을 관리하는 큐
		PriorityQueue<Person> waitQueue;// 계단을 타려고 대기하는 살마들을 관리하는 큐
		
		public Stair(int row, int col, int requiredTime) {
			super();
			this.row = row;
			this.col = col;
			this.requiredTime = requiredTime;
		}
		
	}
	
	
	static List<Person> peopleList; // 사람들 
	static Stair stair1, stair2;	// 계단1,2
	static int min;					// 최소 탈출 시간
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			stair1 = null;
			stair2 = null;
			int N = sc.nextInt();
			int[][] map = new int[N][N];
			min = Integer.MAX_VALUE;
			
			peopleList = new LinkedList<>();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j]==1) {
						peopleList.add(new Person(i,j));
					}else if(map[i][j]>=2) {
						if(stair1==null) {
							stair1 = new Stair(i, j, map[i][j]);
						}else {
							stair2 = new Stair(i,j, map[i][j]);
						}
					}
				}
			}
			
			// 계단 정하기
			decideStairToUse(0);
			
			System.out.println("#" + tc + " " + min);
		}
	}
	
	// 계단 정하기
	private static void decideStairToUse(int idx) {
		
		if(idx==peopleList.size()) {
			
			// stair 큐에 삽입
			stair1.waitQueue = new PriorityQueue<>();
			stair2.waitQueue = new PriorityQueue<>();
			
			for (int i = 0; i < peopleList.size(); i++) {
				Person p = peopleList.get(i);
				if(p.stair==1) {
					p.arrivalTime = getArrivalTime(p.row, p.col, stair1.row, stair1.col);
					stair1.waitQueue.add(p);
				}else {
					p.arrivalTime = getArrivalTime(p.row, p.col, stair2.row, stair2.col);
					stair2.waitQueue.add(p);
				}
			}
			
			// 계단이 배정되면 탈출 시뮬레이션 시작
			runSimulation();
			
			return;
		}
		
		peopleList.get(idx).stair = 1;
		decideStairToUse(idx+1);
		peopleList.get(idx).stair = 2;
		decideStairToUse(idx+1);
	}
	
	// 사람이 계단까지 이동하는데 걸리는 시간 계산
	private static int getArrivalTime(int row, int col, int row2, int col2) {
		return Math.abs(row-row2) + Math.abs(col-col2);
	}
	
	
	// 탈출 시뮬 실행
	private static void runSimulation() {
		int endTime1 = getEndTime(stair1);
		int endTime2 = getEndTime(stair2);
		
		min = Math.min(min, Math.max(endTime1, endTime2));
		
	}
	
	
	// 주어진 계단으로 사람들이 모두 탈출하는 시간 반환
	private static int getEndTime(Stair stair) {
		
		stair.movingQueue = new LinkedList<>();
		int currentTime = 0;
		
		while(!stair.waitQueue.isEmpty() || !stair.movingQueue.isEmpty()) { // 두개의 큐가 모두 빌때 까지
			currentTime++;
			
			// 현재 시간에 계단에서 탈출가능한사람 모두 탈출
			while(!stair.movingQueue.isEmpty() && stair.movingQueue.peek().exitTime==currentTime) {
				stair.movingQueue.poll();
			}
			
			// 현재 시간에 계단에 자리가 있으면 계단으로 밀어넣기
			while(!stair.waitQueue.isEmpty() && stair.waitQueue.peek().arrivalTime+1<=currentTime && stair.movingQueue.size()<3) {
				Person p = stair.waitQueue.poll();
				p.exitTime = currentTime + stair.requiredTime;
				stair.movingQueue.add(p);
			}
			
		}
		
		return currentTime;
		
	}
	
}
