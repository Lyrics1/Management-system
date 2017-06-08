package xupt.se.ttms.view.clerk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import xupt.se.ttms.model.Sign_in;
import xupt.se.ttms.service.sign_inSrv;

public class sign_in extends JFrame {

	JTextField f1;
	JTextField f2;
	JButton b1;
	// JButton b2;
	String power;// 表示权限
	JPanel p5;
	// ----------------------------图片路径------------------------------------
	String imagePath = "resource/image/12.jpg";
	Image img = Toolkit.getDefaultToolkit().createImage(imagePath);

	// ----------------------------图片路径------------------------------------

	public sign_in() {
		Container c = getContentPane();
		/*
		 * setLayout(new GridLayout(20, 2)); add(new JLabel("用户名：")); add(new
		 * JTextField(15)); add(new JLabel("密码")); add(new JTextField(15));
		 */
		Label l1 = new Label("user：");
		Label l2 = new Label("Key：");
		f1 = new JTextField(15);
		f2 = new JPasswordField(15);
		b1 = new JButton("完成注册   ");
		// b1.setBounds(50, 50, 100, 30);
		// b2=new JButton("注册");
		// JPanel p1=new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		p5 = new JPanel() {
			@Override
			protected void paintChildren(Graphics g) {
				g.drawImage(img, 0, 0, this);
				super.paintChildren(g);
			}
		};
		JPanel p6 = new JPanel();
		p2.add(l1);
		p2.add(f1);

		p2.setBorder(new MatteBorder(5, 5, 1, 1, Color.WHITE));
		p2.setBackground(Color.WHITE);
		p3.add(l2);
		p3.add(f2);
		p3.setBackground(Color.WHITE);
		p3.setBorder(new MatteBorder(5, 5, 1, 1, Color.WHITE));
		p4.add(b1);
		// p4.add(b2);
		p4.setBorder(new MatteBorder(5, 5, 1, 1, Color.WHITE));
		p4.setBackground(Color.WHITE);
		p5.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		p5.add(p2);
		p5.add(p3);
		p5.add(p4);
		p5.setBorder(new MatteBorder(150, 400, 1, 1, Color.WHITE));
		c.add(p5, BorderLayout.CENTER);

		b1.addActionListener(new Enter());
		// b2.addActionListener(new ReWrite());

	}

	// ----------------------判断权限-------------------------------
	class Enter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if ((f1.getText()) != "1" && (f2.getText()) != "1") {
			
					
				
						System.out.println(f1.getText());
						System.out.println(f2.getText());

						sign_inSrv sign = new sign_inSrv();
						Sign_in s = new Sign_in();

						s.setname(f1.getText());
						s.setpass(f2.getText());
						sign.add(s);
						// try {
						// String sql="insert into sign(name,pass) values('" +
						// f1.getText() +"','"+f2.getText()+"');";
						//
						//
						// DBUtil db = new DBUtil();
						// if(!db.openConnection()){
						// System.out.print("fail to connect database");
						//
						// }
						// ResultSet rst = db.getInsertObjectIDs(sql);
						// System.out.println("注册成功");
						//
						//
						//
						// } catch (Exception ee) {
						// ee.printStackTrace();
						// // }

					
				
				// JOptionPane.showMessageDialog(null,
				// "登录成功！用户权限是adimistrator");
				power = "adminstrator";

				sign frame = new sign();
				frame.setTitle("剧院票务管理系统-sign界面");
				// JFrame frame = new JFrame("剧院票务管理系统-sign界面");
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(700, 500);// 界面的像素大小
				frame.setVisible(true);
			} else if ((f1.getText()).equals("经理")
					&& (f2.getText()).equals("123456")) {
				JOptionPane.showMessageDialog(null, "登录成功!登录成功！用户权限是user");
				power = "user";
			} else
				JOptionPane.showMessageDialog(null, "登录失败，请重新登录！");
		}
	}

	class ReWrite implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new sign_in();
		}
	}

	public static void main(String[] args) {
		sign_in frame = new sign_in();
		frame.setTitle("剧院票务管理系统-sign界面");
		// JFrame frame = new JFrame("剧院票务管理系统-sign界面");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 500);// 界面的像素大小
		frame.setVisible(true);
	}
}
