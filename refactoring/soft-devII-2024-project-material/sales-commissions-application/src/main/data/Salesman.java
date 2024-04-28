package main.data;


import java.util.ArrayList;

import main.output.ReceiptFileUpdater;
import main.output.ReceiptFileUpdaterHTML;
import main.output.ReceiptFileUpdaterTXT;
import main.output.ReceiptFileUpdaterXML;

public class Salesman {
	private String name;
	private String afm;
	private ArrayList <Receipt> allReceipts;
	private ReceiptFileUpdater receiptFileUpdater;
	
	
	public Salesman(){
		allReceipts = new ArrayList<Receipt>();
	}
	
	public void setFileType(String fileType) {
		if(fileType.equals("TXT")){
			receiptFileUpdater = new ReceiptFileUpdaterTXT();
		}	
		else if(fileType.equals("HTML")){
			receiptFileUpdater = new ReceiptFileUpdaterHTML();
		}else{
			receiptFileUpdater = new ReceiptFileUpdaterXML();
		}	
	}
	public ArrayList<Receipt> getReceipts(){
		return allReceipts;

	}
	public void setReceipts(ArrayList<Receipt> allReceipts){
		this.allReceipts=allReceipts;

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAfm() {
		return afm;
	}
	public void setAfm(String afm) {
		this.afm = afm;
	}

	public double calculateTotalSales(){
		double sumSales = 0;
		for(int i = 0; i< allReceipts.size(); i++){
			sumSales += allReceipts.get(i).getSales();
		}
		return sumSales;
	}
	

	public int calculateTotalItems(){
		int sumItems = 0;
		for(int i = 0; i< allReceipts.size(); i++){
			sumItems += allReceipts.get(i).getItems();
		}
		return sumItems;
	}
	
	public float calculateSales(String kind){
		float kindSum = 0;
		for (int i = 0; i< allReceipts.size(); i++){
			if(allReceipts.get(i).getKind().equals(kind)){
				kindSum += allReceipts.get(i).getItems();
			}
		}
		return kindSum;
	}
	
	
	
	public double calculateCommission(){
		double commission = 0;
		if( this.calculateTotalSales() > 6000 && this.calculateTotalSales()<= 10000){
			commission = 0.1*(calculateTotalSales()-6000);
		}
		else if(this.calculateTotalSales() > 10000 && this.calculateTotalSales() <= 40000 ){
			commission = (((calculateTotalSales() - 10000) * 0.15) + (10000*0.1));			
		}
		else if(this.calculateTotalSales() > 40000 ) {
			commission = 10000*0.1 + 30000*0.15 + (calculateTotalSales() - 40000)*0.2;			
		}
		return commission;
	}


	public ReceiptFileUpdater getReceiptFileUpdater() {
		return receiptFileUpdater;
	}


}
