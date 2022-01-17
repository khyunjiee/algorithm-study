package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class bj1062 {
    /* 1062. 가르침
    조합,set => 시간초과 => 비트마스킹
     */
    public static int result = 0;
    public static int[] chars;
    public static HashSet<Integer> totalChars;

    public static void comb(Integer[] tot, int sel, int n, int r, int s, int cnt){
        if (cnt == r) {
            int count = 0;
            sel = sel | 1 | (1 << ('n' - 'a')) | (1 << ('t' - 'a'))  | (1 << ('c' - 'a')) | (1 << ('i' - 'a'));
            for (int temp:chars)
                if (temp == (temp & sel)) count++; // 조합을 통해 선택한 문자 set이 단어의 문자 set을 포함하는지
            result = Math.max(result, count);
            return;
        }

        for (int i = s; i < n; i++) {
            sel = sel | (1 << tot[i]);
            comb(tot, sel, n, r, i + 1, cnt + 1);
            sel = sel ^ (1 << tot[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()) - 5;
        if (K < 0) {
            System.out.println(0);
            return;
        }
        chars = new int[N];
        totalChars = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            char[] word = str.substring(4, str.length() - 4).toCharArray();
            for (char ch:word) {
                chars[i] = chars[i] | (1 << (ch - 'a'));    // 각 단어에 포함된 문자 set
                totalChars.add(ch - 'a');    // 단어들에 포함된 전체 문자 set
            }
        }
        if (K >= totalChars.size()) {
            System.out.println(N);
            return;
        }

        Integer[] tot = totalChars.toArray(new Integer[0]);
        int sel = 0;
        comb(tot, sel, totalChars.size(), K,0,0);
        System.out.println(result);
    }
}
