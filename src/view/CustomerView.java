package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import food.co.Food;
import model.FoodModel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomerView extends JPanel {
	JTextField tfCustName, tfCustTel;
	JButton bCustRegist, bcustDelete;

	JTextField tfCustNameSearch;
	JButton bCustNameSearch;
	FoodModel fd;
	JComboBox cb;
	public JPanel getContentPane;
	GuestTableModel gt;
	JTable table;
	FoodModel model;
	public CustomerView() {
		
		
		addLayout();
	
	}


	
	class GuestTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] columnNames = { "이름", "전화번호" };

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
	

	public void addLayout() {
		
		getContentPane = new JPanel();
		getContentPane.setLayout(new GridLayout(0, 2, 0, 0));
		JPanel c_west = new JPanel();
		c_west.setLayout(null);

		JPanel c_west_north = new JPanel();
		c_west_north.setBorder(new TitledBorder("단골손님 정보 입력"));
		c_west_north.setBounds(0, 63, 245, 131);
		c_west_north.setLayout(null);
		JLabel label = new JLabel("\uC774\uB984");
		JLabel lblNewLabel = new JLabel("\uC804\uD654\uBC88\uD638");

		label.setBounds(22, 37, 57, 15);
		
		tfCustName = new JTextField();
		tfCustName.setBounds(65, 34, 160, 25);
		tfCustName.setColumns(10);
		lblNewLabel.setBounds(12, 79, 57, 15);
		tfCustTel = new JTextField();
		tfCustTel.setBounds(65, 76, 160, 25);
		tfCustTel.setColumns(10);
		
		c_west_north.add(lblNewLabel);

		c_west_north.add(tfCustName);
		c_west_north.add(label);

		c_west_north.add(tfCustTel);
		JPanel c_west_south = new JPanel();
		c_west_south.setBounds(0, 189, 242, 73);
		c_west_south.setLayout(null);
		
		JButton insert = new JButton("\uCD94\uAC00");
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
					FoodModel fo =new FoodModel();
					fo.guest(tfCustName.getText(), tfCustTel.getText());
						JOptionPane.showMessageDialog(null, "저장 완료");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "오류");
					}
				}
			});
		insert.setBounds(0, 10, 122, 63);
		c_west_south.add(insert);
		
		JButton Delete = new JButton("\uC0AD\uC81C");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Delete.setBounds(120, 10, 122, 63);
		c_west_south.add(Delete);

		c_west.add(c_west_north);
		c_west.add(c_west_south);

		getContentPane.add(c_west);
		

		JPanel c_east = new JPanel();
		c_east.setLayout(null);
//		c_east.add(table);
		JPanel c_east_north = new JPanel();
		c_east_north.setBounds(0, 0, 250, 73);
		c_east_north.setBorder(new TitledBorder("단골손님 검색"));
		c_east_north.setLayout(null);

		tfCustNameSearch = new JTextField();
		
		tfCustNameSearch.setBounds(108, 28, 116, 21);
		tfCustNameSearch.setColumns(10);
		tfCustNameSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchguest();
			}
		});
		c_east_north.add(tfCustNameSearch);

		cb = new JComboBox();
		cb.setBounds(18, 28, 74, 21);
		cb.setModel(new DefaultComboBoxModel(new String[] { "\uC774\uB984", "\uC804\uD654\uBC88\uD638" }));
		c_east_north.add(cb);
		c_east.add(c_east_north);
		getContentPane.add(c_east);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\uBAA9\uB85D", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 74, 250, 316);
		c_east.add(panel);
		panel.setLayout(new BorderLayout(1, 0));
		
		gt = new GuestTableModel();
		table = new JTable(gt);
		table.setBounds(12, 21, 226, 18);
		table.setModel(gt);
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
		 getContentPane.setVisible(true);

}
	
	public void searchguest() {
		int idx = cb.getSelectedIndex();
		String str = tfCustNameSearch.getText();
		try {
			ArrayList data = model.searchGuest(idx, str);
			gt.data = data;
			table.setModel(gt);
			gt.fireTableDataChanged();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	void selectbyPk(Food co) {
		tfCustName.setText(co.getG_name());
		tfCustTel.setText(co.getG_num());
		
	}
		}



