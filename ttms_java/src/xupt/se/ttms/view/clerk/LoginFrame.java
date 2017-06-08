package xupt.se.ttms.view.clerk;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LoginFrame{
		
	JFrame mainFrame = new JFrame("欢迎使用");
	
	public LoginFrame(){
	
		int FWIDTH = 1024;
		int FHEIGHT = 768;
		mainFrame.setSize(1024, 768);
		mainFrame.setLocation(0, 0);
		mainFrame.setResizable(false);
		mainFrame.setLayout(null);
		mainFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});

		JPanel pwelcomeimg = new JPanel();
		JPanel plogin = new JPanel();
		pwelcomeimg.setSize(FWIDTH, FHEIGHT*3/4);
		plogin.setSize(FWIDTH, FHEIGHT/4);
		pwelcomeimg.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		plogin.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		plogin.setLocation(0, FHEIGHT*3/4);
		
		ImageIcon imgwelcome = new ImageIcon("resource/image/12.jpg");
		ImageIcon imglogin = new ImageIcon("resource/image/13.png");
		JLabel jLabelwel = new JLabel(imgwelcome);
		JLabel jLabellogin = new JLabel(imglogin);
		jLabellogin.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
		
		JButton blogin = new JButton("登陆");
	
//		blogin.setBounds(50, 30, 80, 30);
		JButton breset = new JButton("重置");
//		breset.setBounds(50, 30, 80, 30);
		final JTextField usernametext = new JTextField(10);
		final JPasswordField passwordtext = new JPasswordField(10);
		JLabel username = new JLabel("用户名");
		JLabel password = new JLabel("密码");	
//		username.setBounds(0, 30, 80, 30);
		
		jLabellogin.add(username);
		jLabellogin.add(usernametext);
		jLabellogin.add(password);
		jLabellogin.add(passwordtext);
		jLabellogin.add(blogin);
		jLabellogin.add(breset);		
		
		pwelcomeimg.add(jLabelwel);
		plogin.add(jLabellogin);
		blogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(usernametext.getText().equals("test") && passwordtext.getText().equals("test"))
						{
							ClerkMenuFrame clerkMenuFrame = new ClerkMenuFrame();
							clerkMenuFrame.setVisible(true);
							mainFrame.dispose();							
						}else
							JOptionPane.showMessageDialog(null, "用户名或密码错误，请重试。");
					}
				});
		
		mainFrame.add(pwelcomeimg);
		mainFrame.add(plogin);
		pwelcomeimg.setVisible(true);
		plogin.setVisible(true);
		mainFrame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoginFrame();
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
					e.printStackTrace();
				}
			}
		});

	}

}
