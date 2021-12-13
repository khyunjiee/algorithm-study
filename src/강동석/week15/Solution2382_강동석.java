package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// [모의 SW 역량테스트] 미생물 격리
/*
 * 해당 좌표마다 미생물이 이동 후 겹치는 부분을 어떻게 처리해 줄 것인지가 중요한 것 같다.
 * 이 부분에서 HashMap을 이용하여 좌표의 값을 key값으로 하여 정보들을 저장 후 새로운 HashMap을 만들어
 * 이동 후 정보들을 저장하며 계산을 하였다.
 */
public class Solution2382_강동석 {

	static int N,M,K,result;
	static int[][] map;
	static int[][] dir = {{0,0},{-1,0},{1,0},{0,-1},{0,1}}; // (상: 1, 하: 2, 좌: 3, 우: 4)
	static Map<Integer, int[]> microbeMap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			K = Integer.parseInt(str[2]);
			
			map = new int[N][N];
			microbeMap = new HashMap<Integer, int[]>(); // 미생물들의 정보를 담은 map
			// key는 좌표를 2차원 배열을 1차원 형식으로 변환한 정수값, value는 {총 미생물수, 한번에 가장 많이 들어온 미생물수, 방향}
			
			for(int i=0; i<K; ++i) {
				str = br.readLine().split(" ");
				int r = Integer.parseInt(str[0]);
				int c = Integer.parseInt(str[1]);
				int num = Integer.parseInt(str[2]);
				int dir = Integer.parseInt(str[3]);
				microbeMap.put(r*N+c, new int[] {num,num,dir});
			}
			for(int i=0; i<M; ++i) { // M번 이동
				move();
			}
			calcResult(); // result값 계산 후 저장
			System.out.println("#"+tc+" "+result);
		}
	}
	
	public static void move() { // 미생물 군집들을 이동시킨다.
		Map<Integer, int[]> afterMap = new HashMap<Integer, int[]>();
		Set<Integer> keySet = microbeMap.keySet();
		
		for(int key : keySet) {
			int r = key/N;
			int c = key%N;
			int sum = microbeMap.get(key)[0]; // 현재 미생물 군집의 미생물 수
			int d = microbeMap.get(key)[2]; // 현재 미생물 군집의 방향
			int nr = r+dir[d][0];
			int nc = c+dir[d][1];
			if(nr==0 || nc == 0 || nr == N-1 || nc == N-1) { // 다음으로 이동한 칸이 경계이면
				sum = sum/2; // 미생물 수 절반으로 감소
				// 방향은 반대
				if(d%2==1) { // 홀수이면 +1
					d++;
				}else { // 짝수이면 -1
					d--;
				}
			}
			int nextKey = nr*N+nc; // 방향을 따라서 이동한 다음 좌표를 1차원 형태로 계산
			int[] nextInfo = afterMap.get(nextKey); // 다음 칸의 군집 정보
			if(nextInfo != null) { // 다음 칸으로 이동 시 이미 다른 미생물이 같은 위치로 이동했을 경우
				int originalMost = nextInfo[1]; // 기존의 가장 큰 미생물의 수
				nextInfo[0]+=sum; // 미생물의 수 증가
				if(originalMost>sum) { // 새로운 미생물이 기존의 가장 큰 미생물의 수보다 적으면
					afterMap.put(nextKey, nextInfo); // 방향과 가장 큰 수 변화 없음
				}else { // 새로운 미생물이 기존의 가장 큰 미생물의 수보다 크면
					nextInfo[1] = sum; // 가장 큰 미생물 수 갱신
					nextInfo[2] = d; // 방향 갱신
				}
			}else { // 다음 방향으로 이동 시 빈칸일 경우
				afterMap.put(nextKey, new int[] {sum,sum,d});
			}
		}
		microbeMap = afterMap;
	}
	
	public static void calcResult() { // 남아있는 미생물 수의 총 합을 계산 후 result에 저장
		Set<Integer> keySet = microbeMap.keySet();
		int sum = 0;
		for(int key : keySet) {
			sum += microbeMap.get(key)[0];
		}
		result = sum;
	}
}