/*水果店的老板想对店里的苹果进行管理，苹果有不同的颜色，例如：红色、绿色、白色，还有名称，价格，产地等信息。他的要求是按苹果价格进行排序 */
package mydefault;
import java.util.*;
class AppleData{
    String color,type,location,data;
    double price;
    AppleData(String color,String type,double price,String location){
        this.color=color;
        this.type=type;
        this.price=price;
        this.location=location;
        data=color+"，"+type+"，"+String.valueOf(price)+"，"+location;
    }
    static void SortByPrice(List<AppleData>list,boolean asc){
        AppleData temp;
        if(asc==true){
            for(int i=0;i<list.size();i++){
                for(int j=0;j<list.size()-i-1;j++){
                    if(list.get(j).price>list.get(j+1).price){
                        temp=list.get(j);
                        list.set(j,list.get(j+1));
                        list.set(j+1,temp);
                    }
                }
            }
        }
        else{
            for(int i=0;i<list.size();i++){
                for(int j=0;j<list.size()-i-1;j++){
                    if(list.get(j).price<list.get(j+1).price){
                        temp=list.get(j);
                        list.set(j,list.get(j+1));
                        list.set(j+1,temp);
                    }
                }
            }
        }
    }
    static void OutputData(List<AppleData>list){
        for(AppleData a:list){
            System.out.println(a.data);
        }
    }
}
public class Apple {
    public static void main(String args[]){
        List<AppleData>list=new ArrayList<AppleData>();
        AppleData a;
        a=new AppleData("红色","红富士",5.5,"烟台");
        list.add(a);
        a=new AppleData("白色","黄金苹果",5.8,"日本");
        list.add(a);
        a=new AppleData("红色","粉红女士苹果",16.5,"澳大利亚");
        list.add(a);
        a=new AppleData("白色","奶苹果",3.2,"日本");
        list.add(a);
        a=new AppleData("绿色","青苹果",13.5,"甘肃");
        list.add(a);
        a=new AppleData("红色","红富士2号",6.5,"烟台");
        list.add(a);
        System.out.println("按苹果价格进行排序");
        System.out.println("按顺序排序请输入1，按逆序排序请输入0：");
        int i;
        Scanner sc=new Scanner(System.in);
        i=sc.nextInt();
        boolean asc=i==1?true:false;
        AppleData.SortByPrice(list,asc);
        AppleData.OutputData(list);
    }
}
