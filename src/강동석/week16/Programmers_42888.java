package programmers;

import java.util.HashMap;
import java.util.Map;

// 오픈채팅방
/*
 * 유저아이디가 유저를 식별할 수 있는 고유 값이므로
 * Map자료구조를 이용하여 유저아이디를 key값으로, 유저닉네임을 value값으로 설정을 한다.
 * Change를 할 때는 Map자료구조의 유저아이디 key값에 그냥 덮어쓰기를 하면 유저닉네임이 변경이 된다.
 * StringBuilder를 이용해 저장할 문자열들을 구분자(여기서는 콤마(,)를 이용했음)를 이용하여 저장한 후
 * 내장메서드 split을 이용하여 String[]으로 변환시켜준다.
 */
public class Programmers_42888 {

	public static void main(String[] args) {
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		String[] answer = solution(record);
		for(String str : answer) {
			System.out.println(str);
		}
	}
	
	public static String[] solution(String[] record) {
        Map<String, String> idMap = new HashMap<String, String>();
        for(String str : record) {
        	String[] strs = str.split(" ");
        	if(str.startsWith("E")) { // Enter이면
        		idMap.put(strs[1], strs[2]); // key값: id, value값: 닉네임
        	}else if(str.startsWith("C")) { // Change이면
        		idMap.put(strs[1], strs[2]); // key값: id, value값: 닉네임, 덮어쓰기
        	}
        }
        StringBuilder sb = new StringBuilder();
        for(String str : record) {
        	String[] strs = str.split(" ");
        	if(str.startsWith("E")) { // Enter이면
        		sb.append(idMap.get(strs[1])).append("님이 들어왔습니다.").append(","); // id로 닉네임 찾아서 sb에 넣기, 콤마는 구분자
        	}else if(str.startsWith("L")) { // Leave이면
        		sb.append(idMap.get(strs[1])).append("님이 나갔습니다.").append(","); // id로 닉네임 찾아서 sb에 넣기, 콤마는 구분자
        	}
        }
        
        return sb.toString().split(",");
    }
}