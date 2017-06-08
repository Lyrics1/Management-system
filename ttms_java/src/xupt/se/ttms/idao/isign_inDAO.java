package xupt.se.ttms.idao;


//import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Sign_in;

import java.util.List;

/**
* @author Administrator
*
*/
public interface isign_inDAO {
	public int insert(Sign_in pla);
	public int update(Sign_in pla);
	public int delete(String name);
	public List<Sign_in> select(String condt);
	
}

