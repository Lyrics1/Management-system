package xupt.se.ttms.idao;


//import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.play;

import java.util.List;

/**
* @author Administrator
*
*/
public interface iplayDAO {
	public int insert(play pla);
	public int update(play pla);
	public int delete(int ID);
	public List<play> select(String condt);
	
}

