package jdk;
import java.util.*;
public class MapTest {
    public static void main(String args[]){
        Map map=new HashMap();
        map.put("轻量级Java EE企业应用实战",109);
        map.put("疯狂Java讲义",10);
        map.put("疯狂Andriod讲义",79);
        map.put("疯狂iOS讲义",99);
        map.put("疯狂Andriod讲义",89);
        System.out.println(map.put("疯狂iOS讲义",10));  //输出10，10之后覆盖了99
        System.out.println(map);
        System.out.println("是否包含值为疯狂iOS讲义key:"+map.containsKey("疯狂iOS讲义"));  //输出true
        for(Object key:map.keySet()){
            System.out.println(key+"-->"+map.get(key));
        }
        map.remove("疯狂Ajax讲义");
        System.out.println(map);
    }
}
