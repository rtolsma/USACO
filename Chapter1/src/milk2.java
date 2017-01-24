/*
ID:1tolsma1
LANG:JAVA
TASK:milk2
*/

/**
 * Created by ryan on 1/23/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class milk2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter pr = new PrintWriter("milk2.out");
        StringTokenizer st;
        int numCows=Integer.parseInt(br.readLine());

        int maxIdle=0;
        int max=0;
        int start=0;
        int end=-0;

        ArrayList<Interval> intervals=new ArrayList<>();

        for(int i=0; i<numCows; i++) {
            st=new StringTokenizer(br.readLine());
            intervals.add(new Interval(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }
        Collections.sort(intervals, new Interval());

        for(int k=0; k<intervals.size(); k++) {
            Interval current=intervals.get(k);
            Interval next;
            if(k+1<intervals.size()) {
                next=intervals.get(k+1);
            } else {
                continue;
                //loop done
            }

            if (current.overlap(next)) {
                current.combine(next);
                intervals.remove(k+1);
                k--;
            }

        }


        //Now we have a small set of disjoint intervals, find largest length
        //find longest set of idleness

        for(int l=0; l<intervals.size(); l++) {
            Interval current=intervals.get(l);
            Interval next;
            if(current.length()>max) max=current.length();

            if(l+1!=intervals.size()) {
                next=intervals.get(l+1);
                if(next.getStart()-current.getEnd()>maxIdle) {
                    maxIdle=next.getStart()-current.getEnd();
                }

            }

        }
        pr.println(max+" "+maxIdle);
        pr.close();



    }
}

class Interval implements Comparator<Interval> {
    int start;
    int end;
    public Interval(int x, int y) {
        start=x;
        end=y;
    }
    public Interval() {
        this(0,0);
    }

    public int compare(Interval one, Interval two) {

        if(one.getStart()>two.getStart()) return 1;
        else if(one.getStart()<two.getStart()) return -1;
        else return 0;

    }

    public int getStart() {
        return start;
    }
    public int getEnd() {
        return end;
    }
    public boolean combine(Interval other) {
        start=Math.min(start, other.getStart());
        end=Math.max(end, other.getEnd());
        return true;
    }

    public boolean setStart(int start) {
        this.start=start;
        return true;
    }

    public boolean setEnd(int end) {
        this.end = end;
        return true;
    }
    public int length() {
        return end-start;
    }

    public boolean overlap(Interval other) {
        if(other.getStart()>this.getEnd() || other.getEnd()<this.getStart())
            return false;
        else return true;

    }

}
