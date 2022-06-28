package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ18870 {
    /* 18870. 좌표 압축
    정렬
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] sorted = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sorted[i] = arr[i];
        }
        Arrays.sort(sorted);
        int p = sorted[0];
        int q = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(p,q);
        for (int i = 1; i < N; i++) {
            if (sorted[i] == p) continue;
            map.put(sorted[i], ++q);
            p = sorted[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(map.get(arr[i])).append(" ");
        System.out.println(sb);
    }
}
