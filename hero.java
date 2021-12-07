/*水果店的老板想对店里的苹果进行管理，苹果有不同的颜色，例如：红色、绿色、白色，还有名称，价格，产地等信息。他的要求是按苹果价格进行排序 */

import java.util.*;
class HeroData{
    int power,num;
    String name,hero,data;
    HeroData(String name,String hero,int power,int num){
        this.name=name;
        this.hero=hero;
        this.power=power;
        this.num=num;
        data="姓名："+name+"、组别："+hero+"、战力："+String.valueOf(power)+"、出场次数："+String.valueOf(num);
    }
    static void SortByPower(List<HeroData>list,boolean asc){
        HeroData temp;
        if(asc==true){
            for(int i=0;i<list.size();i++){
                for(int j=0;j<list.size()-i-1;j++){
                    if(list.get(j).power>list.get(j+1).power){
                        temp=list.get(j);
                        list.set(j,list.get(j+1));
                        list.set(j+1,temp);
                    }
                }
            }
        }
        else{
            for(int i=0;i<list.size()-1;i++){
                for(int j=i+1;j<list.size();j++){
                    if(list.get(j).power>list.get(i).power){
                        temp=list.get(i);
                        list.set(i,list.get(j));
                        list.set(j,temp);
                    }
                }
            }
        }
    }
    static void SortByNum(List<HeroData>list,boolean asc){
        HeroData temp;
        if(asc==true){
            for(int i=0;i<list.size();i++){
                for(int j=0;j<list.size()-i-1;j++){
                    if(list.get(j).num>list.get(j+1).num){
                        temp=list.get(j);
                        list.set(j,list.get(j+1));
                        list.set(j+1,temp);
                    }
                }
            }
        }
        else{
            for(int i=0;i<list.size()-1;i++){
                for(int j=i+1;j<list.size();j++){
                    if(list.get(j).num>list.get(i).num){
                        temp=list.get(i);
                        list.set(i,list.get(j));
                        list.set(j,temp);
                    }
                }
            }
        }
    }
    static void OutputData(List<HeroData>list){
        for(HeroData a:list){
            System.out.println(a.data);
        }
    }
    static void OutputDataFuzhu(List<HeroData>list){
        for(int i=0;i<list.size();i++){
            if((list.get(i).hero).equals("辅助")){
                System.out.println(list.get(i).data);
            }
        }
    }
    static void OutputDataSheshou(List<HeroData>list){
        for(int i=0;i<list.size();i++){
            if((list.get(i).hero).equals("射手")){
                System.out.println(list.get(i).data);
            }
        }
    }

}

public class hero {
    public static void main(String args[]){
        List<HeroData>list=new ArrayList<HeroData>();
        HeroData a;
        a=new HeroData("小蔡","辅助",2101,150);
        list.add(a);
        a=new HeroData("小乔","法师",130,20);
        list.add(a);
        a=new HeroData("小鲁","射手",602,80);
        list.add(a);
        a=new HeroData("小后","射手",3101,15);
        list.add(a);
        a=new HeroData("小明","辅助",1021,320);
        list.add(a);
        a=new HeroData("小司","法师",1502,36);
        list.add(a);
        a=new HeroData("小禅","辅助",801,200);
        list.add(a);
        a=new HeroData("小成","射手",2603,500);
        list.add(a);
        a=new HeroData("小东","辅助",1131,201);
        list.add(a);
        a=new HeroData("小百","射手",706,50);
        list.add(a);
        System.out.println("1. 按战力降序排序所有英雄:");
        HeroData.SortByPower(list,false);
        HeroData.OutputData(list);
        System.out.println("");
        System.out.println("2. 按战力降序排序组别为“辅助”的所有英雄:");
        HeroData.SortByPower(list,false);
        HeroData.OutputDataFuzhu(list);
        System.out.println("");
        System.out.println("3. 按出场次数升序排序组别为“射手”的所有英雄:");
        HeroData.SortByNum(list,true);
        HeroData.OutputDataSheshou(list);
        System.out.println("");   
    }
}