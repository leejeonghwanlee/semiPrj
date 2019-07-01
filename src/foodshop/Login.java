package foodshop;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
	Foodshop fs = new Foodshop();
	JPanel panel;
	JLabel label,pswrd;
	JTextField txtId;
	JPasswordField txtPass;
	JButton button_login;
	Toolkit kit=Toolkit.getDefaultToolkit();
	public Login() {
		
		 panel=new JPanel();
		 label=new JLabel("ID : ");
		 pswrd=new JLabel("Password : ");
		 txtId=new JTextField(10);
		 txtPass=new JPasswordField(10);
		 button_login=new JButton("Log In");
		
		panel.add(label);
		panel.add(txtId);
		panel.add(pswrd);
		panel.add(txtPass);
		panel.add(button_login);
		button_login.addActionListener(this);
		txtPass.addActionListener(this);
			
		add(panel);
		
		setVisible(true);
		setSize(500, 100);
		setTitle("배달의 민족");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image img=kit.getImage("ride.png");
		setIconImage(img);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String id="1";
		String pass="1";
		
		if(id.equals(txtId.getText())&& pass.equals(txtPass.getText())) {
			JOptionPane.showMessageDialog(null, "로그인성공");
			fs.setVisible(true);
			this.setVisible(false);
		}else
			JOptionPane.showMessageDialog(null, "로그인실패");
	}

	}
	
