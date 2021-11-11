package cn.edu.bit.ch07.service;
import cn.edu.bit.ch07.dao.StudentDatas;
import cn.edu.bit.ch07.entity.Student;
import java.text.*;
import java.util.*;
import java.io.*;

public class StudentServiceImpl implements StudentService {
    public static List<Student>st=new LinkedList<Student>();
    public List<Student> parseStudent() {
        Student temp=new Student();
        try{
            File openFile=new File("C:\\cn\\edu\\bit\\ch07","students.txt");
            Scanner fileScanner=new Scanner(openFile);
            String currentline=fileScanner.nextLine();
            while(fileScanner.hasNextLine()){
                Student.studInfo+=currentline;
                System.out.println(currentline);
                currentline=fileScanner.nextLine();
            }
            Student.studInfo+=currentline;
            System.out.println(currentline);
            fileScanner.close();
        }
        catch(IOException e){
            System.out.println("Error receiving datas."+e);
        }

        StringBuilder bf=new StringBuilder();
        int flag=0,j=0,t=0,finish=0;
        String s=Student.studInfo;
        for(int h=0;h<60;h++){
            temp=new Student();
            for(j=t;;j++,t++){
                if(s.charAt(j)=='\0'){
                    finish=1;
                    break;
                }
                if(s.charAt(j)==','||s.charAt(j)==';')break;
                bf.append(s.charAt(j));
            }
            if(finish==1)break;
            if(flag==0)temp.setName(bf.toString()); 
            else if(flag==1)temp.setSex(bf.toString());
            else if(flag==2)temp.setId(bf.toString());
            else if(flag==3)temp.setClass(bf.toString());
            else if(flag==4){
                temp.setDate(bf.toString());
                st.add(temp);
                flag=-1;
            }   
            ++flag;
            bf=new StringBuilder();
        } 
        return null;
    }

    @Override
    public void sortStudentByBirthday(List<Student> targets, boolean asc) {
        Student temp1,temp2;
        String m="";
        int k=-1;
        String target[]=new String[st.size()];
        for(Student s:targets){
            m=s.getDate();
            target[++k]=m;
        }
        try{
            SimpleDateFormat sdformat=new SimpleDateFormat("yyyy-MM-dd");
            if(asc==false){
                for(int i=0;i<targets.size();i++){
                    for(int j=0;j<targets.size()-1-i;j++){
                        Date d1=sdformat.parse(target[j]);
                        Date d2=sdformat.parse(target[j+1]);
                        if(d2.compareTo(d1)>0){
                            temp1=targets.get(j);
                            temp2=targets.get(j+1);
                            targets.set(j,temp2);
                            targets.set(j+1,temp1);
                        }
                    }
                }
           
            }
            else{
                for(int i=0;i<targets.size();i++){
                    for(int j=0;j<targets.size()-1-i;j++){
                        Date d1=sdformat.parse(target[j]);
                        Date d2=sdformat.parse(target[j+1]);
                        if(d2.compareTo(d1)<0){
                            temp1=targets.get(j);
                            temp2=targets.get(j+1);
                            targets.set(j,temp2);
                            targets.set(j+1,temp1);
                        }
                    }
                }
            }
        }
        catch(ParseException e){
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, List<Student>> groupStudentByClasses(List<Student> target) {
        Map<String, List<Student>> map=new HashMap<>();
        for(Student s:target){
            List<Student> tmplist=map.get(s.studclass);
            if(tmplist==null){
                tmplist=new ArrayList<>();
                tmplist.add(s);
                map.put(s.studclass,tmplist);
            }
            else tmplist.add(s);
           // Map<String,List<Student>>str1=new HashMap<>();
            /*Set<Map.Entry<String,List<Student>>>entry1=str1.entrySet();
            for(Object o:entry1){
                System.out.println(o);
            }*/

            
            /*for(Map.Entry<String, List<Student>> entry:map.entrySet()){
                System.out.print("key-->\t"+entry.getKey());
                System.out.println("value-->"+entry.getValue());
            }*/
            for(Object key:map.keySet()){
                System.out.println(key+"-->"+map.get(key));  //输出key和value
            }
        }
        return null;
    }
    
    @Override
    public Set<Student> distinctStudent(List<Student> target) {
        Set<Student>set=new HashSet<Student>();
        for(Student s:target){
            set.add(s);
            System.out.println(s.getStudInfo());
        }  
        return null;
    }
}
