package programmers;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

// 불량 사용자
/*
 * 풀이방법은 구글링하여 참고하였다.
 * banned_id 배열의 요소들은 순서가 그대로 고정이므로 예를들어 {1,2,3,4}와 {2,1,3,4}를 가져와서 각각 banned_id와 비교하는 것은 서로 다르지만
 * 둘 다 통과했을 경우에 결국 유저들의 목록은 같으므로 순서가 있는 set인 LinkedHashSet을 이용하여 그 결과를 다시 set에 담았다.
 * 모든 경우의 수를 순열로 다 구해서 set을 set에 담는다. 단, set을 set에 담을 때 그냥 담으면 주소값이 넘어가므로
 * set을 담을 때 마다 항상 새로 set을 생성해서 담아야한다.
 */
public class Programmers_64064 {
	
	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id= {"fr*d*", "*rodo", "******", "******"};
		System.out.println(solution(user_id, banned_id));
	}
	
	public static Set<Set<String>> resultSet = new HashSet<>();
	public static int solution(String[] user_id, String[] banned_id) {
		Set<String> set = new LinkedHashSet<String>(); // 중복처리 하면서 입력 순서대로 정렬되기 때문에 LinkedHashSet을 사용
		permutation(user_id,banned_id,set);
		
        return resultSet.size();
    }
	public static void permutation(String[] user_id, String[] banned_id, Set<String> set) {
		if(set.size() == banned_id.length) { // 뽑은 유저아이디 set이 불량 사용자 명단수와 같으면
			if(checkBanned(set,banned_id)) { // 아이디 일치여부 비교
				resultSet.add(new HashSet<String>(set)); // 일치하면 new 선언 해서 resultSet에 보관해야함. 아니면 주소값이 들어가므로 중복
			}
			return;
		}
		
		for(String id : user_id) {
			if(!set.contains(id)) {
				set.add(id);
				permutation(user_id, banned_id, set);
				set.remove(id);
			}
		}
	}
	public static boolean checkBanned(Set<String> set, String[] banned_id) {
		int idx = 0; // 불량 사용자 아이디들읠 하나씩 꺼낼 인덱스
		for(String id : set) {
			if(!isSameId(id, banned_id[idx++])) { // 아이디가 일치하지 않으면
				return false;
			}
		}
		return true;
	}
	public static boolean isSameId(String userId, String bannedId) {
		if(userId.length() != bannedId.length()) return false; // 길이가 다르면 false
		for(int i=0; i<userId.length(); ++i) {
			if(bannedId.charAt(i) == '*') continue; // *은 그냥 패스
			if(userId.charAt(i) != bannedId.charAt(i)) return false; // 문자가 다르면 false
		}
		return true;
	}
}