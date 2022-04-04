package protein;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.*;
import java.util.concurrent.TimeUnit;
//数据库：swissprot_exp.txt
//accession,annotation,type

class pro{
    String filetype,sortMethod,filename="Protein";
    static String ch;
    List<ProteinData> tmp1=new ArrayList<>();
    List<String> tmp2=new ArrayList<>();
    List<ProteinData>datalist=new ArrayList<>();
    String head[]={"Accession","Annotation","Type"};
    Scanner tt=new Scanner(System.in);
    sort s=new sort();
    pro(){}
    pro(String sortMethod){
        this.sortMethod=sortMethod;
    }
    void getData(){
        File fread=new File("C:/Users/User/Desktop/java-1223/project_dataset/swissprot_exp.txt");
        try{
            Reader in = new FileReader(fread);
            BufferedReader br=new BufferedReader(in);
            String n=null,s;
            int i=1;
            while((n=br.readLine())!=null){
                ProteinData p=new ProteinData();
                StringTokenizer parse=new StringTokenizer(n,"\t");
                while(parse.hasMoreTokens()){
                    s=parse.nextToken();
                    if(i==1){p.setAccession(s);;i++;}
                    else if(i==2){p.setAnnotation(s);;i++;}
                    else{
                        p.setType(s);
                        datalist.add(p);
                        //System.out.println(p.data());
                        i=1;
                    }    
                } 
            }
            br.close();
        }
        catch(Exception e){
            e.getStackTrace();
        }
    }
    void parseData(){
        if(sortMethod==null){
            tmp1=datalist;
        }
        else if(sortMethod.equals("accessionOnly")){
            tmp2=s.accessionOnly(datalist);
            System.out.println("Size of dataset: "+tmp2.size());
            filename+="-AccessionOnly";
            head[1]=head[2]="";
        }
        else if(sortMethod.equals("annotationOnly")){
            tmp2=s.annotationOnly(datalist);
            System.out.println("Size of dataset: "+tmp2.size());
            filename+="-AnnotationOnly";
            head[0]=head[2]="";
        }  
        else if(sortMethod.equals("typeOnly")){
            tmp2=s.typeOnly(datalist);
            System.out.println("Size of dataset: "+tmp2.size());
            filename+="-TypeOnly";
            head[0]=head[1]="";
        }  
        else if(sortMethod.equals("parseAccession")){
            tmp1=s.parseAccession(datalist);
            while(tmp1.size()==0){
                System.out.println("No data matched! Please input again.");
                System.out.println("-----------------PARSE--ACCESSION-----------------");
                tmp1=s.parseAccession(datalist);
            }
            System.out.println("Size of dataset: "+tmp1.size());
            filename+="-parseAccession-"+ch;
            int a=variousOutputMethod(tmp1,"parseAccesion");
            if(a==1){
                head[1]=head[2]="";
                tmp1.clear();
            }   
        }  
        else if(sortMethod.equals("parseAnnotation")){
            tmp1=s.parseAnnotation(datalist);
            while(tmp1.size()==0){
                System.out.println("No data matched! Please input again.");
                System.out.println("-----------------PARSE--ANNOTATION-----------------");
                tmp1=s.parseAnnotation(datalist);
            }
            System.out.println("Size of dataset: "+tmp1.size());
            filename+="-parseAnnotation-"+ch;
            int a=variousOutputMethod(tmp1,"parseAnnotation");
            if(a==1){
                head[0]=head[2]="";
                tmp1.clear();
            }
        }
        else if(sortMethod.equals("parseType")){
            tmp1=s.parseType(datalist);
            System.out.println("Size of dataset: "+tmp1.size());
            filename+="-parseType-"+ch;
        }
    }
    int variousOutputMethod(List<ProteinData>ls,String st){
        System.out.println("1. View the column of parsed data only.");
        System.out.println("2. View whole table of parsed data.");
        int input=tt.nextInt();
        while(input>2||input<1){
            System.out.println("Illegal input，please input number 1 or 2:");
            input=tt.nextInt();
        }
        if(input==1){
            if(st.equals("parseAccesion"))tmp2=s.accessionOnly(ls);
            else if(st.equals("parseAnnotation"))tmp2=s.annotationOnly(ls);
        }
        return input;
    }
    void outputData(){
        filetype=FileFormat.setFileType();
        if(filetype.equals("xlsx")) FileFunction.generateToExcel(filename,tmp1,tmp2,head);
        else if(filetype.equals("txt")) FileFunction.generateToTxt(filename,tmp1,tmp2,head);
    }
}
class sort{
    Scanner t=new Scanner(System.in);
    List<String> accessionOnly(List<ProteinData>tmp1){
        List<String>tmp2 = tmp1.stream().map(ProteinData::getAccession).collect(Collectors.toList());
        return tmp2; 
    }
    List<String> annotationOnly(List<ProteinData>tmp1){
        List<String>tmp2 = tmp1.stream().map(ProteinData::getAnnotation).collect(Collectors.toList());
        return tmp2; 
    }
    List<String> typeOnly(List<ProteinData>tmp1){
        List<String>tmp2 = tmp1.stream().map(ProteinData::getType).collect(Collectors.toList());
        return tmp2; 
    }
    List<ProteinData> parseAccession(List<ProteinData>tmp1){
        List<ProteinData>tmp2=new ArrayList<>();
        System.out.println("Ways to parse Accession data:");
        System.out.println("1. Find Accession by full name.");
        System.out.println("2. Find Accession by certain contained character.");
        String input=t.next();
        while(!(input.equals("1")||input.equals("2"))){
            System.out.println("Illegal input，please input number 1 or 2:");
            input=t.next();
        }
        if(input.equals("1")){
            System.out.println("Please input the name of Accession:");
            String a=t.next();
            if(!a.chars().allMatch(Character::isLetterOrDigit)){
                System.out.println("Illegal input，please input letters or numbers only.");
                a=t.next();
            }
            pro.ch=a.toUpperCase();
            tmp2=tmp1.stream().filter(s->s.getAccession().equals(pro.ch)).collect(Collectors.toList());
        }
        else{
            System.out.println("Please input characters contained in Accession:");
            String a=t.next();
            if(!a.chars().allMatch(Character::isLetterOrDigit)){
                System.out.println("Illegal input，please input letters or numbers only.");
                a=t.next();
            }
            pro.ch=a.toUpperCase();
            tmp2=tmp1.stream().filter(s->s.getAccession().contains(pro.ch)).collect(Collectors.toList());
        }
        return tmp2;
    }
    List<ProteinData> parseAnnotation(List<ProteinData>tmp1){
        List<ProteinData>tmp2=new ArrayList<>();
        System.out.println("Ways to parse Annotation data:");
        System.out.println("1. Find Annotation by index.");
        System.out.println("2. Find Annotation by certain contained character.");
        String input=t.next();
        while(!(input.equals("1")||input.equals("2"))){
            System.out.println("Illegal input，please input number 1 or 2:");
            input=t.next();
        }
        if(input.equals("1")){
            System.out.println("Please input the Annotation index:");
            String a=t.next();
            while(!a.chars().allMatch(Character::isDigit)){
                System.out.println("Illegal input，please input numbers only:");
                a=t.next();
            }
            pro.ch=a;
            tmp2=tmp1.stream().filter(s->(s.getAnnotation()).replace("GO:","").equals(pro.ch)).collect(Collectors.toList());
        }
        else{
            System.out.println("Please input numbers contained in Annotation:");
            String a=t.next();
            while(!a.chars().allMatch(Character::isDigit)){
                System.out.println("Illegal input，please input numbers only:");
                a=t.next();
            }
            pro.ch=a;
            tmp2=tmp1.stream().filter(s->(s.getAnnotation()).replace("GO:","").contains(pro.ch)).collect(Collectors.toList());
        }
        return tmp2;
    }
    List<ProteinData> parseType(List<ProteinData>tmp1){
        List<ProteinData>tmp2=new ArrayList<>();
        System.out.println("Ways to parse Protein Type data:");
        System.out.println("1. P\t2. C\t3. F");
        String input=t.next();
        while(!(input.equals("3")||input.equals("1")||input.equals("2"))){
            System.out.println("Illegal input，please input number 1-3:");
            input=t.next();
        }
        if(input.equals("1")) {
            pro.ch="P";
            tmp2=tmp1.stream().filter(s->s.getType().equals("P")).collect(Collectors.toList());
        }
        else if(input.equals("2")) {
            pro.ch="C";
            tmp2=tmp1.stream().filter(s->s.getType().equals("C")).collect(Collectors.toList());
        }
        else {
            pro.ch="F";
            tmp2=tmp1.stream().filter(s->s.getType().equals("F")).collect(Collectors.toList());
        }
        return tmp2;
    }

}
class MainSort{  
    static Scanner t1=new Scanner(System.in);
    public static void mainSort(){
        System.out.println("Enter number below to parse data:");
        System.out.println("1. View accessions only.");
        System.out.println("2. View annotations only.");
        System.out.println("3. View type only.");
        System.out.println("4. Parse accessions.");
        System.out.println("5. Parse annotations.");
        System.out.println("6. Parse type.");
        String input=t1.next();
        while(!(input.equals("3")||input.equals("1")||input.equals("2")||input.equals("4")||input.equals("5")||input.equals("6"))){
            System.out.println("Illegal input，please input number 1-6:");
            input=t1.next();
        }
        String sortMethod=null;
        pro p=new pro();
        if(input.equals("1")) sortMethod="accessionOnly";
        else if(input.equals("2")) sortMethod="annotationOnly";
        else if(input.equals("3")) sortMethod="typeOnly";
        else if(input.equals("4")) sortMethod="parseAccession";
        else if(input.equals("5")) sortMethod="parseAnnotation";
        else if(input.equals("6")) sortMethod="parseType";
        p=new pro(sortMethod);
        p.getData();
        p.parseData();
        p.outputData();
        //System.out.println("是否继续筛选数据？是，扣1；不是，扣0；");
        
    }
}
class FileFormat{
    public static String setFileType(){
        Scanner s=new Scanner(System.in);
        System.out.println("Please select format of the file:");
        System.out.println("1. xlsx");
        System.out.println("2. txt");
        String a=s.next();
        while(!(a.equals("1")||a.equals("2"))){
            System.out.println("Illegal input，please input number 1 or 2:");
            a=s.next();
        }
        String fileformat;
        if(a.equals("1"))fileformat="xlsx";
        else fileformat="txt";
        return fileformat;
    }
}
class ProteinData{
    String accession,annotation,type;
    String data(){
        return accession+"\t"+annotation+"\t"+type;
    }
    void setAccession(String accession){
        this.accession=accession;
    }
    void setAnnotation(String annotation){
        this.annotation=annotation;
    }
    void setType(String type){
        this.type=type;
    }
    String getAccession(){
        return accession;
    }
    String getAnnotation(){
        return annotation;
    }
    String getType(){
        return type;
    }
}
class FileFunction{
    static String path="C:/Users/User/Desktop/";
    public static void generateToExcel(String filename,List<ProteinData>list1,List<String>list2,String title[]){
        String fileType="xlsx";
        String excelPath=path+filename+"."+fileType;
        XSSFWorkbook wb=new XSSFWorkbook();
        XSSFSheet sheet=wb.createSheet("Protein sheet1");

        //添加表头  
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        row.setHeight((short)540); 
        cell.setCellValue(filename);    //创建第一行  

        CellStyle style=wb.createCellStyle(); // 样式对象 
        Font font=wb.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short)14);
        font.setBold(true);
        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直      
        style.setAlignment(HorizontalAlignment.CENTER);// 水平  
        style.setLocked(false); 
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setWrapText(true); // 指定当单元格内容显示不下时自动换行
        style.getShrinkToFit();
        cell.setCellStyle(style); // 样式居中
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));  //单元格合并,四个参数：起始行，起始列，结束行，结束列  
        sheet.autoSizeColumn(5200);

        row=sheet.createRow(1);    //创建第二行  
        for(int i=0,b=0;i<title.length;i++){  
            if(!title[i].equals("")){
                cell=row.createCell(b);
                cell.setCellValue(title[i]);  
                cell.setCellStyle(style); // 样式，居中
                sheet.setColumnWidth(b,20*256); 
                b++;
            }
        }  

        row.setHeight((short) 540);  
  
        if(list1.size()>=1){
            int j=2;
            System.out.println("Processing data...");
            for(ProteinData i:list1){
                row=sheet.createRow(j++);
                row.setHeight((short)500); 
                row.createCell(0).setCellValue(i.getAccession());
                row.createCell(1).setCellValue(i.getAnnotation());
                row.createCell(2).setCellValue(i.getType());
            }
        }  
        else{
            int j=2;
            System.out.println("Processing data...");
            for(String i:list2){
                row=sheet.createRow(j++);
                row.setHeight((short) 500); 
                row.createCell(0).setCellValue(i);
            }

        }
        try{
            FileOutputStream out = new FileOutputStream(new File(excelPath));
            wb.write(out);
            out.close();
            wb.close();
            System.out.println(filename+".xlsx written successfully on disk.");
            System.out.println("Directory path: "+excelPath+", please check it out.");
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void generateToTxt(String filename,List<ProteinData>list1,List<String>list2,String title[]){
        String txtPath=path+filename+".txt";
        try{
            System.out.println("Processing data...");
            FileWriter fw=new FileWriter(txtPath);
            for(String i:title)if(!i.equals(""))fw.write(i+"  ");
            fw.write(System.lineSeparator());
            if(list1.size()>=1){
                for(ProteinData v:list1)fw.write(v.data()+System.lineSeparator());
            }
            else{
                for(String v:list2)fw.write(v+System.lineSeparator());
            }
            fw.close();
            System.out.println(filename+".txt written successfully on disk.");
            System.out.println("Directory path: "+txtPath+", please check it out.");
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
public class proteinTest1{
    public static void main(String args[]){
        pro v;
        System.out.print("Getting protein database from swissprot_exp.txt");
        for(int i=0;i<6;i++){
            System.out.print(".");
            try{
                TimeUnit.MILLISECONDS.sleep(250);
            }
            catch(Exception e){
                e.getMessage();
            }
        }    
        System.out.println("\nDo you want to parse the data？Yes, press 1; No, press 0;");
        Scanner sc=new Scanner(System.in);
        int parseData=sc.nextInt();
        String sortMethod=null;
        if(parseData==0){
            v=new pro(sortMethod);
            v.getData();
            v.parseData();
            v.outputData();
        }
        if(parseData==1)MainSort.mainSort();  
        sc.close();
    }
}

