package baekjoon.aug.aug28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 1774 우주신과의 교감
 *
 * 접근 방식:
 * 최소 신장 트리(MST) 를 구하는 문제입니다.
 * 크루스칼 알고리즘을 사용해서 풀었습니다.
 * 문제 상으로 이미 간선으로 연결된 정점들이 있기 때문에 프림 알고리즘으로 풀면 반례가 생깁니다.
 * 프림 알고리즘이 정점 위주의 알고리즘이어서 그런 것 같습니다.
 * 수업에서 배운 크루스칼 알고리즘을 비슷하게 활용했고, 이미 연결된 간선들은 union 작업을 해주었습니다.
 *
 * 시간 복잡도:
 * 잘 모르겠네요..
 */

public class b1774 {

    static class Edge implements Comparable<Edge> {
        int from, to;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    static int N, M, parents[];
    static double result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());   // 정점 개수
        M = Integer.parseInt(st.nextToken());   // 이미 연결된 간선 개수

        double[][] locations = new double[N+1][2];
        List<Edge> edgeList = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            locations[i][0] = Integer.parseInt(st.nextToken());
            locations[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i+1; j <= N; j++) {
                edgeList.add(new Edge(i, j, distance(locations[i][0], locations[j][0], locations[i][1], locations[j][1])));
            }
        }

        Collections.sort(edgeList);
        make();

        int cnt = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (union(a, b)) cnt++;
        }

        for (Edge e: edgeList) {
            if (cnt == N-1) break;
            if (union(e.from, e.to)) {
                result += e.weight;
                cnt++;
            }
        }

        System.out.println(String.format("%.2f", result));
    }

    private static void make() {
        parents = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;
        else {
            parents[bRoot] = aRoot;
            return true;
        }
    }

    private static double distance(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(Math.abs(x1-x2), 2) + Math.pow(Math.abs(y1-y2), 2));
    }

}
