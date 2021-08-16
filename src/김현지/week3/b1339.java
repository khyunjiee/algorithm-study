package baekjoon.aug.aug14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 1339 단어수학
 *
 * 접근 방식 :
 * String 으로 접근하면 시간 초과가 발생해 char 로 분리해서 접근했다.
 * verbs[][] 배열에 단어를 toCharArray로 분리해서 저장한다.
 * set에 각 알파벳을 저장해 중복을 없애준다.
 * 이는 단어에 포함된 알파벳과 포함되지 않은 알파벳을 구하고, 몇 개의 순열을 구해야하는지 알게 해준다.
 * 입력이 끝나면 set에 있던 알파벳을 ArrayList로 변환해 인덱스로 접근이 가능하도록 해준다.
 * 그 후 재귀를 돌면서 순열을 찾고, 해당 순열에서 최대값을 찾을 수 있는지 완전탐색한다.
 *
 * 시간 복잡도 :
 * O(N!)
 *
 */

public class b1339 {

    static int N, max = Integer.MIN_VALUE;
    static char[][] verbs;
    static boolean[] select;
    static Map<Character, Integer> map;
    static Set<Character> set;
    static List<Character> alpha;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new HashMap<>();
        set = new HashSet<>();
        verbs = new char[N][];

        // 초기화
        for (int n = 0; n < N; ++n) {
            char[] input = br.readLine().toCharArray(); // 입력 단어를 char 배열로 변환
            verbs[n] = input;                           // 변환한 배열을 단어 배열에 저장
            for (char c: input) set.add(c);             // 단어의 알파벳을 set에 저장
        }

        alpha = new ArrayList<>(set);                   // 인덱스로 접근하기 위해 set -> list 변환
        select = new boolean[10];                       // 순열 선택 여부에 대한 boolean 배열

        // 순열 (재귀호출 활용)
        perm(0);

        System.out.println(max);
    }

    // 순열 구하기
    private static void perm(int cnt) {
        if (cnt == alpha.size()) {      // 순열을 모두 구했으면
            calculate();                // 최댓값 계산
            return;
        }

        for (int i = 0; i < 10; ++i) {
            if (!select[i]) {
                select[i] = true;
                map.put(alpha.get(cnt), i); // map에 key는 알파벳, 현재 단어로 순열을 차곡차곡 쌓아준다.
                perm(cnt+1);
                select[i] = false;
            }
        }
    }

    // 최댓값 계산
    private static void calculate() {
        int sum = 0;

        for (int i = 0; i < N; ++i) {
            int temp = 0;
            for (char c: verbs[i]) {
                temp *= 10;             // 자릿수마다 10 곱합
                temp += map.get(c);     // c를 map에서 찾아서 해당 숫자를 더해준다.
            }
            sum += temp;
        }
        max = Math.max(sum, max);
    }
}
