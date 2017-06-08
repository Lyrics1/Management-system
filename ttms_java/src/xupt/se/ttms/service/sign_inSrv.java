package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.dao.sign_inDAO;
import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.model.Sign_in;

public class sign_inSrv {
	private static sign_inDAO playDAO = DAOFactory.creatsign_inDAO();

	// private iScheduleDAO schDAO=DAOFactory.creatScheduleDAO();

	public static int add(Sign_in play) {
		return playDAO.insert(play);
	}

	public int modify(Sign_in play) {
		return playDAO.update(play);
	}

	public int delete(String name) {
		return playDAO.delete(name);
	}

	public List<Sign_in> Fetch(String condt) {
		return playDAO.select(condt);
	}

}
