package jdk;
import java.util.*;
public class Generic {
    public static void main(String args[]){
        List<String>books=new ArrayList<>();
        List<String>books2=new ArrayList<String>();
        Map<String,Integer>maps=new HashMap<>();
        books.add(new String("one"));
        String s=books.get(0);
    }
}
