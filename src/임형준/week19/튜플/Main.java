package src.프로그래머스.카카오기출.튜플;

import java.util.*;

/**
 * date: 22.01.10
 *
 * sol)
 * { 과 } 를 기준으로 parsing
 * parsing 한 값에 대해서 다시 ,로 parsing 하고, 해당 값을 int 배열로 옮김
 * 해당 int[] 배열을 List<int[]> 에 저장하고 sorting
 * set 에 순차적으로 넣어주고, set 에 들어가 있지 않다면 결과 list 에 추가
 *
 * 시간복잡도
 * O(n^2)
 */

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution("{{1,2,3},{2,1},{1,2,4,3},{2}}");
//        solution.solution("{{4,2,3},{3},{2,3,4,1},{2,3}}");

    }
    static class Solution {
        public int[] solution(String s) {
            int N = s.length();
            int pointer = 1;
            List<String> tempList = new ArrayList<>();
            List<int[]> list = new ArrayList<>();

            while(pointer < N){
                if(s.charAt(pointer) == '{'){
                    int next = pointer + 1;
                    while(s.charAt(next) != '}') next++;
                    tempList.add(s.substring(pointer+1,next));
                    pointer = next+1;
                }
                pointer++;
            }

            int size = tempList.size();
            for(int i=0;i<size;i++){
                String[] split = tempList.get(i).split(",");
                int[] arr = new int[split.length];
                for(int j=0;j<split.length;j++){
                    arr[j] = Integer.parseInt(split[j]);
                }
                list.add(arr);
            }
            Comparator<int[]> comp = (o1, o2) -> Integer.compare(o1.length,o2.length);
            list.sort(comp);

            List<Integer> res = new ArrayList<>();
            Set<Integer> set = new HashSet<>();

            size = list.size();
            for(int i=0;i<size;i++){
                int[] att = list.get(i);

                for(int j=0;j<att.length;j++){
                    if(!set.contains(att[j])) {
                        res.add(att[j]);
                        set.add(att[j]);
                    }
                }
            }

            int[] resArr = new int[res.size()];

            for(int i=0;i<res.size();i++){
                resArr[i] = res.get(i);
            }
            return resArr;
        }
    }
}
