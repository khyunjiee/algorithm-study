package src.backjoon.히스토그램;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * date: 22.04.04
 * memo: 틀린 풀이입니다. 수정 중...
 */

public class Main {
    static int N;
    static int[] arr;
    static int answer;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/히스토그램/input.txt"));
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        arr = new int[N];

        int min = Integer.MAX_VALUE;

        for(int i=0;i<N;i++){
            int value = Integer.parseInt(br.readLine());
            arr[i] = value;
            if(value!=0){
                min = Math.min(min,value);
            }
        }

        for(int i=0;i<N;i++){
            int value = arr[i];

            calStack(stack, value);
            stack.push(i);
        }

        if(!stack.isEmpty()){
            int size = stack.size();
            int init=0;
            while(!stack.isEmpty()){
                if(stack.size() == 1){
                    init = stack.pop();
                    break;
                }
                stack.pop();
            }
            answer = Math.max(answer,arr[init] * size);
        }

        System.out.println("min = " + min);
        answer = Math.max(answer,min*N);

        System.out.println(answer);
    }

    private static void calStack(Stack<Integer> stack, int value) {
        if(!stack.isEmpty()){
            int peek = stack.peek();
            if(peek > value){
                int size = stack.size();
                int init=0;
                while(!stack.isEmpty()){
                    if(stack.size() == 1){
                        init = stack.pop();
                        break;
                    }
                    stack.pop();
                }
                answer = Math.max(answer,arr[init] * size);
            }
        }
    }
}
