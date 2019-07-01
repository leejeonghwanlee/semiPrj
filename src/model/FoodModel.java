package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import food.co.Food;

public class FoodModel {
	Connection con;
	
	public FoodModel() throws Exception {
	    con=DBcon.getConnection();
	}

	public void insertCount(Food co, int count) throws Exception {
		
		con.setAutoCommit(false);//자동커밋 해제
		
		//DB에 insert 시키기
		String sql1="insert into count "
		+"values(?,?,?,?,?,?,?)";
		

		PreparedStatement ps1=con.prepareStatement(sql1);
		ps1.setInt(1, co.getCount());
		ps1.setString(2, co.getName());
		ps1.setString(3, co.getType());
		ps1.setString(4, co.getFranchise());
		ps1.setInt(5, co.getPrice());
		ps1.setString(6, co.getDetail());
		ps1.setString(7,  co.getImgfname());
		
		int r1=ps1.executeUpdate();//sql1 실행
		System.out.println("입고");
		con.commit();
		ps1.close();
		con.setAutoCommit(true);//자동커밋 작동
		
	}

	public ArrayList searchCount(int idx, String str) throws Exception {
		//검색
		String[] key= {"NAME","TYPE","FRANCHISE","PRICE",""};
		String sql="select * "+ " from count " + " where "+key[idx]+" like '%"+str+"%'";
				
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		ArrayList data=new ArrayList();
		
		while (rs.next()) {
			ArrayList temp=new ArrayList();
			temp.add(rs.getInt("COUNT"));
			temp.add(rs.getString("NAME"));
			temp.add(rs.getString("TYPE"));
			temp.add(rs.getString("FRANCHISE"));
			temp.add(rs.getInt("PRICE"));
			temp.add(rs.getString("DETAIL"));
			data.add(temp);//ArrayList에 ArrayList를 추가
		}
		rs.close();
		ps.close();
		
		
		return data;
	}

	public Food selectbyPk(int no) throws Exception {
		//Jtable에서 클릭한 레코드의 정보를 video 타입으로 저장해서 return하는 과정
		
		Food co=new Food();
		String sql="select * from count where count="+no;
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while (rs.next()) {
			co.setCount(Integer.parseInt(rs.getString(1)));
			co.setName(rs.getString(2));
			co.setType(rs.getString(3));
			co.setFranchise(rs.getString(4));
			co.setPrice(Integer.parseInt(rs.getString(5)));
			co.setDetail(rs.getString(6));
			co.setImgfname(rs.getString(7));
			System.out.println("rsrsrs 111: "+rs.getString(7));
			System.out.println("rsrsrs 222: "+co.getImgfname());
			
			
			
			
		}
		
//		System.out.print("co : " + co);
		rs.close();
		ps.close();
		
//		System.out.println("no : "+no);
		return co;
	}

	public void modifyCount(Food co) throws Exception {
		//데이터 수정작업
		String sql="UPDATE count set name=?, "
				+" type=? ,"+
				" franchise=? ,"+
				" price=? ,"+
				" detail=? "+
				 " where count=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, co.getName());
		ps.setString(2, co.getType());
		ps.setString(3, co.getFranchise());
		ps.setInt(4, co.getPrice());
		ps.setString(5, co.getDetail());
		ps.setInt(6, co.getCount());
//		ps.setString(7, co.getImgfname());
		
		ps.executeQuery();
		ps.close();
	}

	public void deleteCount(Food co) throws Exception {
		String sql = "delete from Count where count = ?";
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.setInt(1, co.getCount());
		
		ps.executeUpdate();
		ps.close();
		
	}
	
	public void guest(String name, String num)  {
		String str = "insert into guest(guest_name,guest_num)" + 
				"values(?,?)";
		try {
			con = DBcon.getConnection();
			PreparedStatement pstmt = con.prepareStatement(str);
			pstmt.setString(1, name);
			pstmt.setString(2, num);
			
			ResultSet rs = pstmt.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DB 메소드");
		}
	}
	
	public ArrayList searchGuest(int idx,String str)  {
		//검색
		String[] key= {"GUEST_NAME","GUEST_NUM"};
		String sql= "select * "+ " from guest " + " where "+key[idx]+" like '%"+str+"%'";
		
		ArrayList data = new ArrayList<>();
		
		try {
			con = DBcon.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			//=============================================
			while (rs.next()) {
				ArrayList temp=new ArrayList();
				temp.add(rs.getString("GUEST_NAME"));
				temp.add(rs.getString("GUEST_NUM"));
			}
			rs.close();
			st.close();
//			System.out.println("test");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return data;
	}

	
}


