package scau.models;

public class OverallModel {
     private int id;
     private String type;
     private int quotedPrice;
     private int originalPrice;
     private int mileage;
     private String transactionCon;
     private String manufacturer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getQuotedPrice() {
		return quotedPrice;
	}
	public void setQuotedPrice(int quotedPrice) {
		this.quotedPrice = quotedPrice;
	}
	public int getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public String getTransactionCon() {
		return transactionCon;
	}
	public void setTransactionCon(String transactionCon) {
		this.transactionCon = transactionCon;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
}
