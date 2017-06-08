package xupt.se.ttms.dao;

import xupt.se.ttms.idao.iScheduleDAO;


import xupt.se.ttms.model.Schedule;
import xupt.se.util.DBUtil;

import java.util.LinkedList;


import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ScheduleDAO implements iScheduleDAO{

	public int insert(Schedule sch) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into schedule(sched_id, studio_id, play_id,"
					+ " sched_time,sched_ticket_price )"
					+ " values("
					+ sch.getSched_id()
					+ ","
					+ sch.getStudio_id()
					+ "," + sch.getPlay_id() 
					+ ", '" + sch.getSched_time()
					+ "', " + sch.getSched_ticket_price()
					+ ")";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst!=null && rst.first()) {
				sch.setSched_id(rst.getInt(1));
			}
			db.close(rst);
			db.close();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@SuppressWarnings("rawtypes")
	public int update(Schedule sch) {
		// TODO Auto-generated method stub
		int rtn=0;
		try {
			String sql = "update schedule set " + " sched_id ="
					+ sch.getSched_id() + ", " + " studio_id = "
					+ sch.getStudio_id() + ", " + " play_id = "
					+ sch.getPlay_id() + ", " + " sched_time = '"
					+ sch.getSched_time() + "'," + " sched_ticket_price = "
					+ sch.getSched_ticket_price() + " ";

			
			sql += " where schedule_id = " + sch.getSched_id();
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn =db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	public int delete(int ID) {
		// TODO Auto-generated method stub
		int rtn=0;		
		try{
			String sql = "delete from  schedule ";
			sql += " where schedule_id = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn=db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;	
	}

	@SuppressWarnings("rawtypes")
	public List<Schedule> select(String condt) {
		// TODO Auto-generated method stub
		List<Schedule> stuList = null;
		stuList=new LinkedList<Schedule>();
		try {
			String sql = "select sched_id, studio_id, play_id,sched_time,sched_ticket_price from schedule ";
			condt.trim();
			if(!condt.isEmpty())
				sql+= " where " + condt;
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					Schedule sch=new Schedule();
					sch.setSched_id(rst.getInt("sched_id"));
					sch.setStudio_id(rst.getInt("studio_id"));
					sch.setPlay_id(rst.getInt("play_id"));
					sch.setSched_time(rst.getDate("sched_time"));
					sch.setSched_ticket_price(rst.getDouble("sched_ticket_price"));
					stuList.add(sch);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return stuList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Schedule> selectid(int condt) {
		// TODO 自动生成的方法存根
		System.out.println("ooooo"+condt);
		List<Schedule> stuList = null;
		stuList=new LinkedList<Schedule>();
		try {
			String sql = "select * from schedule where play_id="+condt;
			System.out.println(sql);
			
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if(rst!=null) {
//				System.out.print("fail "+rst.first()+rst.next()+rst);
				while(rst.next()){
					System.out.println(1111111111);
//					System.out.print(rst.getInt("sched_id"));
					Schedule sch=new Schedule();
					sch.setSched_id(rst.getInt("sched_id"));
					sch.setStudio_id(rst.getInt("studio_id"));
					sch.setPlay_id(rst.getInt("play_id"));
					sch.setSched_time(rst.getDate("sched_time"));
					sch.setSched_ticket_price(rst.getDouble("sched_ticket_price"));
					System.out.println(sch.getPlay_id());
					stuList.add(sch);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return stuList;
	}
	
	

}
