package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// [3차] 압축
/*
 * map을 이용하여 기존에 존재하는 값들을 배열에 저장하고, 새로운 문자열을 발견하면 map에 새로 추가하는 방식으로
 * 어렵지 않게 해결할 수 있다.
 */
public class Programmers_17684 {

	public static void main(String[] args) {
		String msg = "ABABABABABABABAB";
		System.out.println(solution(msg));
	}
	
	public static int[] solution(String msg) {
		int length = msg.length();
		Map<String,	Integer> map = new HashMap<String, Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(char ch='A'; ch<='Z'; ++ch) {
			String str = String.valueOf(ch);
			map.put(str, ch-'A'+1); // A~Z까지 1~26의 값을 저장
		}
		int idx = 27; // Z다음에 저장될 순번
		int from=0,to=0; // msg에서 문자를 가리키는 인덱스
		while(true) {
			while(to<length&&map.containsKey(msg.substring(from,to+1))) {
				to++;
			}
			list.add(map.get(msg.substring(from, to))); // from~to-1 까지는 map에 존재하므로 list에 저장
			if(to!=length) { // 마지막까지 읽지 않았으면
				map.put(msg.substring(from,to+1), idx++); // from~to 까지는 map에 존재하지 않으므로 idx번째 새로 추가
				from=to; // 다음 문자열의 시작점을 옮기기
			}else { // 마지막까지 읽었으면
				break;
			}
		}
		int size = list.size();
        int[] answer = new int[size];
        for(int i=0; i<size; ++i) {
        	answer[i] = list.get(i);
        }
        return answer;
    }
}