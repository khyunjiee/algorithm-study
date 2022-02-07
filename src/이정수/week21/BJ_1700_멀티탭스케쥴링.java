package com.ssafy.algo.study.week21;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 문제:멀티탭 스케쥴링
 * 링크:https://www.acmicpc.net/problem/1700
 * 
 * 접근:
 * OPT 알고리즘입니다.
 * 앞으로 가장 오랫동안 사용되지 않을 전자제품을 교체합니다.
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 소요 시간:
 * 2h
 * 
 */
public class BJ_1700_멀티탭스케쥴링 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[] sequence = new int[K];
		
		for (int i = 0; i < K; i++) {
			sequence[i] = sc.nextInt();
		}
		
		int unplugCnt = 0;
		Set<Integer> multiTap = new HashSet<>();
		
		for (int i = 0; i < K; i++) {
			if(!multiTap.contains(sequence[i]) && multiTap.size()==N) { // 멀티탭에 안꽂혀있고 이미 멀티탭이 꽉찬 경우
				
				// 교체할 전기용품 찾기
				int maxIdx = 0; 
				int selected= 0;
				label: for (Integer product : multiTap) {
					for (int j = i+1; j < K+1; j++) {
						if(j==K) { // 다시 사용될 일이 없는 제품인 경우
							selected = product;
							break label;
						}
						if(sequence[j]==product) {
							if(maxIdx<j) {
								selected = product;
								maxIdx = j;
							}
							break;
						}
					}
				}
				
				multiTap.remove(selected);
				unplugCnt++;
			}
			
			multiTap.add(sequence[i]);
			
		}
		
		
		System.out.println(unplugCnt);
	}

}
