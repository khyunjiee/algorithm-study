import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BJ_1043_거짓말 {
    
    static int[] people;
    static List<Integer>[] parties;
    static int answer=0, M, N;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        
        int numberOfPeopleWhoKnowsTruth = sc.nextInt();

        people = new int[N+1];
        parties = new List[M];
        
        for (int i = 0; i < numberOfPeopleWhoKnowsTruth; i++) {
            people[sc.nextInt()] = 1;
        }
        
        for (int i = 0; i < M; i++) {
            parties[i] = new LinkedList<>();
            int numberOfParticipants = sc.nextInt();

            for (int j = 0; j < numberOfParticipants; j++) {
                parties[i].add(sc.nextInt());
            }

        }

        dfs(0, 0);

        System.out.println(answer);

    }

    private static void dfs(int depth, int numberOfLies) {

        if(depth==M){
            answer = Math.max(answer, numberOfLies);
            return;
        }

        int[] temp = Arrays.copyOf(people, N+1);
        int truth = 0;
        int fake = 0;

        for (int participant :
                parties[depth]) {

            if(people[participant]==1){
                truth++;
            }else if(people[participant]==2){
                fake++;
            }

        }

        // 불가능한 경우
        if(truth>0 && fake>0){
            return;
        }

        // 진실 말하기
        if(fake==0){
            for (int participant :
                    parties[depth]) {
                people[participant] = 1;
            }

            dfs(depth+1, numberOfLies);
        }

        people = Arrays.copyOf(temp, N+1);

        // 과장하기
        if(truth==0){
            for (int participant :
                    parties[depth]) {
                people[participant] = 2;
            }

            dfs(depth+1, numberOfLies+1);
        }

        people = Arrays.copyOf(temp, N+1);
    }
}
