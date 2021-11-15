package jdk;
import java.util.*;
public class ListTest3 {
    public static void main(String args[]){
        List books=new ArrayList();
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("疯狂Java讲义"));
        books.add(new String("疯狂Andriod讲义"));
        books.add(new String("疯狂iOS讲义"));
        books.sort((o1,o2)->((String)o1).length()-((String)o2).length());  //目标类型为 Comparator(Lamda 形式)，排序
        System.out.println(books);
        books.replaceAll(ele->((String)ele).length());    //目标类型为 UnaryOperator(Lamda 形式)，替换集合中所有元素
        System.out.println(books);
    }
}
