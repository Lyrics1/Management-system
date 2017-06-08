package xupt.se.ttms.view.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.view.sched.SchedAddUI;
import xupt.se.ttms.view.sched.SchedEditUI;
import xupt.se.ttms.view.sched.SchedMgrUI;
import xupt.se.ttms.view.sched.ScheduleShow;
import xupt.se.ttms.view.tmpl.ImagePanel;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.util.DBUtil;


public class TUICHU extends MainUITmpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null; // 界面提示
	
    //文字
	private JLabel hint;
	
	
	public TUICHU() {

	}

	@Override
	protected void initContent() {
	
		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("退出系统", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 40);
		ca1.setFont(new java.awt.Font("宋体", 1, 40));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);
		

		hint = new JLabel(" ",JLabel.CENTER);
		hint.setText("<html>  豪华长颈鹿电影院！<br>"
				+"<br>"
				+ "我们诚挚邀请您下次光临！<br>"
				+"<br>"
				+ "祝您生活愉快！<br>"
				+ "</html>");
		hint.setBounds(30, 50, rect.width-500, rect.height - 200);
		hint.setFont(new java.awt.Font("宋体", 1, 30));
		hint.setForeground(Color.black);
		contPan.add(hint);
		

	
	}
	
	public static void main(String[] args) {
		TUICHU frmStuMgr = new TUICHU();
		frmStuMgr.setVisible(true);
	}
}

