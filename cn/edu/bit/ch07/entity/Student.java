package cn.edu.bit.ch07.entity;

public class Student {
    public static String studInfo=new String();
    public String name,studclass,id,date=new String(),sex,s;
    public void setName(String name){
        this.name=name;
    }
    public void setClass(String studclass){
        this.studclass=studclass;
    }
    public void setId(String id){
        this.id=id;
    }
    public void setDate(String date){
        this.date=date;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public void setStudInfo(String s){
        this.s=s;
    }
    public String getStudInfo(){
        return s;
    }
    public String getName(){
        return name;
    }
    public String getStudClass(){
        return studclass;
    }
    public String getId(){
        return id;
    }
    public String getSex(){
        return sex;
    }
    public String getDate(){
        return date;
    }
}
