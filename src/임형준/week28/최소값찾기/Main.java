package src.backjoon.최소값찾기;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * date: 22.03.23
 */

public class Main {
    static int N;
    static int L;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/최소값찾기/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N+1];

        Deque<Integer> deque = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine()," ");
        for(int i=1;i<N+1;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1;i<N+1;i++){
            int target = arr[i];
            while(!deque.isEmpty() && arr[deque.peekLast()] > target){
                deque.pollLast();
            }
            deque.offer(i);
            if(!deque.isEmpty() && deque.peekFirst() <= i-L){
                deque.pollFirst();
            }
            bw.write(arr[deque.peekFirst()] + " ");
        }

        bw.flush();
        bw.close();
    }
}
