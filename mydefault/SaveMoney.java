class Bank{
    int saved,year;
    double interest,interestRate=0.29;
    double computerInterest(){
        return year*interestRate*saved;
    }
    void setInterestRate(double r){
        interestRate=r;
    }
}
class ConstructionBank extends Bank{
    double year;
    double computerInterest(){
        super.year=(int)year;
        double r=year-(int)year;
        int day=(int)(r*1000);
        double yearInterest=super.computerInterest();
        double dayInterest=day*0.0001*saved;
        interest=yearInterest+dayInterest;
        System.out.printf("%d元存在建设银行%d年零%d天的利息：%f元\n",saved,super.year,day,interest);
        return interest;
    }
}
class BankofDalian extends Bank{
    double year;
    public double computerInterest(){
        super.year=(int)year;
        double r=year-(int)year;
        int day=(int)(r*1000);
        double yearInterest=super.computerInterest();
        double dayInterest=day*0.00012*saved;
        interest=yearInterest+dayInterest;
        System.out.printf("%d元存在大连银行%d年零%d天的利息：%f元\n",saved,super.year,day,interest);
        return interest;
    }
}
public class SaveMoney {
    public static void main(String args[]){
        int amount=8000;
        ConstructionBank b1=new ConstructionBank();
        b1.saved=amount;
        b1.year=8.236;
        b1.setInterestRate(0.035);
        double interest1=b1.computerInterest();
        BankofDalian b2=new BankofDalian();
        b2.saved=amount;
        b2.year=8.236;
        b2.setInterestRate(0.035);
        double interest2=b2.computerInterest();
        System.out.printf("两个银行利息相差%f元\n",interest2-interest1);
    }   
}
