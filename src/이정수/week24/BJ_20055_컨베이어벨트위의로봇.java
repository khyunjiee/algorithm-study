package week24;

import java.util.Scanner;


/**
 * 문제: 컨베이어 벨트 위의 로봇
 * 링크: https://www.acmicpc.net/problem/20055
 * 
 * 풀이:
 * 1. 1차원 배열로 컨베이어 벨트 표현
 * 2. on과 off라는 인덱스로 로봇 올리는 위치와 내리는 위치 표현
 * 3. on, off를 이동시켜서 회전표현
 * 
 * 
 * 시간복잡도:
 * ?
 * 
 * 풀이에 걸린 시간:
 * 2h
 *
 */
public class BJ_20055_컨베이어벨트위의로봇 {

    static class Node{
    	
        boolean isRobotOn;
        int durability;

        public Node(int durability) {
            this.durability = durability;
        }

		@Override
		public String toString() {
			return "Node [isRobotOn=" + isRobotOn + ", durability=" + durability + "]";
		}
        
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        Node[] belt = new Node[2*N];

        int on = 0;     // 로봇 올리는 위치
        int off = N-1;  // 로봇 내리는 위치

        // 벨트 상태 초기화
        for (int i = 0; i < 2*N; i++) {
            belt[i] = new Node(sc.nextInt());
        }

        int cnt = 0;
        

        while(K>0){

            ///////////////////////////////////////////////////////// 벨트 회전
            

            if(off==0){
                off = 2*N -1;
            }else{
                off --;
            }

            if(on==0){
                on = 2*N -1;
            }else{
                on--;
            }
            
            belt[off].isRobotOn = false; // 로봇 내리기

            ///////////////////////////////////////////////////////// 로봇 이동
            if(on<off){
                for (int i = off; i > on ; i--) {
                    // 현재칸에 로봇이 없고 내구도가 0보다 크고 앞칸에 로봇이 있으면
                    if(!belt[i].isRobotOn && belt[i].durability>0 && belt[i-1].isRobotOn){
                        // 뒤칸의 로봇 한칸 앞으로 이동
                        belt[i-1].isRobotOn = false;
                        belt[i].isRobotOn = true;
                        belt[i].durability--;
                        if(belt[i].durability==0) {
                        	K--;
                        }
                    }
                }
            }else{
                for (int i = off; i > 0; i--) {
                    // 현재칸에 로봇이 없고 내구도가 0보다 크고 앞칸에 로봇이 있으면
                    if(!belt[i].isRobotOn && belt[i].durability>0 && belt[i-1].isRobotOn){
                        // 뒤칸의 로봇 한칸 앞으로 이동
                        belt[i-1].isRobotOn = false;
                        belt[i].isRobotOn = true;
                        belt[i].durability--;
                        if(belt[i].durability==0) {
                        	K--;
                        }
                    }
                }
                // 현재칸에 로봇이 없고 내구도가 0보다 크고 뒤칸에 로봇이 있으면
                if(!belt[0].isRobotOn && belt[0].durability>0 && belt[2*N-1].isRobotOn){
                    // 뒤칸의 로봇 한칸 앞으로 이동
                    belt[2*N-1].isRobotOn = false;
                    belt[0].isRobotOn = true;
                    belt[0].durability--;
                    if(belt[0].durability==0) {
                    	K--;
                    }
                }
                for (int i = 2*N-1; i > on; i--) {
                    // 현재칸에 로봇이 없고 내구도가 0보다 크고 뒤칸에 로봇이 있으면
                    if(!belt[i].isRobotOn && belt[i].durability>0 && belt[i-1].isRobotOn){
                        // 뒤칸의 로봇 한칸 앞으로 이동
                        belt[i-1].isRobotOn = false;
                        belt[i].isRobotOn = true;
                        belt[i].durability--;
                        if(belt[i].durability==0) {
                        	K--;
                        }
                    }
                }
            }
            
            belt[off].isRobotOn = false; // 로봇 내리기

            ////////////////////////////////////////////////////// 로봇 올리기
            if(belt[on].durability>0){
                belt[on].isRobotOn = true;
                belt[on].durability--;
                if(belt[on].durability==0) {
                	K--;
                }
            }
            cnt++;

        }

        System.out.println(cnt);
    }
}
