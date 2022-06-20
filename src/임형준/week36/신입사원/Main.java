package 문제집.backjoon.신입사원;

import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int N;
    static boolean[] v;
    static class Person implements Comparable<Person>{
        int s1;
        int s2;

        public Person(int s1,int s2){
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(this.s1, o.s1);
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/신입사원/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            N = Integer.parseInt(br.readLine());
            PriorityQueue<Person> pq = new PriorityQueue<>();
            for(int i=0;i<N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int s1 = Integer.parseInt(st.nextToken());
                int s2 = Integer.parseInt(st.nextToken());
                pq.offer(new Person(s1, s2));
            }
            
            int cnt = 1;
            Person poll = pq.poll();
            int min = poll.s2;
            
            while(!pq.isEmpty()){
                Person p = pq.poll();
                int s2 = p.s2;
                
                if(s2 < min){
                    cnt++;
                    min = s2;
                }
            }
            System.out.println(cnt);
        }
    }
}
