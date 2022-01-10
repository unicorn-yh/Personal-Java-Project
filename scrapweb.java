import java.io.*;
import java.net.*;
import javax.swing.text.Document;

public class scrapweb {
    public static void main(String args[])throws IOException{
        URL url=new URL("https://ourworldindata.org/coronavirus-data?country=MYS~AFG~AUS~BEL~DNK~CHN");
        Document d=JSoup.parse(url,timeoutMillis:30000);
        Elements population=d.getElementsByClass(className:"population");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("公司名列表");
        Element el = population.first();
        String title = el.getElementsByAttribute("title").text();
        System.out.println(title);
        for (int i = 0; i < el.getElementsByAttribute("title").size(); i++) {  //csv存储信息
            String title2 = el.getElementsByAttribute("title").eq(i).text();
            sheet.createRow(i).createCell(0).setCellValue(title2);
        }
        try{
            FileOutputStream fout = new FileOutputStream("D:/上海招聘公司一览表.xls");
            wb.write(fout);
            fout.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
