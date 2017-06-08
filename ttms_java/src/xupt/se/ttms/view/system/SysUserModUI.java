package xupt.se.ttms.view.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import xupt.se.ttms.view.play.PlayMgrUI;
import xupt.se.ttms.view.sched.SchedMgrUI;
import xupt.se.ttms.view.seat.SeatMgrUI;
import xupt.se.ttms.view.studio.StudioMgrUI;
import xupt.se.ttms.view.tmpl.MainUITmpl;

public class SysUserModUI extends MainUITmpl {

	public SysUserModUI() {
		JButton button1 = new JButton("演出厅管理");
		JButton button2 = new JButton("剧目管理");
		JButton button3 = new JButton("演出计划管理");
		JButton button4 = new JButton("座位管理");

		usrName.setText("管理员");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StudioMgrUI frmStuMgr = new StudioMgrUI();
				frmStuMgr.setVisible(true);
			}
		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayMgrUI frmplayMgr = new PlayMgrUI();
				frmplayMgr.setVisible(true);
			}
		});
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SchedMgrUI frmStuMgr = new SchedMgrUI();
				frmStuMgr.setVisible(true);
			}
		});
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SeatMgrUI frmStuMgr = new SeatMgrUI();
				frmStuMgr.setVisible(true);
			}
		});

		contPan.add(button1);
		contPan.add(button2);
		contPan.add(button3);
		contPan.add(button4);

		contPan.setLayout(null);
		button1.setBounds(50, 200, 180, 150);
		button2.setBounds(300, 200, 180, 150);
		button3.setBounds(550, 200, 180, 150);
		button4.setBounds(780, 200, 180, 150);
		contPan.setBackground(null);
		contPan.setVisible(true);

	}

	public static void main(String[] args) {
		SysUserModUI sys = new SysUserModUI();
		sys.setVisible(true);
	}

	public void setModal(boolean b) {
		// TODO Auto-generated method stub

	}
}
