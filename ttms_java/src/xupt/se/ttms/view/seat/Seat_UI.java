/**
 * 
 */
package xupt.se.ttms.view.seat;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.view.system.SysUserModUI;
import xupt.se.ttms.view.ticket.TicketAddUI;
import xupt.se.ttms.view.tmpl.ImagePanel;
import xupt.se.util.DBUtil;

/**
 * @author Administrator
 * 
 */

public class Seat_UI extends JFrame {

	private static final long serialVersionUID = 1L;
	private int r,c;
	private final int frmWidth = 1024;
	private final int frmHeight = 700;
	protected final ImagePanel headPan = new ImagePanel(
			"resource/image/header.jpg");
	protected final ImagePanel contPan = new ImagePanel(
			"D:/eclipse/newcode/endttms/resource/image/selectsite.png");
	// protected final JPanel contPan = new JPanel();
	protected JLabel usrLabel = new JLabel();
	protected JLabel usrName = new JLabel();
	protected JButton btnModPwd = new JButton("修改密码");
	// protected JButton btn1 = new JButton("2222");
	protected JButton btnExit = new JButton("返回");
	private int col, row;
	private JButton btnCom;

	// public Seat_UI(int row,int col){
	// this.row=row;
	// this.col=col;
	// }
	public Seat_UI() {
		this.setSize(frmWidth, frmHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("汉唐剧院票务管理系统");
		this.setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// onWindowClosing();
			}
		});

		headPan.setBounds(0, 0, frmWidth, 80);
		contPan.setBounds(150, 120, frmWidth, 500);
		headPan.setLayout(null);
		contPan.setLayout(null);
		// ImageIcon icon4 = new ImageIcon("C:/Users/BonCzech/Desktop/1.png");
		// contPan.setIcon(icon4);
		this.add(headPan);
		this.add(contPan);

		contPan.setBounds(0, 80, frmWidth, this.frmHeight - 100);
		contPan.setLayout(null);
		this.add(contPan);

		initHeader();
		initContent();
	}

	public class GridLayoutDemo extends Applet {
		@Override
		public void init() {
			setLayout(new GridLayout(3, 2));
			add(new Button("1"));
			add(new Button("2"));
			add(new Button("3"));
			add(new Button("4"));
			add(new Button("5"));
			add(new Button("6"));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Seat_UI().setVisible(true);

				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e,
							"Exception", 0);
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	public int getWidth() {
		return this.frmWidth;

	}

	@Override
	public int getHeight() {
		return this.frmHeight;

	}

	private void initHeader() {
		try {

			usrLabel.setBounds(frmWidth - 160, 5, 80, 30);
			usrLabel.setText("当前用户：");
			headPan.add(usrLabel);

			usrName.setBounds(frmWidth - 80, 5, 80, 30);
			usrName.setText("匿名");
			usrName.setFont(new java.awt.Font("宋体", 1, 15));
			usrName.setForeground(Color.blue);
			headPan.add(usrName);

			// usrLabel = new JLabel("座位管理", SwingConstants.CENTER);
			// usrLabel.setBounds(0, 5, usrLabel.WIDTH, 30);
			// usrLabel.setFont(new java.awt.Font("宋体", 1, 20));
			// usrLabel.setForeground(Color.blue);
			// contPan.add(usrLabel);

			btnModPwd.setBounds(frmWidth - 160, 40, 80, 30);
			btnModPwd.setMargin(new Insets(0, 0, 0, 0));
			btnModPwd.setContentAreaFilled(false);
			headPan.add(btnModPwd);
			btnModPwd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent Event) {
					btnModUserClicked();
				}
			});

			btnExit.setBounds(frmWidth - 80, 40, 80, 30);
			btnExit.setContentAreaFilled(false);
			btnExit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent Event) {
					btnExitClicked(Event);
				}
			});

			headPan.add(btnExit);

			// Show the information of current user
			showCurrentUser();

		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
			e.printStackTrace();
		}
	}

	// private void initBody(){
	// btn1.setBounds(50, 150, 30, 30);
	// btn1.setMargin(new Insets(0,0,0,0));
	// btn1.setContentAreaFilled(false);
	// contPan.add(btn1);
	// btn1.addActionListener(new ActionListener() {
	// public void actionPerformed(ActionEvent Event) {
	// btnModUserClicked();
	// }
	// });
	// }

	private void btnModUserClicked() {
		SysUserModUI dlgUserMod = new SysUserModUI();
		dlgUserMod.setModal(true);
		dlgUserMod.setVisible(true);
	}

	private void showCurrentUser() {
		LoginedUser curUser = LoginedUser.getInstance();
		String name = curUser.getEmpName();
		if (null == name || name.isEmpty())
			usrName.setText("游客");
		else
			usrName.setText(name);
	}

	// To be override by the detailed business block interface
	protected void onWindowClosing() {
		System.exit(0);
	}

	// To be override by the detailed business block interface
	protected void initContent() {
		// JButton b1;
		// JButton b2;
		// JButton b3;
		// JButton b4;
		// int valRow=new SeatAddUI().valRow;
		// int valCol=new SeatAddUI().valCol;
		// System.out.print(row);
		usrName.setText("用户界面");
		// System.out.print(col);
		final ImageIcon icon1 = new ImageIcon("resource/image/white.png");
		ImageIcon icon2 = new ImageIcon("resource/image/red.jpg");
		final ImageIcon icon3 = new ImageIcon("resource/image/green.png");
		int a = 320, b = 180;
		int count = 0;
		Color Blue = new Color(0, 110, 210);
		final int[][] buttonArray = new int[11][11];
		final JButton[][] button = new JButton[15][15];
		
		Rectangle rect = contPan.getBounds();
		usrLabel = new JLabel("演出厅座位", SwingConstants.CENTER);
		usrLabel.setBounds(0, 5, rect.width, 30);
		usrLabel.setFont(new java.awt.Font("宋体", 1, 20));
		usrLabel.setForeground(Color.blue);
		contPan.add(usrLabel);

		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++, count++) {

				button[i][j] = new JButton();
				button[i][j].setBounds(a, b, 31, 31);
				// button[i][j].setBackground(Color.ORANGE);
				button[i][j].setIcon(icon1);

				contPan.add(button[i][j]);
				a = a + 36;

				// buttonArray[i][j] = 0;
				final int m = i, n = j;
				
				button[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int temp = 0;
						// count++;
						if (button[m][n].getIcon() == icon1 && temp == 0) {
							button[m][n].setIcon(icon3);
							r=m+1;
							c=n+1;
							// count+=1;
							temp = 1;
							buttonArray[m][n] = 1;
						}
						if (button[m][n].getIcon() == icon3 && temp == 0) {
							button[m][n].setIcon(icon1);

							temp = 1;
							// count-=1;
							buttonArray[m][n] = 0;
						}
					}
				});
			}
			a = 320;
			b = b + 34;
		}
		button[2][6].setIcon(icon2);
		button[1][4].setIcon(icon2);
		button[3][9].setIcon(icon2);
		button[6][8].setIcon(icon2);
		btnCom = new JButton("购买");
		btnCom.setBounds(rect.width - 80, rect.height - 60, 60, 30);
		btnCom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent Event) {
				btnComClicked();
			}
		});
		contPan.add(btnCom);
	}

	private void btnComClicked() {
		
		int confirm = JOptionPane.showConfirmDialog(null, "确认购买吗？", "购买",
				JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
//			delete();
			update();
			TicketAddUI t = new TicketAddUI();
			t.setVisible(true);
			
			
//			SeatSrv stuSrv = new SeatSrv();
			// stuSrv.delete(stud.getSeat_id());
			// showTable();
		}
	}
	
	private void delete() {
		// TODO 自动生成的方法存根
		int rtn=0;
		try {
			String sql = "delete from end ";
					

			
//			sql += " where schedule_id = " + ;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn =db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void update() {

		int rtn=0;
		try {
			String sql = "update end set " + "set_row =" + this.r +", "+"set_col ="+this.c;
					

			
//			sql += " where schedule_id = " + ;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn =db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return rtn;
		
	}

	// To be override by the detailed business block interface
	protected void btnExitClicked(ActionEvent Event) {
		System.exit(0);
	}
}
