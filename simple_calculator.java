import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Calculator extends JFrame{
	String a[]={"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
	JButton b[]=new JButton[a.length];
	JButton b1=new JButton("DEL");
	JButton b2=new JButton("AC");
	JTextField tf=new JTextField("0",20);
	JPanel p1=new JPanel(new GridLayout(1,2,10,10));
	JPanel p2=new JPanel(new GridLayout(4,4,5,5));
	JPanel p3=new JPanel(new BorderLayout(5,5));
	double result=0;
	
	Calculator(){
		setTitle("Calculator");
		setBounds(100,100,400,400);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=this.getContentPane();
		setLayout(new BorderLayout(10,5));
		tf.setHorizontalAlignment(JTextField.RIGHT);
		tf.setEditable(false);
		tf.setBackground(Color.white);
		init();
		p3.add(p1,BorderLayout.NORTH);
		p3.add(p2,BorderLayout.CENTER);
		add(tf,BorderLayout.NORTH);
		add(p3);
	}
	void init(){
		boolean c=false;
		for(int i=0;i<a.length;i++){
			b[i]= new JButton(a[i]);
			p2.add(b[i]);
			b[i].addActionListener(new num());
		}
		p1.add(b1);
		b1.addActionListener(new num());
		p1.add(b2);
		b2.addActionListener(new num());
	}
	class num implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String s=e.getActionCommand();
			if(s!="=" && s!="AC" && s!="DEL"){
				if(tf.getText().equals("0"))
					tf.setText(s);
				else
					tf.setText(tf.getText()+s);	
			}
			else if(s.equals("=")){
				ans();
			}
			else if(s.equals("AC")){
				tf.setText("0");
			}
			else if(s.equals("DEL")){
				if(tf.getText().length()>1)
					tf.setText(tf.getText().substring(0, tf.getText().length()-1));
				else
					tf.setText("0");
			}
		}
	}
	void ans(){
		int j=0,k=0;
		double t=0;
		StringBuffer number=new StringBuffer();
		String arrayNum[]=new String[10];
		String arraySign[]=new String[10];
		String s1=tf.getText();
		char c[]=s1.toCharArray();
		for(int i=0;i<c.length;i++){
			if((c[i]+"").matches("[0-9]")|| String.valueOf(c[i]).equals(".")){
				number.append(c[i]);
				arrayNum[j]=new String(number);
			}
			else {
				j++;
				number.delete(0,i);
				arraySign[k]=String.valueOf(c[i]);
				k++;
			}	
		}
		
		if((s1.contains("*")||s1.contains("/"))&&(s1.indexOf("+")==-1)&&(s1.indexOf("-")==-1)){
			for(int m=0;m<k;m++){
				double d1=Double.parseDouble(arrayNum[m]);
				double d2=Double.parseDouble(arrayNum[m+1]);
				if(arraySign[m].equals("*")){
					t=d1*d2;
					arrayNum[m+1]=String.valueOf(t);	
				}
				else{
					t=d1/d2;
					arrayNum[m+1]=String.valueOf(t);
				}
			}
			result=t;
		}
		else if((s1.contains("+")||s1.contains("-"))&&(s1.indexOf("*")==-1)&&(s1.indexOf("/")==-1)){
			for(int m=0;m<k;m++){
				double d1=Double.parseDouble(arrayNum[m]);
				double d2=Double.parseDouble(arrayNum[m+1]);
				if(arraySign[m].equals("+")){
					t=d1+d2;
					arrayNum[m+1]=String.valueOf(t);	
				}
				else{
					t=d1-d2;
					arrayNum[m+1]=String.valueOf(t);
				}
			}
			result=t;
		}
		else
			JOptionPane.showMessageDialog(null, "ERROR!");
		tf.setText(String.valueOf(result));
	}	
}

public class simple_calculator{
	public static void main(String []args){
		new Calculator();
	}
}