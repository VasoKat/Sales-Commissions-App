package main.output;

import java.io.File;

import main.data.Receipt;



public abstract class ReceiptFileUpdater {

	protected File fileToAppend;
	protected Receipt receipt;
	public abstract void writeFile(String receiptData);
	public abstract void close();	
	public abstract void setup();	
	public void setReceipt(Receipt receipt) {
		this.receipt=receipt;
		
	}
    public  void setFileToAppend(File fileToAppend) {
		
		this.fileToAppend = fileToAppend;
		
	}
    public void appendFile(){

		try {
			setup();
			writeFile("Receipt ID");
			writeFile("Date");
			writeFile("Kind");
			writeFile("Sales");
			writeFile("Items");
			writeFile("Company");
			writeFile("Country");
			writeFile("City");
			writeFile("Street");
			writeFile("Number");
			close();
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }
}

