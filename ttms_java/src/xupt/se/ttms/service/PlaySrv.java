package xupt.se.ttms.service;

import java.util.ArrayList;
import java.util.List;


import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iplayDAO;
import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.play;
import xupt.se.ttms.model.play;
import xupt.se.ttms.dao.playDAO;

public class PlaySrv {
	private static playDAO playDAO=DAOFactory.creatPlayDAO();
	private iScheduleDAO schDAO=DAOFactory.creatScheduleDAO();
	
	public static int add(play play){
		//System.out.println("test1");
		return playDAO.insert(play); 
	}
	
	public int modify(play play){
		return playDAO.update(play); 		
	}
	
	public int delete(int i){
		return playDAO.delete(i); 		
	}
	
	public List<play> Fetch(String condt){
		return playDAO.select(condt);		
	}
	
	
	public List<play> FetchAll(){
		return playDAO.select("");		
	}

	public List<play> selectScheduledPlay(String condt){
		List<play> list = new ArrayList<play>();
		play play1 = new play();
		play1.setname("test1");
		play play2 = new play();
		play2.setname("test1");
		list.add(play1);
		list.add(play2);
		return list;
	}
}
