package src.backjoon.군사이동;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * date: 22.01.22
 * 참조풀이 : https://sorjfkrh5078.tistory.com/306
 */

/*
크루스칼을 써서 가장 긴 간선부터 정렬해준다.
만약 start end 가 연결됐다면 멈추고 해당 값을 출력해준다.
 */

public class Main {
    static int N,edgeCount;
    static int startPoint, endPoint;

    static int[][] matrix;
    static boolean[] visited;

    static int[] parents;

    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return o.weight - this.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/군사이동/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine()," ");
        startPoint = Integer.parseInt(st.nextToken());
        endPoint = Integer.parseInt(st.nextToken());

        parents = new int[N];

        matrix = new int[N][N];
        visited = new boolean[N];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for(int i=0;i<edgeCount;i++){
            st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(start,end,weight));
        }

        set();

        while(!pq.isEmpty()){
            Edge poll = pq.poll();

            union(poll.start,poll.end);
            if(isUnion(startPoint,endPoint)){
                System.out.println(poll.weight);
                break;
            }
        }
    }

    static void set(){
        for(int i=0;i<N;i++) parents[i] = i;
    }
    static int find(int a){
        if(parents[a] == a ) return a;
        else return parents[a] = find(parents[a]);
    }
    static boolean isUnion(int a, int b){
        return find(a) == find(b);
    }
    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        parents[aRoot] = bRoot;
    }

    private static void print() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
