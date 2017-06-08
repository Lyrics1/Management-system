package xupt.se.ttms.view.play;


import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

import xupt.se.ttms.dao.playDAO;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.play;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.play.PlayAddUI;

public class PlayEditUI extends PlayAddUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private static final JTextComponent txtImage = null;
	private play stud;

	public PlayEditUI(play stu){
		initData(stu);
	}
	
	public void initData(play stu) {
		if(null== stu){
			return;
		}
		txtType_id.setText(Integer.toString(stu.getplay_type_id()));
		txtLang_id.setText(Integer.toString(stu.getplay_lang_id()));
		txtName.setText(stu.getname());
		txtIntro.setText(stu.getintroduction());
		txtImage.setText(stu.getplay_image());
		txtLengh.setText(Integer.toBinaryString(((stu.getplay_length()))));
		txtTicket_price.setText(Double.toHexString(stu.getplay_ticket_price()));
		txtStatus.setText(Integer.toBinaryString(stu.getplay_status()));	
		stud=stu;
		this.invalidate();
	}

	@Override
	protected void btnSaveClicked(){
		//txtType_id,txtLang_id,txtName, txtIntroduction, txtImage,txtLengh,txtTicket_price,txtStatus;
		if (txtType_id.getText() != null && txtLang_id.getText() != null
				&& txtName.getText() != null && txtIntro.getText()!=null 
				&& txtImage.getText() != null  && txtLengh.getText() != null
				&& txtTicket_price.getText() != null && txtStatus.getText() != null) {
			PlaySrv plaSrv = new PlaySrv();
			play stu= stud;
			stu.setplay_type_id(Integer.parseInt(txtType_id.getText()));
			stu.setplay_lang_id(Integer.parseInt(txtLang_id.getText()));
			stu.setname(txtName.getText());
			stu.setintroduction(txtIntro.getText());
			stu.setplay_image(txtImage.getText());
			stu.setplay_length(Integer.parseInt(txtLengh.getText()));
			stu.setplay_ticket_price(Double.parseDouble((txtTicket_price.getText())));	
			stu.setplay_status(Integer.parseInt((txtStatus.getText())));
			PlaySrv.add(stu);
			this.setVisible(false);
			rst=true;
			
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
	
}
