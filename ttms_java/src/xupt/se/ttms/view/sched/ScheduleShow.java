package xupt.se.ttms.view.sched;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.view.tmpl.PopUITmpl;
import xupt.se.util.DBUtil;

public class ScheduleShow extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕
	private String name,Sched_id;
	
	protected boolean rst=false; 				//操作结果
	private JLabel lblSched_id, lblStudio_id, lblPlay_id, lblTime,lblTicketPrice;
	protected JTextField txtID, txtStudio_id, txtPlay_id,txtTime,txtTicketPrice;
	protected JTextArea txtIntro;
	

	public ScheduleShow(String id) {
		super();
		this.Sched_id = id;
//		System.out.println(" "+this.name);
		try {
//			System.out.prin0tln("mvlslskj");
	    	String sql="select sched_id,studio_id,Play_id,Sched_time,Sched_ticket_price"
	    			+ "  from schedule where Sched_id=" + this.Sched_id ;
	    			
	       			System.out.println(" try"+this.Sched_id );
	       			DBUtil db = new DBUtil();
	    			if(!db.openConnection()){
	    				System.out.print("fail to connect database");
	    				
	    			}
//	    			ResultSet rst = db.getInsertObjectIDs(sql);
	    			ResultSet rst = db.execQuery(sql);
//	    			System.out.println(rst);
	    			System.out.println(rst.first());
//	    			System.out.println(rst.next());
	    		
	    			if (rst!=null) {
//	    				System.out.println("mvlslskj");
	    				while(rst.first()){
	    					System.out.println("mvlslskj");
	    					Schedule stu=new Schedule();
	    					stu.setSched_id(rst.getInt("sched_id"));
	    					stu.setStudio_id(rst.getInt("studio_id"));
	    					stu.setPlay_id(rst.getInt("play_id"));
	    					stu.setSched_time(rst.getDate("sched_time"));
	    					stu.setSched_ticket_price(rst.getDouble("Sched_ticket_price"));
	    					
//	    					txtID = new JTextField(stu.getSched_id());
//	    					txtID.setBounds(150, 30, 400, 30);
//	    					contPan.add(txtID);
	    					txtID.setText(String.valueOf(stu.getSched_id()));
	    					txtStudio_id.setText(String.valueOf(stu.getStudio_id()));
	    					txtPlay_id.setText(String.valueOf(stu.getStudio_id()));
	    					txtTime.setText((stu.getSched_time()).toGMTString());
	    					txtTicketPrice.setText(String.valueOf(stu.getSched_ticket_price()));
	    					
	    					return;
	    					
	    				}
		}
//	    					
	    	
	    	
//	    			}
//	    				while(rst.next()){
//	    					Sign_in stu=new Sign_in();
//	    					
//	    					stu.setname(rst.getString("name"));
//	    					stu.setpass(rst.getString("pass"));
//	    					
//	    				
////	    					stuList.add(stu);
//	    				}
//	    			}
//	    			System.out.println("注册成功");
	    			
	    			
	    			
	    		} catch (Exception ee) {
	    			ee.printStackTrace();
	    		}
	
	}
	@Override
	protected void initContent() {
		this.setTitle("查找演出计划");
		
		
		lblSched_id = new JLabel("演出计划id：");
		lblSched_id.setBounds(70, 50, 80, 30);
		contPan.add(lblSched_id);
		txtID = new JTextField();
		txtID.setBounds(170, 50, 250, 30);
		contPan.add(txtID);
		
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

//		btnSave = new JButton("保存");
//		btnSave.addActionListener(this);
//		btnSave.setBounds(190, 420, 60, 30);
//		contPan.add(btnSave);
//
//		btnCancel = new JButton("取消");
//		btnCancel.addActionListener(this);
//		btnCancel.setBounds(290, 420, 60, 30);
//		contPan.add(btnCancel);

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
