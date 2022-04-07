import java.util.*;
import java.io.*;
import java.net.*;


public class test{
    public static void main(String args[]){
        String dir="c:/users/user/desktop/java-1223/url.html";
        String dir2="d:/url.txt";

        try{
            File fread=new File("C:/Users/User/Desktop/java-1223/project_dataset/swissprot_data11.tsv");
            BufferedReader br=new BufferedReader(new FileReader(fread));
            //BufferedWriter bw=new BufferedWriter(new FileWriter(dir));
            //PrintWriter pw=new PrintWriter(bw);
            //BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir2)));
            String temp=null,s;
            StringBuffer sb=new StringBuffer();
            
            while((temp=br.readLine())!=null){
                //StringTokenizer st= new StringTokenizer(temp,"\t");
                //while(st.hasMoreTokens()){
                    //s=st.nextToken();
                    for(int i=0;i<=temp.length();i++){
                        System.out.print(temp.split("\t")[i]+"*");
                    }
                    //System.out.println(temp.split("\t")[2]);
                //}
              System.out.println("\n");
            }
            
            //System.out.println("----------------------------------");
            //System.out.println("网页"+dir+"的内容保存完成，保存在"+dir+"文件中，请注意查看");
            br.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}