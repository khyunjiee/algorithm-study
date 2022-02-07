package src.backjoon.최소스패닝트리;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * date : 22.02.06
 */

public class Main {
    static class Edge implements Comparable<Edge>{
        int start,end,weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight,o.weight);
        }
    }
    static int[] parents;

    private static void makeSet(){
        parents = new int[V+1];
        for(int i=1;i<V+1;i++){
            parents[i] = i;
        }
    }

    private static int find(int a){
        if(a==parents[a]) return a;
        return parents[a] = find(parents[a]);
    }
    private static boolean union(int a,int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }

    static int V,E;
    static Edge[] edgeList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/최소스패닝트리/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 간선리스트 작성
        edgeList = new Edge[E];

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(start,end,weight);
        }
        Arrays.sort(edgeList);

        makeSet(); // 모든 정점을 각각의 집합으로 만들고 출발
        int cnt=0;
        int result=0;
        for(Edge edge : edgeList){
            if(union(edge.start, edge.end)){
                result += edge.weight;
                if(++cnt == V-1) break;
            }
        }
        System.out.println(result);
    }
}
