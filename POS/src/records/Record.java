package records;

import java.util.Date;

public class Record {
	private String productID;
	private Date date;
	private Double retailPrice;
	private Integer recordID;
	private Integer billID;
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}
	public void setRecordID(Integer recordID) {
		this.recordID = recordID;
	}
	public Integer getRecordID() {
		return recordID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductID() {
		return productID;
	}
	public void setBillID(Integer billID) {
		this.billID = billID;
	}
	public Integer getBillID() {
		return billID;
	}
}
