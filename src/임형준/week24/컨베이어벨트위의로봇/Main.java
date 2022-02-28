package src.backjoon.컨베이어벨트위의로봇;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * sol)
 *
 * 2개의 큐를 선언하고, 시물레이션을 따라가는데 왜 틀렸는 지 모르겠다..ㅠ
 */

public class Main {
    static int N,K;
    static int result;
    static Deque<Product> q1;
    static Deque<Product> q2;

    static class Product{
        int score;
        boolean isRobot;

        public Product(int score, boolean isRobot) {
            this.score = score;
            this.isRobot = isRobot;
        }

        @Override
        public String toString() {
            return "{" +
                    "score=" + score +
                    ", isRobot=" + isRobot +
                    '}';
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/컨베이어벨트위의로봇/input.txt"));

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        q1 = new LinkedList<>();
        q2 = new LinkedList<>();

        for(int i=0;i<N;i++){
            q1.offer(new Product(sc.nextInt(),false));
        }
        for(int i=0;i<N;i++){
            q2.offerFirst(new Product(sc.nextInt(),false));
        }

        while(true){
            print(result);
            result++;
            step1();
            step2();
            step3();
            if(step4()) break;
        }
        System.out.println(result);
    }

    private static void print() {
        System.out.println("q1");
        System.out.println(q1);
        System.out.println("q2");
        System.out.println(q2);
        System.out.println("====");
    }

    private static void print(int cnt) {
        System.out.println("cnt = " + cnt);
        System.out.println("q1");
        System.out.println(q1);
        System.out.println("q2");
        System.out.println(q2);
        System.out.println("====");
    }

    private static boolean step4() {
        int totCnt = 0;

        int size = N;
        while(size-->0){
            Product q1Poll = q1.poll();
            if(q1Poll.score == 0) totCnt ++;
            q1.offer(q1Poll);
        }
        size = N;
        while(size-->0){
            Product q2Poll = q2.poll();
            if(q2Poll.score == 0) totCnt ++;
            q2.offer(q2Poll);
        }

        return totCnt >= K;
    }

    private static void step3() {
        Product q1PollFront = q1.pollFirst();
        if(q1PollFront.score >=1){
            q1PollFront.score -=1;
            q1PollFront.isRobot = true;
        }
        q1.offerFirst(q1PollFront);
    }

    private static void step2() {

        int size = q1.size()-1;

        while(size-->0){
            Product q1Poll = q1.pollLast();

            if(q1Poll.score >= 1 && !q1Poll.isRobot){
                if(q1.peekLast().isRobot){
                    q1Poll.isRobot = true;
                    q1.peekLast().isRobot = false;
                    q1Poll.score -=1;
                }
            }
            q1.offerFirst(q1Poll);
        }
        q1.offerFirst(q1.pollLast());
    }

    private static void step1() {
        Product q1Poll = q1.pollLast();
        q1Poll.isRobot = false;

        q2.offerLast(q1Poll);
        q1.offerFirst(q2.pollFirst());
    }
}
