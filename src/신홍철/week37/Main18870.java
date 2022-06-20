import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] zipArr;
    static int n;
    static class DotInfo implements Comparable<DotInfo>{
        int no;
        int seq;

        public DotInfo(int no, int seq) {
            this.no = no;
            this.seq = seq;
        }

        @Override
        public int compareTo(DotInfo o) {
            return this.no > o.no ? 1 : -1;
        }
    }
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<DotInfo> pq = new PriorityQueue<>();
        DotInfo input;
        int num = 0, pre = Integer.MIN_VALUE;
        int[] answer = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0;i<n;i++){
            input = new DotInfo(Integer.parseInt(st.nextToken()),i);
            pq.add(input);
        }
        pre = pq.peek().no;
        DotInfo cur;
        num = 0;
        while(!pq.isEmpty()){
            cur = pq.poll();
            if(pre != cur.no) {
                pre = cur.no;
                num++;
            }
            answer[cur.seq] = num;
        }
        for(int ans : answer){
            sb.append(ans).append(" ");
        }
        System.out.println(sb);
    }
}
