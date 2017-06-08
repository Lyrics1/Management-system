package xupt.se.ttms.view.UI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.File;
import java.util.Random;
import javax.swing.*;
public class MainFrame {  
    GridBagLayout  layout=new  GridBagLayout();  
    GridBagConstraints gbc=new GridBagConstraints();  
    JMenuBar menubar1 = new JMenuBar();  
    JMenu menu1=new JMenu("Ʊ�����");  
    JMenu menu2=new JMenu("��Ա����");  
    JMenu menu5=new JMenu("��Ϣ����");  
    JMenu menu6=new JMenu("�û�����");  
    JMenuItem menu1Item1=new JMenuItem("��Ʊ");  
    JMenuItem menu1Item2=new JMenuItem("��Ʊ");  
    JMenuItem menu2Item1=new JMenuItem("��Աע��");  
    JMenuItem menu2Item2=new JMenuItem("��Ϣ����");  
    JMenuItem menu5Item1=new JMenuItem("�汾��Ϣ");  
    JMenuItem menu5Item2=new JMenuItem("ʹ�ð���");  
    JMenuItem menu6Item1=new JMenuItem("�޸�����");  
    JFrame jf=new JFrame("��ԺƱ�����ϵͳ");  
    static JPanel jp=new JPanel(new GridLayout(1,1));  
    Toolkit kit=Toolkit.getDefaultToolkit();  
    Dimension  screeSize=kit.getScreenSize();  
    static JScrollPane  js;  
    public MainFrame(){  
         menu1.add(menu1Item1);  
         menu1.add(menu1Item2);  
         menu2.add(menu2Item1);  
         menu2.add(menu2Item2);  
         menu5.add(menu5Item1);  
         menu5.add(menu5Item2);  
         menu6.add(menu6Item1);   
         menubar1.add(menu1);  
         menubar1.add(menu2);  
         menubar1.add(menu5);  
         menubar1.add(menu6);  
         jf.setJMenuBar(menubar1);  
         
         jf.add(jp);  
         jf.setSize(screeSize.width, screeSize.height);  
         jf.setVisible(true);  
         jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        
    }  
} 