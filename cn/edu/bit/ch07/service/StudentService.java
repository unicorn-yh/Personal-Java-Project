package cn.edu.bit.ch07.service;
import cn.edu.bit.ch07.entity.Student;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StudentService {
    List<Student> parseStudent();
    void sortStudentByBirthday(List<Student> target, boolean asc);
    Map<String, List<Student>> groupStudentByClasses(List<Student> target);
    Set<Student> distinctStudent(List<Student> target);
}
