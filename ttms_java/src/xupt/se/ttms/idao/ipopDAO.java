package xupt.se.ttms.idao;

import java.util.List;
import xupt.se.ttms.dao.popDAO;
import xupt.se.ttms.model.pop;

public interface ipopDAO {
	public int insert(pop pop);
	public int update(pop pop);
	public List<pop> select(String c);
}
