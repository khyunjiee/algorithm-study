package com.ssafy.algo.study.week7;

import java.util.Arrays;

public class 자물쇠와열쇠_60059 {
	/*
	 * 접근:
	 * 키를 90도씩 돌려가면서 자물쇠와 겹칠수 있는 경우의 수를 모두 수행합니다.
	 * 
	 * 시간복잡도:
	 * x축으로 자물쇠와 키를 겹치도록 두는 경우의 수 39개
	 * y축도 마찬가지
	 * 39*39 = 약1600개
	 *  자물쇠 모든 칸확인 40회
	 * 
	 */

	public static void main(String[] args) {
		System.out.println(solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}, new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}));
	}
	
	public static boolean solution(int[][] key, int[][] lock) {
		// 패딩 삽입한 자물쇠 생성
		int paddedKeyLen = lock.length + 2*(key.length-1);
		int[][] paddedKey = new int[paddedKeyLen][paddedKeyLen];
		for (int i = 0; i < lock.length; i++) {
			for (int j = 0; j < lock.length; j++) paddedKey[i+key.length-1][j+key.length-1] = lock[i][j];
		}
		
        // 겹치게 두는 모든 경우의 수
		for (int i = 0; i < paddedKey.length - key.length +1; i++) {
			for (int j = 0; j < paddedKey.length - key.length +1; j++) {
				for (int k = 0; k < 4; k++) {// key를 4방향으로 회전
					int[][] newPaddedKey = new int[paddedKey.length][paddedKey.length];
					// paddedKey 복사
					copy(paddedKey, newPaddedKey);
					
					// 겹치기
					for (int x = 0; x < key.length; x++) {
						for (int y = 0; y < key.length; y++) {
							switch(k) { 
							case 0:
								newPaddedKey[i+x][j+y] += key[x][y]; 
								break;
							case 1:
								newPaddedKey[i+x][j+y] += key[y][key.length-1-x];
								break;
							case 2:
								newPaddedKey[i+x][j+y] += key[key.length-1-x][key.length-1-y];
								break;
							case 3:
								newPaddedKey[i+x][j+y] += key[key.length-1-y][x];
								break;
							}
						}
					}
					// 일치여부 확인
					if(check(newPaddedKey, lock.length, key.length)) return true;
				}
			}
		}
        return false;
    }
	
	
	// 키와 자물쇠 일치 여부 확인
	private static boolean check(int[][] map, int lockLen, int keyLen) {
		for (int ii = 0; ii < lockLen; ii++) {
			for (int jj = 0; jj < lockLen; jj++) {
				if(map[ii+keyLen-1][jj+keyLen-1]!=1) return false; // 하나라도 1이 아니면 불일치
			}
		}
		return true;
	}
	
	private static void copy(int[][] vKey, int[][] map) {
		for (int i = 0; i < vKey.length; i++) {
			for (int j = 0; j < vKey.length; j++) map[i][j] = vKey[i][j];
		}
	}
}
