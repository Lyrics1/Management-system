package xupt.se.ttms.dao;


import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import xupt.se.ttms.idao.iplayDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.play;
import xupt.se.util.DBUtil;

public class playDAO implements iplayDAO {

	@Override
	/*---------------------------------------添加剧目---------------------------------------------------*/
	public int insert(play stu) {
		// TODO 自动生成的方法存根
		try {
			String sql = "insert into play(play_type_id, play_lang_id, play_name,play_introduction,"
					+ "play_image,play_length,play_ticket_price,play_status) values("
					+ stu.getplay_type_id()
					+ ", " + stu.getplay_lang_id()
					+ ", '" + stu.getname()
					+ "', '" + stu.getintroduction()
					+ "', '" + stu.getplay_image()
					+"'," + stu.getplay_length()
					+ ", " + stu.getplay_ticket_price()
					+ ", " + stu.getplay_status()
					+ " )";
//			System.out.println(stu.getname());
//			String sql="insert into play(play_name) values('"+"zqs"+"')";
			DBUtil db = new DBUtil();
			db.openConnection();
			System.out.println("test");
			ResultSet rst = db.getInsertObjectIDs(sql);
			System.out.println("test2");
			if (rst!=null && rst.first()) {
				stu.setplay_id(rst.getInt(1));
			}
			db.close(rst);
			db.close();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	
	}

	@Override
	/*---------------------------------------修改剧目---------------------------------------------------*/
	public int update(play stu) {
		int rtn=0;
		try {
			String sql = "update play set" + "play_type_id ="
					+ stu.getplay_type_id() + ", " + "play_lang_id = "
					+ stu.getplay_id() + ", " + " play_name= '"
					+ stu.getname() + "', " + " play_introduction= '"
					+ stu.getintroduction() + "', " + " play_image= '"
					+ stu.getplay_image() + "', " + " play_length= "
					+ stu.getplay_length() + ", " + " play_ticket_price= "
					+ stu.getplay_ticket_price() + ", " + " play_status= "
					+ stu.getplay_status() + " ";

			sql += " where play_id = " + stu.getplay_id();
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn =db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}
	/*---------------------------------------删除剧目---------------------------------------------------*/
	@Override
	public int delete(int ID) {
		int rtn=0;		
		try{
			String sql = "delete from play";
			sql += " where play_id = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn=db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}
	/*---------------------------------------查找剧目---------------------------------------------------*/
	@Override
	public List<play> select(String condt) {
		List<play> plaList = null;
		plaList=new LinkedList<play>();
		try {
			String sql = "select * from play";
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
					play stu=new play();
					stu.setplay_id(rst.getInt("play_id"));
					stu.setplay_type_id(rst.getInt("play_type_id"));
					stu.setplay_lang_id(rst.getInt("play_lang_id"));
					stu.setname(rst.getString("play_name"));
					stu.setintroduction(rst.getString("play_introduction"));
					stu.setplay_image(rst.getString("play_image"));
					stu.setplay_length(rst.getInt("play_length"));
					stu.setplay_ticket_price(rst.getDouble("play_ticket_price"));
					stu.setplay_status(rst.getInt("play_status"));
				
					plaList.add(stu);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return plaList;
	}
	}
	

	

