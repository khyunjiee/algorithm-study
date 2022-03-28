package src.backjoon.결혼식;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * date : 22.03.21
 * memo : depth가 2까지만 dfs를 돌린다.
 */

public class Main {
    static int N;
    static int M;
    static ArrayList<Integer>[] listArr;
    static boolean[] checked;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/결혼식/input.txt"));

        Scanner sc = new Scanner(System.in);
        N  = sc.nextInt();
        M = sc.nextInt();
        listArr = new ArrayList[N+1];
        for(int i=1;i<N+1;i++){
            listArr[i] = new ArrayList<>();
        }

        checked = new boolean[N+1];
        for(int i=0;i<M;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            listArr[a].add(b);
            listArr[b].add(a);
        }

        dfs(1,0);
        System.out.println(Arrays.toString(checked));
        int cnt=0;
        for(boolean v : checked){
            if(v) cnt++;
        }
        System.out.println(cnt == 0 ? 0 : cnt-1);
    }

    private static void dfs(int target,int depth) {
        if(depth >= 2){
            return ;
        }

        for(int num : listArr[target]){
            checked[num] = true;
            dfs(num,depth+1);
        }
    }
}
