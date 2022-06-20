package 문제집.backjoon.AC;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * date: 22.05.31
 */

public class Main {
    static int T;
    static Deque<String> deque;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/AC/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        out: for(int t=0;t<T;t++){
            deque = new LinkedList<>();

            String rule = br.readLine();
            int N = Integer.parseInt(br.readLine());

            String s = br.readLine();

            String[] split = s.replace("[", "").replace("]", "").split(",");
            for(String i: split){
                deque.offer(i);
            }
            boolean dir = true;
            if(N==0){
                sb.append("error").append("\n");
                continue;
            }
            for(int i=0;i<rule.length();i++){
                char c = rule.charAt(i);
                if(c=='R'){
                    dir = !dir;
                }else{
                    if(deque.isEmpty()){
                        sb.append("error").append("\n");
                        continue out;
                    }else{
                        if(dir){
                            deque.poll();
                        }else{
                            deque.pollLast();
                        }
                    }
                }
            }
            sb.append("[");
            while(!deque.isEmpty()){
                if(dir){
                    sb.append(deque.poll());
                }else{
                    sb.append(deque.pollLast());
                }
                if(!deque.isEmpty()){
                    sb.append(",");
                }
            }
            sb.append("]").append("\n");
        }
        System.out.println(sb);
    }
}
