public class Board {
    /**
     * An array of 9 numbers from 0 to 2 representing the board.
     * 0 is blank, 1 is X and 2 is O.
     * The default is all blank because arrays are initialized with 0 by default.
     */
    int[] board_state = new int[9];

    /**
     * Since our board is just numbers from 0-2,
     * we need a dictionary to convert these numbers
     * to actual characters. So 0 will be a '-',
     * 1 will be an 'X', and 2 will be an 'O'.
     */
    static char[] mappings = {'-', 'X', 'O'};

    /**
     * Checks if the game is over.
     */
    public static int board_won_generic(int[] generic_board_state) {
        // Scan horizontal
        for (int i = 0; i < 9; i += 3) {
            if (generic_board_state[i] == generic_board_state[i + 1] && generic_board_state[i + 1] == generic_board_state[i + 2] && generic_board_state[i] != 0) {
                return generic_board_state[i];
            }
        }

        // Scan vertical
        for (int i = 0; i < 3; i++) {
            if (generic_board_state[i + 3] == generic_board_state[i] && generic_board_state[i + 3] == generic_board_state[i + 6] && generic_board_state[i] != 0) {
                return generic_board_state[i];
            }
        }

        // Scan diagonal
        if (generic_board_state[0] == generic_board_state[4] && generic_board_state[4] == generic_board_state[8] && generic_board_state[0] != 0) {
            return generic_board_state[0];
        }
        if (generic_board_state[2] == generic_board_state[4] && generic_board_state[4] == generic_board_state[6] && generic_board_state[2] != 0) {
            return generic_board_state[2];
        }

        // Nobody has won yet!
        return -1;
    }

    public int board_won() {
        int won = board_won_generic(board_state);

        // Tie-game detection
        if (won == -1) {
            for (int i = 0; i < 9; i++) {
                if (board_state[i] == 0) return -1;
            }
            return 3;
        }

        return won;
    }


    /**
     * Replace the %c characters in our board with the correct
     * characters from our board array.
     */
    public void show_board() {
        /*
         * Hint for Matteo
         * Here's the board but with array indexes
         * 0 1 2
         * 3 4 5
         * 6 7 8
         * */

        String board = """
                   1     2     3
                      |     |    \s
                a  %c  |  %c  |  %c \s
                 _____|_____|_____
                      |     |    \s
                b  %c  |  %c  |  %c \s
                 _____|_____|_____
                      |     |    \s
                c  %c  |  %c  |  %c \s
                      |     |    \s""";
        System.out.printf(board + "%n", mappings[board_state[0]], mappings[board_state[1]], mappings[board_state[2]], mappings[board_state[3]], mappings[board_state[4]], mappings[board_state[5]], mappings[board_state[6]], mappings[board_state[7]], mappings[board_state[8]]);
    }

    /**
     * Does a move and updates the board state.
     * Inputs:
     * column, a, b or c
     * row: 1, 2 or 3
     * player: X or O
     * Returns: Error Codes
     * 0:  Successful move
     * -1: Invalid character in move
     * -2: Space already occupied
     */
    public int do_move(String move, char player) {
        if (move.length() < 2) {
            return -3;
        }

        char column = move.charAt(0);
        char row = move.charAt(1);

        int index;
        switch (Character.toLowerCase(column)) {
            case 'a':
                index = 0;
                break;
            case 'b':
                index = 3;
                break;
            case 'c':
                index = 6;
                break;
            default:
                return -1;
        }

        switch (row) {
            case '1':
                // Do nothing because we're already at the first row and stuff
                break;
            case '2':
                // shift over 1 row
                index += 1;
                break;
            case '3':
                // shift over 2 rows
                index += 2;
                break;
            default:
                return -1;
        }

        // check if there's already some kid who took this square
        if (board_state[index] != 0) {
            return -2;
        }

        switch (Character.toLowerCase(player)) {
            case 'x':
                board_state[index] = 1;
                break;
            case 'o':
                board_state[index] = 2;
                break;
            default:
                return -1;
        }

        return 0;
    }
}
