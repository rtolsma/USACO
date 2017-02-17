/*
ID:1tolsma1
LANG:JAVA
TASK:ariprog
*/

/**
 * Created by ryan on 2/12/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/*

After a near all nighter, and approximately 6 hours of work. I have created a solution
an optimized solution that works within the time constraints (finally :)


 */

public class ariprog {
    static TreeSet<Integer> differences;
    static TreeSet<Integer> bisquares;
    static int length;
    static int size;
    static int[] many;
    static boolean[] primes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter pr = new PrintWriter("ariprog.out");

        length=Integer.parseInt(br.readLine());
        size=Integer.parseInt(br.readLine());
        many=new int[2*size*size+1];
        primes=new boolean[2*size*size+1];
        int MAX=2*size*size;
         differences=new TreeSet<>();
         bisquares=new TreeSet<>();
       //testing time
       long start=System.currentTimeMillis();
        generate(bisquares, size);
        System.out.println(System.currentTimeMillis()-start);
        start=System.currentTimeMillis();
        ArrayList<Sequence> pairs=new ArrayList<>();




        for(int i=0; i<primes.length; i++) {
            if(primes[i]) {

                int step=1;
                    while(step*(length-1)+i<=MAX) {

                    if(sequence(length, i, step)) {
                        pairs.add(new Sequence(i, step));
                    }

                        step++;
                }

            }

        }
        Collections.sort(pairs, new Sequence(0,0));

        for(Sequence s: pairs) {
            pr.println(s.a+ " "+s.b);
        }
        if(pairs.isEmpty()) {
            pr.println("NONE");
        }


        System.out.println(System.currentTimeMillis()-start);
        pr.close();


    }



    public static boolean sequence(int length, int a, int b) {

        if(many[b]<length) return false; //returns false if there aren't enough ocurrences
        for(int i=0; i<length; i++) {

            if(!primes[a+i*b]) return false;
        }
        return true;

    }



    public static void generate(TreeSet<Integer> squares, int depth) {
         //stores by index which numbers are prime
        //int max=Integer.MIN_VALUE;
        for(int i=0; i<=depth; i++) {//switch depth with 25
            for (int j = i; j <= depth; j++) { //try setting j=i next time
                int value = (int) (Math.pow(i, 2) + Math.pow(j, 2)); //creates all combos of primes
                primes[value]=true;//sets that index to true
                //if(value>max) max=value; //stores max prime value to save iterative searching
            }
        }

        //create differences
        for(int i=0; i<primes.length; i++) {
            if(primes[i]) {
                for(int j=i; j<primes.length; j++) {
                    int diff=Math.abs(i-j);
                    many[diff]++;
                }
            }

        }


        //take out difference values that don't have enough
        for( int k=0; k<many.length; k++) {
            if(many[k]>=length-1) {
                differences.add(k);
            }
            /*if(primes[k]) {
                squares.add(k);
            }
            */
        }

    }

}

class Sequence implements Comparator<Sequence> {
    int a;
    int b;

    public Sequence(int x, int y) {
        a = x;
        b = y;
    }

    @Override
    public int compare(Sequence one, Sequence other) {
        if (one.b == other.b) {
            if (one.a > other.a) {
                return 1;
            }
            else if (one.a < other.a) {
                return -1;
            }
        } else if (one.b > other.b) {
            return 1;
        }
        return -1;

    }
}