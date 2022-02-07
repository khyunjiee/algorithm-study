package programmers;

import java.util.Arrays;
import java.util.Comparator;

// [3차] 파일명 정렬
/*
 * HEAD와 NUMBER부분을 파싱한 후 HEAD는 소,대문자 통일만 시켜서
 * 정렬만 잘해주면 그렇게 어렵지 않은 문제이다.
 * 여기서 TAIL부분은 사실상 비교할 필요가 없으므로 따로 파싱할 필요도 없다.
 * 다만, 비교를 위해 기존의 인덱스를 저장해야 할 필요가 있다.
 */
public class Programmers_17686 {
	
	public static void main(String[] args) {
		String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
		System.out.println(solution(files));
	}
	
	public static String[] solution(String[] files) {
        int numOfFile = files.length;
        String[][] infos = new String[numOfFile][3]; // {HEAD, NUMBER, 원래 인덱스}
        for(int i=0; i<numOfFile; ++i) {
        	String fileName = files[i];
        	StringBuilder head = new StringBuilder();
        	int len = fileName.length();
        	int pointer=0;
        	for(; pointer<len; ++pointer) {
        		char ch = fileName.charAt(pointer);
        		if(ch<'0' || ch>'9') { // 숫자가 아니면
        			head.append(ch); // head에 추가
        		} else { // 아니면 종료
        			break;
        		}
        	}
        	StringBuilder number = new StringBuilder();
        	for(int k=pointer; k<len && k<pointer+5; ++k) { // 최대 5개 문자를 확인
        		char ch = fileName.charAt(k);
        		if(ch>='0' && ch<='9') { // 숫자이면
        			number.append(ch); // number에 추가
        		} else { // 숫자가 아니면 종료
        			break;
        		}
        	}
        	infos[i][0]=head.toString().toLowerCase(); // HEAD에 비교를 위해 소문자로 모두 변환 후 저장
        	infos[i][1]=number.toString(); // NUMBER 저장
        	infos[i][2]=String.valueOf(i); // 원래 인덱스 저장
        }
        Arrays.sort(infos, new Comparator<String[]>() {

			@Override
			public int compare(String[] o1, String[] o2) {
				if(o1[0].equals(o2[0])) { // HEAD가 같으면
					return Integer.compare(Integer.parseInt(o1[1]), Integer.parseInt(o2[1])); // NUMBER순 오름차순
				}else {
					return o1[0].compareTo(o2[0]); // HEAD기준 사전순 정렬
				}
			}
        });
        String[] answer = new String[numOfFile];
        for(int i=0; i<numOfFile; ++i) {
        	answer[i] = files[Integer.parseInt(infos[i][2])];
        }
        return answer;
    }
}
