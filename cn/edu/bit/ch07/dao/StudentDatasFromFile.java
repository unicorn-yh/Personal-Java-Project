package cn.edu.bit.ch07.dao;
import java.io.IOException;

public class StudentDatasFromFile extends StudentDatas {
    @Override
    public String getStudents() throws IOException {
        int k=1;
        if(k==1) throw new IOException("Invalid path to obtain datas.");
        return null;
    }
}
