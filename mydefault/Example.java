class People{
    protected double weight,height;
    void speakHello(){
        System.out.println("yayayaya");
    }
    void averageHeight(){
        height=173;
        System.out.println("average height:"+height);
    }
    void averageWeight(){
        weight=70;
        System.out.println("average weight:"+weight);
    }
}
class ChinaPeople extends People{
    void speakHello(){
        System.out.println("您好");
    }
    void averageHeight(){
        height=168.78;
        System.out.println("中国人的平均身高："+height+"厘米");
    }
    void averageWeight(){
        System.out.println("中国人的平均体重：65千克");
    }
    void chinaGongfu(){
        System.out.println("坐如钟，站如松，睡如弓");
    }
}
class AmericanPeople extends People{
    void speakHello(){
        System.out.println("How do you do");
    }
    void averageHeight(){
        System.out.println("American's average height：176cm");
    }
    void averageWeight(){
        weight=75;
        System.out.println("American's average weight："+weight+" kg");
    }
    void americanBoxing(){
        System.out.println("直拳、钩拳、组合拳");
    }
}
class BeijingPeople extends ChinaPeople{
    void averageHeight(){
        System.out.println("北京人的平均身高：172.5厘米");
    }
    void averageWeight(){
        System.out.println("北京人的平均体重：70千克");
    }
    void beijingOpera(){
        System.out.println("花脸、青衣、花旦和老生");
    }
}
public class Example {
    public static void main(String args[]){
        ChinaPeople cp=new ChinaPeople();
        AmericanPeople ap=new AmericanPeople();
        BeijingPeople bp=new BeijingPeople();
        cp.speakHello();
        ap.speakHello();
        bp.speakHello();
        cp.averageHeight();
        ap.averageHeight();
        bp.averageHeight();
        cp.averageWeight();
        ap.averageWeight();
        bp.averageWeight();
        cp.chinaGongfu();
        ap.americanBoxing();
        bp.beijingOpera();
        bp.chinaGongfu();
    }   
}
