/**
 * 
 */
package xupt.se.ttms.view.tmpl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.view.clerk.ClerkMenuFrame;
import xupt.se.ttms.view.system.SysUserModUI;

/**
 * @author Administrator
 * 
 */

public class Main extends JFrame {

	String imagePath = "resource/image/m.jpg";
	Image img = Toolkit.getDefaultToolkit().createImage(imagePath);

	private static final long serialVersionUID = 1L;
	private final int frmWidth = 1024;
	private final int frmHeight = 700;
	protected final ImagePanel headPan = new ImagePanel(
			"resource/image/header.jpg");
	protected final JPanel contPan = new JPanel() {
		@Override
		protected void paintChildren(Graphics g) {
			g.drawImage(img, 0, 0, this);
			super.paintChildren(g);
		}
	};
	protected JLabel usrLabel = new JLabel();
	protected JLabel usrName = new JLabel();
	protected JButton btnModPwd = new JButton("修改密码");
	protected JButton btnExit = new JButton("返回");

	public Main() {
		this.setSize(frmWidth, frmHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("汉唐剧院票务管理系统");
		this.setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				onWindowClosing();
			}
		});

		headPan.setBounds(0, 0, frmWidth, 80);
		headPan.setLayout(null);
		this.add(headPan);

		contPan.setBounds(0, 80, frmWidth, this.frmHeight - 100);
		contPan.setLayout(null);
		this.add(contPan);

		initHeader();
		//initContent();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Main().setVisible(true);
					;

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

	private void btnModUserClicked() {
		SysUserModUI dlgUserMod = new SysUserModUI();
		// dlgUserMod.setModal(true);
		dlgUserMod.setVisible(true);
	}

	private void showCurrentUser() {
		LoginedUser curUser = LoginedUser.getInstance();
		String name = curUser.getEmpName();
		if (null == name || name.isEmpty())
			usrName.setText("匿名用户");
		else
			usrName.setText(name);
	}

	// To be override by the detailed business block interface
	protected void onWindowClosing() {
		System.exit(0);
	}

	// To be override by the detailed business block interface
	protected void initContent() {
	}

	// To be override by the detailed business block interface
	protected void btnExitClicked(ActionEvent Event) {
		ClerkMenuFrame clerkMenuFrame = new ClerkMenuFrame();
		clerkMenuFrame.setVisible(true);
	}

}
