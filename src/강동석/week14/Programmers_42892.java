package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 길 찾기 게임
/*
 * 주어진 좌표들의 y값을 내림차순으로 정렬하면
 * 루트 노드부터 차례대로 다음 깊이의 노드들이 순서대로 정렬된다.
 * 루트노드에서 자식 노드들을 차례대로 연결하는 과정만 x값을 비교해가며 왼쪽인지 오른쪽인지 유의하면 된다.
 * 전위 순회와 후위 순회는 크게 어렵지 않았다.
 */
public class Programmers_42892 {

	public static void main(String[] args) {
		int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
		System.out.println(solution(nodeinfo));
	}
	
	static class Node implements Comparable<Node>{
		int num,x,y;
		Node left,right;
		
		public Node(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(o.y, this.y); // y에 대해 내림차순 정렬
		}
		
	}
	static int idx; // 정답 배열에 담을 위치를 정하는 인덱스
	static int[][] answer; // 정답 배열
	static List<Node> list = new ArrayList<>();
	
	public static int[][] solution(int[][] nodeinfo) {
		answer = new int[2][nodeinfo.length];
		int len = nodeinfo.length;
		for(int i=0; i<len; ++i) {
			list.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));
		}
		Collections.sort(list); // y값 기준 내림차순 정렬
		
		Node root = list.get(0); // 루트노드 생성
		for(int i=1; i<len; ++i) {
			Node node = list.get(i);
			linkNode(root, node); // 루트노드를 타고 아래로 내려가며 해당 노드의 자리 찾기
		}
		preOrder(root); // 전위 순회
		idx = 0; // 정답 배열에 저장한 인덱스 초기화
		postOrder(root); // 후위 순회
		
        return answer;
    }

	public static void linkNode(Node parent, Node child) { // 노드를 root에서 부터 내려가면서 알맞은 곳에 위치시킨다.
		if(parent.x < child.x) { // 부모보다 오른쪽이라면
			if(parent.right==null) { // 부모의 오른쪽 자식이 없으면
				parent.right = child;
			}else { // 부모의 오른쪽 자식이 있으면
				linkNode(parent.right, child); // 오른쪽 자식을 부모로 하여 다시 찾기
			}
		}else { // 부모보다 왼쪽이라면
			if(parent.left==null) { // 부모의 왼쪽 자식이 없으면
				parent.left = child;
			}else { // 부모의 왼쪽 자식이 있으면
				linkNode(parent.left, child); // 오른쪽 자식을 부모로 하여 다시 찾기
			}
		}
	}
	public static void preOrder(Node node) { // 전위 순회
		if(node!=null) { // 노드가 존재하면
			answer[0][idx++] = node.num; // 우선 해당 노드의 번호를 저장
			if(node.left!=null) preOrder(node.left); // 왼쪽 자식이 있으면 먼저 방문
			if(node.right!=null) preOrder(node.right); // 오른쪽 자식이 있으면 그다음 방문
		}
	}
	public static void postOrder(Node node) { // 후위 순회
		if(node!=null) { // 노드가 존재하면
			if(node.left!=null) postOrder(node.left); // 왼쪽 자식이 있으면 먼저 방문
			if(node.right!=null) postOrder(node.right); // 오른쪽 자식이 있으면 그다음 방문
			answer[1][idx++] = node.num; // 양쪽 자식 전부 방문 후 해당 노드의 번호를 저장
		}
	}
}