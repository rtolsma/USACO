/*
ID:1tolsma1
LANG:JAVA
TASK:namenum
*/

/**
 * Created by ryan on 1/23/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.zip.CheckedInputStream;

public class namenum {

    public static void main(String[] args) throws IOException {
        long start=System.currentTimeMillis();

        BufferedReader br = new BufferedReader(new FileReader("Data/namenum.in"));
        PrintWriter pr = new PrintWriter("namenum.out");
        BufferedReader dictionary=new BufferedReader(new FileReader("Data/dict.txt"));

        //***FILLING DICTIONARY
        ArrayList<String> dic=new ArrayList<>();

        String temp="";
        while((temp=dictionary.readLine())!=null) {
            dic.add(temp);
        }

        //****END DICTIONARY

        String id=br.readLine();
        int[] ids=new int[id.length()];

        for(int k=0; k<id.length(); k++) {
            ids[k]=Integer.parseInt(id.substring(k,k+1));
        }
        ArrayList<String> words=new ArrayList<>();
        String build="";
        recurse(words, dic, build, id, 0);


        for(String word: words) pr.println(word);
        if(words.isEmpty()) pr.println("NONE");
        pr.close();


        System.out.println((System.currentTimeMillis()-start)/1000);
    }

    //for each word add combo to the next one
    public static void recurse(ArrayList<String> words, ArrayList<String> dic, String current, String id, int depth) {
        if(depth==id.length()) {
          //  if(isValid(current, dic ))
            if(isValid(current, dic)) {
                words.add(current);

            }
            //Debug
            return;
        }

        if(!verify(current, dic, depth)) {
            return;
        }

        int number=Integer.parseInt(Character.toString(id.charAt(depth)));

        for(int i=0; i<3; i++) {
            char next= 'A';
            next+=(number-2)*3+i;
            if(next>='Q') next++;
            recurse(words, dic,current+ Character.toString(next), id, depth+1);
        }

    }

    public static boolean isValid(String key, ArrayList<String> dic) {
        int low=0,high=dic.size()-1;
        int mid;
        while(low<=high) {
            mid=low+(high-low)/2;
            String midString=dic.get(mid);
            if(key.equals(midString)) return true;
            else if(key.compareTo(midString)>0) low=mid+1;
            else if(key.compareTo(midString)<0) high=mid-1;

        }
        return false;

    }

    public static boolean verify(String key, ArrayList<String> dic, int depth) {

        if(key.equals("")) return true;
        int low=0, high=dic.size()-1;
        int mid=low;
        for(int i=0; low<=high; i++) {  //&&i<depth
            mid=low+(high-low)/2;
            String midString=dic.get(mid);
            if(midString.startsWith(key)) return true;
            else if(key.compareTo(midString)>0) low=mid+1;
            else if(key.compareTo(midString)<0) high=mid-1;
        }
        if(dic.get(mid).startsWith(key)) return true;

        return false;

    }

}
