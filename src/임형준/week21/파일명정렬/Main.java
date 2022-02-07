package src.프로그래머스.카카오기출.파일명정렬;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * date : 22.01.20
 */

/*
풀이:
list 에 숫자 이전의 값 (ex: img), 숫자 값 (ex: 10 ), 인덱스 값 (ex: 2) 를 파싱해서 넣어준다.
첫번째 값 - 두번째 값 - 세번째 값 순으로 정렬해서 출력해준다.
 */

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.solution(new String[]{"foo.a-s.d122.zip,img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});
//        solution.solution(new String[]{"F-5 Freedom Fighter", "B-0050 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"});
        solution.solution(new String[]{"img000012345", "img1.png","img2","IMG02"});
    }
    static class Solution {
        static List<String>[] lists;
        static int N;
        public String[] solution(String[] files) {
            N = files.length;
            lists = new ArrayList[N];
            for(int i=0;i<N;i++) lists[i] = new ArrayList<>();

            for(int i=0;i<N;i++){
                String file = files[i];
                int pointer = 0;
                while(file.charAt(pointer) < '0' || file.charAt(pointer) > '9'){
                    pointer++;
                }
                lists[i].add(file.substring(0,pointer));
                int temp = pointer;
                while(pointer < file.length() && file.charAt(pointer) >= '0' && file.charAt(pointer) <= '9'){
                    pointer++;
                }
                lists[i].add(String.valueOf(Integer.parseInt(file.substring(temp,pointer))));
                lists[i].add(String.valueOf(i));
            }

            Comparator<List<String>> comp = new Comparator<List<String>>() {
                @Override
                public int compare(List<String> o1, List<String> o2) {
                    String s1 = o1.get(0);
                    String s2 = o2.get(0);
                    int n1 = Integer.parseInt(o1.get(1));
                    int n2 = Integer.parseInt(o2.get(1));
                    int compare = s1.toUpperCase().compareTo(s2.toUpperCase());

                    if( compare != 0 ){
                        return compare;
                    }else{
                        int compare2 = Integer.compare(n1, n2);
                        if(compare2 !=0) return compare2;
                        else{
                            return Integer.compare(Integer.parseInt(o1.get(2)),Integer.parseInt(o2.get(2)));
                        }
                    }
                };
            };

            Arrays.sort(lists,comp);

            String[] result = new String[N];

            int p=0;
            for(List<String> list : lists){
                int index = Integer.parseInt(list.get(2));
                result[p++] = files[index];
            }
            System.out.println(Arrays.toString(result));
            return result;
        }
    }
}
