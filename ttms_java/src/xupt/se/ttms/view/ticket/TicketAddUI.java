package xupt.se.ttms.view.ticket;

import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.view.tmpl.PopUITmpl;
import xupt.se.util.DBUtil;

public class TicketAddUI extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; // 取消，保存按鈕

	protected boolean rst = false; // 操作结果
	private JLabel lblname, lblsid, lbltime, lblprice, lblrow, lblcol, lblpid;
	protected JTextField txtname, txtsid, txttime, txtprice, txtrow, txtcol,
			txtpid;
	protected JTextArea txtIntro;
	protected JButton end;

	@Override
	protected void initContent() {

		setWindowName("票据信息");
		lblname = new JLabel("剧目名称:");
		lblname.setBounds(150, 30, 80, 30);
		contPan.add(lblname);
		txtname = new JTextField();
		txtname.setBounds(250, 30, 180, 30);
		contPan.add(txtname);

		lblsid = new JLabel("演出厅id:");
		lblsid.setBounds(150, 80, 80, 30);
		contPan.add(lblsid);
		txtsid = new JTextField();
		txtsid.setBounds(250, 80, 180, 30);
		contPan.add(txtsid);

		lbltime = new JLabel("观影时间:");
		lbltime.setBounds(150, 130, 80, 30);
		contPan.add(lbltime);
		txttime = new JTextField();
		txttime.setBounds(250, 130, 180, 30);
		contPan.add(txttime);

		lblprice = new JLabel("票价:");
		lblprice.setBounds(150, 180, 80, 30);
		contPan.add(lblprice);
		txtprice = new JTextField();
		txtprice.setBounds(250, 180, 180, 30);
		contPan.add(txtprice);

		lblrow = new JLabel("座位行号:");
		lblrow.setBounds(150, 230, 80, 30);
		contPan.add(lblrow);
		txtrow = new JTextField();
		txtrow.setBounds(250, 230, 180, 30);
		contPan.add(txtrow);

		lblcol = new JLabel("座位列号:");
		lblcol.setBounds(150, 280, 80, 30);
		contPan.add(lblcol);
		txtcol = new JTextField();
		txtcol.setBounds(250, 280, 180, 30);
		contPan.add(txtcol);

		lblpid = new JLabel("剧目id:");
		lblpid.setBounds(150, 330, 80, 30);
		contPan.add(lblpid);
		txtpid = new JTextField();
		txtpid.setBounds(250, 330, 180, 30);
		contPan.add(txtpid);
		
		end =new JButton ("打印票据");
		end.setBounds(270, 400, 130, 30);
		contPan.add(end);


	}

	public TicketAddUI() {
		
		try {
			String sql = "select * from end order by sale_id desc limit 1";

//			System.out.println(" try" + this.idd);
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");

			}
			ResultSet rst = db.execQuery(sql);
			System.out.println(rst.first());

			if (rst != null) {
				while (rst.first()) {
					txtname.setText(rst.getString("play_name"));
					txtsid.setText(String.valueOf(rst.getInt("studio_id")));
					txttime.setText(rst.getString("sched_time"));
					txtprice.setText(String.valueOf(rst
							.getDouble("ticket_price")));
					txtrow.setText(String.valueOf(rst.getInt("set_row")));
					txtcol.setText(String.valueOf(rst.getInt("set_col")));
					txtpid.setText(String.valueOf(rst.getInt("play_id")));
					
					
					System.out.println(rst.getInt("studio_id"));
					System.out.println(rst.getInt("sched_time"));
					System.out.println(rst.getInt("ticket_price"));
					System.out.println(rst.getInt("set_row"));
					System.out.println(rst.getInt("set_col"));
					break;
				}
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}

	}

	public static void main(String[] args) {
		TicketAddUI t = new TicketAddUI();
		t.setVisible(true);
	}
}
