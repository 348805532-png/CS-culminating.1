public class AI {
    private static int can_we_win(int[] board_state) {
        // generate game states for all our possible moves
        // perform a win check on each
        for (int i = 0; i < 9; i++) {
            if (board_state[i] == 0) {
                int[] new_board_state = board_state.clone();
                new_board_state[i] = 1;
                if (Board.board_won_generic(new_board_state) == 1) {
                    return i;
                }
            }
        }


        return -1;
    }

    private static int can_opponent_win(int[] board_state) {
        for (int i = 0; i < 9; i++) {
            if (board_state[i] == 0) {
                int[] new_board_state = board_state.clone();
                new_board_state[i] = 2;

                if (Board.board_won_generic(new_board_state) == 2) {
                    return i;
                }
            }
        }


        return -1;
    }

    private static int fork_check(int[] board_state) {
        if ((board_state[0] == 2 && board_state[8] == 2) || (board_state[2] == 2 && board_state[6] == 2)) {
            // Fork! play any of the edges here
            for (int i = 1; i <= 7; i += 2) {
                if (board_state[i] == 0) {
                    return i;
                }
            }
        }

        return -1;
    }

    private static int opposite_corner_check(int[] board_state) {
        // The opposite corner to 0 is 8
        // The opposite corner to 6 is 2

        if (board_state[0] == 2 && board_state[8] == 0) {
            return 8;
        }

        if (board_state[8] == 2 && board_state[0] == 0) {
            return 0;
        }

        if (board_state[6] == 2 && board_state[2] == 0) {
            return 2;
        }

        if (board_state[2] == 2 && board_state[6] == 0) {
            return 6;
        }

        return -1;
    }

    private static int corner_check(int[] board_state) {
        if (board_state[0] == 0) {
            return 0;
        }

        if (board_state[2] == 0) {
            return 2;
        }

        if (board_state[6] == 0) {
            return 6;
        }

        if (board_state[8] == 0) {
            return 8;
        }

        return -1;
    }

    private static int last_ditch_effort(int[] board_state) {
        for (int i = 1; i <= 7; i += 2) {
            if (board_state[i] == 0) {
                return i;
            }
        }

        // THIS SHOULD NEVER HAPPEN! just so it stops screaming at me please
        return -1;
    }

    // all the functions running in order of importance
    public static int next_move(int[] board_state) {
        if (can_we_win(board_state) != -1) {
            return can_we_win(board_state);
        } else if (can_opponent_win(board_state) != -1) {
            return can_opponent_win(board_state);
        } else if (fork_check(board_state) != -1) {
            return fork_check(board_state);
        } else if (opposite_corner_check(board_state) != -1) {
            return opposite_corner_check(board_state);
        } else if (corner_check(board_state) != -1) {
            return corner_check(board_state);
        } else {
            return last_ditch_effort(board_state);
        }
    }

}
