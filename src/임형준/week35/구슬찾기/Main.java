package 문제집.backjoon.구슬찾기;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * date: 22.05.29
 */

public class Main {
    static int N,M;
    static ArrayList<Integer>[] greater;
    static ArrayList<Integer>[] lower;
    static boolean[] v;
    static int cnt;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/구슬찾기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        greater = new ArrayList[N+1];
        lower = new ArrayList[N+1];
        for(int i=1;i<N+1;i++){
            greater[i] = new ArrayList<>();
            lower[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int big = Integer.parseInt(st.nextToken());
            int small = Integer.parseInt(st.nextToken());
            greater[big].add(small);
            lower[small].add(big);
        }

        List<Integer> res = new ArrayList<>();

        for(int i=1;i<N+1;i++){
            v = new boolean[N+1];
            cnt = 0;
            dfsGT(greater[i]);
            int gt = cnt;

            v = new boolean[N+1];
            cnt = 0;
            dfsLOW(lower[i]);
            int lt = cnt;

            if( gt >= (N+1)/2 || lt >= (N+1)/2 ){
                res.add(i);
            }
        }
        System.out.println(res.size());
    }

    private static void dfsGT(List<Integer> list) {
        if(list==null || list.isEmpty() ) return;
        for(int n : list){
            if(!v[n]){
                v[n] = true;
                cnt++;
                dfsGT(greater[n]);
            }
        }
    }
    private static void dfsLOW(List<Integer> list) {
        if(list==null || list.isEmpty() ) return;
        for(int n : list){
            if(!v[n]){
                v[n] = true;
                cnt++;
                dfsLOW(lower[n]);
            }
        }
    }
}
