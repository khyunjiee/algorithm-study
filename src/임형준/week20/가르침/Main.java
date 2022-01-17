package src.backjoon.가르침;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * date : 22.01.11
 * link : https://www.acmicpc.net/problem/1062
 * 풀이:
 *
 * 1. anta tica-> antic 5가지 알파벳을 제외한 21가지 알파벳으로 조합을 생성
 * 2. 만들어진 조합으로 set 을 만들어서 일치 여부 확인
 */
public class Main {
    static int N, K;
    static String[] arr ;
    static int[] temp;
    // anta tica antic
    static int maxCnt;
    static char[] basics = "antic".toCharArray();
    static char[] alphas = "bdefghjklmopqrsuvwxyz".toCharArray();
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/가르침/input.txt"));

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        arr = new String[N];
        maxCnt = 0;

        if(K<5){
            System.out.println(0);
        }else{
            temp = new int[K-5];

            for(int i=0;i<N;i++){
                arr[i] = sc.next();
            }
            comb(0,0);
            System.out.println(maxCnt);
        }

    }

    private static void comb(int cnt, int start) {
        if(cnt == K-5){
            Set<Character> set = new HashSet<>();
            for (char basic : basics) set.add(basic);
            for(int n : temp) set.add(alphas[n]);

            int count=0;

            a:for(int i=0;i<N;i++){
                for(char c : arr[i].toCharArray()){
                    if(!set.contains(c)) continue a;
                }
                count++;
            }
            maxCnt = Math.max(maxCnt,count);

            return;
        }
        for(int i=start;i<21;i++){
            temp[cnt] = i;
            comb(cnt+1,i+1);
        }

    }

}
