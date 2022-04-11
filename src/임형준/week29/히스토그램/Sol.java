package src.backjoon.히스토그램;

import java.io.*;
import java.util.Stack;

/**
 * memo: https://loosie.tistory.com/305 <-- 해당 링크의 풀이입니다
 */

public class Sol {

    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("./src/backjoon/히스토그램/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n =  Integer.parseInt(br.readLine());
        arr = new int[n+2];
        for(int i=1; i<n+1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> s = new Stack<>();
        s.push(0);
        int ans =0;
        for(int i=1; i<n+2; i++) {
            while(!s.isEmpty()) {
                int top = s.peek();
                if(arr[top] <= arr[i]) break;
                s.pop();
                ans = Math.max(ans, arr[top]*(i-s.peek()-1));
            }
            s.push(i);
        }
        System.out.println(ans);
    }
}
