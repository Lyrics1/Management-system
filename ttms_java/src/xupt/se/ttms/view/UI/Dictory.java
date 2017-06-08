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


public class Dictory extends MainUITmpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null; // 界面提示
	
    //文字
	private JLabel hint;
	
	
	public Dictory() {

	}

	@Override
	protected void initContent() {
	
		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("影院历史", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 40);
		ca1.setFont(new java.awt.Font("宋体", 1, 40));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);
		

		hint = new JLabel(" ",JLabel.LEFT);
		hint.setText("<html>  电影院（cinema）是为观众放映电影的场所。电影在产生初期，是在咖啡厅、茶馆等场所放映的。随着电影的<br>"
				+ "进步与发展，出现了专门为放映电影而建造的电影院。电影的发展——从无声到有声乃至立体声，从黑白片到<br>"
				+ "彩色片，从普通银幕到宽银幕乃至穹幕、环幕，使电影院的形体、尺寸、比例和声学技术都发生了很大变化。<br>"
				+ "电影院必须满足电影放映的工艺要求，得到应有的良好视觉和听觉效果现在电影已经成为人们饭后的论点。</html>");
		hint.setBounds(50, 50, rect.width-480, rect.height - 200);
		hint.setFont(new java.awt.Font("宋体", 1, 20));
		hint.setForeground(Color.black);
		contPan.add(hint);
		

	
	}
	
	public static void main(String[] args) {
		Dictory frmStuMgr = new Dictory();
		frmStuMgr.setVisible(true);
	}
}
