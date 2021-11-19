import java.util.*;
class Studentss{
    private String num;
    private String name;
    private int age;
    private char sex;
    private String major;
    private String classes;
    public Studentss(){}
    public Studentss(String num,String name,int age,char sex,String major,String classes){
        this.num=num;
        this.name=name;
        this.age=age;
        this.sex=sex;
        this.major=major;
        this.classes=classes;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    //省略部分getter和setter方法
}

class Course{
    private String id;
    private String name;
    private int credithour;
    protected static int counter;
    private ArrayList <Studentss>stulist=new ArrayList<Studentss>();
    public Course(){}
        public Course(String id,String name,int credithour){
            this.id=id;
            this.name=name;
            this.credithour=credithour;
        }
        public void bookCourse(Studentss stu){
            stulist.add(stu);
            counter++;
        }
        public void getMessage(){
            System.out.println("课程编号："+id);
            System.out.println("课程名称："+name);
            System.out.println("课程学分："+credithour);
            System.out.println("已选课人数："+counter);
            System.out.println("选课人名单：");
            for(int i=0;i<stulist.size();i++)
                System.out.println(((Studentss)stulist.get(i)).getName());
        }
}

//File:CourseTest.java
public class CourseTest{
    public static void main(String[] args){
        Course courseA=new Course("1001","数据结构",3);
        Course courseB=new Course("1003","操作系统",2);
        Studentss stuA=new Studentss("001","小刚",20,'M',"软件工程","三班");
        Studentss stuB=new Studentss("002","小红",21,'f',"软件工程","五班");
        Studentss stuC=new Studentss("003","张三",22,'M',"软件工程","二班");
        Studentss stuD=new Studentss("004","莉莉",19,'f',"软件工程","四班");
        courseA.bookCourse(stuA);
        courseA.bookCourse(stuB);
        courseB.bookCourse(stuC);
        courseB.bookCourse(stuD);
        courseA.getMessage();
        courseB.getMessage();
        System.out.println("共有"+Course.counter+"个学生已选课");
    }
}