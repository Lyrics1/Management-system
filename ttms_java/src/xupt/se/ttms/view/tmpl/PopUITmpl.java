package xupt.se.ttms.view.tmpl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import xupt.se.ttms.model.pop;
import xupt.se.ttms.service.popUIsrv;
import xupt.se.util.DBUtil;

public class PopUITmpl extends JDialog implements ActionListener {
	// ----------------------------图片路径-----------------------------/------
	String imagePath = "resource/image/m.jpg";
	Image img = Toolkit.getDefaultToolkit().createImage(imagePath);

	// ----------------------------图片路径------------------------------------
	private JLabel l1, l2, l3, l4;
	////////////
	private pop popl;
	///////////
	protected JTextField t1, t2, t3, t4;
	private JButton btnCancel, btnSave; // 取消，保存按鈕
	protected JTextField txtName;
	protected boolean rst = false; // 操作结果
	private static final long serialVersionUID = 1L;
	private final int frmWidth = 800;
	private final int frmHeight = 600;
	public final ImagePanel headPan = new ImagePanel(
			"resource/image/header_pop.jpg");

	// ////////////////////////////
	public JPanel contPan = new JPanel() {
		@Override
		protected void paintChildren(Graphics g) {
			g.drawImage(img, 0, 0, this);
			super.paintChildren(g);
		}
	};

	public JLabel windowName = new JLabel();

	public PopUITmpl() {
		this.setSize(frmWidth, frmHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				onWindowClosing();
			}
		});

		headPan.setBounds(0, 0, frmWidth, 60);
		headPan.setLayout(null);
		this.add(headPan);

		contPan.setBounds(0, 60, frmWidth, this.frmHeight - 80);
		contPan.setLayout(null);
		this.add(contPan);

		initHeader();
		initContent();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new PopUITmpl().setVisible(true);
					;

				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e,
							"Exception", 0);
					e.printStackTrace();
				}
			}
		});

	}

	private void initHeader() {
		try {

			windowName.setBounds(frmWidth - 160, 5, 160, 50);
			windowName.setFont(new java.awt.Font("dialog", 1, 20));
			windowName.setForeground(Color.blue);
			headPan.add(windowName);
			setWindowName("修改密码");

		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
			e.printStackTrace();
		}
	}

	public void setWindowName(String name) {
		windowName.setText(name);
	}

	// To be override by the detailed business block interface
	protected void onWindowClosing() {
		this.dispose();
	}

	// To be override by the detailed business block interface
	protected void initContent() {
		l1 = new JLabel("用户名：");
		l1.setBounds(60, 60, 200, 30);
		contPan.add(l1);
		t1 = new JTextField();
		t1.setBounds(200, 60, 200, 30);
		contPan.add(t1);

		
//		lblName = new JLabel("演出厅名称:");
//		lblName.setBounds(60, 30, 80, 30);
//		contPan.add(lblName);
//		txtName = new JTextField();
//		txtName.setBounds(150, 30, 400, 30);
//		contPan.add(txtName);
//		
		
		
		l2 = new JLabel("请输入原始密码：");
		l2.setBounds(60, 120, 200, 30);
		contPan.add(l2);
		t2 = new JTextField();
		t2.setBounds(200, 120, 200, 30);
		contPan.add(t2);

		l3 = new JLabel("请输入新密码：");
		l3.setBounds(60, 180, 200, 30);
		contPan.add(l3);
		t3 = new JTextField();
		t3.setBounds(200, 180, 200, 30);
		contPan.add(t3);

		l4 = new JLabel("请再次输入密码：");
		l4.setBounds(60, 240, 200, 30);
		contPan.add(l4);
		t4 = new JTextField();
		t4.setBounds(200, 240, 200, 30);
		contPan.add(t4);

		JButton btnSave = new JButton("保存");
		btnSave.addActionListener(this);
		btnSave.setBounds(100, 300, 60, 30);
		//System.out.println("hgui");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
		//		System.out.println("hgui");
				btnSaveClicked();
			}
		});
		contPan.add(btnSave);

		JButton btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(200, 300, 60, 30);
		contPan.add(btnCancel);
	
		

	}
	
	public boolean getReturnStatus(){
		   return rst;
	}
	

	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst = false;
			this.dispose();
			getParent().setVisible(true);

		} else if (e.getSource() == btnSave) {
			btnSaveClicked(); // 以前未调用，新添加的调用语句
		}

	}

	protected void btnSaveClicked() {
		
		if (t1.getText() != null && t2.getText() != null
				&& t3.getText() != null&& t4.getText() != null) {
			
			popUIsrv popSrv = new popUIsrv();
			pop pop = new pop();
			pop.sett1(t1.getText());
			pop.sett2(t2.getText());
			pop.sett3(t3.getText());
			pop.sett4(t4.getText());
			popSrv.update(pop);
			this.setVisible(false);
			rst = true;
			JOptionPane.showMessageDialog(null, "成功");
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}
	}
//		PopUITmpl popadd = new PopUITmpl();
//		popadd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		popadd.toFront();
//		popadd.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
//		popadd.setVisible(true);
//		if (popadd.getReturnStatus()) {
//			showTable();
//		}
//		
//	}
//
//	private void showTable() {
//		// TODO 自动生成的方法存根
//		
	

}
