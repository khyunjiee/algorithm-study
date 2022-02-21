public class PG_43238_입국심사 {
    public static void main(String[] args) {
        System.out.println(solution(6,new int[]{7, 10}));
    }

    static public long solution(int n, int[] times) {

        long left = 1;
        long right = 1000000000000000000L;
        long answer = -1;
        while(left<=right){

            long mid = (left+right)/2;
            long result = 0;

            for (int i = 0; i < times.length; i++) {
                result += mid/times[i];
            }

            if(n<=result){
                answer = mid ;
                right = mid - 1;
            }else {
                left = mid + 1;
            }

        }

        return answer;
    }
}
