package xupt.se.ttms.dao;


import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import xupt.se.ttms.idao.isign_inDAO;

import xupt.se.ttms.model.Sign_in;
import xupt.se.util.DBUtil;

public class sign_inDAO implements isign_inDAO {

	@Override
	public int insert(Sign_in stu) {
		// TODO 自动生成的方法存根
		try {
			String sql="insert into sign(name,pass) values('" + stu.getname() +"','"+stu.getpass()+"');";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			
			if (rst!=null && rst.first()) {
				stu.setname(rst.getString("root"));
			}
			db.close(rst);
			db.close();
			System.out.println("注册成功");
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	
	}

	@Override
	public int update(Sign_in stu) {
		int rtn=0;
		try {
			String sql = "update sign set " + " name='"
					+ stu.getname() + "', " + "pass = "
					+ stu.getpass()+ "' ";

			sql += " where name = " + stu.getname();
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn =db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public int delete(String name) {
		int rtn=0;		
		try{
			String sql = "delete from sign";
			sql += " where name = " + name;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn=db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;	
	}

	@Override
	public List<Sign_in> select(String condt) {
		List<Sign_in> stuList = null;
		stuList=new LinkedList<Sign_in>();
		try {
			String sql = "select name,pass from sign";
			condt.trim();
			if(!condt.isEmpty())
				sql+= " where " + condt;
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			System.out.println(rst);
			if (rst!=null) {
				while(rst.next()){
					Sign_in stu=new Sign_in();
					
					stu.setname(rst.getString("name"));
					stu.setpass(rst.getString("pass"));
					
				
					stuList.add(stu);
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
	

	

