/*
ID:1tolsma1
LANG:JAVA
TASK:crypt1
*/

/**
 * Created by ryan on 2/1/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Plan of attack
/*
Generate a list of all combinations of digits with length 3, and same for
those with length 2.  ~n!

Go through each 3 digit combination with each 2 digit combo
and verify if they work
 */

public class crypt1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("crypt1.in"));
        PrintWriter pr = new PrintWriter("crypt1.out");
        StringTokenizer st;
        int numDigits=Integer.parseInt(br.readLine());

        ArrayList<Integer> digits=new ArrayList<>();
        st=new StringTokenizer(br.readLine());

        int total=0;//number of answers

        //Get list of digits
        for(int p=0; p<numDigits; p++) {
            digits.add(Integer.parseInt(st.nextToken()));
        }



        String allDigits="";
        for(Integer k : digits) allDigits+=k.toString();

        ArrayList<Integer> threes=new ArrayList<>();
        ArrayList<Integer> twos=new ArrayList<>();

        //Fill threes and twos with the different integer combos
        combinations(threes, allDigits, "", 3);
        combinations(twos, allDigits,"", 2);

        for(int i=0; i<threes.size(); i++) {
            Integer top=threes.get(i);
            for(int j=0; j< twos.size(); j++) {
                Integer bottom=twos.get(j);
                Integer firstDigit=bottom%10;
                Integer secondDigit=(bottom/10)%10;

                Integer firstPartial=firstDigit*top;
                Integer secondPartial=secondDigit*top;
                if(firstPartial.toString().length()==3 &&secondPartial.toString().length()==3) {



                    Integer fullSolution=bottom*top;
                    String solution=fullSolution.toString();

                    if(isValid(solution,digits) && isValid(firstPartial.toString(),digits) &&
                            isValid(secondPartial.toString(),digits))  {
                        total++;
                    }

                }


            }
        }
        pr.println(total);
        pr.close();
    }

    public static void combinations(ArrayList<Integer> combos, String digits, String combo, int length) {

        //For every digit, go through with combos with every digit for length digits
        //Long loop :(
        if(length==0) {
            combos.add(Integer.parseInt(combo));
            return;
        }
        for(int k=0; k<digits.length(); k++) {

            combinations(combos, digits, combo+digits.substring(k,k+1), length-1);

        }
    }

    public static boolean isValid(String integer, ArrayList<Integer> digits) {

        for(int i=0; i<integer.length(); i++) {

            if(!digits.contains(Integer.parseInt(integer.substring(i,i+1)))) {
                return false;
            }

        }
        return true;


    }




}
