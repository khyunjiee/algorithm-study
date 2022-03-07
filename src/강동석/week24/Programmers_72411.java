package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 메뉴 리뉴얼
/*
 * 1. 모든 등장하는 알파벳을 체크한다.
 * 2. course의 숫자만큼 알파벳 조합을 만들어 해당 코스요리를 map에 <String,Integer> 형태로 value값을 등장 횟수로 저장한다.
 * 3. map에서 value가 최댓값인 key들을 List<String>에 저장한다.
 * 4. 2~3번을 끝까지 반복한다.
 * 5. List<String>을 String[]형태로 변환 후 정렬한다.
 */
public class Programmers_72411 {

	public static void main(String[] args) {
		String[] orders = {"XYZ", "XWY", "WXA"};
		int[] course = {2,3,4};
		String[] arr = solution(orders, course);
		for(String str : arr) {
			System.out.print(str+" ");
		}
	}
	
	static int num;
	static boolean[] alphabet;
	static boolean[][] used;
	static int[] arr;
	static Map<String, Integer> map;
	static List<String> answerList;
	
	public static String[] solution(String[] orders, int[] course) {
        alphabet = new boolean[26]; // 알파벳 26개의 사용 여부
        used = new boolean[orders.length][26]; // 각 order마다 사용한 알파벳 체크
        map = new HashMap<String, Integer>();
        answerList = new ArrayList<String>();
        for(int i=0,size=orders.length; i<size; ++i) { // 모든 order에 대하여
        	String str = orders[i];
        	for(int j=0,len=str.length(); j<len; ++j) {
        		alphabet[str.charAt(j)-'A']=true; // 해당 알파벳의 사용여부 true
        		used[i][str.charAt(j)-'A']=true; // 각 order마다 사용한 알파벳 체크
        	}
        }
        
        for(int n : course) {
        	num = n;
        	int cnt = 0;
        	for(String str : orders) {
        		if(str.length()>=num) cnt++;
        		if(cnt>=2) break;
        	}
        	if(cnt>=2) { // 해당 길이 이상의 문자들이 2개 이상이면
        		arr =  new int[n];
        		combination(0,0);
        		int max = 0;
        		for(String key : map.keySet()) {
        			max = Math.max(max, map.get(key));
        		}
        		for(String key : map.keySet()) {
        			if(map.get(key)==max) {
        				answerList.add(key);
//        				System.out.println(key);
        			}
        		}
        		map.clear(); // map 초기화
        	}
        }
        String[] answer = new String[answerList.size()];
        for(int i=0,len=answer.length; i<len; ++i) {
        	answer[i] = answerList.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }
	
	public static void combination(int n, int idx) { // 현재 n개 알파벳 선택, idx번째 알파벳
		if(num-n>26-idx) return; // 남은 알파벳 수보다 뽑아야 할 알파벳이 더 많으면 종료
		if(n==num) { // num개의 알파벳 다 선택했으면
//			for(int i : arr) {
//				System.out.print(i+" ");
//			}
//			System.out.println();
			int numOfCombo = getNumOfCombo();
			if(numOfCombo>1) { // 해당 코스요리가 둘 이상 존재하면
				StringBuilder sb = new StringBuilder();
				for(int i=0; i<arr.length; ++i) {
					sb.append((char)('A'+arr[i]));
				}
//				System.out.println(sb.toString());
				map.put(sb.toString(), numOfCombo);
			}
			return;
		}
		
		for(int i=idx; i<26; ++i) {
			if(alphabet[i]) { // i번째 알파벳이 true이면
				arr[n]=i;
				combination(n+1,i+1); // 선택한 갯수 증가, 다음 확인할 알파벳 idx증가
			}
		}
	}
	
	public static int getNumOfCombo() {
		int cnt = 0;
	loop:for(int i=0,len=used.length; i<len; ++i) {
			for(int j=0; j<arr.length; ++j) {
				if(!used[i][arr[j]]) { // 해당하는 알파벳이 하나라도 없으면 무시
					continue loop;
				}
			}
			cnt++; // 해당 알파벳 전부 포함하면 코스요리 갯수 1 증가
		}
		
		return cnt;
	}
}