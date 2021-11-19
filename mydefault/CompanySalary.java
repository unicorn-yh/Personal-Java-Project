abstract class Employee{
    public abstract double earnings();
}
class YearWorker extends Employee{
    public double earnings(){
        return 12000;
    }
}
class MonthWorker extends Employee{
    public double earnings(){
        return 12*2300;
    }
}
class WeekWorker extends Employee{
    public double earnings(){
        return 52*780;
    }
}
class Company{
    Employee[] e;
    double salaries=0;
    Company(Employee[] e){
        this.e=e;
    }
    double salariespay(){
        salaries=0;
        for(int i=0;i<e.length;i++){
            salaries=salaries+e[i].earnings();
        }
        return salaries;
    }
}
public class CompanySalary {
    public static void main(String args[]){
        Employee[] e=new Employee[29];
        for(int i=0;i<e.length;i++){
            if(i%3==0)e[i]=new WeekWorker();
            else if(i%3==1)e[i]=new MonthWorker();
            else if(i%3==2)e[i]=new YearWorker();
        }
        Company c=new Company(e);
        System.out.println("公司薪水总额："+c.salariespay()+"元");
    } 
}
