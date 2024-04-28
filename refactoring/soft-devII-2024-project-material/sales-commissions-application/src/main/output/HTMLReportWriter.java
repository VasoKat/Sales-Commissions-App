package main.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import main.data.Salesman;

public class HTMLReportWriter extends ReportWriter{

	private BufferedWriter bufferedWriter;
	public HTMLReportWriter(Salesman salesman){
		this.salesman = salesman;
	}
	
	public void writeKindSales(String kind){
		try {
			bufferedWriter.write("\n\t\t\t"+"<li><em>"+kind+"s Sales:</em> "+salesman.calculateSales(kind)+"</li>");
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
			bufferedWriter.write("<html>"+"\n"+"<head>"+"\n"+"\t");
			bufferedWriter.write("<title>Salesman Report</title>");
	        bufferedWriter.newLine();
	        bufferedWriter.write("</head>"+"\n"+"<body>");
	        bufferedWriter.newLine();
	        bufferedWriter.newLine();
	        bufferedWriter.write("\t\t"+"<h2>Salesman</h2>");
	        bufferedWriter.write("\n\t\t"+"<ul>"+"\n\t\t\t");
	        bufferedWriter.write("<li><b>Name:</b> "+salesman.getName()+"</li>");
	        bufferedWriter.write("\n\t\t\t");
	        bufferedWriter.write("<li><b>AFM:</b> "+salesman.getAfm()+"</li>");
	        bufferedWriter.write("\n\t\t"+"</ul>");
	        bufferedWriter.write("\n\n\t\t"+"<ul>");
	        bufferedWriter.write("\n\t\t\t"+"<li><em>Total Sales:</em> "+salesman.calculateTotalSales()+"</li>");
		} catch (IOException e) {
			e.printStackTrace();
		}
       
	}
	
	public void writeCommission() {
		try {
			bufferedWriter.write("\n\t\t\t"+"<li><em>Commission Sales:</em> "+salesman.calculateCommission()+"</li>");
			bufferedWriter.write("\n\t\t"+"</ul>"+"\n"+"</body>"+"\n"+"</html>");
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
