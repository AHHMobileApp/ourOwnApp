package records;

import java.util.Date;

public class VoidRecord {
	private Date voidDate;
	private Date recordDate;
	private Integer recordID;
	private Double voidPrice;
	private String productID;
	public Date getVoidDate() {
		return voidDate;
	}
	public void setVoidDate(Date voidDate) {
		this.voidDate = voidDate;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public Integer getRecordID() {
		return recordID;
	}
	public void setRecordID(Integer recordID) {
		this.recordID = recordID;
	}
	public Double getVoidPrice() {
		return voidPrice;
	}
	public void setVoidPrice(Double voidPrice) {
		this.voidPrice = voidPrice;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductID() {
		return productID;
	}
}
