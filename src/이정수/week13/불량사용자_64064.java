package com.ssafy.algo.study.week13;

import java.util.Arrays;

public class 불량사용자_64064 {
	/*
	 * 접근:
	 * 1. 순열 생성(user_id에서 banned_id 수만큼의 아이디를 뽑아만들 수 있는 모든 순열) n = user_id 수, r = banned_id 수
	 * 2. 뽑은 아이디와 banned_id를 순서대로 확인하여 모두 매칭이 가능하면 카운트
	 * 3. 같은 조합 생성 방지를 위해 used배열 사용(index를 비트마스킹으로 사용)
	 * 
	 * 시간복잡도:
	 * 순열 생성 * 매칭 확인
	 * 8C4* 4 * 8 = 2240
	 * 
	 * 풀이에 걸린 시간:
	 * 40min
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"fr*d*", "*rodo", "******", "******"}));
	}
	
	static int n, r, result[], cnt;
	static boolean used[];
	static String[] userId, bannedId;
	public static int solution(String[] user_id, String[] banned_id) {
		userId = user_id;			// user_id 멤버변수로 만들기
		bannedId = banned_id;		// banned_id 멤버변수로 만들기
		n = user_id.length;			// user_id 수
		r = banned_id.length;		// banned_id 수
		result = new int[r];		// 생성된 순열 저장 배열
		used = new boolean[1<<n];	// 이미 사용된 조합 체크 배열
        permutation(0, 0);
		
		return cnt;
    }
	
	// 순열 생성
	private static void permutation(int depth, int flag) {
		if(depth==r) {
			if(used[flag]) return; 	// 사용했던 조합은 확인 x
			if(isvalid()) {			// 생성한 아이디 조합이 banned_id와 모두 매칭되면
				used[flag] = true;
				cnt++;
			}
			return;
		}
		for (int i = 0; i < n; i++) {
			if((flag&1<<i)!=0) continue;
			result[depth] = i;
			permutation(depth+1, flag|1<<i);
		}
	}
	
	// 생성된 아이디 순열이 banned_id 배열과 모두 매칭이 되는지 확인
	private static boolean isvalid() {
		for (int i = 0; i < r; i++) {
			if(!canMatch(userId[result[i]], bannedId[i])) return false;
		}
		return true;
	}
	
	// 두 문자열이 매칭이 되는지 확인
	private static boolean canMatch(String uId, String bId) {
		if(uId.length()!=bId.length()) return false;	// 두 문자열의 길이가 다르면 매칭 불가
		for (int i=0;i<bId.length();i++) {
			if(bId.charAt(i)=='*') continue;
			if(bId.charAt(i)!=uId.charAt(i)) return false;
		}
		return true;
	}
}
