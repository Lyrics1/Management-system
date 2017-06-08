package xupt.se.ttms.service;

import java.util.List;


import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Schedule;
public class ScheduleSrv {
	private iScheduleDAO schDAO=DAOFactory.creatScheduleDAO();
	
	public int add(Schedule sch){
		return schDAO.insert(sch); 		
	}
	
	public int modify(Schedule sch){
		return schDAO.update(sch); 		
	}
	
	public int delete(int ID){
		
		return schDAO.delete(ID); 		
	}
	
	public List<Schedule> Fetch(String condt){
		
		return schDAO.select(condt);		
	}
	public List<Schedule> Fetchid(int condt){
		System.out.println("srv"+condt);
		return schDAO.selectid(condt);		
	}

	
	public List<Schedule> FetchAll(){
		return schDAO.select("");		
	}
}
