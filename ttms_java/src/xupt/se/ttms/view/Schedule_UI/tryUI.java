package xupt.se.ttms.view.Schedule_UI;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.sched.SchedAddUI;
import xupt.se.ttms.view.sched.SchedEditUI;
import xupt.se.ttms.view.sched.ScheduleShow;
import xupt.se.ttms.view.studio.StudioAddUI;
import xupt.se.ttms.view.studio.StudioEditUI;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.util.DBUtil;

class SchedTabl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;

	public SchedTabl(JScrollPane jp) {

		DefaultTableModel tabModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		tabModel.addColumn("sched_id");
		tabModel.addColumn("studio_id");
		tabModel.addColumn("play_id");
		tabModel.addColumn("time");
		tabModel.addColumn("ticket_price");
		// 初始化列名
		jt = new JTable(tabModel);

		// 设置各列的宽度
		TableColumnModel columnModel = jt.getColumnModel();

		// 隐藏ID这一列
		TableColumn column = columnModel.getColumn(0);
//		column.setMinWidth(0);
//		column.setMaxWidth(0);
//		column.setWidth(0);

//		column=columnModel.getColumn(0);
//		column.setPreferredWidth(10);
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

	public Schedule getSchedule() {
		int rowSel = jt.getSelectedRow();
		if (rowSel >= 0) {
			Schedule sch = new Schedule();
			sch.setSched_id(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			sch.setStudio_id(Integer.parseInt(jt.getValueAt(rowSel, 1).toString()));
			sch.setPlay_id(Integer.parseInt(jt.getValueAt(rowSel, 2).toString()));
//			if (jt.getValueAt(rowSel, 3) != null)
				sch.setSched_time(Date.valueOf((jt.getValueAt(rowSel, 3).toString())));
//			else
//				sch.setSched_time("2011-9-0");
			sch.setSched_ticket_price(Integer.parseInt(jt.getValueAt(rowSel, 4)
					.toString()));

			return sch;
		} else {
			return null;
		}

	}

	// 创建JTable
	public void showSchedList(List<Schedule> schList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);

			Iterator<Schedule> itr = schList.iterator();
			while (itr.hasNext()) {
				Schedule sch= itr.next();
				Object data[] = new Object[5];
				data[0] = sch.getSched_id();
				data[1] = sch.getStudio_id();
				data[2] = sch.getPlay_id();
				data[3] =(sch.getSched_time().toGMTString());
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

public class tryUI extends MainUITmpl {
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
	private JButton btnAdd, btnEdit, btnDel, btnQuery;

	SchedTable tms; // 显示演出厅列表

	public tryUI() {

	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
	
		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("演出计划管理", JLabel.CENTER);
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
		btnQuery.setBounds(440, rect.height -  45, 60, 30);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnQueryClicked();
			}
		});
		contPan.add(btnQuery);

		btnAdd = new JButton("添加");
		btnAdd.setBounds(rect.width - 220, rect.height - 45, 60, 30);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnAddClicked();
			}
		});
		contPan.add(btnAdd);

		btnEdit = new JButton("修改");
		btnEdit.setBounds(rect.width - 150, rect.height - 45, 60, 30);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnModClicked();
			}
		});
		contPan.add(btnEdit);

		btnDel = new JButton("删除");
		btnDel.setBounds(rect.width - 80, rect.height - 45, 60, 30);
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnDelClicked();
			}
		});
		contPan.add(btnDel);
		contPan.add(ca1);

		tms = new SchedTable(jsc);

		showTable();
	}

	private void btnAddClicked() {

		SchedAddUI addStuUI = null;

		addStuUI = new SchedAddUI();
		addStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addStuUI.setWindowName("添加演出计划");
		addStuUI.toFront();
		addStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		addStuUI.setVisible(true);
		if (addStuUI.getReturnStatus()) {
			showTable();
		}
	}
	
	private void btnModClicked() {
		Schedule stud = tms.getSchedule();
		if(null== stud){
			JOptionPane.showMessageDialog(null, "请选择要修改的计划");
			return; 
		}
		
		SchedEditUI modStuUI = new SchedEditUI(stud);
		modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modStuUI.setWindowName("修改演出厅");
		modStuUI.initData(stud);
		modStuUI.toFront();
		modStuUI.setModal(true);
		modStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		modStuUI.setVisible(true);

		if (modStuUI.getReturnStatus()) {
			showTable();
		}	
	}
	
	
	private void btnDelClicked() {
		Schedule stud = tms.getSchedule();
		if(null== stud){
			JOptionPane.showMessageDialog(null, "请选择要删除的演出计划");
			return; 
		}		
		
		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			ScheduleSrv stuSrv = new ScheduleSrv();
			stuSrv.delete(stud.getSched_id());
			showTable();
		}
	}


//	private void btnQueryClicked() {
//		if (!input.getText().equals("")) {
//			// 请自行补充
//			
//
//		} else {
//			JOptionPane.showMessageDialog(null, "请输入检索条件");
//		}
//	}

	private void btnQueryClicked() {
		if ( judge()) {
			//请自行补充
		} else {
			JOptionPane.showMessageDialog(null, "请输入检索条件");
		}
	}

	public boolean judge(){
		try {
//			System.out.println(input.getText());
	    	String sql="select sched_id  from schedule where sched_id ='"
		+ input.getText() + "'";
	    			
	       			DBUtil db = new DBUtil();
	    			if(!db.openConnection()){
	    				System.out.print("fail to connect database");
	    				
	    			}
//	    			ResultSet rst = db.getInsertObjectIDs(sql);
	    			ResultSet rst = db.execQuery(sql);
	    			System.out.println(rst);
	    			
	    		
	    			if (rst!=null) {
	    				System.out.println("oooo");
	    				while(rst.next()){
	    					System.out.println("oooo");
	    					
	    					Schedule stu=new Schedule();
	    					
	    					stu.setSched_id(rst.getInt("sched_id"));
//	    					System.out.println(stu.getSched_id() );
//	    					stu.setName(rst.getString("studio_name"));
//	    					stu.setRowCount(rst.getInt("studio_row_count"));
//	    					stu.setColCount(rst.getInt("studio_col_count"));
//	    					stu.setIntroduction(rst.getString("studio_introduction"));
	    				
	    				
	    					System.out.println(stu.getSched_id() +input.getText());
	    					if(Integer.parseInt(input.getText())==((stu.getSched_id()) )){
	    						System.out.println("查询成功");
	    						System.out.println("njj"+input.getText());
	    						new ScheduleShow(input.getText()).setVisible(true);
	    						return true;
	    					}
	    					
	    				}}
//	    					
	    	
	    	
//	    			}
//	    				while(rst.next()){
//	    					Sign_in stu=new Sign_in();
//	    					
//	    					stu.setname(rst.getString("name"));
//	    					stu.setpass(rst.getString("pass"));
//	    					
//	    				
////	    					stuList.add(stu);
//	    				}
//	    			}
//	    			System.out.println("注册成功");
	    			
	    			
	    			
	    		} catch (Exception ee) {
	    			ee.printStackTrace();
	    		}
	    		
		return false;
		
	}
	
	private void showTable() {
		List<Schedule> schList = new ScheduleSrv().FetchAll();
		tms.showSchedList(schList);
	}
	public static void main(String[] args) {
		tryUI frmStuMgr = new tryUI();
		frmStuMgr.setVisible(true);
	}
}
