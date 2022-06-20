package 문제집.backjoon.좌표압축;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.*;

/**
 * date: 22.06.19
 */

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/좌표압축/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Integer[] orderArr = arr.clone();
        int[] sequence = new int[N];
        Arrays.sort(orderArr);
        Map<Integer,Integer> map = new HashMap<>();
        int p = 0 ;
        for(int i=0;i<N;i++){
            if(map.containsKey(orderArr[i])){
                continue;
            }
            map.put(orderArr[i],p++);
        }
        
        for(int i=0;i<N;i++){
            sequence[i] = map.get(arr[i]);
        }
        StringBuilder sb = new StringBuilder();
        for(int n : sequence){
            sb.append(n).append(" ");
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb);
    }
}
