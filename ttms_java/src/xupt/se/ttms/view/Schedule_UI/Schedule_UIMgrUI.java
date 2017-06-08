package xupt.se.ttms.view.Schedule_UI;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.play;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.view.sched.ScheduleShow;
import xupt.se.ttms.view.seat.Seat_UI;
import xupt.se.ttms.view.tmpl.Main;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.util.DBUtil;

@SuppressWarnings("unused")
class SchedTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;
	

	public SchedTable(JScrollPane jp) {

		DefaultTableModel tabModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		tabModel.addColumn("演出计划id");
		tabModel.addColumn("演出厅id");
		tabModel.addColumn("剧目id");
		tabModel.addColumn("时间");
		tabModel.addColumn("票价");
		// 初始化列名
		jt = new JTable(tabModel);

		// 设置各列的宽度
		TableColumnModel columnModel = jt.getColumnModel();

		// 隐藏ID这一列
		TableColumn column = columnModel.getColumn(0);
		// column.setMinWidth(0);
		// column.setMaxWidth(0);
		// column.setWidth(0);

		// column=columnModel.getColumn(0);
		// column.setPreferredWidth(10);
		column = columnModel.getColumn(1);
		column.setPreferredWidth(10);
		column = columnModel.getColumn(2);
		column.setPreferredWidth(10);
		column = columnModel.getColumn(3);
		column.setPreferredWidth(50);
		column = columnModel.getColumn(4);
		column.setPreferredWidth(10);

		jp.add(jt);
		jp.setViewportView(jt);

	}

	@SuppressWarnings("rawtypes")
	public Schedule getSchedule() {
		int rowSel = jt.getSelectedRow();
		if (rowSel >= 0) {
			Schedule sch = new Schedule();
			sch.setSched_id(Integer.parseInt(jt.getValueAt(rowSel, 0)
					.toString()));
			sch.setStudio_id(Integer.parseInt(jt.getValueAt(rowSel, 1)
					.toString()));
			sch.setPlay_id(Integer
					.parseInt(jt.getValueAt(rowSel, 2).toString()));
			// if (jt.getValueAt(rowSel, 3) != null)
			sch.setSched_time(Date.valueOf((jt.getValueAt(rowSel, 3).toString())));
			// else
			// sch.setSched_time("2011-9-0");
			sch.setSched_ticket_price(Integer.parseInt(jt.getValueAt(rowSel, 4)
					.toString()));

			return sch;
		} else {
			return null;
		}

	}

	// 创建JTable
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void showSchedList(List<Schedule> schList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);

			Iterator<Schedule> itr = schList.iterator();
			while (itr.hasNext()) {
				System.out.println("我来了");
				Schedule sch = itr.next();
				Object data[] = new Object[5];
				data[0] = sch.getSched_id();
				data[1] = sch.getStudio_id();
				data[2] = sch.getPlay_id();
				data[3] = (sch.getSched_time().toGMTString());
				data[4] = sch.getSched_ticket_price();
				tabModel.addRow(data);
				;
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

public class Schedule_UIMgrUI extends Main {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null; // 界面提示

	// 用来放表格的滚动控件
	private JScrollPane jsc;

	// 查找的提示和输入
	private JLabel hint;
	private JTextField input;

	// 查找，编辑和删除按钮
	@SuppressWarnings("unused")
	private JButton btnAdd, btnEdit, btnDel, btnQuery;

	SchedTable tms; // 显示演出厅列表

	public Schedule_UIMgrUI() {
		
	}

	private int temp;
	public Schedule_UIMgrUI(int i) {
	
		
		this.temp=i;
		System.out.println("我来了"+this.temp);
		initContent();
	}
	
	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		System.out.println("我是woshi"+temp);

		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("选择演出计划", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 25));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 40, rect.width, rect.height - 90);
		contPan.add(jsc);

		hint = new JLabel("请输入演出计划id:", JLabel.RIGHT);
		hint.setBounds(60, rect.height - 45, 150, 30);
		contPan.add(hint);
		hint.setForeground(Color.black);

		input = new JTextField();
		input.setBounds(220, rect.height - 45, 200, 30);
		contPan.add(input);

		// 查找 ，删除和编辑的按钮，其中含有相关的事件处理！
		btnQuery = new JButton("查找");
		btnQuery.setBounds(440, rect.height - 45, 60, 30);
		btnQuery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent Event) {
				btnQueryClicked();
			}
		});
		contPan.add(btnQuery);

		btnAdd = new JButton("下一步");
		btnAdd.setBounds(rect.width - 220, rect.height - 45, 100, 30);
		btnAdd.addActionListener(new ActionListener() {
//			@Override
			@SuppressWarnings({ "rawtypes" })
			
			public void actionPerformed(ActionEvent Event) {

			
				select() ;

				Seat_UI sui = new Seat_UI();
				sui.setVisible(true);
			}
		});
		contPan.add(btnAdd);

		tms = new SchedTable(jsc);
		System.out.println("我"+this.temp);
		showTable(this.temp);
	}
	public boolean select() {
		try {
			
			String sql = "select studio_id,sched_time  from schedule where play_id ="
					+ this.temp;

			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");

			}
			// ResultSet rst = db.getInsertObjectIDs(sql);
			ResultSet rst = db.execQuery(sql);
			System.out.println(rst);

			if (rst != null) {
				System.out.println("oooo");
				while (rst.next()) {
					System.out.println("oooo");

					@SuppressWarnings("rawtypes")
					Schedule stu = new Schedule();

					stu.setSched_time(rst.getDate("sched_time"));
					stu.setStudio_id(rst.getInt("studio_id"));
					

//					System.out.println(stu.getSched_id() + input.getText());
					update(stu.getStudio_id(),stu.getSched_time());
//					

				}
			}
			

		} catch (Exception ee) {
			ee.printStackTrace();
		}

		return false;

	}
	

	@SuppressWarnings("unused")
	protected void update(int i, Date sched_time) {

		int rtn=0;
		try {
			String sql = "update end set " + " studio_id =" + i +", "+"sched_time ='"+sched_time+"'";
					

			
//			sql += " where schedule_id = " + ;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn =db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return rtn;
		
	}

	@SuppressWarnings("unused")
	private void btnAddClicked() {
		// 选座位
	
	}

	// private void btnQueryClicked() {
	// if (!input.getText().equals("")) {
	// // 请自行补充
	//
	//
	// } else {
	// JOptionPane.showMessageDialog(null, "请输入检索条件");
	// }
	// }

	private void btnQueryClicked() {
		if (judge()) {
			// 请自行补充
		} else {
			JOptionPane.showMessageDialog(null, "请输入检索条件");
		}
	}

	public boolean judge() {
		try {
			// System.out.println(input.getText());
			String sql = "select sched_id  from schedule where sched_id ='"
					+ input.getText() + "'";

			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");

			}
			// ResultSet rst = db.getInsertObjectIDs(sql);
			ResultSet rst = db.execQuery(sql);
			System.out.println(rst);

			if (rst != null) {
				System.out.println("oooo");
				while (rst.next()) {
					System.out.println("oooo");

					@SuppressWarnings("rawtypes")
					Schedule stu = new Schedule();

					stu.setSched_id(rst.getInt("sched_id"));
					// System.out.println(stu.getSched_id() );
					// stu.setName(rst.getString("studio_name"));
					// stu.setRowCount(rst.getInt("studio_row_count"));
					// stu.setColCount(rst.getInt("studio_col_count"));
					// stu.setIntroduction(rst.getString("studio_introduction"));

					System.out.println(stu.getSched_id() + input.getText());
					if (Integer.parseInt(input.getText()) == ((stu
							.getSched_id()))) {
						System.out.println("查询成功");
						System.out.println("njj" + input.getText());
						new ScheduleShow(input.getText()).setVisible(true);
						return true;
					}

				}
			}
			//

			// }
			// while(rst.next()){
			// Sign_in stu=new Sign_in();
			//
			// stu.setname(rst.getString("name"));
			// stu.setpass(rst.getString("pass"));
			//
			//
			// // stuList.add(stu);
			// }
			// }
			// System.out.println("注册成功");

		} catch (Exception ee) {
			ee.printStackTrace();
		}

		return false;

	}

	private void showTable(int id) {
//		System.out.println("我要");
		System.out.println("UI"+id);
		@SuppressWarnings("rawtypes")
		List<Schedule> schList = new ScheduleSrv().Fetchid(id);
		System.out.println("我要");
		tms.showSchedList(schList);
		usrName.setText("用户界面");
	}

	public static void main(String[] args) {
		Schedule_UIMgrUI frmStuMgr = new Schedule_UIMgrUI();
		frmStuMgr.setVisible(true);
	}
}
