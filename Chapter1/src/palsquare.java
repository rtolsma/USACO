/*
ID:1tolsma1
LANG:JAVA
TASK:palsquare
*/

/**
 * Created by ryan on 1/23/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class palsquare {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("palsquare.in"));
        PrintWriter pr = new PrintWriter("palsquare.out");

        int base=Integer.parseInt(br.readLine());

        int[] squares=new int[300];
        for(int i=0; i<squares.length; i++) {
            squares[i]=i*i;
        }

        for(int k=1; k<squares.length; k++) {

            String newBase=Integer.toString(squares[k],base);
            if(isPalindrome(newBase)) {
                pr.println((Integer.toString(k,base)+" "+newBase).toUpperCase());
            }


        }
            pr.close();
    }
        public static boolean isPalindrome(String word) {
            for(int i=0; i<=word.length()-i-1;i++) {

                if(word.charAt(i)!=word.charAt(word.length()-i-1)) return false;
            }
            return true;

        }
}
