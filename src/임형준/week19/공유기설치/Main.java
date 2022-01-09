package src.backjoon.공유기설치;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * date : 22.01.07
 * link : https://www.acmicpc.net/problem/2110
 * sol)
 *
 * 공유기 탐색 가능한 수의 상한을 이진 탐색으로 구한다.
 *
 * 시간복잡도 O(n^2)
 *
 * 도움링크 : https://st-lab.tistory.com/277
 */

public class Main {
    static int N,C;
    static int[] arr;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/공유기설치/input.txt"));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        C = sc.nextInt();
        arr = new int[N];

        for(int i=0;i<N;i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        int binary = getBinary();
        System.out.println(binary);
    }

    private static int getBinary() {
        int low = 1;
        int high = arr[N-1] - arr[0] + 1;

        while(low < high){
            int middle = (low+high)/2;

            if(install(middle) < C){
                high = middle;
            }else{
                low = middle+1;
            }
        }
        return low-1;
    }

    // 거리를 middle 로 0번부터 공유기를 설치한 숫자
    private static int install(int middle) {
        int count = 1;
        int before = arr[0];

        for(int i=1;i<N;i++){
            if(arr[i] - before >= middle){
                count++;
                before = arr[i];
            }
        }
        return count;
    }
}
