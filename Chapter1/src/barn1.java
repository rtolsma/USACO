/*
ID:1tolsma1
LANG:JAVA
TASK:barn1
*/

/**
 * Created by ryan on 1/31/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class barn1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("barn1.in"));
        PrintWriter pr = new PrintWriter("barn1.out");
        StringTokenizer st = new StringTokenizer(br.readLine());

        int boards = Integer.parseInt(st.nextToken());
        int numStalls = Integer.parseInt(st.nextToken());
        int occupied = Integer.parseInt(st.nextToken());
        ArrayList<Integer> gaps=new ArrayList<>();
        int[] closed=new int[occupied];

        if(boards>=occupied) {
            pr.println(occupied);
            pr.close();
            return;
        }

        for(int l=0; l<occupied; l++) {
            closed[l]=Integer.parseInt(br.readLine());
        }
        Arrays.sort(closed);
        for(int i=0; i<occupied; i++) {
            if( (i>0 && i<occupied-1) || closed[i]==numStalls) {
                gaps.add(closed[i]-closed[i-1]);
            }
        }

        Collections.sort(gaps);

        int total=closed[0]-1+(numStalls-closed[closed.length-1]);
        for(int j=0; j<boards-1; j++) {
            if(j==gaps.size()) break;
            total+=gaps.get(gaps.size()-j-1)-1;

        }

        pr.println(numStalls-total);
        pr.close();


    }

}
