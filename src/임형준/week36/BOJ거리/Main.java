package 문제집.backjoon.BOJ거리;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dp;
    static char[] next;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/BOJ거리/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        String input = br.readLine();
        dp = new int[N];
        next = new char[]{'B','O','J'};
        Map<Character,Integer> map = new HashMap<>();
        map.put('B',0);
        map.put('O',1);
        map.put('J',2);
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0,0});
        int target = 0;

        while (!q.isEmpty()){

            while (!q.isEmpty() && q.peek()[0] == target){
                int[] poll = q.poll();

                int index = poll[1];

                for(int i=index+1;i<N;i++){
                    int cur = map.get(input.charAt(i));

                    if(cur == (target+1)%3){
                        dp[i] = Math.min(dp[i],dp[index] + ((i-index) * (i-index)));
                        q.offer(new int[]{cur,i});
                    }
                }
            }
            target = (target+1)%3;
        }
        System.out.println(dp[N-1] == Integer.MAX_VALUE ? -1 : dp[N-1]);
    }
}
