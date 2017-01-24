/*
ID:1tolsma1
LANG:JAVA
TASK:beads
*/

/**
 * Created by ryan on 1/20/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class beads {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("beads.in"));
        PrintWriter pr = new PrintWriter("beads.out");

        int numBeads=Integer.parseInt(br.readLine());
        String beads=br.readLine();

        beads=rotate(beads);
        ArrayList<Integer> streaks=new ArrayList<>();

        int runningTotal=1;
        char currentBead;
        int p=0;
        while (beads.charAt(p) == 'w') {
            p++;
        }
        currentBead=beads.charAt(p);


        for(int i=0; i<beads.length();i++) {
            char nextBead=beads.charAt(i);
            if(nextBead!=currentBead && nextBead!='w') {
                streaks.add(runningTotal);
                runningTotal=1;
                currentBead=nextBead;
            }
            else {
                runningTotal++;
            }
        }

        int max=0;
        int index=0;
        for(int k=0; k<streaks.size()-1; k++) {

            if(streaks.get(k)+streaks.get((k+1)%streaks.size())>max) {
                max=streaks.get(k)+streaks.get(k+1);
                index=k;
            }
        }



        pr.println(max);
        pr.close();

    }

    public static String rotate(String s) {
        String temp=new String(s);
        char currentChar;
        int i=0;
        while(temp.charAt(i)=='w') {
            i++;
        }
        currentChar=temp.charAt(i);
        while(currentChar==temp.charAt(temp.length()-1) || temp.charAt(temp.length()-1)=='w') {

            if(temp.charAt(temp.length()-1)!='w') currentChar=temp.charAt(temp.length()-1);
            temp=temp.substring(temp.length()-1)+temp.substring(1,temp.length()-1);

        }
        return temp;
    }
}
