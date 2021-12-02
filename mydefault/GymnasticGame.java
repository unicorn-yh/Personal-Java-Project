//import java.util.*;
abstract class DoThing{
    public abstract void doThing(double []a);
    public abstract void setNext(DoThing next);
}
/*class DoInput extends DoThing{
    DoThing nextDoThing;
    void setNext(DoThing next){
        nextDoThing=next;
    }
    void doThing(double []a){
        System.out.println("input number of judges");
        Scanner sc=new Scanner(System.in);
        int count=sc.nextInt();
        System.out.println("input marks by each judges");
        a=new double[count];
        for(int i=0;i<count;i++)a[i]=sc.nextDouble();
        nextDoThing.doThing(a);
    }
}*/
public class GymnasticGame {
    
}
