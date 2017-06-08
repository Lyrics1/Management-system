package xupt.se.ttms.view.sched;



import java.sql.Date;

import javax.swing.JOptionPane;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.ScheduleSrv;

public class SchedEditUI extends SchedAddUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Schedule stud;

	public SchedEditUI(Schedule sch) {
		initData(sch);
	}

	public void initData(Schedule sch) {
		if (null == sch) {
			return;
		}
		txtTime.setText(sch.getSched_time().toGMTString());
		txtTicketPrice.setText(Double.toString(sch.getSched_ticket_price()));
		
		
		stud = sch;
		this.invalidate();
	}

	@Override
	protected void btnSaveClicked() {
		if (txtTime.getText() != null && txtTicketPrice.getText() != null) {
			ScheduleSrv schSrv = new ScheduleSrv();
			Schedule sche = new Schedule();
			sche.setSched_time(Date.valueOf(txtTime.getText()));
			sche.setSched_ticket_price(Double.parseDouble(txtTicketPrice.getText()));
			schSrv.modify(sche);
			this.setVisible(false);
			rst = true;

		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}
	}

}