package pg;

import java.util.ArrayList;

public class PG84021 {
    /* Level 3. 퍼즐 조각 채우기
    시뮬레이션
     */
    private static final int[] dr = {0, 1, 0, -1};
    private static final int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) {
        PG84021.Solution test = new PG84021.Solution();
//        System.out.println(test.solution(new int[][] {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}},
//                new int[][] {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}}));
//        System.out.println(test.solution(new int[][] {{0,0,0},{1,1,0},{1,1,1}}, new int[][] {{1,1,1},{1,0,0},{0,0,0}}));
        System.out.println(test.solution(new int[][] {{0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0}, {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1}, {0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0}, {0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1}, {0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0}, {1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0}, {0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0}, {0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1}, {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0}},
        new int[][] {{1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1}, {1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0}, {0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0}, {1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1}, {1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1}, {0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1}, {1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1}, {1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1}, {1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1}}));
    }

    static class Piece {
        ArrayList<int[]> coords = new ArrayList<>();
    }

    public static boolean isValid(int r, int c, int n) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    public static void printPieces(ArrayList<Piece> pieces) {
        for (Piece piece:pieces) printPiece(piece);
    }

    public static void printPiece(Piece piece) {
        for (int[] coord:piece.coords)
            System.out.print("("+coord[0]+","+coord[1]+"), ");
        System.out.println();
    }

    public static void dfs(int r, int c, int n, int val, int[][] map, ArrayList<int[]> el) {
        el.add(new int[] {r, c});
        map[r][c] = 1 - val;
        int nr, nc;
        for (int i = 0; i < dr.length; i++) {
            nr = r + dr[i]; nc = c + dc[i];
            if (isValid(nr, nc, n) && map[nr][nc] == val) {
                dfs(nr, nc, n, val, map, el);
            }
        }
    }

    public static ArrayList<Piece> boardInit(int[][] board, int val) {
        ArrayList<Piece> pieces = new ArrayList<>();
        int n = board.length;
        Piece tmp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == val) {
                    tmp = new Piece();
                    dfs(i, j, n, val, board, tmp.coords);
                    pieces.add(tmp);
                }
            }
        }
        return pieces;
    }

    public static int[][] copyMap(int[][] map, int n) {
        int[][] mapCopy = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                mapCopy[i][j] = map[i][j];
        return mapCopy;
    }

    public static int[][] rotateMap(int[][] map, int n) {
        int[][] mapRotate = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                mapRotate[n-1-j][i] = map[i][j];
        return mapRotate;
    }

    public static int playPuzzle(int[][] game_board, int[][] table) {
        ArrayList<Piece> gameBoardPieces = boardInit(game_board, 0);
        ArrayList<Piece> temp;
        ArrayList<int[]> gCoords, tCoords;
        int n = game_board.length;
        int count = 0, rd, cd;
        int[] gCoord, tCoord;
        ArrayList<Piece> gRemove;
        for (int i = 0; i < 4; i++) {
            temp = boardInit(copyMap(table, n), 1);
            gRemove = new ArrayList<>();
            for (Piece gPiece:gameBoardPieces) {
                outer:for (Piece tPiece:temp) {
                    if (gPiece.coords.size() == tPiece.coords.size()) {
                        gCoords = gPiece.coords;    tCoords = tPiece.coords;
                        rd = gCoords.get(0)[0] - tCoords.get(0)[0];
                        cd = gCoords.get(0)[1] - tCoords.get(0)[1];
//                        System.out.print("g:"); printPiece(gPiece);
//                        System.out.print("t:"); printPiece(tPiece);
//                        System.out.println("rd:"+rd+", "+"cd:"+cd);
                        for (int j = 0; j < gPiece.coords.size(); j++) {
                            gCoord = gCoords.get(j);    tCoord = tCoords.get(j);
                            if (gCoord[0] != tCoord[0] + rd || gCoord[1] != tCoord[1] + cd) continue outer;
                        }
                        gRemove.add(gPiece);
                        for (int[] coord:tPiece.coords) table[coord[0]][coord[1]] = 0;
                        temp.remove(tPiece);
                        count += gPiece.coords.size();
                        break;
                    }
                }
            }
            for (Piece g:gRemove) gameBoardPieces.remove(g);
            table = rotateMap(table, n);
        }
        return count;
    }

    static class Solution {
        public int solution(int[][] game_board, int[][] table) {
//            ArrayList<Piece> gameBoardPieces =  boardInit(game_board);
//            ArrayList<Piece> tablePieces =  boardInit(table);
//            printPieces(gameBoardPieces);
            return playPuzzle(game_board, table);
        }
    }
}
