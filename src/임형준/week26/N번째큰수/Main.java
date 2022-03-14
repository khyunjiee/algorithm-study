package src.backjoon.N번째큰수;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * date :22.03.11
 */

public class Main {
    static int N;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/N번째큰수/input.txt"));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0;i<N*N;i++){
            int num = sc.nextInt();
            if(pq.size() == N){
                if(num > pq.peek()){
                    pq.poll();
                    pq.offer(num);
                }
            }else{
                pq.offer(num);
            }
        }

        System.out.println(pq.poll());
    }
}
