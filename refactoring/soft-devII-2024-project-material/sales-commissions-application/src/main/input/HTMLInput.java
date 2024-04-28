package main.input;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;

public class HTMLInput extends Input{
	private Document doc;
	public HTMLInput(File recieptFileHTML){
		this.inputFile = recieptFileHTML;
		inputFilePath =  inputFile.getAbsolutePath();
	}
	
	public void setup() {
		try {
			doc = Jsoup.parse(new File(inputFilePath), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void readSalesmanData() {
		Element salesmanElement = doc.select("h2:contains(Salesman)").first();
		for(int i=0;i<INDEX_OF_ELEMENT_RECEIPTS;i++) {
			String inputFileFieldValue =salesmanElement.nextElementSibling().select("li:contains("+inputFileFields[i]+")").text().replace(inputFileFields[i]+":", "").trim();
			try {
				if(inputFileFieldValue.equals("")==false) {
					setInputFileField(inputFileFieldValue,inputFileFields[i]);
					continue;
				
				}else {
					throw new IOException("Τα δεδομένα του αρχείου δεν είναι στη σωστή σειρά.");
				}	
			}catch (IOException e) {
				e.printStackTrace();
	        }
		}
	}
	
	public void readReceiptData() {
		Elements receiptElements = doc.select("h4:contains(Receipt)");
		for(Element receiptElement : receiptElements) {
			for(int i=INDEX_OF_FIRST_RECEIPT_FIELD;i<inputFileFields.length;i++) {
				String inputFileFieldValue =receiptElement.nextElementSibling().select("li:contains("+inputFileFields[i]+")").text().replace(inputFileFields[i]+":", "").trim();
				try {
					if(inputFileFieldValue.equals("")==false) {
						setInputFileField(inputFileFieldValue,inputFileFields[i]);
						continue;
					
					}else {
						throw new IOException("Τα δεδομένα του αρχείου δεν είναι στη σωστή σειρά.");
					}	
				}catch (IOException e) {
					e.printStackTrace();
		        }
				
			}	
		}
	}	
	
}

