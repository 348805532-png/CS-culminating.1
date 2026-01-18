//NAME: Matteo Allen
//NAME: Ibrahim Naqui

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        while (true) {
            boolean using_ai = Main_Menu.show();
            Board board = new Board();

            if (using_ai) {
                // X to center as the AI's first tactical play in a series of many
                board.do_move("b2", 'x');

                // game sub-loop
                while (true) {
                    // Check for a win
                    int won = board.board_won();

                    if (won != -1) {
                        if (won == 3) {
                            board.show_board();
                            System.out.println("================");
                            System.out.println("Tie game!\n" +
                                    "Thank you for playing!");
                            System.out.println("================");
                            break;
                        }

                        board.show_board();
                        System.out.println("================");
                        System.out.printf("We have a winner: %c!\n" +
                                "Thank you for playing!\n", Board.mappings[won]);
                        System.out.println("================");
                        break;
                    }

                    // Show the board
                    System.out.println("Human [O] to move");
                    board.show_board();

                    System.out.println();
                    System.out.print("Please enter your move (e.g. a1, b3): ");

                    String move = sc.nextLine();

                    int move_valid = board.do_move(move, 'o');

                    while (move_valid < 0) {
                        if (move_valid == -1) {
                            System.out.print("There is an invalid character in your input! Try again: ");
                        } else if (move_valid == -2) {
                            System.out.print("The space you have tried to make is already occupied. Try again: ");
                        } else if (move_valid == -3) {
                            System.out.print("Your move must be two characters long. Try again: ");
                        }

                        move = sc.nextLine();
                        move_valid = board.do_move(move, 'o');
                    }

                    board.board_state[AI.next_move(board.board_state)] = 1;

                    System.out.println("================");
                    // Do AI move
                    System.out.println("AI [X] to move");
                    System.out.println("[AI] Thinking of a move...");
                    Thread.sleep(r.nextInt(200, 300));
                    System.out.println("[AI] Move applied!");
                    System.out.println("================");
                }
            } else {
                int player1 = r.nextInt(1, 3);
                int player2;
                if (player1 == 1) {
                    player2 = 2;
                } else {
                    player2 = 1;
                }

                char p1_symbol = Board.mappings[player1];
                char p2_symbol = Board.mappings[player2];

                char x_symbol;
                char y_symbol;

                if (p1_symbol == 'X') {
                    x_symbol = '1';
                    y_symbol = '2';
                } else {
                    x_symbol = '2';
                    y_symbol = '1';
                }

                System.out.printf("PLAYER 1 is %c\n" +
                        "PLAYER 2 is %c\n\n", p1_symbol, p2_symbol);

                while (true) {
                    // Check for a win
                    int won = board.board_won();

                    if (won != -1) {
                        if (won == 3) {
                            board.show_board();
                            System.out.println("================");
                            System.out.println("Tie game!\n" +
                                    "Thank you for playing!");
                            System.out.println("================");
                            break;
                        }

                        board.show_board();
                        System.out.println("================");
                        System.out.printf("We have a winner: %c!\n" +
                                "Thank you for playing!\n", Board.mappings[won]);
                        System.out.println("================");
                        break;
                    }

                    // Show the board here
                    System.out.printf("Player %c [X] to move\n", x_symbol);
                    board.show_board();

                    System.out.println();
                    System.out.print("Please enter your move (e.g. a1, b3): ");

                    String move = sc.nextLine();

                    int move_valid = board.do_move(move, 'x');

                    while (move_valid < 0) {
                        if (move_valid == -1) {
                            System.out.print("There is an invalid character in your input! Try again: ");
                        } else if (move_valid == -2) {
                            System.out.print("The space you have tried to make is already occupied. Try again: ");
                        } else if (move_valid == -3) {
                            System.out.print("Your move must be two characters long. Try again: ");
                        }

                        move = sc.nextLine();
                        move_valid = board.do_move(move, 'x');
                    }

                    System.out.println("================");

                    // Win check 2 for the second one
                    won = board.board_won();

                    if (won != -1) {
                        if (won == 3) {
                            board.show_board();
                            System.out.println("================");
                            System.out.println("Tie game!\n" +
                                    "Thank you for playing!");
                            System.out.println("================");
                            break;
                        }

                        board.show_board();
                        System.out.println("================");
                        System.out.printf("We have a winner: %c!\n" +
                                "Thank you for playing!\n", Board.mappings[won]);
                        System.out.println("================");
                        break;
                    }

                    // Do P2 move
                    System.out.printf("Player %c [O] to move\n", y_symbol);
                    board.show_board();

                    System.out.println();
                    System.out.print("Please enter your move (e.g. a1, b3): ");

                    move = sc.nextLine();

                    move_valid = board.do_move(move, 'o');

                    while (move_valid < 0) {
                        if (move_valid == -1) {
                            System.out.print("There is an invalid character in your input! Try again: ");
                        } else if (move_valid == -2) {
                            System.out.print("The space you have tried to make is already occupied. Try again: ");
                        } else if (move_valid == -3) {
                            System.out.print("Your move must be two characters long. Try again: ");
                        }

                        move = sc.nextLine();
                        move_valid = board.do_move(move, 'o');
                    }

                    System.out.println("================");
                }
            }
        }
    }
}
