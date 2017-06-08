package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.ipopDAO;
import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.model.pop;


public class popUIsrv {
	private ipopDAO popDAO=DAOFactory.creatpopDAO();
	
	public int add(pop pop){
		return popDAO.insert(pop); 		
	}
	public int update(pop pop){
		return popDAO.update(pop); 		
	}
	public List<pop> Fetch(String condt){
		return popDAO.select(condt);		
	}
	
	public List<pop> FetchAll(){
		return popDAO.select("");		
	}
////////////////////////////////补充函数///////////////////////
	
}
