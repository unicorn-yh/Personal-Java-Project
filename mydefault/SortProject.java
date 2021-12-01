import java.util.*;
//import java.lang.*;
//import java.time.LocalDate;
class StudentDatas {
    private static String datas;
    static {
        StringBuilder sb = new StringBuilder();
        sb.append("lhn,m,1120190409,63011920,1999-9-21;");
        sb.append("tyh,f,1120190727,63011918,2000-3-20;");
        sb.append("lj,m,1120190871,63011920,2001-5-3;");
        sb.append("dyt,m,1120191175,63011918,1999-6-10;");
        sb.append("zly,f,1120193641,63011917,2000-3-21;");
        datas = sb.toString();
    }
    public static String getStudents() {
        return datas;
    }
}

interface StudentService {
    public Student[] s=new Student[5];
    static Student[] parseStudent(){
        return s;
    }
    static void sortStudentByBirthday(Student[] target, boolean asc){};
}

class Student {
    String name;
    String sex;
    String year;
    String index;
    String classNo;
    void set(String name,String sex,String index,String classNo,String year){
        this.name=name;
        this.sex=sex;
        this.index=index;
        this.classNo=classNo;
        this.year=year;
    }
    void setName(String name){
        this.name=name;
    }
    void setSex(String sex){
        this.sex=sex;
    }
    void setIndex(String index){
        this.index=index;
    }
    void setClassNo(String classNo){
        this.classNo=classNo;
    }
    void setYear(String year){
        this.year=year;
    }
    int getYear(){
        return Integer.parseInt(year);
    }

}

class ParseSort implements StudentService{
    public static Student[] s=new Student[5];
    public Student[] parseStudent(){
        String studentDatas;
        int temp=0;
        int beginIndex=0;
        int k;
        studentDatas=StudentDatas.getStudents();
        for(int i=0;i<s.length;i++){
            s[i]=new Student();
            k=studentDatas.indexOf(",",temp);
            s[i].setName(studentDatas.substring(beginIndex,k-1));
            temp=k;
            beginIndex=k+1;
            k=studentDatas.indexOf(",",temp);
            s[i].setSex(studentDatas.substring(beginIndex,k-1));
            temp=k;
            beginIndex=k+1;
            k=studentDatas.indexOf(",",temp);
            s[i].setIndex(studentDatas.substring(beginIndex,k-1));
            temp=k;
            beginIndex=k+1;
            k=studentDatas.indexOf(",",temp);
            s[i].setClassNo(studentDatas.substring(beginIndex,k-1));
            temp=k;
            beginIndex=k+1;
            k=studentDatas.indexOf(";",temp);
            s[i].setSex(studentDatas.substring(beginIndex,k-1));
            temp=k;
            beginIndex=k+1;
        }
        return s;
    }

    public void sortStudentByBirthday(Student[] target, boolean asc){
        String temp;
        if(asc==true){
            for(int i=0;i<4;i++){
                for(int j=0;j<3;j++){
                    if(target[j+1].getYear()<target[j].getYear()){
                        temp=target[j].year;
                        target[j].year=target[j+1].year;
                        target[j+1].year=temp;
                        temp=target[j].name;
                        target[j].name=target[j+1].name;
                        target[j+1].name=temp;
                        temp=target[j].sex;
                        target[j].sex=target[j+1].sex;
                        target[j+1].sex=temp;
                        temp=target[j].classNo;
                        target[j].classNo=target[j+1].classNo;
                        target[j+1].classNo=temp;
                        temp=target[j].index;
                        target[j].index=target[j+1].index;
                        target[j+1].index=temp;

                    }
                }
            }  
        }
        else{
            for(int i=0;i<4;i++){
                for(int j=0;j<3;j++){
                    if(target[j+1].getYear()>target[j].getYear()){
                        temp=target[j].year;
                        target[j].year=target[j+1].year;
                        target[j+1].year=temp;
                        temp=target[j].name;
                        target[j].name=target[j+1].name;
                        target[j+1].name=temp;
                        temp=target[j].sex;
                        target[j].sex=target[j+1].sex;
                        target[j+1].sex=temp;
                        temp=target[j].classNo;
                        target[j].classNo=target[j+1].classNo;
                        target[j+1].classNo=temp;
                        temp=target[j].index;
                        target[j].index=target[j+1].index;
                        target[j+1].index=temp;
                    }
                }
            }
        }
    }

    void studPrint(Student[] t){
        for(int i=0;i<5;i++)
            System.out.println(t[i].name+","+t[i].sex+","+t[i].index+","+t[i].classNo+","+t[i].year);
    }
}

public class SortProject{
    public static void main(String args[]){
        System.out.println("Input 1 for ascending or 0 for descending:");
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        boolean asc;
        if(a==1)asc=true;
        else asc=false;
        ParseSort stud=new ParseSort();
        stud.parseStudent();
        stud.sortStudentByBirthday(ParseSort.s, asc);
        if(asc==true)System.out.println("Ascending order:");
        else System.out.println("Descending order:");
        stud.studPrint(ParseSort.s);
        sc.close();
    }
}
