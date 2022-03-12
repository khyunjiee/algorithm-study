package SK;

import java.util.*;

public class One {

    public static void main(String[] args) {
        System.out.println(solution(4578, new int[]{1, 4, 99, 35, 50, 1000}));
    }

    public static int solution(int money, int[] costs) {
        int[] won = {1, 5, 10, 50, 100, 500};
        PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1[1], o2[1]));
        int answer = 0;

        for (int i = 0; i < costs.length; i++) {
            pq.offer(new double[]{i, (double) costs[i] / won[i]});
        }

        while(!pq.isEmpty()){
            if(money==0)
                break;

            int produce = 0;
            int idx = (int)pq.poll()[0];

            produce = money/won[idx];
            answer+=produce*costs[idx];
            money-=produce*won[idx];
        }

        return answer;
    }
}
