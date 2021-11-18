class Derived extends pk.Original{
    void Access(){   //private & friendly 不能访问
        System.out.println("**** 不同包的子类 ****");          
        System.out.println("protected member "+n_protected);   //子类可以访问父类
        System.out.println("public member "+n_public);

        pk.Original o=new pk.Original();    //只能访问 public
        System.out.println("**** 访问在不同包中的父类 ****");
        System.out.println("public member "+o.n_public);
    }
}
class AnotherPackage{
    void Access(){
        pk.Original o=new pk.Original();
        System.out.println("**** 另一包中的其他类 ****");
        System.out.println("public member "+o.n_public);
    }
}
public class AccessControl {
    public static void main(String args[]){
        Derived d=new Derived();
        d.Access();
        AnotherPackage a=new AnotherPackage();
        a.Access();
    }
    
}
