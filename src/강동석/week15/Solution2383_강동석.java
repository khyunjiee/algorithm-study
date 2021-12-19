package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// [모의 SW 역량테스트] 점심 식사시간
/*
 * 누군가가 계단을 모두 내려가는 순간 대기자가 투입해야한다. 사람들을 index순서대로 접근하면 안된다.
 * 즉, 논리적 오류를 범하기 쉬운 문제이므로 꼼꼼하게 확인해야하는 문제!
 */
public class Solution2383_강동석 {

	static int N,result;
	static int[][] map;
	static List<int[]> peopleList,stairList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			peopleList = new ArrayList<int[]>();
			stairList = new ArrayList<int[]>();
			for(int i=0; i<N; ++i) {
				str = br.readLine().split(" ");
				for(int j=0; j<N; ++j) {
					map[i][j] = Integer.parseInt(str[j]);
					if(map[i][j]==1) peopleList.add(new int[] {i,j}); // 사람들의 위치를 저장
					else if (map[i][j]>1) stairList.add(new int[] {i,j}); // 계단 2개의 위치를 저장
				}
			}
			
			int max = (int)Math.pow(2, peopleList.size());
			int splitNum = 0; // 두팀으로 나누기 위해 비트를 이용
			
			result = Integer.MAX_VALUE;
			while(true) {
				int timeA = calcStair(splitNum,0,stairList.get(0)); // 비트값이 0과 1 두 팀으로 나누어 서로 다른 계단으로 보낸다
				int timeB = calcStair(splitNum,1,stairList.get(1));
				result = Math.min(result, Math.max(timeA, timeB)); // 두 계단의 시간들을 다 계산해서 오래 걸린 시간을 최솟값과 비교하여 갱신
				splitNum++;
				if(splitNum==max) break;
			}
			System.out.println("#"+tc+" "+result);
		}
		
	}
	
	public static int calcStair(int splitNum, int teamBit, int[] stair) {
		int size = peopleList.size(); // 전체 인원 수
		int selectedNum = 0; // 뽑힌 팀원 수
		Queue<Integer> outStair = new LinkedList<Integer>(); // 아직 계단에 올라서지 못한 사람들
		Queue<Integer> onStair = new LinkedList<Integer>(); // 계단을 내려가는 중인 사람들
		for(int i=0; i<size; ++i) { // 팀원 선택하기
			if((splitNum>>i & 1) == teamBit) { // 해당 비트가 teamBit이면 선택함
				int[] p = peopleList.get(i); // 사람의 좌표
				outStair.offer((Math.abs(p[0]-stair[0])+Math.abs(p[1]-stair[1]))*-1); // 사람으로부터 계단까지의 거리를 음수로 저장
				selectedNum++; // 팀원 수 1 증가
			}
		}
		
		if(selectedNum==0) return 0; // 아무도 안뽑으면 시간 0 리턴, 어차피 다른 쪽 계단에서 시간이 소요되므로 문제 없음
		int finishedNum = 0; // 계단을 다 내려간 사람 수
		int time = 0; // 종료까지 소요시간
		while(true) {
			time++; // 시간 증가
			for(int i=0,on=onStair.size(); i<on; ++i) { // 계단을 내려가는 중인 사람들 먼저 체크
				int cur = onStair.poll();
				cur++; // 계단 한 칸 이동
				if(cur>map[stair[0]][stair[1]]) { // 계단을 다 내려가면
					finishedNum++;
				}else {
					onStair.offer(cur);
				}
			}
			for(int i=0,out=outStair.size(); i<out; ++i) { // 계단에 내려가기 전인 사람들 체크
				int cur = outStair.poll();
				if(cur<0) { // 계단 입구에 도착전이면
					outStair.offer(++cur); // 계단에 한칸 접근
				}else if(cur==0) { // 계단 입구에 도착했으면
					if(onStair.size()<3) onStair.offer(++cur); // 계단에 3명보다 작으면 계단 내려가기
					else outStair.offer(cur); // 계단이 3명이 이미 있으면 그대로 계단 입구에 대기
				}
			}
			if(finishedNum==selectedNum) break; // 모든 사람이 계단을 다 내려가면 종료
		}
		return time;
	}
}