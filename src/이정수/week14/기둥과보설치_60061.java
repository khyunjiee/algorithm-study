package com.ssafy.algo.study.week14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class 기둥과보설치_60061 {
	/*
	 * 접근: 1. 기둥과 보를 세울 수 있는지 여부 확인하는 메소드 생성 
	 * 2. 삭제시 해당 기둥이나 보를 삭제하고 나머지 구조물들에 대해
	 * 가능여부 확인 
	 * 3. 맵에 패딩줘서 indexOutOfBounds Exception방지
	 * 
	 * 시간복잡도:
	 * ?
	 * 
	 * 풀이에 걸린 시간:
	 * 7h
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(Arrays.deepToString(solution(5,
				new int[][] { { 0, 0, 0, 1 }, { 2, 0, 0, 1 }, { 4, 0, 0, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 1 },
						{ 2, 1, 1, 1 }, { 3, 1, 1, 1 }, { 2, 0, 0, 0 }, { 1, 1, 1, 0 }, { 2, 2, 0, 1 } })));
	}

	static final int PILLAR = 0, BEAM = 1, INSTALL = 1, REMOVE = 0;
	static int N;
	static boolean[][] pillar, beam;

	public static int[][] solution(int n, int[][] build_frame) {
		int frameCount = 0;
		N = n + 3;
		pillar = new boolean[N][N];
		beam = new boolean[N][N];

		for (int[] frame : build_frame) {
			int x = frame[0] + 1;
			int y = frame[1] + 1;
			int frameType = frame[2];
			int installOrRemove = frame[3];

			if (installOrRemove == INSTALL) {
				if (frameType == PILLAR) {
					if (canInstallPillar(x, y)) {
						pillar[x][y] = true;
						frameCount++;
					}
				} else if (frameType == BEAM) {
					if (canInstallBeam(x, y)) {
						beam[x][y] = true;
						frameCount++;
					}
				}
			} else if (installOrRemove == REMOVE) {
				// 프레임 제거
				if (frameType == PILLAR) {
					pillar[x][y] = false;
				} else if (frameType == BEAM) {
					beam[x][y] = false;
				}

				if (canRemove(x, y, frameType)) {
					frameCount--;
					continue;
				}

				// 프레임 복구
				if (frameType == PILLAR) {
					pillar[x][y] = true;
				} else if (frameType == BEAM) {
					beam[x][y] = true;
				}
			}

		}

		int[][] answer = new int[frameCount][];
		int idx = 0;

		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < N - 1; j++) {
				if (pillar[i][j])
					answer[idx++] = new int[] { i - 1, j - 1, PILLAR };
				if (beam[i][j])
					answer[idx++] = new int[] { i - 1, j - 1, BEAM };
			}
		}

		return answer;
	}

	// 프레임 제거 후 나머지 프레임 조건 만족 여부 확인 메소드
	private static boolean canRemove(int x, int y, int frameType) {
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < N - 1; j++) {
				if (pillar[i][j] && !canInstallPillar(i, j))
					return false;
				if (beam[i][j] && !canInstallBeam(i, j))
					return false;
			}
		}

		return true;
	}

	private static boolean canInstallBeam(int x, int y) {
		// 한쪽 끝 부분이 기둥 위에 있거나 || 양쪽 끝 부분이 다른 보와 동시에 연결
		return pillar[x][y - 1] || pillar[x + 1][y - 1] || (beam[x - 1][y] && beam[x + 1][y]);
	}

	private static boolean canInstallPillar(int x, int y) {
		// 바닥 위에 있거나 || 보의 한쪽 끝 부분 위에 있거나 || 다른 기둥 위에 있거나
		return y == 1 || beam[x][y] || beam[x - 1][y] || pillar[x][y - 1];
	}

}
