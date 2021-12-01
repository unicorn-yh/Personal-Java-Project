class Person{
    String name;
    int age;
    static int x;
    Person(String s,int a){
        name=s;
        age=a;
    }
    Person(String s){
        name=s;
        age=-1;
    }
    Person(int age,String name){
        this.age=age;
        this.name=name;
    }
    Person(){
        this(0,"");
    }
    void sayHello(){
        System.out.println("Hello! My name is"+name);
    }
    void sayHello(Person another){
        System.out.println("Hello,"+another.name+"! My name is"+name);
    }
    boolean isOlderThan(int a){
        return (age>a)?true:false;
    }
    public static void main(String args[]){
        System.out.println("Hello World!");
    }
}
class Student2 extends Person{
    String school;
    int score;
    void sayHello(Student2 another){
        System.out.println("Hi!");
        if(school==another.school)System.out.println("Shoolmates");
    }
    boolean isGoodStudent(){
        return score>=90;
    }
    void testThisSuper(){};
    void sayHello(){
        super.sayHello();
        System.out.println("My school is"+school);
    }
    public void setClassId(int i) {
    }
    public void setName(String string) {
    }
}
