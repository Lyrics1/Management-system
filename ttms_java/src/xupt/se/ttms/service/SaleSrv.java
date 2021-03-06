package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iSaleDAO;
import xupt.se.ttms.model.Ticket;

public class SaleSrv {
	private iSaleDAO stuDAO=DAOFactory.creatSaleDAO();

	public boolean doSale(List<Ticket> tickets){
		return stuDAO.doSale(tickets);
	}
}
