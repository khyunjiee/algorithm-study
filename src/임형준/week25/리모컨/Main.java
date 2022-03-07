package src.backjoon.리모컨;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * date: 22.03.03
 *
 * sol) 1~999999 범위를 완전탐색한다.
 * set을 이용해서 리모컨에 없는 숫자면 continue 해준다.
 */

public class MainV2 {
    static int N,M;
    static String init;
    static Set<Integer> set;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/리모컨/input.txt"));

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

         set = new HashSet<>();

        for(int i=0;i<M;i++){
            int n = sc.nextInt();
            set.add(n);
        }

        init = String.valueOf(N);

        int min = Integer.MAX_VALUE;

        if(set.size() == 10){
            System.out.println(Math.abs(N-100));
            return;
        }
        int targetLength=0;
        for(int target=0;target<=999999;target++){
            if(verify(target)){
                int dif = Math.abs(target-N);
                if(min>dif){
                    min = dif;
                    targetLength = String.valueOf(target).length();
                }
            }
        }
        // 여기서 실수. N의 길이를 더해주는게 아니라, 입력한 수 만큼을 더해주어야 한다.
        min += targetLength;

        int min2 = Math.abs(N-100);

        int result = Math.min(min,min2);

        System.out.println(result);
    }

    static boolean verify(int num){
        String s = String.valueOf(num);
        for(int i=0;i<s.length();i++){
            int n = s.charAt(i) - '0';
            if(set.contains(n)) return false;
        }
        return true;
    }
}
