
/**
 * Created by ryan on 1/20/17.
 */
/*
ID:1tolsma1
LANG:JAVA
TASK:gift1
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class gift1 {

    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new FileReader("gift1.in"));
        PrintWriter pr=new PrintWriter("gift1.out");

        int numPeople=Integer.parseInt(br.readLine());
        ArrayList<String> people=new ArrayList<>();
        int[] money=new int[numPeople];
        //populates people array
        for(int i=0; i<numPeople; i++) {
            people.add(br.readLine());
        }

        String temp="";
        StringTokenizer st;
        String person;
        while((temp=br.readLine())!=null) {
            person=temp;
            int giverIndex=people.indexOf(person);
            st=new StringTokenizer(br.readLine());
            int cash=Integer.parseInt(st.nextToken());
            int friends=Integer.parseInt(st.nextToken());
            if(friends ==0) continue;

            money[giverIndex]=money[giverIndex]-cash+cash%friends;

            for(int i=0; i<friends; i++) {
                money[people.indexOf(br.readLine())]+=cash/friends;
            }


        }

        for(int k=0; k<numPeople; k++) {
            pr.println(people.get(k)+" "+money[k]);
        }
        pr.close();
    }

}
