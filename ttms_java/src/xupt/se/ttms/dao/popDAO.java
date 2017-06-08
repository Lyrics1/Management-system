package xupt.se.ttms.dao;
import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import xupt.se.ttms.idao.ipopDAO;
import xupt.se.ttms.model.pop;
import xupt.se.ttms.view.tmpl.PopUITmpl;
import xupt.se.util.DBUtil;

public class popDAO implements ipopDAO{
@Override
	public int insert(pop pop)
	{
		try{		
			String sql = "insert into sign(name,pass)"+
		"values('"+pop.gett1()+"','"
		+pop.gett3()+ "' )";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst!=null && rst.first()) {
				pop.sett1(rst.getString(1));
			}
			db.close(rst);
			db.close();
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	} 
	
	
	public int update(pop pop)
	{
		int rtn=0;
		//System.out.println("123");
		try
		{
			String sql="update sign set pass='"+pop.gett3() +"' where name ='"+ pop.gett1()+"'"; 
			//String sql="update sign set"+"pass='"+pop.gett3()+ "' ";
		//	System.out.println(pop.gett3());
			
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn =db.execCommand(sql);
			db.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}
	
	///////////////////////////////
	public List<pop> select(String c) {
		List<pop> popList = null;
		popList=new LinkedList<pop>();
		try {
			String sql = "name,pass";
			c.trim();
			if(!c.isEmpty())
				sql+= " where " + c;
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					pop pop=new pop();
					pop.sett1(rst.getString("name"));
					pop.sett3(rst.getString("pass"));				
					popList.add(pop);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return popList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

