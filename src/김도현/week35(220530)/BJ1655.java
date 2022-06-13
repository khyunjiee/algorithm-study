package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BJ1655 {
    /* 1655. 가운데를 말해요
    우선순위 큐(더위사냥, 쌍쌍바)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> lef = new PriorityQueue<>(Comparator.comparing(Integer::intValue).reversed());
        PriorityQueue<Integer> rig = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        int first, second;
        if (N == 1) sb.append(br.readLine()).append('\n');
        else {
            first = Integer.parseInt(br.readLine());
            second = Integer.parseInt(br.readLine());
            if (first < second) {
                lef.add(first);
                rig.add(second);
            }else {
                lef.add(second);
                rig.add(first);
            }
            sb.append(Integer.toString(first) + '\n' + lef.peek().toString() + '\n');
            for (int i = 2; i < N; i++) {
                first = Integer.parseInt(br.readLine());
                if (first < rig.peek()) {
                    if (lef.size() <= rig.size()) lef.add(first);
                    else if (first < lef.peek()) {
                        rig.add(lef.poll());
                        lef.add(first);
                    }else rig.add(first);
                }else {
                    if (lef.size() > rig.size()) rig.add(first);
                    else {
                        lef.add(rig.poll());
                        rig.add(first);
                    }
                }
                sb.append(lef.peek().toString() + '\n');
            }
        }
        System.out.println(sb);
    }
}
