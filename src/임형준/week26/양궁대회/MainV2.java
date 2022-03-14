package src.프로그래머스.카카오기출.양궁대회;

import java.util.Arrays;

public class MainV2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] solution1 = solution.solution(10, new int[]{0,0,0,0,0,0,0,0,3,4,3});
        System.out.println(Arrays.toString(solution1));
    }

    static class Solution {
        static int[] user;
        static int[] infos;
        static int N;
        static int[] resultArr;
        static int maxScore;
        public int[] solution(int n, int[] info) {
            N = n;
            user = new int[11];
            infos = info.clone();
            maxScore = 0;
            resultArr = new int[]{-1};
            dfs(0);
            return resultArr;
        }

        private void dfs(int cnt) {
            if(cnt == N){
                int userScore =0 ;
                int infoScore =0;

                for(int i=0;i<=10;i++){
                    if( user[i] ==0 && infos[i] ==0 ) continue;
                    if(user[i] > infos[i]) userScore += 10-i;
                    else infoScore += 10-i;
                }
                if(userScore > infoScore && maxScore <= userScore - infoScore){
                    maxScore = userScore - infoScore;
                    resultArr = user.clone();
                }
                return ;
            }

            for(int i=0;i<=10 && user[i] <= infos[i];i++){
                user[i]++;
                dfs(cnt+1);
                user[i]--;
            }
        }
    }
}
