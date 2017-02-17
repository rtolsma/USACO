/*
ID:1tolsma1
LANG:JAVA
TASK:skidesign
*/

/**
 * Created by ryan on 2/5/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/*
Strategy for this problem was nearly a binary search based off of peak values.
Necessary to create a recursive solution, since the simulated values were not necessarily
sorted...at least that's what I think
 */
public class skidesign {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("skidesign.in"));
        PrintWriter pr = new PrintWriter("skidesign.out");

        int num=Integer.parseInt(br.readLine());

        int[] hills=new int[num];

        for(int i=0; i<num; i++) {
            hills[i]=Integer.parseInt(br.readLine());
        }
        Arrays.sort(hills);


       int min=search( hills[0], hills[num-1], hills);

        pr.println(min);
        pr.close();

    }
        //this has been tested!!! it's all good
    public static int simulate(int[] course, double pivot) {
        int total=0;
        double top=Math.floor(pivot+17.0/2.0);
        double bottom=Math.floor(pivot-17.0/2.0);

        for(int k=0; k<course.length; k++) {

            if(course[k]>top) {
                total+=Math.pow(course[k]-top,2);
            } else if(course[k]<bottom) {
                total+= Math.pow(bottom-course[k], 2) ;

            }

        }
        return total;
    }
        public static int search(int low, int high, int[] hills) {
            int cost=0, min=Integer.MAX_VALUE;
            int next, previous;
            double mid;

            int left=Integer.MAX_VALUE, right=Integer.MAX_VALUE;
                mid=low+(high-low)/2.0;
                cost=simulate(hills, mid);
                next=simulate(hills, mid+1);
                previous=simulate(hills, mid-1);
                if(cost<min) {
                    min=cost;
                }

                if(min>next) {
                     right=search((int)mid+1, high, hills);
                }

                if(min>previous) {
                    left=search(low, (int) Math.ceil(mid-1), hills);
                }


                //if it's a local peak, loop will auto exit

            return Math.min(Math.min(right, left),min);
        }
}
