package main.output;

import java.io.FileWriter;
import java.io.IOException;

public class ReceiptFileUpdaterTXT extends ReceiptFileUpdater{
	private FileWriter fileWriter;

	public void writeFile(String receiptData){
		try {
			fileWriter.write("\n");
			fileWriter.write(receiptData+": ");
			if (receiptData=="Receipt ID") {
				fileWriter.write(""+receipt.getReceiptID());
			}else if (receiptData=="Date") {
				fileWriter.write(receipt.getDate());
			}else if (receiptData=="Kind") {
				fileWriter.write(receipt.getKind());
			}else if (receiptData=="Sales") {
				fileWriter.write(""+receipt.getSales());
			}else if (receiptData=="Items") {
				fileWriter.write(""+receipt.getItems());
			}else if (receiptData=="Company") {
				fileWriter.write(receipt.getCompany().getName());
			}else if (receiptData=="Country") {
				fileWriter.write(receipt.getCountry());
			}else if (receiptData=="City") {
				fileWriter.write(receipt.getCity());
			}else if (receiptData=="Street") {
				fileWriter.write(receipt.getStreet());
			}else if (receiptData=="Number") {
				fileWriter.write(""+receipt.getNumber());
			}
			
			fileWriter.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setup(){
		try {
			fileWriter = new FileWriter(fileToAppend,true);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public void close(){
		try {
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
