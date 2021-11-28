class Locals{
    int a;
    void m() {
        int b=0;
        System.out.println(a);//a的值为0
        System.out.println(b);//编译不能通过
    }
}
public class LocalVarAndMemberVar{
    public static void main(String [] args){
        Locals l=new Locals();
        l.m();
    }
}