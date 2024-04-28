package main.data;

public class Receipt {
	protected int receiptId;
	protected String date;
	protected double sales;
	protected int items;
	protected Company company;
	protected String kind;
	protected String country;
	protected String city;	
	protected String street;
	protected int number;

    public Receipt(){
		
		kind = new String("No specific kind");
		company  = new Company();
	}
	
    
    
	public Company getCompany(){
		
		return company;
	}

	public void setCompany(Company company) {

		this.company = company;
	}
	
	public String getKind() {
		return kind;
		
	}
	public void setKind(String kind) {

		this.kind = kind;
	}
	
	public double getSales() {
		return sales;
	}

	
	public void setSales(double sales) {

		this.sales = sales;
	}

	
	public void setItems(int items) {
		this.items = items;
	}

	
	public int getItems() {
		return this.items;
	}

	
	public void setReceiptID(int id) {
		this.receiptId = id;		
		
	}

	
	public int getReceiptID() {
		return receiptId;
	}

	
	public void setDate(String date) {
		this.date = date;
	}

	
	public String getDate() {
		return date;			
	}
	
	public String getCountry() {
		return country;			
	}
	
	public void setCountry(String country) {

		this.country = country;
	}
	
	public String getCity() {
		return city;			
	}
	
	public void setCity(String city) {

		this.city = city;
	}
	public String getStreet() {
		return street;			
	}
	public void setStreet(String street) {

		this.street = street;
	}
	public int getNumber() {
		return number;			
	}
	public void setNumber(int number) {

		this.number = number;
	}
}
