package pk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
//import java.lang.*;
//import java.time.LocalDate;

class StudentDatas{
    private static String datas;
    static {
        StringBuilder sb = new StringBuilder();
        sb.append("李海宁,男,1120190409,63011920,1999-9-21;");
        sb.append("田雨禾,女,1120190727,63011918,2000-3-20;");
        sb.append("吕骄,男,1120190871,63011920,2001-5-3;");
        sb.append("戴英特,男,1120191175,63011918,1999-6-10;");
        sb.append("张乐怡,女,1120193641,63011917,2000-3-21");
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
    public String date,data;
    void setDate(String date){
        this.date=date;
    }
    void setData(String data){
        this.data=data;
    }
}

class ParseSort implements StudentService{
    public static Student[] s=new Student[5];
    public Student[] parseStudent(){
        for(int i=0;i<s.length;i++)s[i]=new Student();
        s[0].setData("李海宁,男,1120190409,63011920,1999-9-21");
        s[0].setDate("1999-9-21");
        s[1].setData("田雨禾,女,1120190727,63011918,2000-3-20");
        s[1].setDate("2000-3-20");
        s[2].setData("吕骄,男,1120190871,63011920,2001-5-3");
        s[2].setDate("2001-5-3");
        s[3].setData("戴英特,男,1120191175,63011918,1999-6-10");
        s[3].setDate("1999-6-10");
        s[4].setData("张乐怡,女,1120193641,63011917,2000-3-21");
        s[4].setDate("2000-3-21");
        return s;
    }

    public void sortStudentByBirthday(Student[] target, boolean asc) throws ParseException{
        String temp;
        SimpleDateFormat sdformat=new SimpleDateFormat("yyyy-MM-dd");
        if(asc==false){
           for(int i=0;i<5;i++){
               for(int j=0;j<4-i;j++){
                   Date d1=sdformat.parse(target[j].date);
                   Date d2=sdformat.parse(target[j+1].date);
                   if(d2.compareTo(d1)>0){
                    temp=target[j].date;
                    target[j].date=target[j+1].date;
                    target[j+1].date=temp;
                    temp=target[j].data;
                    target[j].data=target[j+1].data;
                    target[j+1].data=temp;
                   }
               }
           }
           
        }
        else{
            for(int i=0;i<5;i++){
                for(int j=0;j<4-i;j++){
                    Date d1=sdformat.parse(target[j].date);
                    Date d2=sdformat.parse(target[j+1].date);
                    if(d2.compareTo(d1)<0){
                        temp=target[j].date;
                        target[j].date=target[j+1].date;
                        target[j+1].date=temp;
                        temp=target[j].data;
                        target[j].data=target[j+1].data;
                        target[j+1].data=temp;
                    }
                }
            }
        }
    }

    void studPrint(Student[] t){
        for(int i=0;i<5;i++)System.out.println(t[i].data);
    }
}

public class SortStudent{
    public static void main(String args[]) throws ParseException{
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
