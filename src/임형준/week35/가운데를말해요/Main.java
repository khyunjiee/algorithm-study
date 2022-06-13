package 문제집.backjoon.가운데를말해요;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * date: 22.05.26
 */

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/가운데를말해요/input.txt"));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<N;i++){
            int n = Integer.parseInt(br.readLine());

            if(i%2==0){
                maxHeap.offer(n);
            }else{
                minHeap.offer(n);
            }

            if(!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()){
                int minH = minHeap.poll();
                int maxH = maxHeap.poll();

                maxHeap.offer(minH);
                minHeap.offer(maxH);
            }

            sb.append(maxHeap.peek()).append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb);
    }
}
