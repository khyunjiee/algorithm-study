package src.backjoon.이차원배열과연산;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * date: 22.05.08
 *
 * 관련 내용을 블로그에 정리했습니다
 * 정리 링크 : https://camel-man-ims.tistory.com/39
 */

public class Main {
    static int r,c,k;
    static int[][] map;
    static int answer;
    static int rowMaxCnt;
    static int colMaxCnt;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/이차원배열과연산/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        answer = 0;
        map = new int[100][100];
        colMaxCnt = 3;
        rowMaxCnt = 3;

        Comparator<int[]> comp = (o1, o2) -> {
            int cnt1 = o1[1];
            int cnt2 = o2[1];

            if(cnt1 == cnt2){
                return Integer.compare(o1[0],o2[0]);
            }else{
                return Integer.compare(o1[1],o2[1]);
            }
        };

        for(int i=0;i<3;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<3;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Map<Integer,Integer> hashMap;
        PriorityQueue<int[]> pq;
        int time =0;
        while(time<=100){
            if(map[r-1][c-1]==k){
                break;
            }
            hashMap = new HashMap<>();
            pq = new PriorityQueue<>(comp);
            // 행의 개수가 열의 개수보다 많거나 같으면
            if(rowMaxCnt >= colMaxCnt){
                for(int i=0;i<rowMaxCnt;i++){
                    hashMap.clear();
                    for(int j=0;j<100;j++){
                        if(map[i][j]!=0){
                            hashMap.put(map[i][j],hashMap.getOrDefault(map[i][j],0)+1);
                        }
                    }
                    Set<Integer> keys = hashMap.keySet();

                    for(int key : keys) {
                        pq.offer(new int[]{key, hashMap.get(key)});
                    }

                    int size = pq.size();
                    if(size>=50){
                        size=50;
                    }
                    colMaxCnt = Math.max(colMaxCnt,size*2);

                    for(int j=0;j<size*2;j+=2){
                        int[] poll = pq.poll();

                        map[i][j] = poll[0];
                        map[i][j+1] = poll[1];
                    }
                    for(int j=size*2;j<100;j++){
                        map[i][j] = 0;
                    }
                }
            }else{
                for(int j=0;j<colMaxCnt;j++){
                    hashMap.clear();
//                    int p = 0;
//                    while(p< 100 && map[p][j] != 0){
//                        p++;
//                    }
                    for(int i=0;i<100;i++){
                        if(map[i][j] != 0){
                            hashMap.put(map[i][j], hashMap.getOrDefault(map[i][j],0)+1);
                        }
                    }
                    Set<Integer> keys = hashMap.keySet();

                    for(int key : keys) {
                        pq.offer(new int[]{key, hashMap.get(key)});
                    }
                    int size = pq.size();
                    if(size >= 50){
                        size = 50;
                    }
                    rowMaxCnt = Math.max(rowMaxCnt,size*2);

                    for(int i=0;i<size*2;i+=2){
                        int[] poll = pq.poll();

                        map[i][j] = poll[0];
                        map[i+1][j] = poll[1];
                    }
                    for(int i=size*2;i<100;i++){
                        map[i][j] = 0;
                    }
                }
            }
            time++;
        }
        System.out.println(time == 101 ? -1 : time);
    }

    private static void print() {
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.print(map[i][j] +" ");
            }
            System.out.println();
        }
    }
}
