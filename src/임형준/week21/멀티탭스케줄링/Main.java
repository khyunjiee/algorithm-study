package src.backjoon.멀티탭스케줄링;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * date: 22.01.24
 * 참조 링크 : https://loosie.tistory.com/484
 */

/*
풀이:

멀티탭에 꽂혀 있거나 멀티탭이 여유 있다면 꽂아준다.
멀티탭에 꽂혀 있는 것을 뽑아야 할 경우 가장 나중에 나온 것을 뽑아준다.
만약 꽂혀 있는 것이 나중에 나오지 않을 경우는 뒤에서 봤을 때 현재 꽂혀 있는 위치가 index 가 된다.
 */

public class Main {
    static int N,K;
    static List<Integer> list;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/멀티탭스케줄링/input.txt"));

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        list = new ArrayList<>();
        for(int i=0;i<K;i++) list.add(sc.nextInt());

        Set<Integer> set = new HashSet<>();
        int cnt = 0;


        for(int i=0;i<K;i++){
            // 이미 꼽혀있거나 여유가 있으면 꼽고 넘어감
            if(set.contains(list.get(i))) continue;
            if(set.size() < N){
                set.add(list.get(i));
                continue;
            }

            // 꼽혀있지 않을 때 누구를 뽑을 것인가
            // 더 근처에 있는 놈이 많이 사용될 것이므로, 나중에 사용되는 놈을 뽑아준다.
            int max = -1;
            int idx = -1;

            for(int plugIn : set){
                int temp;
                List<Integer> subList = list.subList(i + 1, K);
                // 뒤에 나오는 숫자가 꽂혀 있다면 해당 값
                if(subList.contains(plugIn)){
                    temp = subList.indexOf(plugIn);
                }else{
                    // 꽂혀 있지 않다면 뒤에서부터 세는 현재 값
                    temp = K-i;
                }

                if(temp > max){
                    max = temp;
                    idx = i;
                }
            }
            set.remove(idx);
            set.add(list.get(i));
            cnt++;
        }
        System.out.println(cnt);
    }
}
