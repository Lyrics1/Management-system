package xupt.se.ttms.view.sched;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.view.tmpl.PopUITmpl;

public class SchedAddUI extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; // 取消，保存按鈕

	protected boolean rst = false; // 操作结果
	private JLabel lblSched_id,lblStudio_id,lblPlay_id,lblTime, lblTicketPrice;
	protected JTextField txtId, txtStudio_id, txtPlay_id,txtTime, txtTicketPrice;
    
	
	@Override
	protected void initContent() {
		this.setTitle("添加演出计划");
		
		
		lblSched_id = new JLabel("演出计划id：");
		lblSched_id.setBounds(70, 50, 80, 30);
		contPan.add(lblSched_id);
		txtId = new JTextField();
		txtId.setBounds(170, 50, 250, 30);
		contPan.add(txtId);
		
		lblStudio_id = new JLabel("演出厅id：");
		lblStudio_id.setBounds(70, 120, 80, 30);
		contPan.add(lblStudio_id);
		txtStudio_id = new JTextField();
		txtStudio_id.setBounds(170, 120, 250, 30);
		contPan.add(txtStudio_id);

		lblPlay_id = new JLabel("剧目id：");
		lblPlay_id.setBounds(70, 190, 80, 30);
		contPan.add(lblPlay_id);
		txtPlay_id = new JTextField();
		txtPlay_id.setBounds(170, 190, 250, 30);
		contPan.add(txtPlay_id);

		lblTime = new JLabel("演出时间:");
		lblTime.setBounds(70, 260, 80, 30);
		contPan.add(lblTime);
		txtTime = new JTextField();
		txtTime.setBounds(170, 260, 250, 30);
		contPan.add(txtTime);

		lblTicketPrice = new JLabel("票价:");
		lblTicketPrice.setBounds(70, 330, 80, 30);
		contPan.add(lblTicketPrice);
		txtTicketPrice = new JTextField();
		txtTicketPrice.setBounds(170, 330, 250, 30);
		contPan.add(txtTicketPrice);

		btnSave = new JButton("保存");
		btnSave.addActionListener(this);
		btnSave.setBounds(190, 420, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(290, 420, 60, 30);
		contPan.add(btnCancel);

		/*
		 * ImageJPanel imageJP = new ImageJPanel(new ImageIcon(
		 * "files/imgs/pencil.jpg").getImage()); imageJP.setBounds(360, 160,
		 * 100, 100); imageJP.setLayout(null); this.add(imageJP);
		 */
	}

	public boolean getReturnStatus() {
		return rst;
	}

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
		if (txtStudio_id.getText()!=null && txtPlay_id.getText() != null && 
				txtTime.getText() != null && txtTicketPrice.getText() != null) {
			ScheduleSrv schSrv = new ScheduleSrv();
			Schedule sch = new Schedule();
			sch.setStudio_id(Integer.parseInt(txtStudio_id.getText()));
			sch.setPlay_id(Integer.parseInt(txtPlay_id.getText()));
			sch.setSched_time(Date.valueOf(txtTime.getText()));
			sch.setSched_ticket_price(Integer.parseInt(txtTicketPrice.getText()));
			
			schSrv.add(sch);
			this.setVisible(false);
			rst = true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}
	}

}
