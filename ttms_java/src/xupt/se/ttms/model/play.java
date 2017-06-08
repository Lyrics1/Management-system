package xupt.se.ttms.model;

//import javax.xml.crypto.Data;

public class play {
	
	private int play_id;
	private int play_type_id;
	private int play_lang_id;
	private String play_name;
	private  String play_introduction;
	private String play_image;
	private int play_length;
	private double play_ticket_price;
	private int play_status;
	
	public void setplay_id(int play_id){
		this.play_id = play_id;
		
	}
	public int getplay_id(){
		return play_id;
	}
	
	public void setplay_type_id(int play_type_id){
		this.play_type_id = play_type_id;
		
	}

	public int getplay_type_id(){
		return play_type_id;
	}
	
	
	public void setplay_lang_id(int pay_lang_id){
		this.play_lang_id = pay_lang_id;
	}
	
	public int getplay_lang_id(){
		return play_lang_id;
	}
	
	public void setname(String play_name){
		this.play_name=play_name;
	}
	
	public String getname(){
		return play_name;
	}
	
	public void setintroduction(String play_introduction){
		this.play_introduction= play_introduction;
	}
	
	public String getintroduction(){
		return play_introduction;
	}
	
	public void setplay_image(String play_image){
		this.play_image=play_image;
	}
	
	public String getplay_image(){
		return play_image;
	}
	
	public void setplay_length(int s){
		this.play_length = s;
	}
	
	public int getplay_length (){
		return play_length;
	}
	
	public void setplay_ticket_price(double d){
		this.play_ticket_price = d;
	}
	public double getplay_ticket_price(){
		return play_ticket_price;
	}
	
	public void setplay_status(int play_status){
		this.play_status= play_status;
	}
	
	public int getplay_status(){
		return play_status;
	}
	
	
}

