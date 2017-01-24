/*
ID:1tolsma1
LANG:JAVA
TASK:Cowchecklist
*/

/**
 * Created by ryan on 1/22/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Cowchecklist {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowchecklist.in"));
        PrintWriter pr = new PrintWriter("cowchecklist.out");
        StringTokenizer st=new StringTokenizer(br.readLine());

        int numH=Integer.parseInt(st.nextToken());
        int numG=Integer.parseInt(st.nextToken());

        ArrayList<Cow> h=new ArrayList<>();
        ArrayList<Cow> g=new ArrayList<>();

        int hIndex=0;
        int gIndex=0;

        //Populate cow lists
        for(int i=0; i<numH; i++) {
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            h.add(new Cow(x,y));
        }
        for(int j=0;j<numG; j++) {
            st=new StringTokenizer((br.readLine()));
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            g.add(new Cow(x,y));
        }


        //Greedy Algorithm; start at either H=0 or G=0
        //and then choose minimum distance
        //for h=0 first
        double energy=0;
        double energy1=0;
        for(int k=0; k<2; k++) {
            Cow current;
            hIndex=0;
            gIndex=0;
            if(k==0) {
                current = h.get(0);
                hIndex++;
            } else {
                current=g.get(0);
                gIndex++;
                energy1=energy;
                energy=0;
            }
            while (gIndex < numG || hIndex < numH) {


                //Cases if we reach end of G or H list
                if (hIndex >= numH) {
                    energy += current.distance(g.get(gIndex));
                    current = g.get(gIndex);
                    gIndex++;
                    continue;
                } else if (gIndex >= numG) {
                    energy += current.distance(h.get(hIndex));
                    current = h.get(hIndex);
                    hIndex++;
                    continue;
                }

                double dH = current.distance(h.get(hIndex));
                double dG = current.distance(g.get(gIndex));


                if (dH < dG) {
                    current = h.get(hIndex);
                    hIndex++;
                    energy += dH;
                } else {
                    current = g.get(gIndex);
                    gIndex++;
                    energy += dG;
                }

            }
        }
        pr.println(Math.min(energy,energy1));
        pr.flush();

    }


}
    class Cow {
        int x, y;

        public Cow(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double distance(int x1, int y1) {

            return Math.pow(x1 - x, 2) + Math.pow(y1 - y, 2);
        }

        public double distance(Cow c) {
            return distance(c.x, c.y);
        }
    }


