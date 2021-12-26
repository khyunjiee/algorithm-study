package PG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


/*
 * Level3. 표 편집
*/

public class PG81303 {
	
	static class Node {
		boolean state;
		Node prev, next;
		public Node() {
			this.state = true;
			this.prev = null;
			this.next = null;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Node[] nodeList = new Node[n];
		for (int i = 0; i < n; i++) nodeList[i] = new Node();
		for (int i = 1; i < n; i++) {
			nodeList[i-1].next = nodeList[i];
			nodeList[i].prev = nodeList[i-1];
		}
		st = new StringTokenizer(br.readLine(), ",");
		String str;
		int temp;
		Node cur = nodeList[k], undo;
		Stack<Node> stack = new Stack<>();
		while (st.hasMoreElements()) {
			str = st.nextToken();
			if (str.charAt(0) == 'U') {
				temp = Integer.parseInt(str.substring(2));
				for (int i = 0; i < temp; i++) cur = cur.prev;
			} else if (str.charAt(0) == 'D') {
				temp = Integer.parseInt(str.substring(2));
				for (int i = 0; i < temp; i++) cur = cur.next;
			} else if (str.charAt(0) == 'C') {
				cur.state = false;
				stack.push(cur);
				if (cur.prev != null) cur.prev.next = cur.next;
				if (cur.next != null) {
					cur.next.prev = cur.prev;
					cur = cur.next;
				} else cur = cur.prev;
			} else {
				undo = stack.pop();
				undo.state = true;
				if (undo.prev != null) undo.prev.next = undo;
				if (undo.next != null) undo.next.prev = undo;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (Node node : nodeList) {
			if (node.state) sb.append('O');
			else sb.append('X');
		}
		System.out.println(sb.toString());
		
	}

}
