package main.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import main.data.Salesman;


public class TXTReportWriter extends ReportWriter{
	private BufferedWriter bufferedWriter;
	public TXTReportWriter(Salesman salesman){
		this.salesman = salesman;
	}
	
	public void writeKindSales(String kind){
		try {
			bufferedWriter.write(kind+"s Sales: " + salesman.calculateSales(kind));
			bufferedWriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setup(String fullPathName){
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(new File(fullPathName));
			this.bufferedWriter = new BufferedWriter(fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeReportData(){
		try {
			bufferedWriter.write("Name: " + salesman.getName());
			bufferedWriter.newLine();

	        bufferedWriter.write("AFM: " + salesman.getAfm());
	        bufferedWriter.newLine();
	        bufferedWriter.newLine();
	        bufferedWriter.newLine();

	        
	        bufferedWriter.write("Total Sales: " + salesman.calculateTotalSales());
	        bufferedWriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public void writeCommission() {
		try {
			bufferedWriter.write("Commission: " + salesman.calculateCommission());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void close(String fullPathName){
		try {
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
