package com.ssafy.algo.study.week8;

public class 징검다리건너기_64062 {
	/*
	 * 접근:
	 * 2중 for문으로 풀경우 최악의 경우  (200000*200000000) 시간 초과 발생
	 * 
	 * 1. stone클래스 구성(디딤돌 숫자, idx)
	 * 2. 디딤돌 숫자기준 오름차순 정렬
	 * 3. 가장 작은 디딤돌 숫자 기준으로 한번에 전체 디딤돌 숫자 하나씩 없애기
	 * 4. 숫자가 0이된 디딤돌 리스트로 관리
	 * 5. 숫자가 0이된 디딤돌의 인덱스가  k+1번 연속되면 더이상 뛰어 넘을 수 없음
	 * 6. 시간초과 발생
	 * 
	 * 지난주 문제였던 무지의 먹방 라이브와 같은 접근으로 풀었는데 시간초과가 나 버렸습니다.
	 * 아무리 생각해도 모르겠어서 구글링의 도움을 받아 이분탐색으로 다시 풀었습니다.
	 * 
	 * stone의 최대값인 200,000,000을 이분탐색하여 건너가는 니니즈 친구들의 수를 결정합니다.
	 * 건너가는 니니즈 친구들의 숫자보다 크기가 작은 디딤돌이 k개 이상 연속하면 니니즈 친구들의 숫자를 줄이고
	 * 니니즈 친구들이 모두 건너갈 수 있으면 더 많은 숫자의 니니즈 친구들이 건너갈 수 있는지 탐색합니다.
	 *
	 *log 200000000 = 27.xxx로 전체 stone의 개수 * 27을해도 시간초과가 일어나지 않습니다.
	 * 
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(solution(new int[] {2, 4, 5, 3, 2, 1, 4, 2, 5, 1},3));
	}
	
	public static int solution(int[] stones, int k) {
		int left = 1, right = 200000000;
		int mid=0;
		while(left<=right) {
			mid = (left + right)/2;
			if(check(mid, stones, k)) {// 건너갈 수 있으면 숫자 높이기
				left = mid + 1;
			}else {// 건너갈 수 없으면 숫자 낮추기
				right = mid - 1;
			}
		}
		int result = check(right, stones, k)?right:left;
		return result;
	}
	
	
	// mid 명이 징검다리를 건널 수 있는디 여부 리턴(건널 수 있으면 true)
	private static boolean check(int numberOfFreinds, int[] stones, int k) {
		int sequenceCnt = 0;
		for (int i = 0; i < stones.length; i++) {
			if(stones[i] < numberOfFreinds) sequenceCnt++;
			else sequenceCnt = 0;
			if(sequenceCnt>=k) return false; // 니니즈 친구들보다 숫자가 작은 디딤돌이 k번 이상 연속하면 건널수 없음
		}
		return true;
	}

}
	
//	static class Stone{
//		int number;
//		int idx;
//		public Stone(int number, int idx) {
//			super();
//			this.number = number;
//			this.idx = idx;
//		}
//		@Override
//		public String toString() {
//			return "Stone [number=" + number + ", idx=" + idx + "]";
//		}
//	}
//	static Comparator<Stone> compNumber  = new Comparator<Stone>() {
//		@Override
//		public int compare(Stone o1, Stone o2) {
//			return Integer.compare(o1.number, o2.number);
//		}
//	};
//	
//	static Comparator<Stone> compIdx = new Comparator<Stone>() {
//		@Override
//		public int compare(Stone o1, Stone o2) {
//			return Integer.compare(o1.idx, o2.idx);
//		}
//	};
//	public static int solution(int[] stones, int k) {
//		// 스톤 리스트 생성
//		List<Stone> StoneList  = new ArrayList<>();
//		for (int i = 0; i < stones.length; i++) StoneList.add(new Stone(stones[i], i));
//		
//		// 남은 디딤돌 숫자 기준 오름차순 정렬
//		StoneList.sort(compNumber);
//		
//		// 가장 작은 디딤돌 숫자 기준으로 한번에 전체 디딤돌 숫자 하나씩 줄이기
//		int cnt = 0;// 징검다리를 건넌 니니즈 친구들 수
//		int preNumber=0;
//		for(int i=0;i<StoneList.size();i++) {
//			// 동일한 숫자가 적힌 디딤돌중 가장 마지막까지 이동
//			for (int j = i+1; j < stones.length; j++) 
//				if(StoneList.get(j).number == StoneList.get(i).number) i++; 
//			
//			int diff = StoneList.get(i).number - preNumber; // 적힌 숫자 차이
//			cnt += diff; 
//			preNumber = StoneList.get(i).number;
//			if(i>=k-1) { // 0이 된 디딤돌의 개수가 k개 이상인 경우에만 확인
//				//숫자가 0이된 디딤돌의 인덱스가  k번 연속되는지 확인
//				if(check(StoneList.subList(0, i+1), k)) break;
//			}
//		}
//		return cnt;
//    }
//	
//	// 징검다리가 k번 연속적으로 0 이면 true 리턴
//	private static boolean check(List<Stone> zeroStoneList, int k) {
//		// 정렬
//		zeroStoneList.sort(compIdx);
//		for (int i = 0; i < zeroStoneList.size()-(k-1); i++) {
//			if(zeroStoneList.get(i).idx+ k-1 == zeroStoneList.get(i+k-1).idx) return true;
//		}
//		return false;
//	}

