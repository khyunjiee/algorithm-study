package com.ssafy.algo.study.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빗물_14719 {
	/*
	 * 접근:
	 * 각 블록 위로 채워지는 물의 높이는 왼쪽에서 가장 높은 블록과 오른쪽에서 가장 높은 블록중 
	 * 더 낮은 높이만큼 채워집니다.
	 * 
	 * 시간 복잡도:
	 * O(N^2)
	 * 
	 * 풀이에 걸린 시간:
	 * 3시간
	 * 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		int[] blocks = new int[W];
		for (int i = 0; i < blocks.length; i++) 
			blocks[i] = Integer.parseInt(st.nextToken());
		
		int totalWater= 0;
		for (int i = 1; i < W-1; i++) { // 가장 자리에는 물이 채워질 수 없음
			// 좌측에서 가장 높은 블록
			int leftBlock=0;
			for (int j = i-1; j >= 0; j--) {
				if(leftBlock<blocks[j]) leftBlock = blocks[j];
			}
			
			// 우측에서 가장 높은 블록
			int rightBlock = 0;
			for (int j = i+1; j < W; j++) {
				if(rightBlock<blocks[j]) rightBlock = blocks[j];
			}
			
			// 양쪽에 자신보다 높은 블록이 있는 경우에만 빗물이 채워짐
			if(leftBlock> blocks[i] && rightBlock > blocks[i]) {
				int water =Math.min(leftBlock, rightBlock)-blocks[i];
				totalWater += water;
			}
			
		}
		System.out.println(totalWater);
	}

}
