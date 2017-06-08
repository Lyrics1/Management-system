package xupt.se.ttms.view.play_UI;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

//import com.sun.java.util.jar.pack.Package.File;

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.play;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.view.Schedule_UI.Schedule_UIMgrUI;
import xupt.se.ttms.view.play.PlayAddUI;
import xupt.se.ttms.view.play.PlayEditUI;
import xupt.se.ttms.view.play.PlayShow;
import xupt.se.ttms.view.studio.StudioEditUI;
import xupt.se.ttms.view.tmpl.*;
import xupt.se.util.DBUtil;

class PlayTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;
	

	public PlayTable(JScrollPane jp) {
		DefaultTableModel tabModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		tabModel.addColumn("影片id");
		tabModel.addColumn("type_id");
		tabModel.addColumn("lang_id");
		tabModel.addColumn("影片名");
		tabModel.addColumn("影片简介");
		tabModel.addColumn("封面图");
		tabModel.addColumn("播放时长");
		tabModel.addColumn("价格");
		tabModel.addColumn("状态");
		
		// 初始化列名
		jt = new JTable(tabModel);

		// 设置各列的宽度
		TableColumnModel columnModel = jt.getColumnModel();

		// 隐藏ID这一列
		TableColumn column = columnModel.getColumn(0);
//		column.setMinWidth(0);
//		column.setMaxWidth(0);
//		column.setWidth(0);
//
//		column = columnModel.getColumn(0);
//		column.setPreferredWidth(15);
		
		column = columnModel.getColumn(1);
		column.setPreferredWidth(15);
		column = columnModel.getColumn(2);
		column.setPreferredWidth(15);
		column = columnModel.getColumn(3);
		column.setPreferredWidth(15);
		column = columnModel.getColumn(4);
		column.setPreferredWidth(380);
		column = columnModel.getColumn(5);
		column.setPreferredWidth(15);
		column = columnModel.getColumn(6);
		column.setPreferredWidth(15);
		column = columnModel.getColumn(7);
		column.setPreferredWidth(15);
		column = columnModel.getColumn(8);
		column.setPreferredWidth(15);
		

		jp.add(jt);
		jp.setViewportView(jt);

	}

	public play getplay() {
		int rowSel = jt.getSelectedRow();
		if (rowSel >= 0) {
			play stud = new play();
			stud.setplay_id(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			stud.setplay_type_id(Integer.parseInt(jt.getValueAt(rowSel, 1).toString()));
			stud.setplay_lang_id(Integer.parseInt(jt.getValueAt(rowSel, 2)
					.toString()));
			
			if (jt.getValueAt(rowSel, 3) != null)
				stud.setname(jt.getValueAt(rowSel, 3).toString());
			else
				stud.setname("");
			
			if (jt.getValueAt(rowSel, 4) != null)
				stud.setintroduction(jt.getValueAt(rowSel, 4).toString());
			else
				stud.setintroduction("");
			
			if (jt.getValueAt(rowSel, 5) != null)
				stud.setplay_image(jt.getValueAt(rowSel, 5).toString());
			else
				stud.setplay_image("");
			

			stud.setplay_length(Integer.parseInt(jt.getValueAt(rowSel,6).toString()));
			
			
			stud.setplay_ticket_price(Double.parseDouble(jt.getValueAt(rowSel,7).toString()));
			
			
			stud.setplay_status(Integer.parseInt(jt.getValueAt(rowSel, 8).toString()));
			
			
			

			return stud;
			
		} else {
			return null;
		}

	}

	// 创建JTable
	public void showPlayList(List<play> stuList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);

			Iterator<play> itr = stuList.iterator();
			while (itr.hasNext()) {
				play stu = itr.next();
				Object data[] = new Object[9];
				data[0] = Integer.toString(stu.getplay_id());
				data[1] = Integer.toString(stu.getplay_type_id());
				data[2] = Integer.toString(stu.getplay_lang_id());
				data[3] = stu.getname();
				data[4] = stu.getintroduction();
				data[5] = stu.getplay_image();
				data[6] = Integer.toString(stu.getplay_length());
				data[7] = Double.toString(stu.getplay_ticket_price());
				data[8] = Integer.toString(stu.getplay_status());
				tabModel.addRow(data);
				
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



public class Play_UIMgrUI extends MainUITmpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null; // 界面提示
	// 用来放表格的滚动控件
	private JScrollPane jsc;
	// 查找的提示和输出
	private JLabel hint;
	private JTextField input;

	// 查找，编辑和删除按钮
	private JButton btnAdd, btnEdit, btnDel, btnQuery;

	PlayTable tms; // 显示剧目列表
	 private int temp;
	public Play_UIMgrUI() {
		//initContent();
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("选择剧目", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 40, rect.width, rect.height - 90);
		contPan.add(jsc);



		//保存信息

		btnAdd = new JButton("下一步");
		btnAdd.setBounds(rect.width - 220, rect.height - 45, 100, 30);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				if(choose()){
					play stud = tms.getplay();
					
					insert_end(stud.getname(),stud.getplay_ticket_price(),stud.getplay_id());
				//	System.out.println("111我是wish"+stud.getplay_id());
				Schedule_UIMgrUI ss = new Schedule_UIMgrUI(stud.getplay_id());
				ss.setVisible(true);
					}
//				btnAddClicked();
			}
//			private int delete_end()
			
			private int insert_end(String play_name,double d,int id) {
				// TODO 自动生成的方法存根
				try {
					String sql = "insert into end (play_name,ticket_price,play_id) values('"+play_name+"',"+d+","+id+")";
							
//					System.out.println(stu.getname());
//					String sql="insert into play(play_name) values('"+"zqs"+"')";
					DBUtil db = new DBUtil();
					db.openConnection();
					System.out.println("test");
					ResultSet rst = db.getInsertObjectIDs(sql);
					System.out.println("test2");
//					if (rst!=null && rst.first()) {
//						stu.setplay_id(rst.getInt(1));
//					}
					db.close(rst);
					db.close();
					return 1;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return 0;
			}
		});
		contPan.add(btnAdd);

//		btnEdit = new JButton("修改");
//		btnEdit.setBounds(rect.width - 150, rect.height - 45, 60, 30);
//		btnEdit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent Event) {
//				btnModClicked();
//			}
//		});
//		contPan.add(btnEdit);
//
//		btnDel = new JButton("删除");
//		btnDel.setBounds(rect.width - 80, rect.height - 45, 60, 30);
//		btnDel.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent Event) {
//				btnDelClicked();
//			}
//		});
//		contPan.add(btnDel);
//		contPan.add(ca1);

		tms = new PlayTable(jsc);

		showTable();
	}

	private void btnAddClicked() {

		PlayAddUI addStuUI = null;

//		addStuUI = new PlayAddUI();
//		addStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		addStuUI.setWindowName("添加剧目");
//		addStuUI.toFront();
//		addStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
//		addStuUI.setVisible(true);
//		if (addStuUI.getReturnStatus()) {
//			showTable();
//		}
	}

	private boolean choose() {
		play stud = tms.getplay();
		if (null == stud) {
			JOptionPane.showMessageDialog(null, "请选择要修改的剧目");
			return false;
		}
		return true;
	}

//		PlayEditUI modStuUI = new PlayEditUI(stud);
//		modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		modStuUI.setWindowName("修改剧目");
//		modStuUI.initData(stud);
//		modStuUI.toFront();
//		modStuUI.setModal(true);
//		modStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
//		modStuUI.setVisible(true);

//		if (modStuUI.getReturnStatus()) {
//			showTable();
//		}
//	}

//	private void btnDelClicked() {
//		play stud = tms.getplay();
//		if (null == stud) {
//			JOptionPane.showMessageDialog(null, "请选择要删除的剧目");
//			return;
//		}
//
//		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除",
//				JOptionPane.YES_NO_OPTION);
//		if (confirm == JOptionPane.YES_OPTION) {
//			PlaySrv stuSrv = new PlaySrv();
//			stuSrv.delete(stud.getplay_id());
//			showTable();
//		}
//	}


//	private void btnQueryClicked() {
////		System.out.println("8888888");
//		if ( judge()) {
//			//请自行补充
//		} else {
//			JOptionPane.showMessageDialog(null, "请输入检索条件");
//		}
//	}

	public boolean judge(){
		try {
			System.out.println("77777");
//			System.out.println(input.getText());
			//String temp=input.getText();
	    	String sql="select play_name  from play where play_name ='"+input.getText()+"'";
	    		DBUtil db = new DBUtil();
	    			if(!db.openConnection()){
	    				System.out.print("fail to connect database!");
	    				
	    			}
	    			ResultSet rst = db.execQuery(sql);
	    			System.out.println(rst);
	   
	    			if (rst!=null) {
//	    				System.out.println("999999");
	    				while(rst.next()){
	    					System.out.println("555555");
	    					play stu=new play();
	    					stu.setname(rst.getString("play_name"));
//	    					stu.setintroduction(rst.getString("play_introduction"));
//	    					stu.setplay_image(rst.getString("play_image"));
//	    					stu.setplay_length(rst.getInt("play_length"));
//	    					stu.setplay_ticket_price(rst.getDouble("play_ticket_price"));
//	    					stu.setplay_status(rst.getInt("play_status"));
	    					System.out.println(stu.getname()+input.getText());
	    					if(input.getText().equals (stu.getname())){
	    						System.out.println("查询成功");
	    						System.out.println("000000000000"+input.getText()+stu.getname());
	    						new PlayShow(input.getText()).setVisible(true);
	    						return true;
	    					}
	    					
	    				}}
		
	    			
	    		} catch (Exception ee) {
	    			ee.printStackTrace();
	    		}
	    		
		return false;
		
	}
	private void showTable() {
		usrName.setText("用户界面");
		List<play> stuList = new PlaySrv().FetchAll();
		tms.showPlayList(stuList);
		//Object[] in = {"type_id", "lang_id", "name", "Play desciption","image","length","price","status" };
	}

	public static void main(String[] args) {
		//System.out.println("test 11");
		Play_UIMgrUI frmStuMgr = new Play_UIMgrUI();
		//System.out.println("test 12");
		frmStuMgr.setVisible(true);
		//System.out.println("test 13");
	}
}
