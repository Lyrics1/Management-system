package xupt.se.ttms.idao;

import xupt.se.ttms.dao.SaleDAO;
import xupt.se.ttms.dao.ScheduleDAO;
import xupt.se.ttms.dao.SeatDAO;
import xupt.se.ttms.dao.StudioDAO;
import xupt.se.ttms.dao.TicketDAO;
import xupt.se.ttms.dao.playDAO;
import xupt.se.ttms.dao.popDAO;
import xupt.se.ttms.dao.sign_inDAO;

public class DAOFactory {
	public static iStudioDAO creatStudioDAO() {
		return new StudioDAO();
	}

	public static iSeatDAO creatSeatDAO() {
		return new SeatDAO();
	}

	public static iScheduleDAO creatScheduleDAO() {
		return new ScheduleDAO();
	}

	public static xupt.se.ttms.dao.playDAO creatPlayDAO() {

		return new playDAO();
	}

	public static ipopDAO creatpopDAO() {
		return new popDAO();
	}

	public static iTicketDAO creatTicketDAO() {
		// TODO 自动生成的方法存根
		return new TicketDAO();
	}

	public static iSaleDAO creatSaleDAO() {
		// TODO 自动生成的方法存根
		return new SaleDAO();
	}

	public static sign_inDAO creatsign_inDAO() {
		// TODO 自动生成的方法存根
		return new sign_inDAO();
	}
}
