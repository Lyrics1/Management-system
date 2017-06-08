package xupt.se.ttms.view.play;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.play;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.tmpl.*;
public class PlayModUI extends PopUITmpl implements ActionListener {

	String imagepath="‪C:/Users/跟屁虫/Desktop/BJ.jpg";
	Image img=Toolkit.getDefaultToolkit().createImage(imagepath);
	
	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕
	private JPanel contpan;
	protected boolean rst=false; 				//操作结果
	private JLabel play_type_id,play_lang_id,play_name,play_introduction,play_image,
				   play_length,play_ticket_price,play_status;
	protected JTextField txtType_id,txtLang_id,txtName, txtIntroduction, txtImage,
	     	   		 	 txtLengh,txtTicket_price,txtStatus;
	protected JTextArea txtIntro;
	
	
	@Override
	protected void initContent(){
		
		contpan = new JPanel(){
			protected void paintChildren(Graphics g){
				g.drawImage(img, 0,0,this);
				super.paintChildren(g);
			}
		};
		
		this.setTitle("添加剧目");

		play_name = new JLabel("play_type_id:");
		play_name.setBounds(50, 30, 80, 30);
		contPan.add(play_name);
		txtType_id = new JTextField();
		txtType_id.setBounds(130, 30, 80, 30);//x,y,weight,height
		contPan.add(txtType_id);
		
		play_name = new JLabel("play_lang_id:");
		play_name.setBounds(220, 30, 80, 30);
		contPan.add(play_name);
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
		
//		play_ticket_price = new JLabel("剧目票价:");
//		play_ticket_price.setBounds(50, 400, 80, 30);
//		contPan.add(play_ticket_price);
//		txtTicket_price = new JTextField();
//		txtTicket_price.setBounds(130, 400, 250, 30);
//		contPan.add(txtTicket_price);
		
		
		btnSave = new JButton("保存");
		btnSave.addActionListener(this);
		btnSave.setBounds(150, 400, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(230, 400, 60, 30);
		contPan.add(btnCancel);
/*		ImageJPanel imageJP = new ImageJPanel(new ImageIcon(
				"files/imps/pencil.jpg").getImage());
		imageJP.setBounds(360, 160, 100, 100);
		imageJP.setLayout(null);
		this.add(imageJP);
		*/
	}
	
	
	public boolean getReturnStatus(){
		   return rst;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst=false;
			this.setVisible(false);
		} else if (e.getSource() == btnSave) {
			btnSaveClicked();
		}
	}
	
	protected void btnSaveClicked(){
		if (txtName.getText() != null && txtImage.getText() != null
				&& txtLengh.getText() != null) {
			PlaySrv stuSrv = new PlaySrv();
			play stu=new play();
			stu.setname(txtName.getText());
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

