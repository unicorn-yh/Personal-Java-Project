public class Student1{
    private String id;   //学号
    private int classId; //班号
    private String name; //姓名
    private String sex;  //性别
    private int age;    //年龄
    public void setId(String id) {   //设置学生id
        this.id = id;
    } 
    public String getId(){    //获取学生id
        return id;
    } 
    public void setClassId(int classId){  //设置班级id
        this.classId = classId;
    
    }
    public int getClassId() {   //获取班级ID
        return classId;
    }
    public void setAge(int age){  //设置年龄
        this.age = age;
    }
    public int getAge(){   //获取年龄
        return age;
    }
    public void setName(String name){  //设置姓名
        this.name = name;
    }
    public String getName(){    //获取姓名
        return name;
    }
    public void setSex(String sex){  //设置性别
        this.sex = sex;
    }
    public String getSex() {  //获取性别
        return sex;
    }
    public String toString() { //重写toString()
        return "学生号："+id+" 班级号："+classId+" 姓名："+name+" 性别："+sex+" 年龄："+age;
    }
    public static void main(String args[]) {
        Student1 s = new Student1();
        s.setId("201190609112");
        s.setClassId(111-1);
        s.setName("张星龙");
        s.setSex("男");
        s.setAge(21);
        System.out.println(s);//打印内容为重写toString()后的内容
    }
}
    
    