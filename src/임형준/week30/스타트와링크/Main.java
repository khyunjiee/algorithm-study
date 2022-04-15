package src.backjoon.스타트와링크;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * date: 22.04.06
 */

public class Main {
    static int N;
    static int[][] arr;
    static boolean[] v;
    static int answer;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/스타트와링크/input.txt"));

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        v = new boolean[N];
        answer = Integer.MAX_VALUE;

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0,0);

        System.out.println(answer);
    }

    private static void comb(int cnt, int start) {
        if(cnt==N/2){
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();

            for(int i=0;i<N;i++){
                if(v[i]){
                    list1.add(i);
                }else{
                    list2.add(i);
                }
            }
            int cnt1= 0;
            int cnt2= 0;

            for(int i=0;i<N/2-1;i++){
                int a1 = list1.get(i);
                int b1 = list2.get(i);
                for(int j=i+1;j<N/2;j++){
                    int a2 = list1.get(j);
                    int b2 = list2.get(j);
                    cnt1 += arr[a1][a2];
                    cnt1 += arr[a2][a1];

                    cnt2 += arr[b2][b1];
                    cnt2 += arr[b1][b2];
                }
            }

            if(Math.abs(cnt1-cnt2) == 0){
                System.out.println(0);
                System.exit(0);
            }

            answer = Math.min(answer,Math.abs(cnt1-cnt2));
            return ;
        }

        for(int i=start;i<N;i++){
            v[i] = true;
            comb(cnt+1,i+1);
            v[i] = false;
        }
    }

    private static void print() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
