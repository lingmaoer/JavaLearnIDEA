import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String[] str=new String[20];
        String COWS = in.nextLine();
        for (int i = 0; i < N; i++) {
            String x = in.nextLine();
            str[i]=x;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < str[0].length(); j++) {

            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("Valid or Invalid");
    }
}