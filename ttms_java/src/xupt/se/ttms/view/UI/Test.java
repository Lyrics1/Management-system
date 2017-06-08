package xupt.se.ttms.view.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

/** 
 * JTable������ 
 */
public class Test extends JFrame {
	
    private JFrame frame = null;

    private JTable table = null;

    private Table_Model model = null;

    private JScrollPane s_pan = null;
//    public void choose(){
//    	
//    	JButton one=new JButton("������ӳ");
//    	JPanel panel=new JPanel();
//    	panel.add(one);
//    	panel.setLayout(null);
//    	one.setBounds(0,100,100,30);
//    }
    public void Test() {

    	
    	
        frame = new JFrame("��Ժ����ϵͳ--ѡƱ����");
        
        ImageIcon backgroundimg = new ImageIcon("6.jpg");

     
        model = new Table_Model(40);
        table = new JTable(model);
        table.setRowHeight(40);
        String[] age = { "9��00-12:00", "9��00-12:00", "9��00-12:00", "9��00-12:00", "9��00-12:00", "9��00-12:00", "9��00-12:00", "9��00-12:00", "29", "30" };
        JComboBox com = new JComboBox(age);
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(2).setCellEditor(new DefaultCellEditor(com)); // ����ĳ�в���JComboBox��� 


        model.addRow("������˹��", "20", "7��00-9��00",false);
        model.addRow("����������Ӱ��", "30", "7.00-9.00",false);
        model.addRow("�й�����ӰԺ", "40", "7.00-9.00",false);
        model.addRow("������˹��", "20", "7.00-9.00",false);
        model.addRow("����������Ӱ��", "7.00-9.00", "7.00-9.00",false);
        model.addRow("�й�����ӰԺ", "40", "7.00-9.00",false);
        model.addRow("������˹��", "20", "7.00-9.00",false);

        
        s_pan = new JScrollPane(table);
        s_pan.setBounds(0, 40, 700, 500);;

        frame.getContentPane().add(s_pan, BorderLayout.CENTER);
        
        frame.setSize(700, 500);
        frame.setVisible(true);

        //model.addRow(2); // ��ĳ������һ���� 

        //table.updateUI(); // ˢ�� 
        
//        JPanel contentPane=new JPanel( );
//        contentPane.setBounds(7);
//        frame.setContentPane(contentPane);
       
        JButton b1=new JButton("ȷ��");
        JPanel pel=new JPanel();
//        b1.setContentAreaFilled(true);
        b1.setBounds(500,0,100,40);
        //s_pan.add(b1);
        pel.add(b1);
        frame.getContentPane().add(pel, BorderLayout.SOUTH);
     
        b1.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e) {
				 
				 new site().site();
			 }
		});}
    

 

}

class Table_Model extends AbstractTableModel {
    private static final long serialVersionUID = -3094977414157589758L;

    private Vector content = null;

    private String[] title_name = { "��Ժ����", "�۸�", "����","ѡ��" };

    public Table_Model() {
        content = new Vector();
    }

    public Table_Model(int count) {
        content = new Vector(count);
    }

    /** 
     * ����һ���� 
     * @param row �к� 
     */
    public void addRow(int row) {
        Vector v = new Vector(4);
        v.add(0, null);
        v.add(1, null);
        v.add(2, null);
        v.add(3,null);
        content.add(row, v);
    }

    /** 
     * ����һ������ 
     */
    public void addRow(String name, String  t, String age,boolean f) {
        Vector v = new Vector(4);
        v.add(0, name);
        v.add(1,t ); // JCheckBox��Boolean��Ĭ����ʾ������������Ϊ�˿�Ч������ʵ��JComboBox��ʾ***������ 

        v.add(2, age); // ������ǰ���Ѿ����ó���JComboBox����������������ʲô�ַ�����û��ϵ 
        v.add(3,f);
        content.add(v);
    }

    public void removeRow(int row) {
        content.remove(row);
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        
        return true;
    }

    public void setValueAt(Object value, int row, int col) {
        ((Vector) content.get(row)).remove(col);
        ((Vector) content.get(row)).add(col, value);
        this.fireTableCellUpdated(row, col);
    }

    public String getColumnName(int col) {
        return title_name[col];
    }

    public int getColumnCount() {
        return title_name.length;
    }

    public int getRowCount() {
        return content.size();
    }

    public Object getValueAt(int row, int col) {
        return ((Vector) content.get(row)).get(col);
    }

    public Class getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }

	public void addRow(String string, String string2, String string3) {
		// TODO �Զ����ɵķ������
		
	}

}

 