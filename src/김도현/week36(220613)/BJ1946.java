package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ1946 {
    /* 1946. 신입사원
    정렬
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<int[]> list = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                list.add(new int[] {
                        Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
                });
            }
            list.sort(Comparator.comparing(ints -> ints[0]));

            int temp = N;
            for (int[] e:list) {
                if (e[1] > temp) N--;
                temp = Math.min(temp, e[1]);
            }
            System.out.println(N);
        }
    }
}
