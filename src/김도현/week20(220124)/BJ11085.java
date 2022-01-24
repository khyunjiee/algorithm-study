package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ11085 {
    /* 11085. 군사이동
    Dijkstra
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        ArrayList<int[]>[] nodeList = new ArrayList[p];
        for (int i = 0; i < p; i++) nodeList[i] = new ArrayList<>();
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            nodeList[A].add(new int[]{B, D});
            nodeList[B].add(new int[]{A, D});
        }

        int[] width = new int[p];
        boolean[] visisted = new boolean[p];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));

        pq.offer(new int[]{c, 1000});
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
//            System.out.println(Arrays.toString(cur));
//            System.out.println(Arrays.toString(width));
//            System.out.println(Arrays.toString(visisted));
            int index = cur[0];
            int val = cur[1];
            if (index == v) break;
            if (visisted[index]) continue;
            visisted[index] = true;

            for (int[] node:nodeList[index]) {
                int temp = Math.min(val, node[1]);
                if (width[node[0]] < temp) {
                    width[node[0]] = temp;
                    pq.offer(new int[]{node[0], temp});
                }
            }
        }
        System.out.println(width[v]);
    }
}
