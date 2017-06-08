package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;

public interface iScheduleDAO {
	public int insert(Schedule sch);
	public int update(Schedule sch);
	public int delete(int ID);
	public List<Schedule> select(String condt);
	public List<Schedule> selectid(int condt); 
}
