package xupt.se.ttms.service;

import java.util.List;



import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iSeatDAO;
import xupt.se.ttms.model.Seat;


public class SeatSrv {
	private iSeatDAO seatDAO=DAOFactory.creatSeatDAO();
	
	public int add(Seat seat){
		return seatDAO.insert(seat); 		
	}
	
	public int modify(Seat stu){
		return seatDAO.update(stu); 		
	}
	
	public int delete(int ID){
		return seatDAO.delete(ID); 		
	}
	
	public List<Seat> Fetch(String condt){
		return seatDAO.select(condt);		
	}
	
	public List<Seat> FetchAll(){
		return seatDAO.select("");		
	}
}
