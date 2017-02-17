/*
ID:1tolsma1
LANG:JAVA
TASK:wormhole
*/

/**
 * Created by ryan on 2/5/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;

public class wormhole {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("wormhole.in"));
        PrintWriter pr = new PrintWriter("wormhole.out");
        StringTokenizer st;
        int num=Integer.parseInt(br.readLine());
        ArrayList<Hole> holes=new ArrayList<Hole>();

        ArrayList<ArrayList<Hole>> allHoles;
        for(int h=0; h<num; h++) {
            st=new StringTokenizer(br.readLine());
            holes.add(new Hole(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int total=0;
        /*
        Starting with number 1, go through each pair and see if it works
        then all done. If one doesn't start with that one and go to each pair
         */
        for(int k=0; k<num;k++) {
            for (int i = 0; i < num; i++) {
                for (int j = 1 + i; j < num; j++) {

                    if (holes.get(i).connected(holes.get(j))) {
                        //
                    }

                }
            }
        }



        pr.println(total);
        pr.close();
    }



    public static ArrayList<ArrayList<Hole>> combinations(ArrayList<Hole> set) {

        if(set.size()==0) {
            ArrayList<ArrayList<Hole>> toReturn=new ArrayList<>();
            toReturn.add(new ArrayList<>());
            return toReturn;
        }
        ArrayList<ArrayList<Hole>> result=new ArrayList<>();
        Hole current=set.remove(0);
        ArrayList<ArrayList<Hole>> recursiveReturn=combinations(set);
        for (ArrayList<Hole> li : recursiveReturn) {

            for (int index = 0; index <= li.size(); index++) {
                ArrayList<Hole> temp = (ArrayList<Hole>) li.clone();
                temp.add(index, current);
                result.add(temp);
            }

        }
        return result;
    }


    public static ArrayList<Pair> getPairs(ArrayList<Hole> hole) {
        ArrayList<Pair> p=new ArrayList<>();
                for(int i=0; i<hole.size()/2; i++) {
                    p.add(new Pair(hole.get(2*i), hole.get(2*i+1)));
                }
        return p;
    }


    public static boolean isValid(ArrayList<Pair> pairs) {
        for(int i=0; i<pairs.size(); i++) {
            Pair current=pairs.get(i);

            if(current.isLoop()) {
                return true;
            }
            for(int j=i+1; j<pairs.size(); j++) {

               if(current.isConnected(pairs.get(j))) {
                   return true;
               }
            }

        }
        return false;

    }

    public static void swap(int i, int j, ArrayList list) {
        if(i<0 || j<0 || i>=list.size() || j>=list.size()||j==i) {
            return;
        }
        Object one=list.get(i);
        Object two=list.get(j);
        list.set(i, two);
        list.set(j, one);
    }


}

class Pair implements Comparator<Pair> {

    Hole first;
    Hole second;
    boolean loop;
    public Pair(Hole x, Hole y) {
        first=x;
        second=y;
        if(first!=null && second!=null)
        loop=first.connected(second);
    }
    public boolean isLoop() {
        return loop;
    }

    public boolean isConnected(Pair other) {
        if(isLoop() && other.isLoop()) {
            return true;
        } else if(first.connected(other.first) || first.connected(other.second)) {

            if(second.connected(other.first) || second.connected(other.second)) {
                return true;
            }


        }
        return false;
    }
    @Override
    public boolean equals(Object other) {
        if(other==null || !(other instanceof Pair)) {
            return false;
        }
        Pair temp=(Pair) other;
        if(first.equals(temp.first) && second.equals(temp.second)) {
            return true;
        } else if(first.equals(temp.second) && second.equals(temp.first)) {
            return true;
        }
        return false;

    }

    @Override
    public int compare(Pair one, Pair two) {
        if(one.first.x>two.first.x) return 1;
        else if(one.first.x==two.first.x) {
            if(one.first.y==two.first.y) return 0;
            else return one.first.y>two.first.y ? 1 :0;
        }
        else {
            return -1;
        }
    }

}

class Hole {
    int x;
    int y;
    HashSet<Hole> pairs=new HashSet<>();
    public Hole(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public boolean connected(Hole other) {
        if (this.x == other.x || this.y == other.y) {
            pairs.add(other);
            return true;
        }
        return false;
    }
        public void addPair(Hole hole) {
        pairs.add(hole);

    }

    @Override
    public boolean equals(Object o) {
        if(o==null || !(o instanceof Hole)) return false;

        Hole temp=(Hole) o;
        if(temp.x==this.x && temp.y==y) {
            return true;
        }
        return false;
    }
}
