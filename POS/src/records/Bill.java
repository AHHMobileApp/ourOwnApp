package records;

import java.util.Date;
import java.util.List;

public class Bill {
	private Integer billID;
	private Date date;
	private String vipNo;
	private List<Record> records;
	private Double total;
	
	public Integer genBillID(Integer number){
		Integer billID = 0;
		int year = this.date.getYear() - 100;
		int month = this.date.getMonth() + 1;
		int day = this.date.getDate();
		String billIDStr = "";
		if (number < 10){
			billIDStr = "0" + number;
		} else {
			billIDStr = number.toString();
		}
		billIDStr = month + billIDStr + year + day;
		for (int i = 0; i < billIDStr.length(); i++){
			Character c = new Character(billIDStr.charAt(i));
			String s = c.toString();
			int temp = Integer.parseInt(s);
			temp += i;
			if (temp >= 10){
				temp -= 10;
			}
			billID += (int)(temp * (Math.pow(10,billIDStr.length()- i - 1)));
		}
		/*regen the original billID by formatting Month|Number|Year|Day*/
//		billIDStr = billID.toString();
//		billID = 0;
//		for (int i = 0; i < billIDStr.length(); i++){
//			Character c = new Character(billIDStr.charAt(i));
//			String s = c.toString();
//			int temp = Integer.parseInt(s);
//			temp -= i;
//			if (temp < 0){
//				temp += 10;
//			}
//			billID += (int)(temp * (Math.pow(10,billIDStr.length()- i - 1)));
//		}
//		System.out.println(billID);
		return billID;
	}
	
	public Integer regenBillID(Integer billID){
		String billIDStr = billID.toString();
		billID = 0;
		for (int i = 0; i < billIDStr.length(); i++){
			Character c = new Character(billIDStr.charAt(i));
			String s = c.toString();
			int temp = Integer.parseInt(s);
			temp -= i;
			if (temp < 0){
				temp += 10;
			}
			billID += (int)(temp * (Math.pow(10,billIDStr.length()- i - 1)));
		}
		return billID;
	}
	public void setRecords(List<Record> records) {
		this.records = records;
	}
	public List<Record> getRecords() {
		return records;
	}
	public Integer getBillID() {
		return billID;
	}
	public void setBillID(Integer billID) {
		this.billID = billID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public static void main(String[] args){
		Bill bill = new Bill();
		Date date = new Date();
		bill.setDate(date);
		bill.setBillID(bill.genBillID(7));
		Integer oriBillID = bill.regenBillID(bill.getBillID());
		int lastID = Integer.parseInt(oriBillID.toString().substring(2,4));
		System.out.println(lastID);
	}

	public void setVipNo(String vipNo) {
		this.vipNo = vipNo;
	}

	public String getVipNo() {
		return vipNo;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotal() {
		return total;
	}
}
