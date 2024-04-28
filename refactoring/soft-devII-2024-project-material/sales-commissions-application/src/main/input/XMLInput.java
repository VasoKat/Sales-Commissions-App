package main.input;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class XMLInput extends Input {
	private Document doc;
	private int numReceiptFields=inputFileFields.length - INDEX_OF_FIRST_RECEIPT_FIELD;
	private NodeList nodeList;
	public XMLInput(File receiptFileXML ){
		inputFile = receiptFileXML;
		inputFilePath =  inputFile.getAbsolutePath();
	}
	
	public void setup() {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			doc = documentBuilder.parse(inputFile);

	        doc.getDocumentElement().normalize();
		} catch (SAXException | IOException|ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void readSalesmanData() {
		nodeList = doc.getElementsByTagName("Agent");
		for(int i=0;i<INDEX_OF_ELEMENT_RECEIPTS;i++) {
			String inputFileFieldValue =((Element) nodeList.item(0)).getElementsByTagName(inputFileFields[i]).item(0).getChildNodes().item(0).getNodeValue().trim();
			setInputFileField(inputFileFieldValue,inputFileFields[i]);
		}	
	}
	
	public void readReceiptData() {
		 NodeList receiptsNodeList = ((Element) nodeList.item(0)).getElementsByTagName("Receipt");
		 for (int i = 0; i < receiptsNodeList.getLength() * numReceiptFields; i++) {
	            int inputFileFieldsIndex = i / numReceiptFields;
	            int elementIndex = i % numReceiptFields + INDEX_OF_FIRST_RECEIPT_FIELD;
	            elementIndex=resetInputFileFieldsIndex(elementIndex,(elementIndex - numReceiptFields));

	            String inputFileFieldValue = ((Element) receiptsNodeList.item(inputFileFieldsIndex)).getElementsByTagName(inputFileFields[elementIndex].replaceAll("\\s", "")).item(0).getChildNodes().item(0).getNodeValue().trim();

	            setInputFileField(inputFileFieldValue, inputFileFields[elementIndex].replaceAll("\\s", ""));
	     }
	}
	

    
}


