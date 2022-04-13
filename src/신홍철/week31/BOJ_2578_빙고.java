import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백트래킹을 이용해서 모든 경우를 고려.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static final int BOARD_SIZE = 9;
    static boolean[][] rowCheck = new boolean[BOARD_SIZE][BOARD_SIZE+1];
    static boolean[][] colCheck = new boolean[BOARD_SIZE][BOARD_SIZE+1];
    static boolean[][] squareCheck = new boolean[BOARD_SIZE][BOARD_SIZE+1];
    static boolean isSolved = false;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        int cellCheckCount = 0;
        int[][] sudoku = new int[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0;i<BOARD_SIZE;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j<BOARD_SIZE;j++){
                sudoku[i][j] = Integer.parseInt(st.nextToken());
                if(sudoku[i][j] != 0){
                    rowCheck[i][sudoku[i][j]] = true;
                    colCheck[j][sudoku[i][j]] = true;
                    squareCheck[(i/3)*3+(j/3)][sudoku[i][j]] = true;
                }
            }
        }
        solve(sudoku,cellCheckCount);
    }

    private static void print(int[][] sudoku) {
        for(int[] row : sudoku){
            for(int data : row){
                sb.append(data+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void solve(int[][] sudoku, int cellCheckCount) {
        if(isSolved) return ;
        if(cellCheckCount == 81) {
            print(sudoku);
            isSolved = true;
            return ;
        }
        int r = cellCheckCount/BOARD_SIZE;
        int c = cellCheckCount%BOARD_SIZE;
        if(sudoku[r][c] == 0){
            for(int i = 1;i<=9;i++){
                if(!rowCheck[r][i] && !colCheck[c][i] && !squareCheck[(r/3)*3+(c/3)][i]){
                    rowCheck[r][i] = true;
                    colCheck[c][i] = true;
                    squareCheck[(r/3)*3+(c/3)][i] = true;
                    sudoku[r][c] = i;
                    solve(sudoku,cellCheckCount+1);
                    sudoku[r][c] = 0;
                    rowCheck[r][i] = false;
                    colCheck[c][i] = false;
                    squareCheck[(r/3)*3+(c/3)][i] = false;
                }
            }
        }else{
            solve(sudoku, cellCheckCount+1);
        }
    }
}
