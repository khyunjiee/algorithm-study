package programmers;

import java.util.ArrayList;

// 기둥과 보 설치
/*
 * 기둥과 보를 설치할 때는 문제의 조건에 맞게 메소드를 분리하여 진행.
 * 삭제할 때는 일단 삭제한 후 연결된 부분들이 전부 건축가능한 상태면 그대로 삭제 진행, 하나라도 건축불가능하면 삭제한거 다시 취소
 * 인덱스 범위 고려시 입력값에서 기둥은 y==N인 경우가 없고, 보는 x==N인 경우가 없음!
 * 복붙 했다가 오타 못찾아서 디버깅하는데 시간을 너무 소비했다....
 * 항상 복붙할때는 조심하자!
 */
public class Programmers_60061 {

	public static void main(String[] args) {
		int n = 5;
		int[][] build_frame = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
		System.out.println(solution(n, build_frame));
	}
	
	static boolean[][][] map;
	static int N;
	public static ArrayList<int[]> solution(int n, int[][] build_frame) {
		N = n;
		map = new boolean[n+1][n+1][2]; // [x][y][0또는1] 0은 기둥, 1은 보
		for(int i=0,len=build_frame.length; i<len; ++i) {
			int[] info = build_frame[i]; // 하나의 설치 정보
			int x=info[0],y=info[1],a=info[2],b=info[3];
			if(a==0) { // 기둥
				if(b==0) { // 삭제
					map[x][y][0]=false; // 일단 삭제
					if(!removeGidoongPossible(x, y)) map[x][y][0]=true; // 기둥 삭제 불가능하면 삭제 취소
				}else { // 설치
					if(buildGidoongPossible(x, y)) map[x][y][0]=true; // 기둥 설치
				}
			}else { // 보
				if(b==0) { // 삭제
					map[x][y][1]=false; // 일단 삭제
					if(!removeBoPossible(x, y)) map[x][y][1]=true; // 보 삭제 불가능하면 삭제 취소
				}else { // 설치
					if(buildBoPossible(x, y)) map[x][y][1]=true; // 보 설치
				}
			}
		}
		ArrayList<int[]> list = new ArrayList<int[]>();
		for(int i=0; i<n+1; ++i) {
			for(int j=0; j<n+1; ++j) {
				if(map[i][j][0]) list.add(new int[] {i,j,0}); // 기둥이 존재하면 추가
				if(map[i][j][1]) list.add(new int[] {i,j,1}); // 보가 존재하면 추가
			}
		}
        return list;
    }

	public static boolean buildGidoongPossible(int x, int y) { // map[x][y]에 기둥 설치가능 여부
		if(y!=0) { // 바닥이 아니면
			return (map[x][y-1][0] || map[x][y][1] || (x>0 && map[x-1][y][1])); // 바로 아래 기둥이 있거나 양쪽 중 하나라도 보가 있으면
		}else { // 바닥이면 무조건 설치
			return true;
		}
	}
	
	public static boolean buildBoPossible(int x, int y) { // map[x][y]에 보 설치가능 여부
		return map[x][y-1][0] || map[x+1][y-1][0] || (x>0 && map[x-1][y][1] && map[x+1][y][1]); // 양 쪽 중 하나라도 아래 기둥이 있거나, 양 옆으로 보가 있으면
	}
	
	public static boolean removeGidoongPossible(int x, int y) { // map[x][y]에 기둥 삭제가능 여부
		boolean flag = true;
		if(map[x][y+1][0] && !buildGidoongPossible(x, y+1)) flag=false; // 바로 위에 기둥이 존재하지만 기둥을 지을 수 없으면 false
		if(x>0 && map[x-1][y+1][1] && !buildBoPossible(x-1, y+1)) flag=false; // 바로 왼쪽에 보가 존재하지만 보를 지을 수 없으면 false
		if(map[x][y+1][1] && !buildBoPossible(x, y+1)) flag=false; // 바로 오른쪽에 보가 존재하지만 보를 지을 수 없으면 false
		return flag;
	}
	
	public static boolean removeBoPossible(int x, int y) { // map[x][y]에 보 삭제가능 여부
		boolean flag = true;
		if(map[x][y][0] && !buildGidoongPossible(x, y)) flag=false; // 해당 위치에 기둥이 존재하지만 기둥을 지을 수 없으면 false
		if(map[x+1][y][0] && !buildGidoongPossible(x+1, y)) flag=false; // 해당 위치 오른쪽에 기둥이 존재하지만 기둥을 지을 수 없으면 false
		if(x>0 && map[x-1][y][1] && !buildBoPossible(x-1, y)) flag=false; // 바로 왼쪽에 보가 존재하지만 보를 지을 수 없으면 false
		if(map[x+1][y][1] && !buildBoPossible(x+1, y)) flag=false; // 바로 오른쪽에 보가 존재하지만 보를 지을 수 없으면 false
		return flag;
	}
}