package baekjoon.aug.aug15;

import java.util.*;

/**
 * 백준 1068 트리
 *
 * 접근 방법:
 * 0번 노드가 무조건 루트 노드가 아닐 수도 있고, 루트 노드가 여러개일 수도 있다는 것을 주의해야 한다.
 * 들어오는 순서대로 해당 노드의 부모 노드를 배열에 저장한다.
 * 그 후 지워야하는 노드 번호를 입력받으면 remove() 메소드를 실행한다.
 * 해당 메소드는 매개변수로 들어오는 인덱스의 배열 값을 -2로 초기화하고
 * 그 인덱스를 부모로하는 모든 배열 노드에 대해서 remove 메소드를 재귀 호출한다.
 * 해당 방식으로 지울 노드의 자식들까지 모두 -2로 초기화한 후, 리프 노드를 찾는다.
 * 리프 노드는 해당 인덱스의 값이 -2가 아닌 것 중에서, 부모가 아닌 것을 찾는다.
 *
 * 시간 복잡도 :
 * O(N^2)
 *
 */

public class b1068 {

    static int[] parents;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        parents = new int[N];

        // 해당 인덱스의 부모 인덱스를 배열에 저장
        for (int i = 0; i < N; ++i) {
            parents[i] = sc.nextInt();
        }

        int removeIndex = sc.nextInt();
        remove(removeIndex);

        int result = 0;

        for (int i = 0; i < parents.length; ++i) {
            if (parents[i] >= -1) { // 값이 -2가 아니라면 (삭제되지 않았다면)
                int cnt = 0;
                for (int j = 0; j < parents.length; ++j) {
                    if (parents[j] == i) cnt++; // 해당 인덱스가 부모라면 cnt가 0 이상
                }
                if (cnt == 0) result++;
            }
        }
        System.out.println(result);
    }

    // 자식들을 모두 삭제
    private static void remove(int index) {
        parents[index] = -2;    // 매개변수로 드러오는 인덱스 값을 -2로 저장

        // 매개변수 인덱스 노드를 부모로 하는 모든 인덱스에 대해 재귀
        for (int i = 0; i < parents.length; ++i) {
            if (parents[i] == index) {
                remove(i);
            }
        }
    }
}
