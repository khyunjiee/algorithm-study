package src.backjoon.색종이붙이기;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * date : 22.01.20
 * 참조 풀이 link : https://daily-life-of-bsh.tistory.com/141
 */

/*
풀이 :
0 to N으로 완전탐색을 한다.
색종이 큰거부터 작은거를 붙여봤을 때, 일단 붙여지면 거기서 dfs() 로 안으로 들어간다.
마지막에 도달했을 때 cnt 를 세준다.
dfs()를 들어갔을 때 해당 색종이를 표시해주었으므로, dfs()를 나올 때 색종이 표시를 해제해준다.
 */

public class Main {
    static final int N = 10;
    static int[][] map;
    static int[] paperCounts;
    static boolean[][] isVisited;
    static int result;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/색종이붙이기/input.txt"));

        Scanner sc = new Scanner(System.in);
        map = new int[N][N];
        paperCounts = new int[6];
        isVisited = new boolean[N][N];
        Arrays.fill(paperCounts,5);
        result = Integer.MAX_VALUE;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = sc.nextInt();
            }
        }
        dfs(0,0,0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void dfs(int r, int c, int cnt) {
        if(r>= N-1 && c> N-1 ){
            result = Math.min(cnt,result);
            return ;
        }
        if(result <= cnt ) return ;
        if(c > N-1){
            dfs(r+1,0,cnt);
            return ;
        }

        if(map[r][c] == 1){
            for(int p=5;p>=1;p--){
                if(paperCounts[p] >0 && checking(r,c,p)){
                    marking(r,c,p,0);
                    paperCounts[p]--;
                    dfs(r,c+1,cnt+1);
                    paperCounts[p]++;
                    marking(r,c,p,1);
                }
            }
        }else dfs(r,c+1,cnt);


    }

    static boolean checking(int r, int c, int range){
        for(int i=r;i<r+range;i++){
            for(int j=c;j<c+range;j++){
                if(isOut(i,j) || map[i][j] != 1) return false;
            }
        }
        return true;
    }

    static void marking(int r,int c, int range, int fill){
        for(int i=r;i<r+range;i++){
            for(int j=c;j<c+range;j++){
                map[i][j] = fill;
            }
        }
    }

    static void print(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(isVisited[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isOut(int nx, int ny){
        return nx<0 || ny<0 || nx>=N || ny>=N;
    }
}
