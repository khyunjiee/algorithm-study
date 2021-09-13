package programmers.sep.sep13;

import java.util.*;

/**
 * 프로그래머스 Lv.2 순위검색
 *
 * 접근 방식:
 * 지원자가 선택한 문항들이 입력으로 들어오면,
 * 해당 지원자가 해당되는 조건의 경우의 수를 모두 만듭니다.
 * 경우의 수를 Map 자료구조의 key로 집어넣고, 지원자의 점수를 해당 키값의 리스트에 추가합니다.
 * info를 모두 처리한 후에는 리스트를 오름차순 정렬하고, 쿼리문에 해당되는 범위를 찾기 위해 이분 탐색을 실시합니다.
 * 이분 탐색이 아닌 전체 탐색을 할 경우는 시간 초과가 발생합니다.
 *
 * 시간 복잡도:
 * 경우의 수를 확인할 때, 모든 참여자에 대해서 dfs를 진행하므로
 * 50,000 * 2^4 가 될 것 같습니다.
 *
 * 소요 시간:
 * 1시간 30분
 */

public class p72412 {
    public static void main(String[] args) {
        Solution_p72412 s = new Solution_p72412();
        int[] result = s.solution(new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"});
        System.out.println(Arrays.toString(result));
    }
}

// 실제 솔루션 부분
class Solution_p72412 {
    // 모든 경우의 수를 저장할 Map
    Map<String, List<Integer>> employees = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        makeStr(info);  // map을 완성합니다

        return executeQuery(query); // 쿼리를 실행한 결과를 return합니다.
    }

    // map을 완성시키고 각 list를 모두 오름차순 정렬합니다.
    void makeStr(String[] info) {
        // 모든 경우의 수를 map에 추가
        for (int i = 0; i < info.length; i++) {
            concatStr("", 0, info[i].split(" "));
        }

        // 해당 키 값에 해당하는 리스트를 모두 오름차순 정렬
        for(String key: employees.keySet()) {
            Collections.sort(employees.get(key));
        }
    }

    // 해당 info의 모든 경우의수를 map에 추가합니다.
    void concatStr(String str, int cnt, String[] info) {
        if (cnt == 4) { // depth가 4라면
            // 여태까지 구한 str이 key중에 없다면 새로 생성
            if (!employees.containsKey(str)) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(info[4]));    // 현재 지원자의 점수를 list에 추가
                employees.put(str, list);
            }
            // 여태까지 구한 str이 key에 있다면
            else {
                employees.get(str).add(Integer.parseInt(info[4]));  // 현재 지원자의 점수를 list에 추가
            }
            return;
        }

        // 재귀 호출. - 인 경우와 조건이 포함되었을 경우 모두 체크
        concatStr(str+"-", cnt+1, info);
        concatStr(str+info[cnt], cnt+1, info);
    }

    // 쿼리를 실행한다.
    int[] executeQuery(String[] query) {
        int[] result = new int[query.length];   // 결과 배열
        for (int i = 0; i < query.length; i++) {
            String[] q = query[i].split(" ");
            String queryStr = q[0]+q[2]+q[4]+q[6];
            result[i] = countEmployee(queryStr, Integer.parseInt(q[7]));
        }

        return result;
    }

    int countEmployee(String key, int score) {
        if (!employees.containsKey(key)) return 0;

        List<Integer> list = employees.get(key);
        int start = 0;
        int end = list.size()-1;

        while (start <= end) {
            int mid = (start+end)/2;
            if (list.get(mid) < score) start = mid+1;
            else end = mid-1;
        }

        return list.size()-start;
    }
}