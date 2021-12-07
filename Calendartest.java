import java.util.*;
class calendarbean{
    int year=0,month=0,days=0;
    calendarbean(int year,int month,int days){
        this.year=year;
        this.month=month;
        this.days=days;
    }
    String [] getCalendar(){
        String [] a=new String[42];
        Calendar r=Calendar.getInstance();
        r.set(year,month-1,1);
        int weekDay=r.get(Calendar.DAY_OF_WEEK)-2;
        int day=0;
        if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)day=31;
        if(month==4||month==6||month==9||month==11)day=30;
        if(month==2){
            if(year%4==0 && year%100!=0 || year%400==0)day=29;
            else day=28;
        }
        for(int i=0;i<weekDay;i++)a[i]=" ";
        for(int i=weekDay,n=1;i<weekDay+day;i++){
            a[i]=String.valueOf(n);
            if(a[i].equals(String.valueOf(days)))a[i]=String.valueOf(n+"*");
            n++;
        }
        for(int i=weekDay+day;i<a.length;i++)a[i]=" ";
        return a;
    }
}
public class Calendartest {
    public static void main(String args[]){
        Calendar cal=Calendar.getInstance();
        Scanner sc=new Scanner(System.in);
        int input,year=0,month=0,days=0;
        System.out.println("输入日历数据吗？输入数据请输入1，不输入请输入0：");
        input=sc.nextInt();
        if(input==1){
        System.out.println("请输入年：");
        year=sc.nextInt();
        System.out.println("请输入月：");
        month=sc.nextInt();
        System.out.println("请输入日：");
        days=sc.nextInt();
        }
        else{
            year = cal.get(Calendar.YEAR);
		    month = cal.get(Calendar.MONTH)+1;
		    days = cal.get(Calendar.DATE);
        }
        calendarbean b=new calendarbean(year,month,days);
        String a[]=b.getCalendar();
        char st[]="一二三四五六日".toCharArray();
        for(char c:st)System.out.printf("%3c",c);
        for(int i=0;i<a.length;i++){
            if(i%7==0)System.out.println(" ");
            System.out.printf("%4s",a[i]);
        }  
    }   
}
