package xupt.se.ttms.view.UI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.File;
import java.util.Random;
import javax.swing.*;

public class box extends JFrame{
	
	
	 JTextArea jta = null;  
	 JScrollPane jscrollPane;  
	 JFrame jf;  
	    JPanel jpanel; 
	
			public static void main(String[] args){
			new box();	
			}

		public box(){
			
			JFrame background=new JFrame();
			this.setTitle("剧院管理系统--电影简介");
			this.setSize(700,500);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
			ImageIcon backgroundimg = new ImageIcon("6.jpg");
			JLabel lable=new JLabel(backgroundimg);
			lable.setBounds(700, 800,backgroundimg.getIconWidth(), backgroundimg.getIconHeight());
			JPanel imgagePanel=(JPanel)this.getContentPane();
			imgagePanel.setOpaque(false);
			this.getLayeredPane().add(lable,new Integer(Integer.MIN_VALUE));
			setVisible(true);
			
			
			 
		  
			
			JLabel labler=new JLabel("SIGN——PIG");
			labler.setForeground(new Color(0, 120, 210));
			labler.setBounds(40,360,150,40);
			imgagePanel.add(labler);
			
			JLabel labler1=new JLabel("一条狗的使命");
			labler1.setForeground(new Color(0, 120, 210));
			labler1.setBounds(200,360,150,40);
			imgagePanel.add(labler1);
			
			JLabel labler2=new JLabel("Trible_X");
			labler2.setForeground(new Color(0, 120, 210));
			labler2.setBounds(360,360,150,40);
			imgagePanel.add(labler2);
			
			JLabel labler3=new JLabel("魔幻城堡");
			labler3.setForeground(new Color(0, 120, 210));
			labler3.setBounds(520,360,150,40);
			imgagePanel.add(labler3);
			
			 ImageIcon icon = new ImageIcon("15.jpg");
			 JButton o=new JButton("TTMS");
			o.setIcon(icon);
			
//			JButton one=new JButton("正在上映");
//			JButton two=new JButton("即将上映");
//			JButton three=new JButton("");	
			JButton four=new JButton("TTMS");
			JButton five=new JButton("选择场次");
			JButton six=new JButton("订单信息");
			JButton seven=new  JButton("");
			JButton eight=new  JButton("退出登录");
			JButton a=new  JButton("");
			JButton b=new  JButton("");
			JButton c=new  JButton("");
//			JButton d=new  JButton("");
//			JButton e=new  JButton("");
//			JButton f=new  JButton("");
//			JButton g=new  JButton("");
			ImageIcon icon1 = new ImageIcon("6.jpg");
			ImageIcon icon2 = new ImageIcon("02.jpg");
			ImageIcon icon3 = new ImageIcon("03.jpg");
			ImageIcon icon4 = new ImageIcon("16.jpg");
			ImageIcon icon5 = new ImageIcon("14.jpg");
			ImageIcon icon6 = new ImageIcon("11.jpg");
			seven.setIcon(icon1);
			a.setIcon(icon2);
			b.setIcon(icon3);
			c.setIcon(icon4);
//			d.setIcon(icon5);
//			e.setIcon(icon6);
//			f.setIcon(icon6);
//			g.setIcon(icon6);
		
			JPanel panel=new JPanel();
			panel.add(o);
			panel.add(a);
			panel.add(b);
			
			panel.add(c);
//			panel.add(d);
//			panel.add(e);
//			panel.add(f);
//			panel.add(g);
//			panel.add(one);
//			panel.add(two);
//			panel.add(three);
			panel.add(four);
			panel.add(five);
			panel.add(six);
			panel.add(seven);
			panel.add(eight);
			panel.setLayout(null);
	
			panel.setBounds(0, 0, 700, 500);
			o.setBounds(20,150,150,200);
			a.setBounds(180,150,150,200);
			b.setBounds(340,150,150,200);
			c.setBounds(500,150,150,200);
//			d.setBounds(20,360,150,40);
//			e.setBounds(180,360,150,40);
//			f.setBounds(340,360,150,40);
//			g.setBounds(500,360,150,40);
//			one.setBounds(0,100,100,30);
//			two.setBounds(100,100,100,30);
//			three.setBounds(200,100,100,30);
			four.setBounds(0,100,100,30);
			five.setBounds(100,100,200,30);
			six.setBounds(300,100,200,30);
			eight.setBounds(500,100,200,30);
			seven.setBounds(0,0,700,100);
			panel.setBackground(null);
			panel.setOpaque(false);
			add(panel);
			
		
			  	GridBagLayout  layout=new  GridBagLayout();  
			    GridBagConstraints gbc=new GridBagConstraints();  
			   
			    JMenuBar menubar1 = new JMenuBar();  
//			    menubar1.setBackground();
			    JMenu menu1=new JMenu("票务管理");  
			    JMenu menu2=new JMenu("会员管理");  
			    JMenu menu5=new JMenu("信息服务");  
			    JMenu menu6=new JMenu("热门电影"); 
			    JMenu menu7=new JMenu("每日提示");
			    JMenu menu8=new JMenu("用户设置");
			    JMenuItem menu1Item1=new JMenuItem("售票");  
			    JMenuItem menu1Item2=new JMenuItem("退票");  
			    JMenuItem menu2Item1=new JMenuItem("会员注册");  
			    JMenuItem menu2Item2=new JMenuItem("信息管理");  
			    JMenuItem menu5Item1=new JMenuItem("版本信息");  
			    JMenuItem menu5Item2=new JMenuItem("使用帮助");  
			    JMenuItem menu6Item1=new JMenuItem("详细信息");  
			    JMenuItem menu7Item1=new JMenuItem("最新动态");  
			    JMenuItem menu8Item1=new JMenuItem("修改密码");  

			    Toolkit kit=Toolkit.getDefaultToolkit();  
			    Dimension  screeSize=kit.getScreenSize();  
			    JScrollPane  js;  
			    menu1.add(menu1Item1);  
		         menu1.add(menu1Item2);  
		         menu2.add(menu2Item1);  
		         menu2.add(menu2Item2);  
		         menu5.add(menu5Item1);  
		         menu5.add(menu5Item2);  
		         menu6.add(menu6Item1);  
		         menu7.add(menu7Item1);
		         menu8.add(menu8Item1);
		         menubar1.add(menu1);  
		         menubar1.add(menu2);  
		         menubar1.add(menu5);  
		         menubar1.add(menu6);  
		         menubar1.add(menu7);
		         menubar1.add(menu8);
		         this.setJMenuBar(menubar1);  

  
		          
		         
		         six.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent e) {
						 
						 new success().site();
					 }
				});

		
		         
		         o.addActionListener(new ActionListener(){
		        	 public void actionPerformed(ActionEvent e) {
//		        		 System.out.println("jhkjdhvjkv");
		        		 new StudioMgrUI();
//		        		 new Test().Test();
		        	 }
		         });
		         a.addActionListener(new ActionListener(){
		        	 public void actionPerformed(ActionEvent e) {
//		        		 System.out.println("jhkjdhvjkv");
		        		 new Display();
//		        		 new Test().Test();
		        	 }
		         });
		         b.addActionListener(new ActionListener(){
		        	 public void actionPerformed(ActionEvent e) {
//		        		 System.out.println("jhkjdhvjkv");
		        		 new Display();
//		        		 new Test().Test();
		        	 }
		         });
		         c.addActionListener(new ActionListener(){
		        	 public void actionPerformed(ActionEvent e) {
		        		 System.out.println("jhkjdhvjkv");
		        		 new Display();
//		        		 new Test().Test();
		        	 }
		         });
		         five.addActionListener(new ActionListener(){
		        	 public void actionPerformed(ActionEvent e) {
		        		 System.out.println("jhkjdhvjkv");
//		        		 new Display();
		        		 new Test().Test();
		        	 }
		         });
		         eight.addActionListener(new ActionListener(){
		        	 public void actionPerformed(ActionEvent e) {
//		        		 System.out.println("jhkjdhvjkv");
		        		 sign  frame = new sign();
		        			frame.setTitle("剧院票务管理系统-sign界面");
		        			//JFrame frame = new JFrame("剧院票务管理系统-sign界面");
		        			frame.setLocationRelativeTo(null);
		        			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        			frame.setSize(700,500);//界面的像素大小
		        			frame.setVisible(true);
		        	 }
		         });
		         
		         
		         
		         
		}

		
		
		
		
}
