package xupt.se.ttms.view.UI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
public class sign  extends JFrame{
	
	JTextField f1;
    JTextField f2;
    JButton b1;
    JButton b2;
    String power;//��ʾȨ��  
    JPanel p5;
//----------------------------ͼƬ·��------------------------------------
    String imagePath = "sign.jpg";
    Image img = Toolkit.getDefaultToolkit().createImage(imagePath);
    
//----------------------------ͼƬ·��------------------------------------	
    
	public sign(){
		Container c = getContentPane();
		/*setLayout(new GridLayout(20, 2));
		add(new JLabel("�û�����"));
		add(new JTextField(15));
		add(new JLabel("����"));
		add(new JTextField(15));*/
		Label l1=new Label("�û���");
	    Label l2=new Label("���룺");
	     f1=new JTextField(15);
	     f2=new JPasswordField(15);
		 b1=new JButton("��¼");
		 b2=new JButton("����");
		// JPanel p1=new JPanel();	
		 JPanel p2=new JPanel();
		 JPanel p3=new JPanel();
		 JPanel p4=new JPanel();
		 p5=new JPanel(){
           protected  void  paintChildren(Graphics g) {
                g.drawImage(img,0,0,this);
                super.paintChildren(g);
               }
           };
           JPanel p6 = new JPanel();
        p2.add(l1);
        p2.add(f1);
     
        p2.setBorder(new MatteBorder(5, 5, 1, 1, Color.WHITE));
        p2.setBackground(Color.WHITE);
        p3.add(l2);
        p3.add(f2);
        p3.setBackground(Color.WHITE);
        p3.setBorder(new MatteBorder(5, 5, 1, 1, Color.WHITE));
        p4.add(b1);
        p4.add(b2);
        p4.setBorder(new MatteBorder(5, 5, 1, 1, Color.WHITE));
        p4.setBackground(Color.WHITE);
        p5.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
        p5.add(p2);
        p5.add(p3);
        p5.add(p4);
        p5.setBorder(new MatteBorder(150,400,1,1,Color.WHITE));
        c.add(p5,BorderLayout.CENTER);
        
        
        
        b1.addActionListener(new Enter());
        b2.addActionListener(new ReWrite());
        
        
	}
	
	
	//----------------------�ж�Ȩ��-------------------------------
class Enter implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{   if((f1.getText()).equals("admin")&&(f2.getText()).equals("123"))
            {b1.addActionListener(new ActionListener(){
				       	 public void actionPerformed(ActionEvent e) {
				       		 System.out.println("jhkjdhvjkv");
				       		 new box();
				       	 }
				        });
//				JOptionPane.showMessageDialog(null, "��¼�ɹ����û�Ȩ����adimistrator");
					power="adminstrator";
					
					
            }
           else if((f1.getText()).equals("����")&&(f2.getText()).equals("123456"))
            {
            		JOptionPane.showMessageDialog(null, "��¼�ɹ�!��¼�ɹ����û�Ȩ����user");
            			power="user";
            }
          else JOptionPane.showMessageDialog(null, "��¼ʧ�ܣ������µ�¼��");}
}



class ReWrite implements ActionListener{
 public void actionPerformed(ActionEvent e)
 {	 f1.setText("");
  	 f2.setText("");
 }
}
	

	
	
	
	                     
	public static void main(String[] args)
	{
		sign  frame = new sign();
		frame.setTitle("��ԺƱ�����ϵͳ-sign����");
		//JFrame frame = new JFrame("��ԺƱ�����ϵͳ-sign����");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700,500);//��������ش�С
		frame.setVisible(true);
	}
}
