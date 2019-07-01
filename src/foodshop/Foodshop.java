package foodshop;


import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.CustomerView;
import view.SemiProjectView;

public class Foodshop extends JFrame{
	SemiProjectView food;
	CustomerView CustomerView;
	public Foodshop() {
		food=new SemiProjectView();
		CustomerView=new CustomerView();
		Toolkit kit=Toolkit.getDefaultToolkit();
		JTabbedPane pane =new JTabbedPane();
		pane.addTab("¥‹∞Ò∞¸∏Æ", CustomerView.getContentPane);
		pane.add("πË¥ﬁ¿« πŒ¡∑", food);
		pane.setSelectedIndex(1);
		pane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(pane.getSelectedIndex() == 0) {
					setSize(500,400);
				}else {
					setSize(800,600);
				}
			}});
		add("Center",pane);
		setSize(800,600);
		setTitle("¥Î«—πŒ±π 1µÓ πË¥ﬁæ€, πË¥ﬁ¿«πŒ¡∑");
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image img=kit.getImage("ride.png");
		setIconImage(img);
		setResizable(false);
	}
	
	
	public static void main(String[] args) {
//		new Foodshop();
//		new CustomerView();
		new Login();
	}

	

}
