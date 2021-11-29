class Village{
    static int waterAmount;
    int peopleNumber;
    String name;
    Village(String s){
        name=s;
    }
    static void setWaterAmount(int m){
        if(m>0)waterAmount=m;
    }
    void drinkWater(int n){
        if(waterAmount-n>=0){
            waterAmount=waterAmount-n;
            System.out.println(name+"喝了"+n+"升水");
        }
        else waterAmount=0;
    }
    int lookWaterAmount(){
        return waterAmount;
    }
    void setPeopleNumber(int n){
        peopleNumber=n;
    }
    int getPeopleNumber(){
        return peopleNumber;
    }
}
public class Land {
    public static void main(String args[]){
        Village.setWaterAmount(200);
        int leftWater=Village.waterAmount;
        System.out.println("水井中有"+leftWater+"升水");
        Village zhao,ma;
        zhao=new Village("赵庄");
        ma=new Village("马家河子");
        zhao.setPeopleNumber(80);
        ma.setPeopleNumber(120);
        zhao.drinkWater(50);
        leftWater=ma.lookWaterAmount();
        String name=ma.name;
        System.out.println(name+"发现水井中有"+leftWater+"升水");
        ma.drinkWater(100);
        leftWater=zhao.lookWaterAmount();
        name=zhao.name;
        System.out.println(name+"发现水井中有"+leftWater+"升水");
        int peopleNumber=zhao.getPeopleNumber();
        System.out.println("赵庄的人口："+peopleNumber);
        peopleNumber=ma.getPeopleNumber();
        System.out.println("马家河子的人口："+peopleNumber);
    }  
}
