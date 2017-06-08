package xupt.se.ttms.view.play;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableRow;

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.play;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.tmpl.*;
import xupt.se.util.DBUtil;
public class PlayShow extends PopUITmpl implements ActionListener {

	String imagepath="‪C:/Users/跟屁虫/Desktop/BJ.jpg";
	Image img=Toolkit.getDefaultToolkit().createImage(imagepath);
	
	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕
	private JPanel contpan;
	protected boolean rst=false; 				//操作结果
	private JLabel play_type_id,play_lang_id,play_name,play_introduction,play_image,play_length,play_ticket_price,play_status;
	String name;
	protected JTextField  txtType_id,txtLang_id,txtIntroduction, txtImage,txtLengh,txtTicket_price,txtStatus;
	protected JTextArea txtIntro;
	
	
	public PlayShow(String name) {
		super();
		this.name = name;

		try {

//			String sql="select play_type_id, play_lang_id, play_name,play_introduction, "
//					+ "play_image,play_length,play_ticket_price,play_status"
//					+"from play "
//					+ "where play_name='"+this.name;
			String sql = "select * from play where play_name='"+this.name+"'";
	       			System.out.println(" try"+this.name);
	       			DBUtil db = new DBUtil();
	    			if(!db.openConnection()){
	    				System.out.print("fail to connect database");
	    				
	    			}

	    			ResultSet rst = db.execQuery(sql);

	    			System.out.println(rst.first());

	    			if (rst!=null) {

	    				while(rst.first()){
//	    					System.out.println("mvlslskj");
	    					play stu=new play();
//	    					stu.setplay_id(rst.getInt("play_id"));
	    					stu.setplay_type_id(rst.getInt("play_type_id"));
	    					stu.setplay_lang_id(rst.getInt("play_lang_id"));
	    					stu.setname(rst.getString("play_name"));
	    					stu.setintroduction(rst.getString("play_introduction"));
	    					stu.setplay_image(rst.getString("play_image"));
	    					stu.setplay_length(rst.getInt("play_length"));
	    					stu.setplay_ticket_price(rst.getInt("play_ticket_price"));
	    					stu.setplay_status(rst.getInt("play_status"));
	    					
	    			
	    					//System.out.println(stu.getname()+stu.getplay_id()+stu.getplay_image()+stu.getplay_lang_id());
//	    					txtName = new JTextField(stu.getname());
//	    					txtName.setBounds(150, 30, 400, 30);
//	    					contPan.add(txtName);
//	    					
//	    					txtName.setText(String.valueOf(stu.getplay_id()));
//	    					txtName.setText(String.valueOf(stu.getplay_type_id()));
	    					txtType_id.setText(Integer.toString(stu.getplay_type_id()));
	    					txtLang_id.setText(Integer.toString(stu.getplay_lang_id()));
	    					txtName.setText(stu.getname());
	    					txtIntro.setText(stu.getintroduction());
	    					txtImage.setText(stu.getplay_image());
	    					txtLengh.setText(Integer.toString(((stu.getplay_length()))));
	    					txtTicket_price.setText(Double.toString(stu.getplay_ticket_price()));
	    					txtStatus.setText(Integer.toString(stu.getplay_status()));
	 
	    					return;
	    				}
	    			}
		}
	    					
	    				
	    				
	
	    			
	    			
	    		 catch (Exception ee) {
	    			ee.printStackTrace();
	    		}
	
	}
	
	
	@Override
	protected void initContent(){
		
//		contpan = new JPanel(){
//			protected void paintChildren(Graphics g){
//				g.drawImage(img, 0,0,this);
//				super.paintChildren(g);
//			}
//		};
		
		this.setTitle("添加剧目");

		play_type_id = new JLabel("play_type_id:");
		play_type_id.setBounds(50, 30, 80, 30);
		contPan.add(play_type_id);
		txtType_id = new JTextField();
		txtType_id.setBounds(130, 30, 80, 30);//x,y,weight,height
		contPan.add(txtType_id);
		
		play_lang_id = new JLabel("play_lang_id:");
		play_lang_id.setBounds(220, 30, 80, 30);
		contPan.add(play_lang_id);
		txtLang_id = new JTextField();
		txtLang_id.setBounds(300, 30, 80, 30);//x,y,weight,height
		contPan.add(txtLang_id);

		play_name = new JLabel("剧目名称:");
		play_name.setBounds(50, 80, 80, 30);
		contPan.add(play_name);
		txtName = new JTextField();
		txtName.setBounds(130, 80, 250, 30);//x,y,weight,height
		contPan.add(txtName);
		
		play_introduction = new JLabel("剧目简介:");
		play_introduction.setBounds(50, 130, 80, 30);
		contPan.add(play_introduction);
		txtIntro = new JTextArea();
		txtIntro.setBounds(130, 130, 330, 100);
		contPan.add(txtIntro);


		play_image = new JLabel("剧目海报:");
		play_image.setBounds(50, 250, 80, 30);
		contPan.add(play_image);
		txtImage = new JTextField();
		txtImage.setBounds(130, 250, 250, 30);
		contPan.add(txtImage);

		play_length = new JLabel("剧目时长:");
		play_length.setBounds(50, 300, 80, 30);
		contPan.add(play_length);
		txtLengh = new JTextField();
		txtLengh.setBounds(130, 300, 250, 30);
		contPan.add(txtLengh);
		
		play_ticket_price = new JLabel("剧目票价:");
		play_ticket_price.setBounds(50, 350, 80, 30);
		contPan.add(play_ticket_price);
		txtTicket_price = new JTextField();
		txtTicket_price.setBounds(130, 350, 80, 30);
		contPan.add(txtTicket_price);
		
		
		play_status = new JLabel("剧目状态:");
		play_status.setBounds(220, 350, 80, 30);
		contPan.add(play_status);
		txtStatus = new JTextField();
		txtStatus.setBounds(300, 350, 80, 30);
		contPan.add(txtStatus);

	}
	public boolean getReturnStatus(){
		   return rst;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst=false;
			this.dispose();
			getParent().setVisible(true);
			
		} else if (e.getSource() == btnSave) {
			btnSaveClicked();
		}
	}
	
	protected void btnSaveClicked(){
		if (txtType_id.getText() != null && txtLang_id.getText() != null
				&& txtName.getText() != null && txtIntroduction.getText()!=null 
				&& txtImage.getText() != null  && txtLengh.getText() != null
				&& txtTicket_price.getText() != null && txtStatus.getText() != null) {
			PlaySrv stuSrv = new PlaySrv();
			play stu=new play();
			//txtType_id,txtLang_id,txtName, txtIntroduction, txtImage,txtLengh,txtTicket_price,txtStatus;
			stu.setplay_type_id(Integer.parseInt(txtType_id.getText()));
			stu.setplay_lang_id(Integer.parseInt(txtLang_id.getText()));
			stu.setname(txtName.getText());
			stu.setintroduction(txtIntroduction.getText());
			stu.setplay_image(txtImage.getText());
			stu.setplay_length(Integer.parseInt(txtLengh.getText()));
			stu.setplay_ticket_price(Double.parseDouble(txtTicket_price.getText()));
			stu.setplay_status(Integer.parseInt(txtStatus.getText()));

			stuSrv.add(stu);
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
}

