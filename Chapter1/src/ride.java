
/*
ID:1tolsma1
LANG:JAVA
TASK:ride
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Created by ryan on 1/20/17.
 */
public class ride {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("ride.in"));
        PrintWriter pr=new PrintWriter("ride.out");

        String comet=br.readLine();
        String group=br.readLine();

        if (calculate(comet) % 47 == calculate(group)%47) {
            pr.println("GO");
        } else {
            pr.println("STAY");
        }
        pr.close();

    }

    public static int calculate(String s) {
        int total=1;
        for(int i=0; i<s.length(); i++) {

            total*=(s.charAt(i)-'A'+1);
        }
        return total;
    }
}
