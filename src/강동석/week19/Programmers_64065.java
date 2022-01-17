package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 튜플
/*
 * 어떤 숫자가 n번 나오면 배열에서 뒤에서 n번째 숫자라는 특징을 이용.
 * 우선 "},{" 문자열을 이용해 String[]으로 분리한 후, 원소의 갯수가 1개이면 숫자만 substring() 메서드를 이용하여 바로 리턴한다.
 * 원소의 갯수가 2개 이상이면 제일 첫번쨰 원소는 {{를 가지고, 마지막 원소는 }}를 가진다는 점만 고려하면, 나머지는 전부 콤마(,)로 구분하여 숫자를 추출한 후
 * map에다가 해당숫자를 key값으로 가지고 등장횟수를 value값으로 가지는 데이터를 저장한다.
 * 모두 저장한 후 다시 map에서 데이터를 읽으면서 배열에 바로 저장한다.
 */
public class Programmers_64065 {
	
	public static void main(String[] args) {
		String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
		System.out.println(Arrays.toString(solution(s)));
	}
	
	public static int[] solution(String s) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		String[] split1 = s.split("\\},\\{");
		int length = split1.length;
		if(length==1) { // 원소의 갯수가 하나면
			return new int[] {Integer.parseInt(split1[0].substring(2, split1[0].length()-2))};
		}
		String[] split2 = split1[0].substring(2).split(","); // "{{n1,n2,..." 에서 앞쪽의 "{{"를 제거한 후 콤마(,)로 다시 나눈다.
		for(int i=0,len2=split2.length; i<len2; ++i) {
			int num = Integer.parseInt(split2[i]);
			map.put(num, map.getOrDefault(num, 0)+1);
		}
		for(int i=1,len1=split1.length; i<len1-1; ++i) {
			split2 = split1[i].split(","); // "n1,n2,...." 에서 콤마(,)로 다시 나눈다.
			for(int j=0,len2=split2.length; j<len2; ++j) {
				int num = Integer.parseInt(split2[j]);
				map.put(num, map.getOrDefault(num, 0)+1);
			}
		}
		split2 = split1[length-1].substring(0, split1[length-1].length()-2).split(",");
		for(int i=0,len2=split2.length; i<len2; ++i) {
			int num = Integer.parseInt(split2[i]);
			map.put(num, map.getOrDefault(num, 0)+1);
		}
        int[] answer = new int[length];
        for(int key : map.keySet()) {
        	answer[length-map.get(key)] = key;
        }
        return answer;
    }
	
}