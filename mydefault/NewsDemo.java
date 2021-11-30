/**
 *  一个简单的新闻事件侦听演示程序
 * @author tds
 * @author 修改者：
 */
class NewsEvent{     //事件信息
    Object source;   //事件来源
    String text;     //事件内容
    int priority;       //事件级别
    NewsEvent(Object source,String text,int priority){
        this.source=source;
        this.text=text;
        this.priority=priority;
    }
}
interface Listener{
    void newsArrived(NewsEvent e);
}
class NewsAgency{     //新闻机构
    String name;
    int listenerCount=0;   //已有的订阅者
    Listener[] listenerNum=new Listener[100];  //订阅者
    NewsAgency(String name){
        this.name=name;
    }
    void addListener(Listener addnew){     //加入一个订阅者
        if(listenerCount<listenerNum.length){
            listenerNum[listenerCount]=addnew;
            ++listenerCount;
        }
    }
    void start(){     //模拟一个事件发生，并通知所有订阅者
        NewsEvent e=new NewsEvent("Mr.Joan","Bombing",9);
        for(int i=0;i<listenerCount;i++) listenerNum[i].newsArrived(e);
    }
}
class MyListener implements Listener{   //实现一个订阅者
    public void newsArrived(NewsEvent e){   //接收到消息后进行显示
        if(e.priority>5)System.out.println("warning：1");
        System.out.println("Please be awared,"+e.text+" happened!");
    }
}
public class NewsDemo {
	public static void main(String[] args) {
		//模拟有一家新闻社
		NewsAgency bbc = new NewsAgency("BBC");
		//订阅该社的新闻
		bbc.addListener(new MyListener());
		
		//TODO 请在这里再加入一个Listener
		//新闻社启动其工作流程
        bbc.addListener(new Listener() {
            public void newsArrived(NewsEvent e){
                if(e.priority>5)System.out.println("warning：2");
                System.out.println("Please be awared,"+e.text+" happened!");
            }
        });

        //用lambda表达式实现一个Listener
        bbc.addListener((e)->{
            if(e.priority>5)System.out.println("warning：3");
            System.out.println("Please be awared,"+e.text+" happened!");
        });
		bbc.start();				
	}
}
