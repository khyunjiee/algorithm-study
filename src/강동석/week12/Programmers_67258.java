package programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// [카카오 인턴] 보석 쇼핑
/*
 * 얘전에 백준에서 풀었던 회전초밥(?)문제와 조금 비슷했던 것 같았지만, 생각보다 더 어려웠던 문제.
 * 구간을 뒤로 하나씩 늘려가면서, 구간 제일 앞쪽의 보석이 2개이상 구간안에 있으면 앞의 보석을 무조건 제거하는 방식으로 구간을 좁혔다.
 * 일단 모든 보석을 다 포함하면, 그 이후부터는 계속 모든 보석을 포함하면서 구간을 전진하며 최소길이를 찾는 방식으로 해결하였다.
 * 시간복잡도: O(N), 보석의 앞에서부터 마지막까지 한번에 순회하면서 구간을 조정한다.
 */
public class Programmers_67258 {

	public static void main(String[] args) {
		String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
		int[] arr = solution(gems);
		System.out.println(arr[0]+","+arr[1]);
	}
	
	public static int[] solution(String[] gems) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		HashSet<String> set = new HashSet<String>();
		Queue<String> q = new LinkedList<String>(); // 구간 내의 보석들을 담은 큐
		int from = 0; // 모든 구간마다 시작 지점의 인덱스
		int start = 0; // 최소 구간의 시작 지점의 인덱스
		int lenOfGems=Integer.MAX_VALUE; // 보석 구간의 최소 길이
		int length = gems.length;
		for(int i=0; i<length; i++) {
			set.add(gems[i]); // 보석 종류의 갯수 파악
		}
		
		
		for(int i=0; i<length; i++) {
			map.put(gems[i], map.getOrDefault(gems[i], 0)+1); // 각 보석마다 map에 갯수를 저장
			q.add(gems[i]);
			
			while(true) {
				String front = q.peek(); // 구간 제일 앞쪽의 보석이
				if(map.get(front)>1) { // 1개보다 많이 가지고 있으면
					map.put(front, map.get(front)-1); // 필요가 없으므로 빼기
					q.poll();
					from++; // 구간의 시작지점 1칸 전진
				}else {
					break;
				}
			}
			
			if(map.size()==set.size()) { // 모든 종류의 보석을 다 포함하면
				if(lenOfGems>q.size()) { // 기존의 구간보다 짧으면
					lenOfGems = q.size();
					start = from; // 최소 구간의 시작지점 갱신
					if(lenOfGems==set.size()) { // 구간의 길이 = 보석의 종류 수, 즉 모든 보석을 한개씩만 가지고 한번에 연속하면 바로 종료
						break;
					}
				}
			}
		}
		return new int[] {start+1, start+lenOfGems};
    }
}