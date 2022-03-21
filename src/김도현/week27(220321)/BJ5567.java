package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ5567 {
    /* 5567. 결혼식
    Set
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int a, b;
        HashMap<Integer, HashSet<Integer>> friends = new HashMap<>();
        for (int i = 1; i <= n; i++) friends.put(i, new HashSet<>());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            friends.get(a).add(b);
            friends.get(b).add(a);
        }

        HashSet<Integer> set = new HashSet<>(friends.get(1));
        for (int p: friends.get(1)) set.addAll(friends.get(p));
        set.remove(1);
        System.out.println(set.size());
    }
}
