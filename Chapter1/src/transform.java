/*
ID:1tolsma1
LANG:JAVA
TASK:transform
*/

/**
 * Created by ryan on 1/23/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class transform {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("transform.in"));
        PrintWriter pr = new PrintWriter("transform.out");
        StringTokenizer st;
        int numLines=Integer.parseInt(br.readLine());

        int[][] image=new int[numLines][numLines];
        int[][] verify=new int[numLines][numLines];
        int transform=7; //default case

        for(int x=0; x<numLines*2; x++) {
            String line=br.readLine();

            for(int k=0; k<line.length(); k++) {
                if(x<numLines)image[x][k]= line.charAt(k)=='-'? 1 : 0;
                else verify[x-numLines][k]=line.charAt(k)=='-'?1:0;
            }
        }

        //really haxy if statements for 6 cases

        /* one of the cases was horizontal symmetric so
        6 and 2 were both valid, but they wanted 2. so i switched the
        order
         */
        if( equals(rotate(rotate(image)),verify)) {
            transform=2;
        } else if( equals(image,verify)) {
            transform = 6;
        } else if( equals(rotate(image),verify)) {
            transform=1;
        } else if( equals(rotate(rotate(rotate(image))),verify)) {
            transform=3;
        } else if( equals(flip(image),verify)) {
            transform=4;
        } else if( equals(rotate(flip(image)),verify)) {
            transform=5;
        } else if( equals(rotate(rotate(flip(image))),verify)) {
            transform=5;
        } else if( equals(rotate(rotate(rotate(flip(image)))),verify)) {
            transform=5;
        }

        pr.println(transform);
        pr.close();
    }

    public static int[][] rotate(int[][] image) {
        int[][] copy=new int[image.length][image.length];
        for(int i=0; i<image.length; i++) {
            for(int j=0; j<image.length; j++) {
                copy[j][image.length-i-1]=image[i][j];
            }


        }
        //passed by reference...
        return copy;
    }

    public static int[][] flip(int[][] image) {
        int[][] copy=new int[image.length][image.length];

        for(int i=0; i<image.length; i++) {
            for(int j=0; j<image.length; j++) {

                copy[i][image.length-j-1]=image[i][j];
            }
        }
        return copy;

    }

    public static boolean equals(int[][] one, int[][] two) {

        boolean answer=true;
        for(int i=0; i<one.length; i++) {
            for(int j=0; j<one.length; j++) {

                if(one[i][j]!=two[i][j]) return false;
            }
        }
        return answer;
    }

}
