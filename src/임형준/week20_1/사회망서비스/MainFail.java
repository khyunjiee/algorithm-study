package src.backjoon.사회망서비스;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * date : 22.01.14
 * link : https://www.acmicpc.net/problem/2533
 */

/*
1. 자식 노드 중 단말 노드가 있다면 체크
2. 현재 loop<- i 에서 부모 노드가 체크 돼 있지 않다면 체크
 */

public class MainFail {
    static int N;

    static ArrayList<Integer>[] lists;
    static boolean[] visited;
    static int[] parentNode;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/사회망서비스/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        lists = new ArrayList[N+1];
        visited = new boolean[N+1];
        parentNode = new int[N+1];

        // 첫번째부터 시작
        for(int i=0;i<N+1;i++){
            lists[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++){
            int root = sc.nextInt();
            int child = sc.nextInt();
            lists[root].add(child);
            parentNode[child] = root;
        }

        ifHasLeafNode(1);
        ArrayList<Integer> rootNodesChild = lists[1];
        Queue<Integer> queue = new LinkedList<>(rootNodesChild);
        while(!queue.isEmpty()){
            Integer poll = queue.poll();

            ifHasLeafNode(poll);
            ifParentNodeNotChecked(poll);

            ArrayList<Integer> childes = lists[poll];
            queue.addAll(childes);
        }
        int cnt = 0;
        for(int i=1;i<N+1;i++){
            if(visited[i]) cnt++;
        }
        System.out.println(cnt);
    }

    private static void ifParentNodeNotChecked(Integer poll) {
        if(!visited[parentNode[poll]]) visited[poll] = true;
    }

    private static void ifHasLeafNode(Integer poll) {
        ArrayList<Integer> pollChildes = lists[poll];
        for(int child : pollChildes){
            if (lists[child].isEmpty()) {
                visited[poll] = true;
                break;
            }
        }
    }
}
