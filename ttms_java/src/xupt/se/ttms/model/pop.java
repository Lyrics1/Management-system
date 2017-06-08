package xupt.se.ttms.model;

public class pop {
	private String t1="";
	private String t2="";
	private String t3="";
	private String t4="";


//	private static LoginedUser uniInstance = null;
//	
//	private LoginedUser(){		
//	}
//	
//	public synchronized static LoginedUser getInstance() {
//		if (uniInstance == null) {
//			uniInstance = new LoginedUser();
//		}
//		return uniInstance;
//	}
	
	public void sett1(String t11){
		this.t1=t11;
	}
	
	public void sett2(String t12)
	{
		this.t2=t12;
	}

	public void sett3(String t13)
	{
		this.t3=t13;
	}

	public void sett4(String t14)
	{
		this.t4=t14;
	}


	public String gett1(){
		return t1;
	}
	public String gett2(){
		return t2;
	}
	public String gett3(){
		return t3;
	}
	public String gett4(){
		return t4;
	}
}