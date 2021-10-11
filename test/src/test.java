import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[][] arrays=new int[1500][1500];
        int c=0,i,j;
        int z = n*n;
        int ou=1;
        while(ou<=z) {
            i=0;
            j=0;
            for (i+=c,j+=c;j<n-c;j++)  // 从左到右
            {
                if(ou>z) break;
                arrays[i][j] = ou++;
            }
            for (j--,i++;i<n-c;i++) // 从上到下
            {
                if(ou>z) break;
                arrays[i][j] = ou++;
            }
            for (i--,j--;j>=c;j--)// 从右到左
            {
                if(ou>z) break;
                arrays[i][j] = ou++;
            }
            for (j++,i--;i>=c+1;i--)// 从下到上
            {
                if(ou>z)  break;
                arrays[i][j] = ou++;
            }
            c++;
        }
        long sum=0;
        if(n%2==1) {
            for (int k = 0; k <n; k++) {
                sum+=arrays[k][k]+arrays[k][n-k-1];
            }
            System.out.println(sum-arrays[n/2][n/2]);
        }
        else{
            for (int k = 0; k <n; k++) {
                sum+=arrays[k][k]+arrays[k][n-k-1];
            }
            System.out.println(sum);
        }
    }
}
