package xupt.se.ttms.view.seat;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.view.tmpl.*;
import xupt.se.util.DBUtil;

public class SeatShow extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果


//	int lblseat_id;


	private JLabel lblseat_id,lblstudio_id,lblseat_row,lblseat_column;
	protected JTextField txtSeat_id, txtRow, txtColumn,txtstudio_id;

	private String seat_id,studio_id,seat_row,seat_column;
	
//	protected JTextArea txtIntro;

	public SeatShow(String id) {
		super();
		this.seat_id = id;
		try {
            //System.out.println("mvlslskj");
	    	String sql="select seat_id,studio_id,seat_row,seat_column  from seat"
	    			+ " where seat_id ="+this.seat_id;
	    			
	       			//System.out.println("try"+this.seat_id);
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
	    				while(rst.first()){
	    					System.out.println("2222");
	    					Seat stu=new Seat();
	    					stu.setSeat_id(rst.getInt("seat_id"));
	    					System.out.println("3333");
	    					stu.setStudio_id(rst.getInt("studio_id"));
	    					stu.setSeat_row(rst.getInt("seat_row"));
	    					stu.setSeat_column(rst.getInt("seat_column"));
//	    					stu.setIntroduction(rst.getString(""));
	    					System.out.println(stu.getSeat_id()+stu.getStudio_id()+stu.getSeat_row()+stu.getSeat_column());
//	    					txtName = new JTextField(stu.getName());
//	    					txtName.setBounds(150, 30, 400, 30);
//	    					contPan.add(txtName);
	    					System.out.println("1111");	    					
	    					txtSeat_id.setText(String.valueOf(stu.getSeat_id()));
	    					txtstudio_id.setText(String.valueOf(stu.getStudio_id()));
	    					txtRow.setText(String.valueOf(stu.getSeat_row()));
	    					txtColumn.setText(String.valueOf(stu.getSeat_column()));
//	    					txtIntro.setText(stu.getIntroduction());
	    				    System.out.println("mvlslskj");
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
	protected void initContent(){
		this.setTitle("查询座位");

		lblseat_id = new JLabel("座位号:");
		lblseat_id.setBounds(60, 30, 80, 30);// x,y,width,height
		contPan.add(lblseat_id);
		txtSeat_id = new JTextField();
		txtSeat_id.setBounds(150, 30, 400, 30);
		contPan.add(txtSeat_id);

		lblseat_row = new JLabel("座位  行数:");
		lblseat_row.setBounds(60, 80, 80, 30);
		contPan.add(lblseat_row);
		txtRow = new JTextField();
		txtRow.setBounds(150, 80, 120, 30);
		contPan.add(txtRow);

		lblseat_column = new JLabel("座位  列数:");
		lblseat_column.setBounds(340, 80, 80, 30);
		contPan.add(lblseat_column);
		txtColumn = new JTextField();
		txtColumn.setBounds(430, 80, 120, 30);
		contPan.add(txtColumn);
		
		lblstudio_id = new JLabel("演出厅ID:");
		lblstudio_id.setBounds(60, 130, 80, 30);
		contPan.add(lblstudio_id);
		txtstudio_id = new JTextField();
		txtstudio_id.setBounds(150, 130, 400, 30);
		contPan.add(txtstudio_id);

		
//		btnSave = new JButton("保存");
//		btnSave.addActionListener(this);
//		btnSave.setBounds(60, 280, 60, 30);
//		contPan.add(btnSave);
//
//		btnCancel = new JButton("取消");
//		btnCancel.addActionListener(this);
//		btnCancel.setBounds(180, 280, 60, 30);
//		contPan.add(btnCancel);

/*		ImageJPanel imageJP = new ImageJPanel(new ImageIcon(
				"files/imgs/pencil.jpg").getImage());
		imageJP.setBounds(360, 160, 100, 100);
		imageJP.setLayout(null);
		this.add(imageJP);
		*/
	}
	
	
	public boolean getReturnStatus(){
		   return rst;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst=false;
			this.setVisible(false);
		} else if (e.getSource() == btnSave) {
			btnSaveClicked();
		}
	}
	
	protected void btnSaveClicked(){
		if (txtstudio_id.getText() != null && txtRow.getText() != null
				&& txtColumn.getText() != null) {
			SeatSrv stuSrv = new SeatSrv();
			Seat stu=new Seat();
			stu.setStudio_id(Integer.parseInt(txtstudio_id.getText()));
			stu.setSeat_row(Integer.parseInt(txtRow.getText()));
			stu.setSeat_column(Integer.parseInt(txtColumn.getText()));
//			stu.setIntroduction(txtIntro.getText());

			stuSrv.add(stu);
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}

}