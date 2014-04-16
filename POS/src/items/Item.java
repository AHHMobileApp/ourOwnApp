package items;

public class Item {
	private String catagory;
	private String productName;
	private String productID;
	private Double suggestedRetailPrice;
	private Double retailPrice;
	private Integer cost;
	private String des;
	private Integer stock;
	
	public String productIDGenerater(String catagoryID){
		String productID = "";
		productID += catagoryID;
		Integer intPrice = suggestedRetailPrice.intValue();
		String cost = this.cost.toString();
		String price = intPrice.toString();
		int costLength = cost.length();
		int priceLength = price.length();
		if (cost.length() < 4){
			for (int i = 0; i < 4 - costLength; i++){
				cost = "0" + cost;	
			}
		}
		if (price.length() < 4){
			for (int i = 0; i < 4 - priceLength; i++){
				price = "0" + price;
			}
		}
		return productID + cost + price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public Double getSuggestedRetailPrice() {
		return suggestedRetailPrice;
	}
	public void setSuggestedRetailPrice(Double suggestedRetailPrice) {
		this.suggestedRetailPrice = suggestedRetailPrice;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	public String getCatagory() {
		return catagory;
	}
	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}
	public Double getRetailPrice() {
		return retailPrice;
	}

	
}
