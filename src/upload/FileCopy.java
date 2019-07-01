package upload;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class FileCopy extends JFrame implements ActionListener{

	JTextField tf;
	JButton b;
	
	public FileCopy() {
		tf=new JTextField(30);
		b=new JButton("찾기");
		b.addActionListener(this);
		setLayout(new FlowLayout());
		add(tf);
		add(b);
		
		setSize(450,200);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==b) {
			JFileChooser jc=new JFileChooser();
			jc.showOpenDialog(this);
			
			if (jc.showSaveDialog(this)==jc.APPROVE_OPTION) {
				File f=jc.getSelectedFile();
				tf.setText(f.getPath());
				fileSave(f,".//upload2",f.getName());
//				fileSave(f,".\\upload2",f.getName()); //둘다 가능
				
			}
		}
		
	}


	public void fileSave(File file, String path, String name) {
		try {
			File f=new File(path);
			if (!f.exists()) {
				f.mkdir();
			}
			String filePath=path+"\\"+name;
			
			FileInputStream fis=new FileInputStream(file);
			FileOutputStream fos=new FileOutputStream(filePath);
			
			int i=0;
			byte[] buffer=new byte[1024];
			
			while ((i=fis.read(buffer,0,1024))!=-1) {
				fos.write(buffer,0,i);
				
			}
			fis.close();
			fos.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new FileCopy();
	}

}
