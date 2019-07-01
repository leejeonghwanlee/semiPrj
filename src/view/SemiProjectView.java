package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import food.co.Food;
import model.DBcon;
import model.FoodModel;

public class SemiProjectView extends JPanel implements ActionListener {
	JTextField tfcountNum, tfCountName, tfFranchise, tfPrice, taFoodDetail, taname, taphone;

	JComboBox comFoodType;
	// JTextArea taFoodContent;
	JTextField tfInsertCount;
	JButton bFoodInsert, bFoodModify, bFoodDelete;

	JComboBox comFoodSearch;
	JTextField tfFoodSearch;
	JTable tableFood;
	FoodModel model;
	FoodTableModel tbModelFood;

	JPanel picPanel;
	JLabel picLabel;

	JButton bChooseFile;
	File f = null;
	String fName = "";

	public SemiProjectView() {
		addLayout();
		initStyle();
		eventProc();
		ConnectDB();

	}

	void initStyle() {
		tfcountNum.setEditable(true);
		tfInsertCount.setEditable(true);
	}

	public void ConnectDB() {
		try {
			model = new FoodModel();
			System.out.println("배달의 민족 접속 완료");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void eventProc() {

		bFoodInsert.addActionListener(this);

		// bFoodDelete.addActionListener(this);
		// bFoodModify.addActionListener(this);
		// tfFoodSearch.addActionListener(this);
		//

		tableFood.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableFood.getSelectedRow();
				int col = 0;

				int no = (int) tableFood.getValueAt(row, col);
				// int no=Integer.parseInt(data);

				try {
					Food co = model.selectbyPk(no);
					selectbyPk(co);
					
					System.out.println("coco : "+co.getImgfname());

				} catch (Exception e1) {

					e1.printStackTrace();
				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}

	private void addLayout() {

		tfcountNum = new JTextField();
		tfCountName = new JTextField();
		tfFranchise = new JTextField();
		tfPrice = new JTextField();
		taFoodDetail = new JTextField();
		taname = new JTextField();
		taphone = new JTextField();

		String[] cbJanreStr = { "=========", "한식", "분식", "카페", "디저트", "일식", "치킨", "피자", "중국집", "야식" };
		comFoodType = new JComboBox(cbJanreStr);
		taFoodDetail = new JTextField();

		tfInsertCount = new JTextField("1", 6);

		String[] cbVideoSearch = { "음식이름 ", "종류", "프랜차이즈", "가격" };
		comFoodSearch = new JComboBox(cbVideoSearch);
		tfFoodSearch = new JTextField(15);
		// 액션리스너 삽입 , 수정 삭제

		tfFoodSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchFood();
			}
		});
		// bChooseFile.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// System.out.println("파일선택");
		// JFileChooser jc = new JFileChooser();
		// jc.showOpenDialog();
		// f=jc.getSelectedFile();
		// jfpath.setText(f.getPath());
		// }
		// });

		tbModelFood = new FoodTableModel();
		tableFood = new JTable(tbModelFood);
		tableFood.setModel(tbModelFood);
		picPanel = new JPanel();

		JPanel p_west = new JPanel();
		p_west.setLayout(new GridLayout(0, 1));

		JPanel p_west_center = new JPanel();
		p_west_center.setLayout(new BorderLayout());

		JPanel p_west_center_north = new JPanel();
		p_west_center_north.setBackground(SystemColor.menu);
		p_west_center_north.setBorder(new TitledBorder("배달의 민족 메뉴추가"));
		p_west_center_north.setLayout(new GridLayout(3, 2));
		p_west_center_north.add(new JLabel("주문번호"));
		p_west_center_north.add(tfcountNum);

		p_west_center_north.add(new JLabel("음식종류"));
		p_west_center_north.add(comFoodType);

		p_west_center_north.add(new JLabel("음식이름"));
		p_west_center_north.add(tfCountName);

		p_west_center_north.add(new JLabel("프랜차이즈"));
		p_west_center_north.add(tfFranchise);

		p_west_center_north.add(new JLabel("가격"));
		p_west_center_north.add(tfPrice);

		p_west_center_north.add(new JLabel("음식설명"));
		p_west_center_north.add(taFoodDetail);

		JPanel p_west_center_center = new JPanel();
		p_west_center_center.setBorder(new TitledBorder("음식이미지 추가"));
		p_west_center_center.setLayout(new BorderLayout(0, 0));

		p_west_center.setBorder(new TitledBorder("주문 프로그램"));

		p_west_center.add(p_west_center_north, BorderLayout.NORTH);
		p_west_center.add(p_west_center_center, BorderLayout.CENTER);

		//// bChooseFile=new JButton("이미지추가");
		//// ImageIcon icon = new ImageIcon(+".png");
		// JLabel img_l = new JLabel(icon);
		// jfpath=new JTextField();
		// p_west_center_center.add(jfpath);
		// p_west_center_center.add(img_l);
		// //버튼에 리스너 부착
		//// img_l.addActionListener(this);

		bChooseFile = new JButton("음식 이미지파일선택");
		bChooseFile.setBackground(Color.WHITE);
		bChooseFile.setBackground(UIManager.getColor("ToolTip.background"));
		p_west_center_center.add(bChooseFile);
		// 버튼에 리스너 부착
		bChooseFile.addActionListener(this);

		JPanel p_west_south = new JPanel();
		p_west_south.setLayout(null);

		JPanel p_west_south_1 = new JPanel();
		p_west_south_1.setBounds(0, 0, 398, 175);
		p_west_south_1.setBorder(new TitledBorder("이미지"));

		p_west_south.add(p_west_south_1);
		p_west_south_1.setLayout(null);

		picLabel = new JLabel();
		picLabel.setBounds(12, 20, 374, 172);
		p_west_south_1.add(picLabel);

		p_west.add(p_west_center, BorderLayout.CENTER);
		p_west.add(p_west_south, BorderLayout.SOUTH);

		bFoodInsert = new JButton("추가");
		bFoodInsert.setBackground(Color.white);
		bFoodModify = new JButton("수정");
		bFoodModify.setBackground(Color.white);
		bFoodDelete = new JButton("삭제");
		bFoodDelete.setBackground(Color.white);

		bFoodModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modify();
			}
		});

		bFoodDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();

			}
		});

		JPanel p_west_south_2 = new JPanel();
		p_west_south_2.setBounds(0, 178, 389, 100);
		p_west_south_2.setLayout(new GridLayout(1, 3));
		p_west_south_2.add(bFoodInsert);
		p_west_south_2.add(bFoodModify);
		p_west_south_2.add(bFoodDelete);
		p_west_south.add(p_west_south_2);

		JPanel p_east = new JPanel();
		p_east.setLayout(new BorderLayout());

		JPanel p_east_north = new JPanel();
		p_east_north.add(comFoodSearch);
		p_east_north.add(tfFoodSearch);

		p_east_north.setBorder(new TitledBorder("음식주문검색"));
		p_east.add(p_east_north, BorderLayout.NORTH);
		p_east.add(new JScrollPane(tableFood), BorderLayout.CENTER);

		setLayout(new GridLayout(1, 2));
		add(p_west);
		add(p_east);

		JPanel p_south_south = new JPanel();
		p_south_south.setLayout(new BorderLayout());
		p_south_south.add(new JLabel("이름"));
		p_south_south.add(taname);
		p_south_south.add(new JLabel("전화번호"));
		p_south_south.add(taphone);
	}

	class FoodTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "주문번호", "이름", "종류", "프랜차이즈", "가격", "설명" };

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}
	}

	void fileSave(File file, String path, String name) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				f.mkdir();// 폴더만둘기
			}
			String filepath = path + "\\" + name;

			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(filepath);

			int i = 0;
			byte[] buffer = new byte[1024];
			while ((i = fis.read(buffer, 0, 1024)) != -1) {
				fos.write(buffer, 0, i);
			}
			fis.close();
			fos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void delete() {
		Food co = new Food();
		co.setCount(Integer.parseInt(tfcountNum.getText()));
		co.setName(tfCountName.getText());
		co.setPrice(Integer.parseInt(tfPrice.getText()));
		co.setFranchise(tfFranchise.getText());
		co.setDetail(taFoodDetail.getText());
		co.setType((String) comFoodType.getSelectedItem());

		try {
			model.deleteCount(co);
			JOptionPane.showMessageDialog(null, "메뉴삭제완료");
			tfcountNum.setText(null);
			tfPrice.setText(null);
			tfFranchise.setText(null);
			tfCountName.setText(null);
			taFoodDetail.setText(null);

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "메뉴삭제실패");
			e1.printStackTrace();
		}

	}

	private void modify() {
		Food co = new Food();
		co.setCount(Integer.parseInt(tfcountNum.getText()));
		co.setName(tfCountName.getText());
		co.setPrice(Integer.parseInt(tfPrice.getText()));
		co.setFranchise(tfFranchise.getText());
		co.setDetail(taFoodDetail.getText());
		co.setType((String) comFoodType.getSelectedItem());

		try {
			model.modifyCount(co);
			JOptionPane.showMessageDialog(null, "메뉴수정완료");
			tfcountNum.setText(null);
			tfPrice.setText(null);
			tfFranchise.setText(null);
			tfCountName.setText(null);
			taFoodDetail.setText(null);

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "메뉴수정실패");
			e1.printStackTrace();
		}
	}

	private void searchFood() {
		int idx = comFoodSearch.getSelectedIndex();
		String str = tfFoodSearch.getText();

		try {
			ArrayList data = model.searchCount(idx, str);
			tbModelFood.data = data;
			tableFood.setModel(tbModelFood);
			tbModelFood.fireTableDataChanged();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	ImageIcon icon;

	void selectbyPk(Food co) {
		tfcountNum.setText(String.valueOf(co.getCount()));
		tfCountName.setText(co.getName());
		tfFranchise.setText(co.getFranchise());
		taFoodDetail.setText(co.getDetail());
		tfPrice.setText(String.valueOf(co.getPrice()));
		comFoodType.setSelectedItem(co.getType());

		System.out.println("여기와?" + co.getImgfname());

		icon = new ImageIcon(".\\upload2\\" + co.getImgfname());
		ImageIcon newIcon;
		Image image = icon.getImage();
		image.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), 0);
		int imgW = picLabel.getWidth();
		int imgH = picLabel.getHeight();
		// int imgW = 400;
		// int imgH = 200;
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(imgW, imgH, java.awt.Image.SCALE_SMOOTH);
		newIcon = new ImageIcon(newimg);
		picLabel.setIcon(newIcon);

	}

	private void InsertFood(String fName) {
		Food co = new Food();
		co.setCount(Integer.parseInt(tfcountNum.getText()));
		co.setName(tfCountName.getText());
		co.setPrice(Integer.parseInt(tfPrice.getText()));
		co.setFranchise(tfFranchise.getText());
		co.setDetail(taFoodDetail.getText());
		co.setType((String) comFoodType.getSelectedItem());
		co.setImgfname(fName);

		int count = Integer.parseInt(tfInsertCount.getText());

		try {
			model.insertCount(co, count);
			JOptionPane.showMessageDialog(null, "메뉴추가완료");

			tfcountNum.setText(null);
			tfPrice.setText(null);
			tfFranchise.setText(null);
			tfCountName.setText(null);
			taFoodDetail.setText(null);

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "메뉴추가실패");
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object str = e.getSource();
		 e.getActionCommand();
		System.out.println(e.getActionCommand());
		System.out.println(str);
		if (str == bChooseFile) {
			System.out.println(e.getSource());
			System.out.println("파일선택");
			JFileChooser jc = new JFileChooser();
			jc.showOpenDialog(this);
			f = jc.getSelectedFile();//////////////////
		} else if (str == bFoodInsert) {
			fName = System.currentTimeMillis() + f.getName();// 파일 이름을 =가져온다.
			System.out.println("파일이름 :" + f.getName());
			InsertFood(fName);
			System.out.println("f : " + f);
			System.out.println("fname : " + fName);
			fileSave(f, ".//upload2", fName);
		}
	}
}
