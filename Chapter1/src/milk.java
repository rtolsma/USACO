/*
ID:1tolsma1
LANG:JAVA
TASK:milk
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

public class milk {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milk.in"));
        PrintWriter pr = new PrintWriter("milk.out");
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numMilk = Integer.parseInt(st.nextToken());
        int numFarmers = Integer.parseInt(st.nextToken());
        int cost = 0;
        ArrayList<Farmer> farmers = new ArrayList<>();

        for (int i = 0; i < numFarmers; i++) {
            st = new StringTokenizer(br.readLine());
            farmers.add(new Farmer(Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken())));
        }

        Collections.sort(farmers, new Farmer());

        for (int k = 0; k < farmers.size() && numMilk > 0; k++) {
            Farmer fred = farmers.get(k);
            if (numMilk >= fred.amount) {
                numMilk -= fred.amount;
                cost += fred.cost(fred.amount);
                farmers.remove(k);
                k--;
            } else if (numMilk < fred.amount) {
                cost += fred.cost(numMilk);
                numMilk = 0;
            }
        }
        pr.println(cost);
        pr.close();
    }


}

class Farmer implements Comparator<Farmer> {
    int price;
    int amount;

    public Farmer(int dollars, int quantity) {
        price = dollars;
        amount = quantity;
    }

    //for the comparator
    public Farmer() {
        this(0, 0);
    }

    public double cost(int quantity) {
        return price * quantity;
    }


    public int compare(Farmer one, Farmer two) {

        if (one.price > two.price) return 1;
        else if (two.price > one.price) return -1;
        else return 0;
    }
}
