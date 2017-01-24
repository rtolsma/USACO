/*
ID:1tolsma1
LANG:JAVA
TASK:friday
*/

/**
 * Created by ryan on 1/20/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class friday {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("friday.in"));
        PrintWriter pr = new PrintWriter("friday.out");

        int[] lengths={31,28,31,30,31,30,31,31,30,31,30,31};
        int startDate=1; //First monday...
        int tally=2; //Day of week tally
        int[] days=new int[7]; //tracks num of 13's
        int monthIndex=0;
        int yearIndex=1900;

        int numYears=1900+Integer.parseInt(br.readLine());

        while(yearIndex<numYears) {
            startDate++;
            tally++;
            int currentMonthLength=lengths[monthIndex];

            if( (monthIndex==1 && yearIndex%4==0) &&( !(yearIndex%100==0) ||yearIndex%400==0)) {
                //leap year+2000 case
                currentMonthLength++;
            }

            if(startDate==13) {
                days[tally%7]++;
            }

            if(startDate>currentMonthLength) {

                monthIndex++;
                if(monthIndex>11) {
                   //months run over
                    monthIndex=0;
                    yearIndex++;
                }

                startDate=1;
            }




        }

        for(int i=0; i<days.length; i++) {
            pr.print(days[i]);
            if(!(i+1==days.length)) pr.print(" ");
        }
        pr.println();
        pr.close();
    }

}
