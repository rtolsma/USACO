/*
ID:1tolsma1
LANG:JAVA
TASK:dualpal
*/

/**
 * Created by ryan on 1/23/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class dualpal {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("dualpal.in"));
        PrintWriter pr = new PrintWriter("dualpal.out");
        StringTokenizer st=new StringTokenizer(br.readLine());

        int num=Integer.parseInt(st.nextToken());
        int s=Integer.parseInt(st.nextToken());

        ArrayList<Integer> palindromes=new ArrayList<>();

        int current=s+1;
        while(palindromes.size()<num) {

            int numPalindromic=0;
            for(int i=2; i<11; i++) {

                String palindrome=Integer.toString(current, i);
                if(isPalindrome(palindrome)) {
                    numPalindromic++;

                    if(numPalindromic>=2) {
                        palindromes.add(current);
                        break;
                    }
                }


            }
            current++;



        }

        for(int k=0; k<palindromes.size(); k++) {
            pr.println(palindromes.get(k));
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
