package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iSeatDAO;
import xupt.se.ttms.model.Seat;
import xupt.se.util.DBUtil;

public class SeatDAO implements iSeatDAO {

	@Override
	public int insert(Seat seat) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into seat(seat_id, studio_id, seat_row, seat_column)"
					+ " values("
					+ seat.getSeat_id()
					+ ", "
					+ seat.getStudio_id()
					+ ", "
					+ seat.getSeat_row()
					+ ", "
					+ seat.getSeat_column() + " )";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst != null && rst.first()) {
				seat.setSeat_id(rst.getInt(1));
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
	public int update(Seat seat) {
		// TODO Auto-generated method stub
		int rtn = 0;
		try {
			String sql = "update seat set " + " seat_id =" + seat.getSeat_id()
					+ ", " + " studio_id = " + seat.getStudio_id() + ", "
					+ " seat_row = " + seat.getSeat_row() + ", "
					+ " seat_column = " + seat.getSeat_column() + " ";

			sql += " where seat_id = " + seat.getSeat_id();
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public int delete(int ID) {
		// TODO Auto-generated method stub
		int rtn = 0;
		try {
			String sql = "delete from  seat ";
			sql += " where seat_id = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public List<Seat> select(String condt) {
		// TODO Auto-generated method stub
		List<Seat> stuList = null;
		stuList = new LinkedList<Seat>();
		try {
			String sql = "select seat_id, studio_id, seat_row, seat_column from seat ";
			condt.trim();
			if (!condt.isEmpty())
				sql += " where " + condt;
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst != null) {
				while (rst.next()) {
					Seat seat = new Seat();
					seat.setSeat_id(rst.getInt("seat_id"));
					seat.setStudio_id(rst.getInt("studio_id"));
					seat.setSeat_row(rst.getInt("seat_row"));
					seat.setSeat_column(rst.getInt("seat_column"));
					stuList.add(seat);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return stuList;
	}

}
