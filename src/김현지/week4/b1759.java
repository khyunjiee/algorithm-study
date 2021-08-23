package baekjoon.aug.aug20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 백준 1759 암호 만들기
 * 
 * 접근 방법:
 * 순열로 풀었습니다.
 * 조건 중에 최소 한 개의 모음과 두 개 이상의 자음이 포함되어있어야 하는 것이 있어서
 * vows 리스트를 활용해서 모음 여부를 확인하면서 순열 원소를 추가시켜주었습니다.
 *
 * 시간 복잡도:
 * O(N!)
 * 순열적으로 N! 이지만, 앞의 선택한 원소를 선택하지 않으므로
 * 훨씬 적음.
 */

public class b1759 {

    static int L, C;
    static char alpha[];
    static boolean isSelect[];
    static StringBuilder sb;
    static List<Character> vows;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        L = sc.nextInt();   // 암호 길이 (순열 원소 개수)
        C = sc.nextInt();   // 입력으로 들어올 알파벳 개수
        alpha = new char[C];    // 입력으로 들어오는 알파벳 저장
        isSelect = new boolean[C];  // 순열 원소 선택 여부 저장

        // 배열 초기화
        for (int i = 0; i < C; i++) {
            alpha[i] = sc.next().charAt(0);
        }

        // 모음 character 리스트에 저장
        vows = new ArrayList<>();
        vows.add('a'); vows.add('e'); vows.add('i'); vows.add('u'); vows.add('o');

        // 사전 순으로 정렬
        // 순열에서 뒤의 원소가 앞의 원소보다 사전 순으로 빠르면 안된다.
        Arrays.sort(alpha);
        perm(0, 0, 0, 0, "");
        System.out.println(sb);
    }

    // cnt: 현재 원소 선택 개수, start: 시작할 인덱스, vow: 모음 개수, cons: 자음 개수, result: 현재까지 만든 암호 스트링
    private static void perm(int cnt, int start, int vow, int cons, String result) {
        // L개의 원소를 모두 구했으면 리턴
        if (cnt == L) {
            // 모음 1개 이상, 자음 2개 이상이면 암호 후보에 추가
            if (vow >= 1 && cons >= 2) sb.append(result + "\n");
            return;
        }

        // 순열을 구한다.
        for (int i = start; i < C; i++) {
            if (isSelect[i]) continue;  // 이미 선택된 원소면 continue

            isSelect[i] = true;     // 선택 여부 true
            if (vows.contains(alpha[i])) perm(cnt+1, i, vow+1, cons, result+alpha[i]);  // 선택한 원소가 모음이면 vow+1
            else perm(cnt+1, i, vow, cons+1, result+alpha[i]);                          // 선택한 원소가 자음이면 cons+1
            isSelect[i] = false;    // 선택 여부 false
        }
    }
}
