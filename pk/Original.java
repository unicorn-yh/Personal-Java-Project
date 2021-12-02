package pk;

public class Original {      //private只有自己的类才可以访问
    int n_friendly=1;
    private int n_private=2;
    protected int n_protected=3;
    public int n_public=4;
    void Access(){
        System.out.println("****In same class, you can access...");
        System.out.println("friendly member "+n_friendly);
        System.out.println("private member "+n_private);
        System.out.println("protected member "+n_protected);
        System.out.println("public member "+n_public);
    }
}
class Derived extends Original{  //子类
    void Access(){
        System.out.println("**** 相同包的子类 ****");
        System.out.println("friendly member "+n_friendly);
       // System.out.println("private member "+n_private);    //不能访问，private不能跨类
        System.out.println("protected member "+n_protected);
        System.out.println("public member "+n_public);

        Original o=new Original();
        System.out.println("**** 相同包的子类的其他对象 ****");
        System.out.println("friendly member "+o.n_friendly);
        System.out.println("protected member "+o.n_protected);
        System.out.println("public member "+o.n_public);
    }
}
class SamePackage{
    void Access(){
        Original o=new Original();
        System.out.println("**** 相同包的其他类 ****");
        System.out.println("friendly member "+o.n_friendly);
        System.out.println("protected member "+o.n_protected);
        System.out.println("public member "+o.n_public);
    }
}
class AccessControl{
    public static void main(String args[]){
        Original o=new Original();
        o.Access();
        Derived d=new Derived();
        d.Access();
        SamePackage s=new SamePackage();
        s.Access();
    }
}
