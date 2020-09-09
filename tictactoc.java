package tictactoe;

import java.util.Formatter;
import java.util.Scanner;

public class Main {
    //
    //
    public static char swap(char ch) {
        if (ch == 'X')
            return 'O';
        else
            return 'X';
    }

    public static boolean line(char ch, char ch1, char ch2, char ch3) {
        return ch1 == ch && ch2 == ch && ch3 == ch;
    }

    public static boolean intTryParse(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int intParse(String value) {
        return Integer.parseInt(value);
    }

    public static char[][] map(String s) {
        return new char[][]{
                {s.charAt(6), s.charAt(3), s.charAt(0)},
                {s.charAt(7), s.charAt(4), s.charAt(1)},
                {s.charAt(8), s.charAt(5), s.charAt(2)}
        };
    }

    public static void tictactoe(char[][] ch) {
        Formatter formatter1 = new Formatter();
        Formatter formatter2 = new Formatter();
        Formatter formatter3 = new Formatter();

        formatter1.format("| %s %s %s |\n", ch[0][2], ch[1][2], ch[2][2]);
        formatter2.format("| %s %s %s |\n", ch[0][1], ch[1][1], ch[2][1]);
        formatter3.format("| %s %s %s |\n", ch[0][0], ch[1][0], ch[2][0]);
        String result = "---------\n" +
                formatter1.toString() +
                formatter2.toString() +
                formatter3.toString() +
                "---------\n";
        System.out.print(result);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells:");

        String str = "         ";
        char[][] charArray = map(str);
        tictactoe(charArray);
        int continue_game = 9;
        char sign = 'O';

        while (continue_game != 0) {
            System.out.print("Enter the coordinates:");
            String s1 = scanner.next();
            String s2 = scanner.next();

            if (intTryParse(s1) && intTryParse(s2)) {
                int x = intParse(s1) - 1;
                int y = intParse(s2) - 1;
                if (x >= 3 || y >= 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (charArray[x][y] == ' ' || charArray[x][y] == '_') {
                    charArray[x][y] = sign;
                    tictactoe(charArray);
                    continue_game--;
                    boolean win = false;

                    for (int i = 0; i < 3; i++) {
                        if (line(sign, charArray[0][i], charArray[1][i], charArray[2][i]))
                            win = true;
                        else if (line(sign, charArray[i][0], charArray[i][1], charArray[i][2]))
                            win = true;
                    }
                    if (line(sign, charArray[0][0], charArray[1][1], charArray[2][2]))
                        win = true;
                    if (line(sign, charArray[0][2], charArray[1][1], charArray[2][0]))
                        win = true;

                    if (win) {
                        System.out.println(sign + " wins");
                        continue_game = 0;
                    } else if (continue_game == 0) {
                        System.out.println("Draw");
                    }
                    sign = swap(sign);
                } else
                    System.out.println("This cell is occupied! Choose another one!");
            } else
                System.out.println("You should enter numbers!");

        }

    }
}
