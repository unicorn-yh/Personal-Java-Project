package cn.edu.bit.ch07;
import cn.edu.bit.ch07.dao.StudentDatas;
import cn.edu.bit.ch07.service.StudentService;
import cn.edu.bit.ch07.service.StudentServiceImpl;
import java.util.*;

public class StudentTest {
    public static void main(String[] args) {
        StudentService studentService = new StudentServiceImpl();
        System.out.println("Parse Student");
        studentService.parseStudent();
        System.out.println("\nSort Student By Birthday");
        System.out.println("Input 1 for ascending or 0 for descending:");
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        boolean asc;
        if(a==1)asc=true;
        else asc=false;
        studentService.sortStudentByBirthday(StudentServiceImpl.st, asc);
        System.out.println("\nGroup Student by Classes");
        studentService.groupStudentByClasses(StudentServiceImpl.st);
        System.out.println("\nDistinct Student:");
        studentService.distinctStudent(StudentServiceImpl.st);
        sc.close();
    }
}
