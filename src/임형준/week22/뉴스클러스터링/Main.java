package src.프로그래머스.카카오기출.뉴스클러스터링;

import java.util.*;
import java.util.regex.Pattern;

/**
 * date: 22.02.05
 */

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution("aa1+aa2","AAAA12");
    }
    static class Solution {
        public int solution(String str1, String str2) {
            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();

            getList(str1, list1);
            getList(str2, list2);

            if(list1.isEmpty() && list2.isEmpty()) return 65536;

            double subCnt = 0 ;
            double dupCnt = 0;

            subCnt += list1.size() +list2.size();

            boolean[] checked = new boolean[list2.size()];

            for(int i=0;i<list1.size();i++){
                String s = list1.get(i);
                for(int j=0;j<list2.size();j++){
                    String s2 = list2.get(j);
                    if(s.equals(s2) && !checked[j]){
                        checked[j]= true;
                        dupCnt+=1;
                        break;
                    }
                }
            }
            subCnt -= dupCnt;

            double res = dupCnt / subCnt;
            res *= 65536;
            return (int) res;
        }

        private void getList(String str, List<String> list) {
            for(int i=0;i<str.length()-1;i++){
                String sub = str.substring(i, i + 2);
                sub = sub.toUpperCase();
                String regex = "^[A-Z]*$";
                if(Pattern.matches(regex,sub)){
                    list.add(sub);
                }
            }
        }
    }
}
