package xupt.se.ttms.view.clerk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import xupt.se.ttms.view.Schedule_UI.Schedule_UIMgrUI;
import xupt.se.ttms.view.UI.Dictory;
import xupt.se.ttms.view.UI.Display;
import xupt.se.ttms.view.UI.TUICHU;
import xupt.se.ttms.view.play_UI.Play_UIMgrUI;

public class box extends JFrame {

	JTextArea jta = null;
	JScrollPane jscrollPane;
	JFrame jf;
	JPanel jpanel;

	public static void main(String[] args) {
		new box();
	}

	public box() {

		JFrame background = new JFrame();
		this.setTitle("剧院管理系统--电影简介");
		this.setSize(1024, 768);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		ImageIcon backgroundimg = new ImageIcon("resource/image/6.jpg");
		JLabel lable = new JLabel(backgroundimg);
		lable.setBounds(1024, 800, backgroundimg.getIconWidth(),
				backgroundimg.getIconHeight());
		JPanel imgagePanel = (JPanel) this.getContentPane();
		imgagePanel.setOpaque(false);
		this.getLayeredPane().add(lable, new Integer(Integer.MIN_VALUE));
		setVisible(true);

		JLabel labler = new JLabel("SIGN——PIG");
		labler.setForeground(new Color(0, 120, 210));
		labler.setBounds(40, 500, 150, 40);
		imgagePanel.add(labler);

		JLabel labler1 = new JLabel("一条狗的使命");
		labler1.setForeground(new Color(0, 120, 210));
		labler1.setBounds(220, 500, 150, 40);
		imgagePanel.add(labler1);

		JLabel labler2 = new JLabel("奇异博士");
		labler2.setForeground(new Color(0, 120, 210));
		labler2.setBounds(400, 500, 150, 40);
		imgagePanel.add(labler2);

		JLabel labler3 = new JLabel("魔幻城堡");
		labler3.setForeground(new Color(0, 120, 210));
		labler3.setBounds(580, 500, 150, 40);
		imgagePanel.add(labler3);

		JLabel labler4 = new JLabel("汪汪汪");
		labler4.setForeground(new Color(0, 120, 210));
		labler4.setBounds(760, 500, 150, 40);
		imgagePanel.add(labler4);

		ImageIcon icon = new ImageIcon("resource/image/1.jpg");
		JButton o = new JButton("TTMS");
		o.setIcon(icon);

		JButton one = new JButton("正在上映");
		JButton two = new JButton("即将上映");
		JButton three = new JButton("一周最热");
		JButton four = new JButton("今日最热");
		JButton five = new JButton("口碑最佳");
		JButton six = new JButton("经典老片");
		JButton seven = new JButton("欢迎来到电影世界");
		JButton eight = new JButton("特惠选座");
		JButton a = new JButton("");
		JButton b = new JButton("");
		JButton c = new JButton("");
		JButton d = new JButton("");
		// JButton e = new JButton("");
		// JButton f=new JButton("");
		// JButton g=new JButton("");
		ImageIcon icon1 = new ImageIcon("resource/image/main.jpg");

		ImageIcon icon2 = new ImageIcon("resource/image/2.jpg");
		ImageIcon icon3 = new ImageIcon("resource/image/3.jpg");
		ImageIcon icon4 = new ImageIcon("resource/image/4.jpg");
		ImageIcon icon5 = new ImageIcon("resource/image/aa.jpg");
		ImageIcon icon6 = new ImageIcon("resource/image/1.jpg");
		seven.setIcon(icon1);
		a.setIcon(icon2);
		b.setIcon(icon3);
		c.setIcon(icon4);
		d.setIcon(icon5);
		// e.setIcon(icon6);
		// d.setIcon(icon5);
		// e.setIcon(icon6);
		// f.setIcon(icon6);
		// g.setIcon(icon6);

		JPanel panel = new JPanel();
		panel.add(o);
		panel.add(a);
		panel.add(b);

		panel.add(c);
		panel.add(d);
		// panel.add(e);
		// panel.add(f);
		// panel.add(g);
		panel.add(one);
		panel.add(two);
		panel.add(three);
		panel.add(four);
		panel.add(five);
		panel.add(six);
		panel.add(seven);
		panel.add(eight);
		panel.setLayout(null);

		panel.setBounds(0, 0, 1024, 768);
		o.setBounds(20, 300, 150, 200);
		a.setBounds(200, 300, 150, 200);
		b.setBounds(380, 300, 150, 200);
		c.setBounds(560, 300, 150, 200);
		d.setBounds(740, 300, 150, 200);
		// e.setBounds(900, 300, 150, 200);
		// f.setBounds(340,360,150,40);
		// g.setBounds(500,360,150,40);
		one.setBounds(0, 250, 100, 30);
		two.setBounds(100, 250, 100, 30);
		three.setBounds(200, 250, 100, 30);
		four.setBounds(300, 250, 100, 30);
		five.setBounds(400, 250, 100, 30);
		six.setBounds(500, 250, 100, 30);
		eight.setBounds(600, 250, 100, 30);
		seven.setBounds(0, 0, 1024, 250);
		panel.setBackground(null);
		panel.setOpaque(false);
		add(panel);

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		JMenuBar menubar1 = new JMenuBar();
		// menubar1.setBackground();
		JMenu menu1 = new JMenu("系统登录");
		JMenu menu2 = new JMenu("影院简介");
		JMenu menu5 = new JMenu("购票选座");
		JMenu menu6 = new JMenu("影片简介");
		JMenu menu7 = new JMenu("用户设置");
		JMenu menu8 = new JMenu("退出登录");
		JMenuItem menu1Item1 = new JMenuItem("用户");
		JMenuItem menu1Item2 = new JMenuItem("管理员");
		JMenuItem menu2Item1 = new JMenuItem("影院历史");
		JMenuItem menu2Item2 = new JMenuItem("放映厅信息");
		JMenuItem menu5Item1 = new JMenuItem("购票");
		// JMenuItem menu5Item2 = new JMenuItem("选座位");
		JMenuItem menu6Item1 = new JMenuItem("影片信息");
		JMenuItem menu7Item1 = new JMenuItem("修改密码");
		JMenuItem menu8Item1 = new JMenuItem("退出");

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screeSize = kit.getScreenSize();
		JScrollPane js;
		menu1.add(menu1Item1);
		menu1.add(menu1Item2);
		menu2.add(menu2Item1);
		menu2.add(menu2Item2);
		menu5.add(menu5Item1);
		// menu5.add(menu5Item2);
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

		o.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("jhkjdhvjkv");
				// new Test().Test();
			}
		});
		a.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("jhkjdhvjkv");
				// new Test().Test();
			}
		});
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("jhkjdhvjkv");
				// new Test().Test();
			}
		});
		c.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("jhkjdhvjkv");
				// new Test().Test();
			}
		});
		menu5Item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//剧目界面
				Play_UIMgrUI ss = new  Play_UIMgrUI();
//				Schedule_UIMgrUI ss = new Schedule_UIMgrUI();
				ss.setVisible(true);
			}
		});
		menu1Item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sign frame = new sign();
				frame.setTitle("剧院票务管理系统-sign界面");
				// JFrame frame = new JFrame("剧院票务管理系统-sign界面");
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(700, 500);// 界面的像素大小
				frame.setVisible(true);
			}
		});
		menu1Item2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sign frame = new sign();
				frame.setTitle("剧院票务管理系统-sign界面");
				// JFrame frame = new JFrame("剧院票务管理系统-sign界面");
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(700, 500);// 界面的像素大小
				frame.setVisible(true);
			}
		});
		menu6Item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Display sui = new Display();
				sui.setVisible(true);
				sui.setBounds(150, 200, 800, 500);
			}
		});
		menu2Item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Dictory frmStuMgr = new Dictory();
				frmStuMgr.setVisible(true);
			}
		});
		menu8Item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TUICHU frmStuMgr = new TUICHU();
				frmStuMgr.setVisible(true);
			}
		});
	}
}
