package xupt.se.ttms.view.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.*;
public class success {
	static int[][] buttonArray = new int[15][15];
		public static void main(String[] args){
			success a=new  success();
			a.site();
		}
		
		public void site(){
			JFrame mazeBoard = new JFrame("��Ժ����ϵͳ--��Ϣ��ѯ");
	 		Toolkit kit = Toolkit.getDefaultToolkit();
	 		Dimension screenSize = kit.getScreenSize();
	 		int screenWidth = screenSize.width;
	 		int screenHeigth = screenSize.height;
	 		mazeBoard.setBounds(screenWidth/2-400, screenHeigth/2-300, 800, 600);
	 		mazeBoard.setResizable(false);
	 		mazeBoard.setVisible(true);
//	 		ImageIcon mazeBack = new ImageIcon("back.jpg");
//	 		JLabel mazeLabel = new JLabel(mazeBack);
//	 		mazeLabel.setBounds(0, 0, mazeBack.getIconWidth(), mazeBack.getIconHeight());
	 		JPanel mazePanel = (JPanel)mazeBoard.getContentPane();
	 		mazePanel.setOpaque(false);
	 		mazePanel.setLayout(new FlowLayout());
	 		mazeBoard.getLayeredPane().setLayout(null);
//	 		mazeBoard.getLayeredPane().add(mazeLabel,new Integer(Integer.MIN_VALUE));
	 		mazeBoard.setLayout(null);
	 		JLabel tip = new JLabel("��Ʊ��Ϣ��");
			tip.setFont(new Font("΢���ź�",Font.PLAIN,32));
			tip.setForeground(new Color(0, 110, 210));
			tip.setBounds(340, 50, 250, 40);
			mazePanel.add(tip);
			JLabel tip2 = new JLabel("��Ӱ���ƣ���Ů��Ұ��");
			tip2.setFont(new Font("΢���ź�",Font.PLAIN,16));
			tip2.setForeground(new Color(0, 110, 210));
			tip2.setBounds(340, 150, 250, 40);
			mazePanel.add(tip2);
			JLabel tip3 = new JLabel("ʱ�䣺17.50 ~ 19.20");
			tip3.setFont(new Font("΢���ź�",Font.PLAIN,16));
			tip3.setForeground(new Color(0, 110, 210));
			tip3.setBounds(340, 200, 250, 40);
			mazePanel.add(tip3);
			JLabel tip4 = new JLabel("�ص㣺��������");
			tip4.setFont(new Font("΢���ź�",Font.PLAIN,16));
			tip4.setForeground(new Color(0, 110, 210));
			tip4.setBounds(340, 250, 250, 40);
			mazePanel.add(tip4);
			JLabel tip5 = new JLabel("�۸� 38 ��");
			tip5.setFont(new Font("΢���ź�",Font.PLAIN,16));
			tip5.setForeground(new Color(0, 110, 210));
			tip5.setBounds(340, 300, 250, 40);
			mazePanel.add(tip5);
			JLabel tip6 = new JLabel("�Ѿ����ŷ����������ֻ���ע�����");
			tip6.setFont(new Font("΢���ź�",Font.PLAIN,12));
			tip6.setForeground(new Color(0, 110, 210));
			tip6.setBounds(340, 350, 250, 40);
			mazePanel.add(tip6);
			JButton b1=new JButton("ȷ��");
			b1.setBounds(340, 450, 100, 40);
			mazePanel.add(b1);
			
			
			b1.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e) {
//					wait();
					
				 }
			});
			
			
			
			
		
}
}

