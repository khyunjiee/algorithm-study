package src.backjoon.거짓말;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * union - find를 이용해서 처음 거짓말인 것을 알고 있는 사람들을 다 연결해준다.
 * 이후 들어온 값들의 라인을 전부 연결해준다.
 * 들어온 값의 라인에 거짓말 인 것을 알고 있는 사람이 있다면 count에서 세지 않는다.
 */

public class Main {
    static int N,M,C;
    static int[] parents;
    static List<Integer> checkList;

    static void set(){
        parents = new int[N+1];

        for(int i=1;i<N+1;i++){
            parents[i] = i;
        }
    }
    static int find(int a){
        if(parents[a] == a) return a;
        else return parents[a]= find(parents[a]);
    }
    static void union(int a,int b){
        int aRoot = find(a);
        int bRoot = find(b);

        // 거짓말을 알고 있는 사람이 있다면 해당 사람을 root로 바꿔준다.
        if(checkList.contains(aRoot)){
            int tmp = aRoot;
            aRoot = bRoot;
            bRoot = tmp;
        }

        parents[bRoot] = aRoot;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/거짓말/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        C = sc.nextInt();
        checkList = new ArrayList<>();

        set();

        for(int i=0;i<C;i++){
            checkList.add(sc.nextInt());
        }
        for(int i=0;i<C-1;i++){
            union(checkList.get(i),checkList.get(i+1));
        }

        List<int[]> peoplesList = new ArrayList<>();
        int cnt = 0;

        for(int i=0;i<M;i++){
            int n = sc.nextInt();
            int[] peoples = new int[n];

            for(int j=0;j<n;j++){
                peoples[j] = sc.nextInt();
            }
            peoplesList.add(peoples);

            unionArrays(peoples);
        }

        a: for(int[] tmp : peoplesList){
            for (int k : tmp) {
                for (int c : checkList) {
                    if (find(c) == find(k)) {
                        continue a;
                    }
                }
            }
            cnt++;
        }
        System.out.println(cnt);
    }

    private static void unionArrays(int[] peoples) {
        for(int i=0;i< peoples.length-1;i++){
            union(peoples[i],peoples[i+1]);
        }
    }
}
