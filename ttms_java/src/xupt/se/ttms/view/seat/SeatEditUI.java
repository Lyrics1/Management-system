package xupt.se.ttms.view.seat;


import javax.swing.JOptionPane;

import xupt.se.ttms.model.Seat;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.view.seat.SeatAddUI;

public class SeatEditUI extends SeatAddUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Seat stud;

	public SeatEditUI(Seat stu){
		initData(stu);
	}
	
	public void initData(Seat stu) {
		if(null== stu){
			return;
		}
		txtstudio_id.setText(Integer.toString(stu.getStudio_id()));
		txtRow.setText(Integer.toString(stu.getSeat_row()));
		txtColumn.setText(Integer.toString(stu.getSeat_column()));
//		txtIntro.setText(stu.getIntroduction());
		stud=stu;
		this.invalidate();
	}

	@Override
	protected void btnSaveClicked(){
		if (txtstudio_id.getText() != null && txtRow.getText() != null
				&& txtColumn.getText() != null) {
			SeatSrv stuSrv = new SeatSrv();
			Seat stu= stud;
			stu.setStudio_id(Integer.parseInt(txtstudio_id.getText()));
			stu.setSeat_row(Integer.parseInt(txtRow.getText()));
			stu.setSeat_column(Integer.parseInt(txtColumn.getText()));
//			stu.setIntroduction(txtIntro.getText());
			stuSrv.modify(stu);
			this.setVisible(false);
			rst=true;
			
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
	
}
