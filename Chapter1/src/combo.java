/*
ID:1tolsma1
LANG:JAVA
TASK:combo
*/

/**
 * Created by ryan on 2/2/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

//for each lock combination, go through all possibilities
//that are within 2 tolerance for each
//NOT SOLVED
public class combo {
    static int[] john, master;
    static int num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("combo.in"));
        PrintWriter pr = new PrintWriter("combo.out");
        StringTokenizer st;
        num=Integer.parseInt(br.readLine());
        john=new int[3];
        master=new int[3];

        //Read in john and master values
        st=new StringTokenizer(br.readLine());
        john[0]=Integer.parseInt(st.nextToken());
        john[1]=Integer.parseInt(st.nextToken());
        john[2]=Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());
        master[0]=Integer.parseInt(st.nextToken());
        master[1]=Integer.parseInt(st.nextToken());
        master[2]=Integer.parseInt(st.nextToken());




        Set<lock> keySet=new HashSet<>();

       traverse(john[0]-2, john[1]-2, john[2]-2,keySet);
        traverse(master[0]-2, master[1]-2, master[2]-2,keySet);


        pr.println(keySet.size());
        pr.close();

    }












    public static void traverse(int one, int two, int three, Set<lock> keys) {
        lock john=new lock(one,two, three);



    }
    //wrapper function

    public static void traverse(lock l, Set<lock> set) {
        traverse(l.first, l.second, l.third, set);
    }

    //wrapper
    public static boolean isValid(lock l) {
        return isValid(l.first, l.second, l.third);
    }

    public static boolean isValid(int one, int two, int three) {


        if(Math.abs(one-john[0])>2 && Math.abs(one-master[0])>2) {
            return false;
        } else if(Math.abs(two-john[1])>2 && Math.abs(two-master[1])>2) {
            return false;
        } else if(Math.abs(three-john[2])>2 && Math.abs(three-master[2])>2) {
            return false;
        }
        return true;
    }


}

class lock {
    int first, second, third;
    lock(int x, int y, int z) {
        first=x;
        second=y;
        third=z;
    }
    @Override
    public String toString() {
        return first+","+second+","+third;

    }
    @Override
    public boolean equals(Object other) {

        if(other==null || !(other instanceof lock)) {
            return false;
        }
        lock compared=(lock) other;
        if(this.first==compared.first && this.second==compared.second && this.third==compared.third) {
            return true;
        }
        else {
            return false;
        }
    }
}