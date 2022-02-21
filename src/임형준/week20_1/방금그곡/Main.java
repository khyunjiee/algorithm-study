package src.프로그래머스.카카오기출.방금그곡;

import java.util.*;

/**
 * date : 22.01.12
 * link : https://programmers.co.kr/learn/courses/30/lessons/17683
 *
 * 풀이 :
 *
 * 1. 음표 -> 1개의 문자로 변환 ( C# -> 1 )
 * 2. loop 수만큼 문자 생성. 비교. 일치 여부 판단.
 * 3. list 에 넣고, 가장 길었을 때의 문자의 이름 출력
 *
 * 풀이 참조 : https://velog.io/@hyunjkluz/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A417683-3%EC%B0%A8-%EB%B0%A9%EA%B8%88%EA%B7%B8%EA%B3%A1-Java
 *
 *
 */

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String abcdefg = solution.solution("CC#BCC#BCC#", new String[]{"03:00,03:08,FOO,CC#B", "13:00,13:05,WORLD,ABCDEF"});
        System.out.println(abcdefg);
    }
    static class Solution {

        String[] melodies = new String[]{"C","C#","D","D#","E","F","F#","G","G#","A","A#","B"};
        // c c# d d# e f f# g g# a a# b ( upper )
        char[] nums = "0123456789ab".toCharArray();
        static Map<String,Character> map;
        static HashMap<String,Integer> result;

        public String solution(String m, String[] musicinfos) {
            map = new HashMap<>();
            result = new LinkedHashMap<>();
            for(int i=0;i<12;i++){
                map.put(melodies[i], nums[i]);
            }

            String matching = changeToNumber(m);

            for(int i=0;i<musicinfos.length;i++){

                String[] split = musicinfos[i].split(",");

                String startTime = split[0];
                String endTime = split[1];
                String songName = split[2];
                String pattern = split[3];

                String[] startSplit = startTime.split(":");
                String[] endSplit = endTime.split(":");

                // 시간 27번 테스트 케이스 에러
//                int hour = Integer.parseInt(endSplit[0]) - Integer.parseInt(startSplit[0]);
//                int min = Math.abs(Integer.parseInt(endSplit[1]) - Integer.parseInt(startSplit[1]));
//
//                int totalTime = hour * 60 + min;

                int start = Integer.parseInt(startSplit[0])*60 + Integer.parseInt(startSplit[1]);
                int end = Integer.parseInt(endSplit[0])*60 + Integer.parseInt(endSplit[1]);

                int totalTime = end - start;

                if(totalTime < matching.length()) continue;

                StringBuilder sb = new StringBuilder();

                String patternToChange = changeToNumber(pattern);

                for(int j=0;j<=totalTime;j++){
                    // pattern 을 안바꾼 상태로 pattern.length() 를 해서 틀렸었다.
                    int pointer = j % patternToChange.length();
                    // 아래 부분도 patternToChange.charAt() 이 아니라, pattern.charAt()을 했었다.
                    sb.append(patternToChange.charAt(pointer));
                }
                for(int p=0;p<=sb.length()-matching.length();p++){
                    String sub = sb.substring(p,p+matching.length());
                    if(sub.equals(matching)) {
                        result.put(songName,totalTime);
                        break;
                    }
                }
            }

            if(result.isEmpty()) return "(None)";

            Set<String> strings = result.keySet();
            int maxCnt = 0;

            for(String s : strings){
                if(maxCnt < result.get(s)){
                    maxCnt = result.get(s);
                }
            }
            String resultString = "";
            for(String s : strings){
                if(result.get(s) == maxCnt) {
                    resultString = s;
                    break;
                }
            }
            return resultString;
        }

        private String changeToNumber(String m) {
            int N = m.length();
            StringBuilder res = new StringBuilder();
            for(int i=0;i<N;i++){
                StringBuilder sb = new StringBuilder();
                sb.append(m.charAt(i));
                if(i!=N-1){
                    char next = m.charAt(i+1);
                    if(next == '#'){
                        sb.append(m.charAt(++i));
                    }
                }
                res.append(map.get(sb.toString()));
            }
            return res.toString();
        }
    }
}
