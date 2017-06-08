package xupt.se.ttms.view.UI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Display extends JFrame {
	private final String[] flagTitles = { "你的名字", "一条狗的使命", "疯狂动物城", "魔幻城堡" };
	private final ImageIcon[] flagImage = { new ImageIcon("resource/image/81.jpg"),
			new ImageIcon("resource/image/82.jpg"), new ImageIcon("resource/image/83.jpg"),
			new ImageIcon("resource/image/84.jpg") };
	private final String[] fd = new String[4];
	private final DescriptionPanel dsp = new DescriptionPanel();
	private final JComboBox jcbo = new JComboBox(flagTitles);

	public Display() {
		fd[0] = "你的名字\n\n"
				+ "在远离大都会的小山村，住着巫女世家出身的高中女孩宫水三叶（上白石萌音 配音）。校园和家庭的原因本就让她充满烦恼，而近一段时间发生的奇怪事件，又让三叶摸不清头脑。不知从何时起，三叶在梦中就会变成一个住在东京的高中男孩。那里有陌生的同学和朋友，有亲切的前辈和繁华的街道，一切都是如此诱人而真实。另一方面，住在东京的高中男孩立花泷（神木隆之介 配音）则总在梦里来到陌生的小山村，以女孩子的身份过着全新的生活。许是受那颗神秘彗星的影响，立花和三叶在梦中交换了身份。他们以他者的角度体验着对方的人生，这期间有愤怒、有欢笑也有暖心。只是两人并不知道，身份交换的背后隐藏着重大而锥心的秘密…… ";
		fd[1] = "一条狗的使命\n\n"
				+ "影片以汪星人的视角展现狗狗和人类的微妙情感，一只狗狗陪伴小主人长大成人，甚至为他追到了女朋友，后来它年迈死去又转世投胎变成其他性别和类型的汪，第二次轮回狗狗变成了警犬威风凛凛，再次转轮回，又成了陪伴一位单身女青年的小柯基犬。在经历了多次轮回之后，最终回到最初的主人身边。 ";
		fd[2] = "疯狂动物城\n\n"
				+ "故事发生在一个所有哺乳类动物和谐共存的美好世界中，兔子朱迪（金妮弗·古德温 Ginnifer Goodwin 配音）从小就梦想着能够成为一名惩恶扬善的刑警，凭借着智慧和努力，朱迪成功的从警校中毕业进入了疯狂动物城警察局，殊不知这里是大型肉食类动物的领地，作为第一只，也是唯一的小型食草类动物，朱迪会遇到怎样的故事呢？ "
				+ "近日里，城中接连发生动物失踪案件，就在全部警员都致力于调查案件真相之时，朱迪却被局长（伊德瑞斯·艾尔巴 Idris Elba 配音）发配成为了一名无足轻重的交警。某日，正在执勤的兔子遇见了名为尼克（杰森·贝特曼 Jason Bateman 配音）的狐狸，两人不打不相识，之后又误打误撞的接受了寻找失踪的水獭先生的任务，如果不能在两天之内找到水獭先生，朱迪就必须自愿离开警局。朱迪找到了尼克，两人联手揭露了一个隐藏在疯狂动物城之中的惊天秘密。";
		fd[3] = "魔幻城堡\n\n"
				+ "　杰克（阿萨·巴特菲尔德 Asa Butterfield 饰）的爷爷自杰克还小的时候，就会常说床边故事给他听，故事中有许多拥有特殊能力的小孩。爷爷神秘死亡后，留下了关于这个属于另一个时空世界种种谜团的线索，杰克循着这些线索，进入了这个神秘的世界，发现这群奇怪的孩子真实存在，他们住在这里是为了躲避可怕的变异怪兽。随着杰克的到来，危险也同时抵达，杰克需要与这群孩子一起，抵御黑暗力量。";
		setDisplay(0);
		add(jcbo, BorderLayout.NORTH);
		add(dsp, BorderLayout.CENTER);
		jcbo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				setDisplay(jcbo.getSelectedIndex());
			}
		});
	}

	public void setDisplay(int index) {
		dsp.setTitle(flagTitles[index]);
		dsp.setImageIcon(flagImage[index]);
		dsp.setDescription(fd[index]);
	}

	public static void main(String[] args) {
		Display frame = new Display();
		frame.setTitle("电影信息");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class DescriptionPanel extends JPanel {
	private final JLabel jit = new JLabel();
	private final JTextArea jd = new JTextArea();

	public DescriptionPanel() {
		jit.setHorizontalAlignment(JLabel.CENTER);
		jit.setHorizontalTextPosition(JLabel.CENTER);
		jit.setVerticalAlignment(JLabel.BOTTOM);

		jit.setFont(new Font("ScansSerif", Font.BOLD, 16));
		jd.setFont(new Font("Serif", Font.PLAIN, 14));

		jd.setLineWrap(true);
		jd.setWrapStyleWord(true);
		jd.setEditable(false);

		JScrollPane sl = new JScrollPane(jd);

		setLayout(new BorderLayout(5, 5));
		add(sl, BorderLayout.CENTER);
		add(jit, BorderLayout.WEST);
	}

	public void setTitle(String t) {
		jit.setText(t);
	}

	public void setImageIcon(ImageIcon i) {
		jit.setIcon(i);
	}

	public void setDescription(String t) {
		jd.setText(t);
	}
}
