package jdk;

import java.util.*;

public class IteratorTest {
    public static void main(String []args){
        Collection books=new HashSet();
        books.add("轻量级Java EE企业应用实战");
        books.add("疯狂Java讲义");
        books.add("疯狂Andriod讲义");
        Iterator it=books.iterator();
        while(it.hasNext()){
            String book=(String)it.next();   //it.next() 是 Object类型
            System.out.println(book);
            if(book.equals("疯狂Java讲义"))it.remove();  //删除集合里上一次next方法返回的函数
            book="测试字符串";
        }
        System.out.println(books);
    }
    
}
