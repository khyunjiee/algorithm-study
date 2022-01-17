package src.backjoon.K번째수;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * memo : 못 풀어서, 풀이 참조했습니다
 *  * link : https://st-lab.tistory.com/281
 *  *
 *  * B[k] = x
 *  *
 *  * int mid = (start + end)/2
 *  *
 *  * mid 가 의미하는 것 = x를 찾는 변수
 *  *
 *  * mid 가 (start + end)/2 라고 가정
 *  * for i <- 1 to N
 *  *   count += Math.min( mid /i , N)
 *  *
 *  * count 가 K보다 작다면 mid 키워줘야 한다.
 *  *
 *  * 가장 처음에 등장하는 K값을 세는 것이기 때문에 lower bound 를 써준다.
 */

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/K번째수/input.txt"));

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int K = in.nextInt();

        int start = 1;
        int end = K;

        while(start < end){
            int middle = (start+end)/2;

            int count = 0;

            for(int i=1;i<=N;i++){
                count += Math.min(middle/i,N);
            }

            if(count >= K){
                end = middle;
            }else{
                start = middle+1;
            }
        }
        System.out.println(start);
    }
}
