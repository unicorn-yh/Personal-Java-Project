/*完善三个数字对象排序程序。
题目内容：
完善以下程序，利用swap函数，完成数字按从小到大的顺序输出。例如输入5 4 3 输出3,4,5。
输入格式:
输入3个数字。
输出格式：
数字从小到大排列
输入样例：
5
4
3
输出样例：
3,4,5 */

import java.util.Scanner;
class MyNumber{
    int num;
    MyNumber(int num){
        this.num=num;
    }
    void swap(MyNumber m, MyNumber n){
        if(m.num > n.num){
            int s = m.num;
            m.num = n.num;
            n.num = s;
        }
    }
    int returnNum(){
        return num;
    }
}
public class SwapNumber{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = 0;
        int b = 0;
        int c = 0;
        if (sc.hasNext()) a = sc.nextInt();
        if (sc.hasNext()) b = sc.nextInt();
        if (sc.hasNext()) c = sc.nextInt();
        sc.close();
        if(a==0 || b==0 || c==0){
            System.out.println("输入不能为0");
            System.exit(-1); 
        }
        MyNumber obj1, obj2, obj3;
        obj1=new MyNumber(a);
        obj2=new MyNumber(b);
        obj3=new MyNumber(c);
        obj1.swap(obj1,obj2);
        obj2.swap(obj2,obj3);
        obj1.swap(obj1,obj2);
        System.out.println(obj1.returnNum()+","+obj2.returnNum()+","+obj3.returnNum());
    }
}
