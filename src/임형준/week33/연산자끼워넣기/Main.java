package src.backjoon.연산자끼워넣기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int[] op;
    static int max;
    static int min;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/연산자끼워넣기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine()," ");
        op = new int[4];
        for(int i=0;i<4;i++){
            op[i] = Integer.parseInt(st.nextToken());
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        dfs(arr[0],1);
        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int beforeSum, int cnt) {
        if(cnt==N){
            max = Math.max(max,beforeSum);
            min = Math.min(min,beforeSum);
            return ;
        }

        for(int d=0;d<4;d++){
            if(op[d] > 0){
                op[d]--;

                switch (d){
                    case 0:
                        dfs(beforeSum + arr[cnt],cnt+1);
                        break;
                    case 1:
                        dfs(beforeSum - arr[cnt],cnt+1);
                        break;
                    case 2:
                        dfs(beforeSum * arr[cnt],cnt+1);
                        break;
                    case 3:
                        dfs(beforeSum / arr[cnt],cnt+1);
                        break;
                }
                op[d]++;
            }
        }
    }
}
