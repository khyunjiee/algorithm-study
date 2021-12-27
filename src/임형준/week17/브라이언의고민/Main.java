package src.알고리즘스터디.날짜12월3째주.브라이언의고민;

/**
 * daate : 21.12.26
 * link : https://programmers.co.kr/learn/courses/30/lessons/1830
 * HaEaLaLaObWORLDb 되는데
 * SpIpGpOpNpGJqOqA index out of range
 * invalid 타입 만들어야 한다
 */

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String haEaLaLaObWORLDb = solution.solution("SpIpGpOpNpGJqOqA");
        System.out.println(haEaLaLaObWORLDb);
    }

    static class Solution {
        public String solution(String sentence) {
            int N = sentence.length();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < N; i++) {
                char c = sentence.charAt(i);

                if (i == N - 1) {
                    sb.append(c);
                } else {
                    // 대문자일경우
                    if (Character.isUpperCase(c)) {
                        int pointer = i + 1;

                        char is = '-';

                        if (pointer != N) {
                            if (Character.isLowerCase(sentence.charAt(i + 1))) {
                                is = sentence.charAt(i + 1);
                            }
                        }

                        while (pointer < N) {
                            if (pointer == N - 1) break;
                            if (Character.isUpperCase(pointer) && Character.isUpperCase(pointer + 1)) break;
                            if (is != '-' && Character.isLowerCase(sentence.charAt(pointer + 1)) && is != sentence.charAt(pointer + 1))
                                break;

                            pointer++;
                        }
                        for (int j = i; j <= pointer; j++) {
                            if (Character.isUpperCase(sentence.charAt(j))) {
                                sb.append(sentence.charAt(j));
                            }
                        }
                        if (i != N - 1) {
                            sb.append(" ");
                        }
                        i = pointer;

                        // 소문자 일경우
                    } else {
                        int pointer = i + 1;

                        while (true) {
                            char lastSmall = sentence.charAt(pointer);
                            if (c == lastSmall) break;
                            pointer++;
                        }
                        for (int j = i; j <= pointer; j++) {
                            if (Character.isUpperCase(sentence.charAt(j))) {
                                sb.append(sentence.charAt(j));
                            }
                        }
                        if (pointer != N - 1) {
                            sb.append(" ");
                        }
                        i = pointer;
                    }
                }
            }


            return sb.toString();
        }
    }
}
