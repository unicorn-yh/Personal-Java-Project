package ExcelPrac;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.*;

class VariantVirus{
    String filetype,sortMethod,filename="";
    static String loc="变种病毒";
    String head[]=new String[6];
    List<Variant> tmpv=new ArrayList<>();
    List<Variant>datalist=new ArrayList<Variant>();
    Scanner tt=new Scanner(System.in);
    VariantVirus(){}
    VariantVirus(String sortMethod){
        this.sortMethod=sortMethod;
    }
    VariantVirus(String sortMethod,String loc){
        this.sortMethod=sortMethod;
        VariantVirus.loc=loc;
    }
    void getData(){
        File fread=new File("C:/Users/User/Desktop/java-1223/javaProject/covid-19-data-master/public/data/variants/covid.txt");
        try{
            Reader in = new FileReader(fread);
            BufferedReader br=new BufferedReader(in);
            String n=null,s;
            int flag=0,i=1;
            while((n=br.readLine())!=null){
                Variant v=new Variant();
                StringTokenizer parse=new StringTokenizer(n,",");
                while(parse.hasMoreTokens()){
                    s=parse.nextToken();
                    if(flag==0){
                        head[i-1]=s;i++;
                        if(i==7){
                            flag=1;
                            i=1;
                        }
                    } //表头
                    else{
                        if(i==1){v.setLocation(s);i++;}
                        else if(i==2){v.setDate(s);i++;}
                        else if(i==3){v.setVariant(s);i++;}
                        else if(i==4){v.setNumSeq(s);i++;}
                        else if(i==5){v.setPercSeq(s);i++;}
                        else{
                            v.setTotalNumSeq(s);
                            datalist.add(v);
                            //System.out.println(v.data());
                            i=1;
                        }
                    }     
                } 
            }
            br.close();
            tmpv=datalist;
        }
        catch(Exception e){
            e.getStackTrace();
        }
    }
    void parseData(){
        sort s=new sort();
        if(sortMethod==null){
            FileFunction.generateToExcel("变种病毒",datalist,head);
        }
        else if(sortMethod.equals("sortByLocation")) {
            tmpv=sort.sortByLocation(tmpv,loc);
            filename+="-"+loc;
        }
        else if(sortMethod.equals("sortByNumSeq")) {
            tmpv=s.sortByNumSeq(tmpv);
            filename+="-确诊人数";
        }  
        
    }
    void outputData(){
        filetype=FileFormat.setFileType();
        if(filetype.equals("xlsx")) FileFunction.generateToExcel(filename,tmpv,head);
        else if(filetype.equals("txt")) FileFunction.generateToTxt(filename,tmpv,head);
    }
}
class sort{
    static Scanner t=new Scanner(System.in);
    public static List<Variant> sortByLocation(List<Variant>tmp1,String loci){
        List<Variant>tmp2=new ArrayList<Variant>();
        tmp2=tmp1.stream().filter(s->s.getLocation().equalsIgnoreCase(loci)).collect(Collectors.toList());
        while(tmp2.size()==0) {
            System.out.println("数据中没有这个国家！请重新输入：");
            String f=t.next();
            VariantVirus.loc=f;
            tmp2=tmp1.stream().filter(s->s.getLocation().equalsIgnoreCase(f)).collect(Collectors.toList());
        }
        return tmp2;
    }
    List<Variant> sortByNumSeq(List<Variant>tmp1){
        List<Variant>tmp2=new ArrayList<Variant>();
        System.out.println("筛选确诊人数的方法：");
        System.out.println("1. 顺序排列");
        System.out.println("2. 逆序排列");
        System.out.println("3. 只显示非零的确诊数据");
        System.out.println("4. 只显示最高的前n组数据");
        int input=t.nextInt();
        while(input>4||input<1){
            System.out.println("输入数字不合法，请输入1-4之间的数字：");
            input=t.nextInt();
        }
        if(input==1)tmp2=tmp1.stream().sorted((s,o)->Integer.compare(Integer.parseInt(s.getNumSeq()),Integer.parseInt(o.getNumSeq()))).collect(Collectors.toList());
        else if(input==2)tmp2=tmp1.stream().sorted((o,s)->Integer.compare(Integer.parseInt(s.getNumSeq()),Integer.parseInt(o.getNumSeq()))).collect(Collectors.toList());
        else if(input==3)tmp2=tmp1.stream().filter(s->Integer.parseInt(s.getNumSeq())!=0).collect(Collectors.toList());
        else if(input ==4){
            System.out.println("请输入n：");
            int a=t.nextInt();
            while(a>tmp1.size()||a==0){
                System.out.println("输入错误！只有"+tmp1.size()+"组数据，请输入1-"+tmp1.size()+"之间的数字：");
                a=t.nextInt();
            }
            tmp2=tmp1.stream().limit(a).collect(Collectors.toList());
        }
        return tmp2;
    }
}
class virusMainSort{
    static Scanner t1=new Scanner(System.in);
    public static void mainSort(){
        System.out.println("根据以下筛选条件输出相应的序号。");
        System.out.println("筛选数据的方法：");
        System.out.println("1. 根据国家筛选");
        System.out.println("2. 根据日期筛选");
        System.out.println("3. 根据变种病毒的类型筛选");
        System.out.println("4. 根据确诊变种病毒的病患人数筛选");
        int input=t1.nextInt();
        while(input>6||input<1){
            System.out.println("输入数字不合法，请输入1-6之间的数字：");
            input=t1.nextInt();
        }
        String sortMethod;
        VariantVirus v=new VariantVirus();
        if(input==1){
            sortMethod="sortByLocation";
            System.out.println("请输出想要筛选出的国家的英语名：");
            String loc=t1.next();
            v=new VariantVirus(sortMethod,loc);
            v.getData();
            v.parseData();
        }
        else if(input==4){
            sortMethod="sortByNumSeq";
            v=new VariantVirus(sortMethod);
            v.getData();
            v.parseData();
        }
        System.out.println("是否继续筛选数据？是，扣1；不是，扣0；");
        
    }
}
class FileFormat{
    public static String setFileType(){
        Scanner s=new Scanner(System.in);
        System.out.println("请输出想要生成的文件格式的序号：");
        System.out.println("1. xlsx");
        System.out.println("2. txt");
        int a=s.nextInt();
        while(a>2||a<1){
            System.out.println("输入数字不合法，请输入1或2：");
            a=s.nextInt();
        }
        String fileformat;
        if(a==1)fileformat="xlsx";
        else fileformat="txt";
        //s.close();
        return fileformat;
    }
}
class Variant{
    String location,variant,date,num_sequences,perc_sequences,total_num_seq;
    String data(){
        return location+"\t"+date+"\t"+variant+"\t"+num_sequences+"\t"+perc_sequences+"\t"+total_num_seq;
    }
    void setLocation(String location){
        this.location=location;
    }
    void setDate(String date){
        this.date=date;
    }
    void setVariant(String variant){
        this.variant=variant;
    }
    void setNumSeq(String num_sequences){
        this.num_sequences=num_sequences;
    }
    void setPercSeq(String perc_sequences){
        this.perc_sequences=perc_sequences;
    }
    void setTotalNumSeq(String total_num_seq){
        this.total_num_seq=total_num_seq;
    }
    String getLocation(){
        return location;
    }
    String getVariant(){
        return variant;
    }
    String getDate(){
        return date;
    }
    String getNumSeq(){
        return num_sequences;
    }
    String getPercSeq(){
        return perc_sequences;
    }
    String getTotalNumSeq(){
        return total_num_seq;
    }
}
class FileFunction{
    static String path="C:/Users/User/Desktop/";
    public static void generateToExcel(String filename,List<Variant>list,String title[]){
        String fileType="xlsx";
        String excelPath=path+filename+"."+fileType;
        XSSFWorkbook wb=new XSSFWorkbook();
        XSSFSheet sheet=wb.createSheet("变种病毒sheet1");

        //添加表头  
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        row.setHeight((short) 540); 
        cell.setCellValue(filename);    //创建第一行  

        CellStyle style = wb.createCellStyle(); // 样式对象 
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)14);
        font.setBold(true);
        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直      
        style.setAlignment(HorizontalAlignment.CENTER);// 水平  
        style.setLocked(true); 
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setWrapText(true);// 指定当单元格内容显示不下时自动换行

        cell.setCellStyle(style); // 样式，居中

        // 单元格合并      
        // 四个参数分别是：起始行，起始列，结束行，结束列      
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));  
        sheet.autoSizeColumn(5200);

        String title1[]={"地点","日期","变种病毒","确诊人数","百分比","总共确诊人数"};
        row=sheet.createRow(2);    //创建第二行    
        for(int i=0;i<title.length;i++){  
            cell=row.createCell(i);  
            cell.setCellValue(title1[i]);  
            cell.setCellStyle(style); // 样式，居中
            sheet.setColumnWidth(i,20*256); 
        }  
        row.setHeight((short) 540); 

        row=sheet.createRow(2);    //创建第三行    
        for(int i=0;i<title.length;i++){  
            cell=row.createCell(i);  
            cell.setCellValue(title[i]);  
            cell.setCellStyle(style); // 样式，居中
            sheet.setColumnWidth(i,20*256); 
        }  
        row.setHeight((short) 540); 

        System.out.println("Processing data...");
        int j=3;
        for(Variant i:list){
            row=sheet.createRow(j++);
            row.setHeight((short) 500); 
            row.createCell(0).setCellValue(i.getLocation());
            row.createCell(1).setCellValue(i.getDate());
            row.createCell(2).setCellValue(i.getVariant());
            row.createCell(3).setCellValue(i.getNumSeq());
            row.createCell(4).setCellValue(i.getPercSeq());
            row.createCell(5).setCellValue(i.getTotalNumSeq());
        }  
        try{
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(excelPath));
            wb.write(out);
            out.close();
            wb.close();
            System.out.println(filename+".xlsx written successfully on disk.");
            System.out.println("\""+filename+"\"的内容保存完成，保存在\""+excelPath+"\"文件中，请注意查看");
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void generateToTxt(String filename,List<Variant>list,String title[]){
        String txtPath=path+filename+".txt";
        try{
            FileWriter fw=new FileWriter(txtPath);
            for(Variant v:list){
                fw.write(v.data()+System.lineSeparator());
            }
            fw.close();
            System.out.println(filename+".txt written successfully on disk.");
            System.out.println("\""+filename+"\"的内容保存完成，保存在\""+txtPath+"\"文件中，请注意查看");
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
public class test1{
    public static void main(String args[]){
        VariantVirus v=new VariantVirus();
        System.out.println("是否筛选新冠肺炎变种病毒的数据？是，扣1；不是，扣0；");
        Scanner sc=new Scanner(System.in);
        int parseData=sc.nextInt();
        String sortMethod=null;
        if(parseData==0)v=new VariantVirus("xlsx", sortMethod);
        if(parseData==1)virusMainSort.mainSort();  
        sc.close();
        
    }
}
