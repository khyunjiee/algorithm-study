package programmers;

import java.util.Arrays;

// 징검다리 건너기
/*
 * 이 문제는 이미 0이 되버린 연속한 디딤돌의 길이를 어떻게 빠르게 구하는지가 관건인 것 같다.
 * union-find 알고리즘을 이용하여 연속한 디딤돌의 길이를 구하였다.
 * 디딤돌에 적힌 숫자를 life라 하고, life기준 오름차순으로 정리한 후 앞에서부터
 * 하나씩 해당 디딤돌의 위치를 선택하면서, 선택한 디딤돌 앞뒤로 이미 선택되어 있으면 하나의 그룹으로 묶고
 * 그룹의 대표자는 그룹의 원소수를 저장하는 방식으로 해결하였다.
 * 시간복잡도: O(NlogN) 정렬하였으므로...?
 * 느낀점: union-find를 kruskal알고리즘 외에 처음으로 적용을 해보았는데, 생각보다 유용한 것 같다.
 */
public class Programmers_64062 {

	class Stone implements Comparable<Stone>{
		int idx,life;

		public Stone(int idx, int life) {
			this.idx = idx;
			this.life = life;
		}
		@Override
		public int compareTo(Stone o) {
			return Integer.compare(this.life, o.life);
		}
	}
	int[] parents;
	public int solution(int[] stones, int k) {
		int N = stones.length;
		parents = new int[N+1]; // 인덱스 1부터 시작
		Stone[] arr = new Stone[N];
		for(int i=0; i<N; ++i) {
			arr[i] = new Stone(i,stones[i]);
		}
		Arrays.sort(arr); // life기준 오름차순 정렬
		int answer = 0;
		for(int i=0; i<N; ++i) {
			int num = arr[i].idx+1;
			int life = arr[i].life;
			parents[num]=-1; // 본인을 대표로 하고, 그룹의 갯수는 음수로 표시하므로 -1로 초기화
			if(parents[num-1]!=0) { // 바로 이전칸이 그룹이 존재하면
				union(num-1, num);
			}
			if(num<N && parents[num+1]!=0) { // 바로 다음칸이 존재하고 그룹이 존재하면
				union(num, num+1);
			}
//			System.out.println(Arrays.toString(parents));
			if(Math.abs(parents[find(num)])>=k) {
				answer = life;
				break;
			}
		}
		return answer;
    }
	public int find(int a) {
		if(parents[a]<0) return a; // 음수이면 대표자이므로 return
		return parents[a] = find(parents[a]);
	}
	public void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return;
		
		parents[aRoot] += parents[bRoot]; // bRoot의 갯수를 aRoot의 갯수에 더하기
		parents[bRoot] = aRoot; // bRoot를 aRoot 자식으로 합치기
	}
}
