package xupt.se.ttms.view.sched;


import java.awt.*;

import javax.swing.*;

public class ImageIPanel extends JPanel {
	private Image image;
	
	//----------------------------图片路径------------------------------------
    String imagePath = "C:/Users/wo/Desktop/3.jpg";
    Image img = Toolkit.getDefaultToolkit().createImage(imagePath);
    
    //----------------------------图片路径------------------------------------	
    public JPanel contPan = new JPanel(){
        protected  void  paintChildren(Graphics g) {
            g.drawImage(img,0,0,this);
            super.paintChildren(g);
           }
       };

	public ImageIPanel(Image image) { // 构建构造方法.传入的参数是Image的文件路径
		this.image = image;
		Dimension size = new Dimension(image.getWidth(null),image.getHeight(null));
		setSize(size); // 设置JPanel的大小为Image图象的大小
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		//setLocation(0, 35);
		setLayout(null);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // 用G 把Image画出来
	}

}