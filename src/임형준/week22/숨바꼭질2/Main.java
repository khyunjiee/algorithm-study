package src.backjoon.숨바꼭질2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * date : 22.02.06
 * 링크 : https://rujang.tistory.com/entry/%EB%B0%B1%EC%A4%80-12851%EB%B2%88-%EC%88%A8%EB%B0%94%EA%BC%AD%EC%A7%88-2-CC
 */

public class Main {
    static class Info{
        int current;
        int cnt;

        public Info(int current, int cnt) {
            this.current = current;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/숨바꼭질2/input.txt"));

        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int end = sc.nextInt();

        boolean[] visited = new boolean[100001];
        Queue<Info> queue = new LinkedList<>();
        queue.offer(new Info(start,0));

        int result=99999999;
        int totalCnt= 0;

        while(!queue.isEmpty()){
            Info poll = queue.poll();
            int cur = poll.current;
            int cnt = poll.cnt;
            visited[cur] = true;

            if(result < cnt) break;
            if(cur == end) {
                result = cnt;
                totalCnt +=1;
                continue;
            }

            if(cur-1 >= 0  && !visited[cur-1]) {
                queue.offer(new Info(cur-1,cnt+1));
            }
            if( cur*2 <= 100000 && !visited[cur*2]) {
                queue.offer(new Info(cur*2,cnt+1));
            }
            if( cur+1 <= 100000 && !visited[cur+1]) {
                queue.offer(new Info(cur+1,cnt+1));
            }
        }
        System.out.println(result);
        System.out.println(totalCnt);
    }
}
