package xupt.se.ttms.idao;
import xupt.se.ttms.model.Seat;

import java.util.List;

public interface iSeatDAO {
	public int insert(Seat seat);
	public int update(Seat seat);
	public int delete(int ID);
	public List<Seat> select(String condt); 
}
