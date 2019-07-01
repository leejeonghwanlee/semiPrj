package food.co;

public class Food {
	int count;//주문번호
	String name;//음식이름
	String type;//음식종류
	String franchise;//프랜차이즈
	int price;//가격
	String detail;//설명
	String imgfname;//이미지추가
	String g_name;// 단골손님이름
	String g_num;//단골손님 전호번호
	
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_num() {
		return g_num;
	}
	public void setG_num(String g_num) {
		this.g_num = g_num;
	}
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String getFranchise() {
		return franchise;
	}
	public void setFranchise(String franchise) {
		this.franchise = franchise;
	}
	
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getImgfname() {
		return imgfname;
	}
	public void setImgfname(String imgfname) {
		this.imgfname = imgfname;
	}
	
	
}
