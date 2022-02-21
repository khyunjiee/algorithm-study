package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 퍼즐 조각 채우기
/*
 * 로직에 이상이 없어보였는데 오답이어서 틀린부분을 아직 발견하지 못했다.
 * game_board에서 빈칸 정보들을 list로 얻고, table에서 블럭 정보들을 list로 얻은 후 비교한다.
 * 비교할 때 회전했을 때 일치하는지 여부는 판단하지 않고 있는 그대로 비교한다. 그리고 일치하는 블럭들은 다 상쇄시킨 후
 * table을 90도 회전하여 다시 블럭 정보들을 list로 얻은 후 비교한다. 이런식으로 270도 까지 회전시켜 모두 비교한다.
 */
public class Programmers_84021 {

	public static void main(String[] args) {
		int[][] game_board = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
		int[][] table = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};
		System.out.println(solution(game_board, table));
	}
	
	static class Block {
		int num; // 블럭의 칸 갯수
		int[] startPos; // 시작점의 좌표
		List<int[]> posList; // 블럭을 이루는 칸들의 좌표
		
		public Block(int num, int[] startPos, List<int[]> posList) {
			this.num = num;
			this.startPos = startPos;
			this.posList = posList;
		}
	}
	
	static int N,answer;
	static boolean[][] visited; // 회전할떄마다 초기화 하기
	static int[][] table_static;
	static List<Block> gameBlockList; // game_board의 빈칸들의 정보
	static List<Block> tableBlockList; // table의 블럭들의 정보
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static int solution(int[][] game_board, int[][] table) {
		table_static = table;
		N = game_board.length;
		
		gameBlockList = new ArrayList<>(); // game_board의 빈칸들의 정보
        for(int i=0; i<N; ++i) {
        	for(int j=0; j<N; ++j) {
        		if(game_board[i][j]==0) { // 빈칸을 발견하면
        			List<int[]> list = getBlocksFromGameBoard(i,j,game_board);
        			gameBlockList.add(new Block(list.size(), new int[] {i,j}, list)); // 블럭 추가
        		}
        	}
        }
        
        tableBlockList = new ArrayList<>();
        settingTableInfo();
        matchBlock();

        rotateTable90(); // table을 90도 시계방향 회전
        for(int[] ar : table_static) {
        	for(int a : ar) {
        		System.out.print(a+" ");
        	}
        	System.out.println();
        }
        System.out.println();
        settingTableInfo();
        matchBlock();
        
        rotateTable90(); // table을 90도 시계방향 회전
        for(int[] ar : table_static) {
        	for(int a : ar) {
        		System.out.print(a+" ");
        	}
        	System.out.println();
        }
        System.out.println();
        settingTableInfo();
        matchBlock();
        
        rotateTable90(); // table을 90도 시계방향 회전
        for(int[] ar : table_static) {
        	for(int a : ar) {
        		System.out.print(a+" ");
        	}
        	System.out.println();
        }
        System.out.println();
        settingTableInfo();
        matchBlock();
//        for(Block block : gameBlockList) {
//        	for(int[] ar : block.posList) {
//        		System.out.println(ar[0]+","+ar[1]);
//        	}
//        	System.out.println();
//        }
        return answer;
    }
	
	public static void rotateTable90() {
		int[][] copied = new int[N][N];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				copied[i][j] = table_static[N-j-1][i];
			}
		}
		table_static = copied; // 90도 회전한 것으로 교체하여 저장
	}
	
	public static void settingTableInfo() {
		tableBlockList.clear(); // table의 블럭들의 정보 초기화
		visited = new boolean[N][N]; // 방문체크 초기화
        for(int i=0; i<N; ++i) {
        	for(int j=0; j<N; ++j) {
        		if(table_static[i][j]==1 && !visited[i][j]) { // 블럭을 발견하면
        			List<int[]> list = getBlocksFromTable(i,j,table_static);
        			tableBlockList.add(new Block(list.size(), new int[] {i,j}, list)); // 블럭 추가
        		}
        	}
        }
	}
	
	public static void matchBlock() {
		for(int idx=0; idx<gameBlockList.size(); ++idx) {
        	Block gameBlock = gameBlockList.get(idx);
        	for(Block tableBlock : tableBlockList) {
        		if(gameBlock.num==tableBlock.num) { // 두 블럭의 칸의 갯수가 같으면
        			if(checkSameBlock(gameBlock.posList,tableBlock.posList)) { // 두 조각이 일치하면
        				answer += gameBlock.num; // 정답 칸의 갯수 증가
        				int startR = tableBlock.startPos[0];
        				int startC = tableBlock.startPos[1];
        				for(int[] pos : tableBlock.posList) {
        					table_static[startR+pos[0]][startC+pos[1]]=0; // 일치하는 조각은 table에서 0으로 표시하여 없애기
        				}
        				gameBlockList.remove(idx--); // 리스트에서 제거
        				break;
        			}
        		}
        	}
        }
	}
	public static List<int[]> getBlocksFromGameBoard(int i, int j, int[][] board) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {i,j});
		List<int[]> posList = new ArrayList<int[]>(); // 칸의 좌표들 저장
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			if(board[r][c]==1) continue; // 이미 채워져 있으면 무시
			board[r][c]=1; // 채우기
			posList.add(new int[] {r-i,c-j}); // 첫 번쨰 칸을 {0,0} 기준으로 변환하여 좌표 저장
			
			for(int d=0; d<4; ++d) {
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				if(nr<0 || nc<0 || nr>=N || nc>=N) continue; // 경계 체크
				if(board[nr][nc]==0) { // 다음칸이 빈칸이면
					q.offer(new int[] {nr,nc}); // 큐에 추가
				}
			}
		}
		
		return posList;
	}
	
	public static List<int[]> getBlocksFromTable(int i, int j, int[][] board) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {i,j});
		List<int[]> posList = new ArrayList<int[]>(); // 칸의 좌표들 저장
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			if(visited[r][c]) continue; // 방문체크
			visited[r][c] = true; // 방문표시
			posList.add(new int[] {r-i,c-j}); // 첫 번쨰 칸을 {0,0} 기준으로 변환하여 좌표 저장
			
			for(int d=0; d<4; ++d) {
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				if(nr<0 || nc<0 || nr>=N || nc>=N) continue; // 경계 체크
				if(board[nr][nc]==1 && !visited[nr][nc]) { // 다음칸이 블럭이고 방문 안했으면
					q.offer(new int[] {nr,nc}); // 큐에 추가
				}
			}
		}
		
		return posList;
	}
	
	public static boolean checkSameBlock(List<int[]> list1, List<int[]> list2) {
		int size = list1.size();
		for(int i=0; i<size; ++i) {
			int[] pos1 = list1.get(i);
			int[] pos2 = list2.get(i);
			if(pos1[0]!=pos2[0] || pos1[1]!=pos2[1]) return false; // 좌표가 다르면 false
		}
		return true; // 좌표가 모두 같으면 true;
	}
}