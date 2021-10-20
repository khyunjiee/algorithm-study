package com.ssafy.algo.study.week11;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 카드짝맞추기_72415 {
	/* 아직 디버깅이 안끝났습니다...
	 * 
	 * 
	 * 접근:
	 * bfs + 백트래킹
	 * 1. 뒤집는 카드 순서 순열 생성
	 * 2. bfs로 비용계산
	 * 
	 * 시간복잡도:
	 * ?
	 * 
	 * 
	 * 풀이에 걸린 시간:
	 * 
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}}, 1,0));
	}
	static class Card{
		int num,r,c;

		public Card(int num, int r, int c) {
			super();
			this.num = num;
			this.r = r;
			this.c = c;
		}
	}
	
	static List<Card> cardList;
	static boolean used[];
	static int[][] boarD;
	static int min = Integer.MAX_VALUE;
	public static int solution(int[][] board, int r, int c) {
		cardList = new LinkedList<>();
		boarD = board;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if(board[i][j]>0) cardList.add(new Card(board[i][j], r,c));
			}
		}
		used = new boolean[cardList.size()];
		// 뒤집는 카드 순열 생성
		permutation(0,r,c,0,-1, false);
		
		
        return min;
    }

	static int aa=0;
	private static void permutation(int cnt,int r, int c,int cost, int postCard, boolean flag) {
		System.out.println(cost);
		if(cnt==cardList.size()) {
			min = Math.min(min, cost);
			return;
		}
		for (int i = 0; i < cardList.size(); i++) {
			if(used[i])continue;
			Card card = cardList.get(i);
			if(flag) { // 이미 하나의 카드를 선택한 경우
				if(card.num!=postCard) continue; // 전에 선택한 카드와 일치하지 않으면 패스
				used[i] = true;
				cost += bfs(r,c, card)+1;
				
				// 지도에서 카드 없애기
				removeCards(postCard);
				permutation(cnt+1,card.r,card.c,cost,card.num, false);
				
				// 지도에 카드 다시 표시
				rewriteCards(postCard);
				used[i] = false;
				
			}else { // 아직 카드를 선택하지 않은 경우
				used[i] = true;
				// 해당카드까지 가는데 필요한 조작 횟수 더하기
				cost += bfs(r,c, card)+1;
				permutation(cnt+1,card.r,card.c,cost,card.num, true);
				used[i] = false;
			}
		}
	}

	private static void rewriteCards(int postCard) {
		for (int i = 0; i < cardList.size(); i++) {
			Card card = cardList.get(i);
			if(card.num==postCard) 
				boarD[card.r][card.c] = postCard;
			
		}
	}


	private static void removeCards(int postCard) {
		for (int i = 0; i < cardList.size(); i++) {
			Card card = cardList.get(i);
			if(card.num==postCard) 
				boarD[card.r][card.c] = 0;
			
		}
	}

	static int[][] delta = {{-1,1,0,0},{0,0,-1,1}};
	static boolean[][] visited;
	private static int bfs(int r, int c, Card card) {
		
		visited = new boolean[4][4];
		Queue<int[]> q = new LinkedList<>();
		visited[r][c] = true;
		q.add(new int[] {r,c});
		int cnt = 0;
		boolean flag = false;
		
		while(!q.isEmpty()) {
			int size = q.size();
			while(--size>=0) {
				int[] curr = q.poll();
				
				if(curr[0]==card.r && curr[1]==card.c) return cnt;
				
				// 4방향으로 한칸이동
				for (int d = 0; d < 4; d++) {
					int nr = curr[0] + delta[0][d];
					int nc = curr[1] + delta[1][d];
					
					if(isValid(nr,nc)) {
						visited[nr][nc] = true;
						q.add(new int[] {nr,nc});
					}
				}
				
				
				// 4방향으로 ctrl 이동
				for (int d = 0; d < 4; d++) {
					int a = ctrl(r,c,d);
					int nr = curr[0] + delta[0][d]*a;
					int nc = curr[1] + delta[1][d]*a;
					
					if(isValid(nr,nc)) {
						visited[nr][nc] = true;
						q.add(new int[] {nr,nc});
					}
				}
			}
			cnt++;
		}
		return 0;
	}


	private static int ctrl(int r, int c, int d) {
		int cnt = 0;
		r+=delta[0][d];
		c+=delta[1][d];
		while(r>=0 && c>=0 && r<4 && c<4) {
			cnt++;
			if(boarD[r][c]!=0) break;
			r+=delta[0][d];
			c+=delta[1][d];
		}
		return cnt;
	}


	private static boolean isValid(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<4 &&nc<4 && !visited[nr][nc];
	}

}
