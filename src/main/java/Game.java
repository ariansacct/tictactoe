import java.util.Arrays;

public class Game {

    int n;
    int[][] board;
    int turn;

    /*
    a 2 by 8 array: each row for one player, columns 0 to 2 for rows 0 to 2, columns 3 to 5 for columns 0 to 2,
    column 6 for the main diagonal, column 7 for the other diagonal
     */
    int[][] scores;

    public Game() {
        n = 3;
        board = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(board[i], -1);
        scores = new int[2][2 * n + 2];
    }

    public void printBoard() {
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] == 0 ? "X" : board[i][j] == 1 ? "O" : "-");
                if (j != cols - 1)
                    System.out.print("\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }

    public void printScores() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(scores[i][j]);
                if (j != 7)
                    System.out.print("\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }

    private boolean isBoardFull() {
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] != -1)
                    return false;
            }
        }
        return true;
    }

    private boolean isWinner(int player) {
        for (int j = 0; j < 2 * n + 2; j++) {
            if (scores[player][j] == n)
                return true;
        }
        return false;
    }

    private boolean drawn() {
        return !isWinner(0) && !isWinner(1) && isBoardFull();
    }

    public void makeMove(int player, int i, int j) {
        board[i][j] = player;
        scores[player][i]++;
        scores[player][j + 3]++;
//        System.out.println("happened for i " + i + " and j " + j + "\n");
        if (i == j)
            scores[player][6]++;
        if (i + j == n - 1)
            scores[player][7]++;
        printBoard();
        if (isWinner(player))
            System.out.println("Player " + player + " wins!");
        if (drawn())
            System.out.println("It's a draw!");
    }
}
