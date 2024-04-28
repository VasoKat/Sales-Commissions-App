package main.input;

import java.io.File;

import main.data.Receipt;
import main.data.Salesman;

public abstract class Input {
	protected static final int INDEX_OF_FIRST_RECEIPT_FIELD = 3;
	protected static final int INDEX_OF_ELEMENT_RECEIPTS = 2;
	protected static final String[] inputFileFields = {"Name", "AFM", "Receipts", "Receipt ID", "Date", "Kind", "Sales", "Items", "Company", "Country", "City", "Street", "Number"};
	protected Salesman salesman;
	protected File inputFile;
	protected String inputFilePath;
	protected String name;
	protected String afm;
	protected int receiptID;
	protected String date;
	protected String kind;
	protected double sales;
	protected int items;
	protected String companyName;
	protected String companyCountry;
	protected String companyCity;
	protected String companyStreet;
	protected int companyStreetNumber;
	public abstract void setup();
	public abstract void readSalesmanData();
	public abstract void readReceiptData();
	
	public Input() {
		salesman = new Salesman();
		kind  = new String("");
	}
	
	public void readFile() {
		setup();
		readSalesmanData();
		readReceiptData();
	}
	public int resetInputFileFieldsIndex(int inputFileFieldsIndex,int value) {
		if (inputFileFieldsIndex ==inputFileFields.length) {
			inputFileFieldsIndex =  value;  
	   
	    }
		return inputFileFieldsIndex;
	}

	public void setInputFileField(String inputFileFieldValue,String inputFileField) {
	    if (inputFileField.equals("Name")) {
	        name = inputFileFieldValue;
	    }else if (inputFileField.equals("AFM")) { 
	    	afm = inputFileFieldValue;
	        addSalesman();
	    }else if (inputFileField.equals("ReceiptID") || inputFileField.equals("Receipt ID")) { 
	        receiptID = Integer.parseInt(inputFileFieldValue);
	    }else if (inputFileField.equals("Date")) {
	        date = inputFileFieldValue;
	    }else if (inputFileField.equals("Kind")) { 
	        kind = inputFileFieldValue;
	    }else if (inputFileField.equals("Sales")) {
	        sales = Double.parseDouble(inputFileFieldValue);
	    }else if (inputFileField.equals("Items")) {
	    	
	        items = Integer.parseInt(inputFileFieldValue);
	    }else if (inputFileField.equals("Company")) { 
	        companyName = inputFileFieldValue;
	    }else if (inputFileField.equals("Country")) {
	        companyCountry = inputFileFieldValue; 
	    }else if (inputFileField.equals("City")) {
	        companyCity = inputFileFieldValue;
	    }else if (inputFileField.equals("Street")) { 
	        companyStreet = inputFileFieldValue;
	    }else if (inputFileField.equals("Number")) { 
	        companyStreetNumber = Integer.parseInt(inputFileFieldValue);
	        addReceipt();
	    }
	}
	
	public void addSalesman() {
		salesman.setName(name);
		salesman.setAfm(afm);
	}
	
	public void addReceipt( ){
		Receipt receipt;			
		if(kind.equals("Shirts")) {
			receipt= new Receipt();
			receipt.setKind("Shirt");
		}else if (kind.equals("Skirts")) {
			receipt= new Receipt();
			receipt.setKind("Skirt");
		}else if (kind.equals("Trousers")) {
			receipt= new Receipt();
			receipt.setKind("Trouser");
		}else {
			receipt= new Receipt();
			receipt.setKind("Coat");
		}	
		receipt.setReceiptID(receiptID);			
		receipt.setDate(date);
		receipt.setSales(sales);
		receipt.setItems(items);
		receipt.getCompany().setName(companyName);
		receipt.getCompany().getCompanyAddress().setCountry(companyCountry);
		receipt.getCompany().getCompanyAddress().setCity(companyCity);
		receipt.getCompany().getCompanyAddress().setStreet(companyStreet);
		receipt.getCompany().getCompanyAddress().setStreetNumber(companyStreetNumber);
		salesman.getReceipts().add(receipt);
	}
	public Salesman getSalesman() {
		return salesman;
	}

	
}
