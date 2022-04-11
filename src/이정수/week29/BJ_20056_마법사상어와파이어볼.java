package week29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * 문제: 마법사 상어와 파이어볼
 * 링크: 
 * 
 * 풀이:
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class BJ_20056_마법사상어와파이어볼 {

	static class Fireball{
		int r,c,m,s,d;
		
		public Fireball(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
			
			if(map[current][r][c]==null) {
				map[current][r][c] = new LinkedList<>();
			}
			
			map[current][r][c].add(this);
		}

		void move() {
			r += delta[0][d]*s;
			if(r>=N) {
				r %= N;
			}else if(r<0) {
				r = (r%N)+N;
				r = r==N?0:r;
			}
			c += delta[1][d]*s;
			if(c>=N) {
				c %= N;
			}else if(c<0) {
				c = (c%N)+N;
				c = c==N?0:c;
			}
			
			if(map[next][r][c]==null) {
				map[next][r][c] = new LinkedList<>();
			}
			
			map[next][r][c].add(this);
			
		}
	}
	
	
	static int[][] delta = {{-1,-1,0,1,1,1,0,-1},{0,1,1,1,0,-1,-1,-1}};
	static List<Fireball>[][][] map;
	static int current = 0, next = 1, N;
	static int[][] newDirection = {{0,2,4,6},{1,3,5,7}};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new List[2][N][N];
		
		Queue<Fireball> queue = new LinkedList<>();
		
		for (int i = 0; i < M; i++) {
			
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			Fireball fireball = new Fireball(r,c,m,s,d);
			queue.add(fireball);
			
		}
		
		printMap();
		
		// 이동 명령
		for (int i = 0; i < K; i++) {
			// 모든 파이어볼이 자신의 방향 d로 속력 s칸 만큼 이동
			while(!queue.isEmpty()) {
				Fireball fireball = queue.poll();
				fireball.move();
			}
			
			next = next^1;
			current = current^1;
			map[next] = new List[N][N];
			
			// 같은 칸에 있는 파이어볼은 모두 하나로 합쳐짐
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if(map[current][j][k]!=null) {
						
						if(map[current][j][k].size()==1) {
							queue.add(map[current][j][k].get(0));
							continue;
						}
						
						int mSum = 0, sSum = 0;
						boolean isEven = false, isOdd = false;
						int numberOfFireball = map[current][j][k].size();
						
						for (Fireball fireball : map[current][j][k]) {
							mSum += fireball.m;
							sSum += fireball.s;
							if(fireball.d%2==0) {
								isEven = true;
							}else {
								isOdd = true;
							}
						}
						
						// 합쳐져서 나눠진 질량이 0 인경우 스킵
						if(mSum/5==0) {
							continue;
						}
						
						int dir = isEven&&isOdd?1:0;
						
						for (int l = 0; l < 4; l++) {
							Fireball fireball = new Fireball(j, k, mSum/5, sSum/numberOfFireball, newDirection[dir][l]);
							queue.add(fireball);
						}
						
					}
				}
			}
			
			next = next^1;
			current = current^1;
			map[next] = new List[N][N];
		}
		printMap();
		int mSum = 0;
		for (Fireball fireball : queue) {
			mSum += fireball.m;
		}
		
		System.out.println(mSum);
	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[current][i][j]!=null) {
					System.out.print("● ");
				}else {
					System.out.print("○ ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

}
