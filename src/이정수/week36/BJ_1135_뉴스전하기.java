package week36;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제:
 * 링크:
 * 
 * 풀이:
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class BJ_1135_뉴스전하기 {
	
	static List<Integer>[] tree;
	static int[] numOfSubordinates;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		tree = new ArrayList[N];
		numOfSubordinates = new int[N];
		
		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
			int parent = Integer.parseInt(st.nextToken());
			if(i==0) continue;
			tree[parent].add(i);
		}
		
		System.out.println(dfs(0));
	}

	private static int dfs(int curr) {
		int max=0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
		
        for (Integer next : tree[curr]) {
            numOfSubordinates[next] = dfs(next);
            pq.add(new int[]{next,numOfSubordinates[next]});
        }
 
        
        int cnt =0;
        while (!pq.isEmpty()){
            int[] node = pq.poll();
            cnt++;
            max = Math.max(max,node[1]+cnt);
        }
 
        return max;
	}

}
