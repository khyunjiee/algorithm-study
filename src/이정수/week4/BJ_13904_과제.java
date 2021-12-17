package com.ssafy.algo.study.week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BJ_13904_과제 {
	/*
	 * 접근:
	 * 높은 점수의 과제 부터 최대한 늦은날에 수행
	 * 
	 * 시간복잡도:
	 * O(N)
	 * 
	 * 풀이에 걸린 시간:
	 * 20m
	 * 
	 */

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		int[][] tasks = new int[N][2];
		
		int maxDay =0;
		
		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			tasks[i][0] = Integer.parseInt(split[0]);
			tasks[i][1] = Integer.parseInt(split[1]);
			maxDay = Math.max(maxDay, tasks[i][0]);
		}
		
		Arrays.sort(tasks, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1]) {
					return o2[0]-o1[0];
				}else {
					return o2[1]-o1[1];
				}
			}
		});
		
		int[] resultArr = new int[maxDay+1];
		
		for (int i = 0; i < tasks.length; i++) {
			int day = tasks[i][0];
			while(resultArr[day]!=0 && day>=0) {
				day--;
			}
			if(day>0) {
				resultArr[day] = tasks[i][1];
			}
		}
		
		int total = 0;
		for (int i = 0; i < resultArr.length; i++) {
			total += resultArr[i];
		}
		
		System.out.println(total);
	}

}
