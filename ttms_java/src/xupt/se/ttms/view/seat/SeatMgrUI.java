package xupt.se.ttms.view.seat;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.lang.model.type.TypeKind;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.util.List;
import java.util.Iterator;

import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.view.tmpl.*;
import xupt.se.util.DBUtil;

import java.applet.Applet;

class SeatTable {
	/**
	 * 
	 */
//	public class GridLayoutDemo extends Applet {  
//	    public void init() {  
//	        setLayout(new GridLayout(3, 2));  
//	        add(new Button("1"));  
//	        add(new Button("2"));  
//	        add(new Button("3"));  
//	        add(new Button("4"));  
//	        add(new Button("5"));  
//	        add(new Button("6"));  
//	    }  
//	}
	private Seat seat;
	private JTable jt=null;

	public SeatTable(JScrollPane jp) {
		
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
		
		tabModel.addColumn("seat_id");
		tabModel.addColumn("studio_id");
		tabModel.addColumn("seat_row");
		tabModel.addColumn("seat_column");
		//初始化列明
		jt=new JTable(tabModel);	
		
		//设置各列的宽度
	    TableColumnModel columnModel = jt.getColumnModel();
	    
	    //隐藏ID这一列
        TableColumn column = columnModel.getColumn(0);
//        column.setMinWidth(0);
//        column.setMaxWidth(0);
//        column.setWidth(0);
//        column.setPreferredWidth(0);

//        column = columnModel.getColumn(0);
//        column.setPreferredWidth(10);
        column = columnModel.getColumn(1);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(2);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(3);
        column.setPreferredWidth(10);
//        column = columnModel.getColumn(4);
//        column.setPreferredWidth(500);        

		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	
	public Seat getSeat() {
		int rowSel=jt.getSelectedRow();
		if(rowSel>=0){
			Seat stud = new Seat();
			stud.setSeat_id(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			stud.setStudio_id(Integer.parseInt(jt.getValueAt(rowSel, 1).toString()));
			stud.setSeat_row(Integer.parseInt(jt.getValueAt(rowSel, 2).toString())); // 0
			stud.setSeat_column(Integer.parseInt(jt.getValueAt(rowSel, 3).toString()));
//			if (jt.getValueAt(rowSel, 4) != null)
//				stud.setIntroduction(jt.getValueAt(rowSel, 4).toString());
//			else
//				stud.setIntroduction("");

			return stud;
		}
		else{ 
			return null;
		}
			
	}
	
	// 创建JTable
	public void showSeatList(List<Seat> stuList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);
			
			Iterator<Seat> itr = stuList.iterator();
			while (itr.hasNext()) {
				Seat stu = itr.next();
				Object data[] = new Object[4];
				data[0] = Integer.toString(stu.getSeat_id());
				data[1] = Integer.toString(stu.getStudio_id());
				data[2] = Integer.toString(stu.getSeat_row());
				data[3] = Integer.toString(stu.getSeat_column());
//				data[4] = stu.getIntroduction();
				tabModel.addRow(data);;
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class SeatMgrUI extends MainUITmpl {
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
	
	SeatTable tms; //显示演出厅列表


	public SeatMgrUI() {
		
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		usrName.setText("管理员");
		ca1 = new JLabel("座位管理", SwingConstants.CENTER);
		ca1.setBounds(0, 5, rect.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 40, rect.width, rect.height - 90);
		contPan.add(jsc);
		
		
		
		hint = new JLabel("请输入座位号:", SwingConstants.RIGHT);
		hint.setBounds(60, rect.height - 45, 150, 30);
		contPan.add(hint);

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

		btnAdd = new JButton("添加");
		btnAdd.setBounds(rect.width - 220, rect.height - 45, 60, 30);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent Event) {
				btnAddClicked();
			}
		});
		contPan.add(btnAdd);

		btnEdit = new JButton("修改");
		btnEdit.setBounds(rect.width - 150, rect.height - 45, 60, 30);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent Event) {
				btnModClicked();
			}
		});
		contPan.add(btnEdit);

		btnDel = new JButton("删除");
		btnDel.setBounds(rect.width - 80, rect.height - 45, 60, 30);
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent Event) {
				btnDelClicked();
			}
		});
		contPan.add(btnDel);
		contPan.add(ca1);
		
		tms = new SeatTable(jsc);
		
		showTable();
	}

	private void btnAddClicked() {

		SeatAddUI addStuUI=null;
		
		addStuUI = new SeatAddUI();
		addStuUI.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addStuUI.setWindowName("添加座位");
		addStuUI.toFront();
		addStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		addStuUI.setVisible(true);
		if (addStuUI.getReturnStatus()) {
			showTable();
		}
	}

	private void btnModClicked() {
		Seat stud = tms.getSeat();
		if(null== stud){
			JOptionPane.showMessageDialog(null, "请选择要修改的座位");
			return; 
		}
		
		SeatEditUI modStuUI = new SeatEditUI(stud);
		modStuUI.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		modStuUI.setWindowName("修改座位");
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
		Seat stud = tms.getSeat();
		if(null== stud){
			JOptionPane.showMessageDialog(null, "请选择要删除的座位");
			return; 
		}		
		
		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			SeatSrv stuSrv = new SeatSrv();
			stuSrv.delete(stud.getSeat_id());
			showTable();
		}
	}
	

	private void btnQueryClicked() {
		if ( judge()) {
			//请自行补充
			

		} else {
			JOptionPane.showMessageDialog(null, "请输入检索条件");
		}
	}
	
	public boolean judge(){
		try {
	    	String sql="select seat_id  from seat where seat_id ="+input.getText();
	    			
	       			
	       			DBUtil db = new DBUtil();
	    			if(!db.openConnection()){
	    				System.out.print("fail to connect database");
	    				
	    			}
//	    			ResultSet rst = db.getInsertObjectIDs(sql);
	    			ResultSet rst = db.execQuery(sql);
	    			System.out.println(rst);
	    			
	    		
	    			if (rst!=null) {
	    				while(rst.next()){
	    			
	    					Seat stu=new Seat();
//	    					stu.setID(rst.getInt("studio_id "));
	    					stu.setSeat_id(rst.getInt("seat_id"));
//	    					stu.setRowCount(rst.getInt("studio_row_count"));
//	    					stu.setColCount(rst.getInt("studio_col_count"));
//	    					stu.setIntroduction(rst.getString("studio_introduction"));
	    				
	    				
	    					System.out.println(stu.getSeat_id()+input.getText());
	    					if(Integer.parseInt(input.getText()) == (stu.getSeat_id())){
	    						System.out.println("查询成功");
//	    						System.out.println("njj"+input.getText());
	    						new SeatShow(input.getText()).setVisible(true);
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
		List<Seat> stuList = new SeatSrv().FetchAll();
		tms.showSeatList(stuList);
	}
	

	public static void main(String[] args) {
		SeatMgrUI frmStuMgr = new SeatMgrUI();
		frmStuMgr.setVisible(true);
	}
}
