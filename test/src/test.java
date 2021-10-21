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
        int width = in.nextInt();
        int height = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        int count=0;
        for (int i = 0; i < height; i++) {
            String row = in.nextLine();
            for(int j=0;j<row.length();j++){
                if(row.toCharArray()[j]=='*')
                {
                    count++;
                    System.out.printf("%.1f %.1f", Math.sqrt(j*j+i*i), Math.atan2(j+1,i+1));
                    System.out.println();
                }
            }
        }
        if(count==0){
            System.out.println("Dead");
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }
}