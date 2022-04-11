package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ12912 {
    /* 12912. 트리 수정
    트리의 지름, 임의의 x에서 가장 먼 노드 y => y에서 가장 먼 노드 z => y와 z사이의 거리가 지름
    */
    static int N, M;
    static boolean[] visited;
    static long dist, totalDist, result = 0;
    static ArrayList<HashMap<Integer,Long>> nodeList, temp;
    static HashMap<Integer,HashSet<Integer>> map = new HashMap<>();
    
    public static void findDiameter() {
        HashMap<Integer,Long> nodes;
        int to; long cost;
        for (int from = 0; from < N; from++) {
            nodes = nodeList.get(from);
            for (Map.Entry<Integer,Long> entry:nodes.entrySet()) {
                to = entry.getKey();
                cost = entry.getValue();
                if (map.get(from).contains(to)) continue;
//                System.out.println(from+" "+to+" "+cost);
                map.get(from).add(to);
                map.get(to).add(from);

                temp.get(from).remove(to);
                temp.get(to).remove(from);
                totalDist = cost;

                M = from;
                Arrays.fill(visited, false);
                dist = 0;
                findNode(from, 0);
                Arrays.fill(visited, false);
                dist = 0;
                findNode(M, 0);
                totalDist += dist;
//                System.out.println("first:"+dist);

                M = to;
                Arrays.fill(visited, false);
                dist = 0;
                findNode(to, 0);
                Arrays.fill(visited, false);
                dist = 0;
                findNode(M, 0);
//                System.out.println("second:"+dist);
                totalDist += dist;
//                System.out.println("total:"+totalDist);

                result = Math.max(result, totalDist);
                temp.get(from).put(to,cost);
                temp.get(to).put(from,cost);
            }
        }
    }

    public static void findNode(int node, long cost) {
        visited[node] = true;
        if (cost > dist) {
            M = node;
            dist = cost;
        }
        int key; long val;
        for (Map.Entry<Integer,Long> entry:temp.get(node).entrySet()) {
            key = entry.getKey();
            val = entry.getValue();
//            System.out.println("node:"+node+", key:"+key+", val:"+val);
            if (visited[key]) continue;
            findNode(key,cost+val);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int from, to;
        long cost;
        visited = new boolean[N];
        for (int i = 0; i < N; i++) map.put(i, new HashSet<>());
        nodeList = new ArrayList<>();
        temp = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            nodeList.add(new HashMap<>());
            temp.add(new HashMap<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Long.parseLong(st.nextToken());
            nodeList.get(from).put(to,cost);
            nodeList.get(to).put(from,cost);
            temp.get(from).put(to,cost);
            temp.get(to).put(from,cost);
        }
        findDiameter();
        System.out.println(result);
    }
}
