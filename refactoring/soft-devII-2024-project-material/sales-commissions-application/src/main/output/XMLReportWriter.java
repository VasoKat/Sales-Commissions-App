package main.output;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import org.w3c.dom.Element;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import main.data.Salesman;

public class XMLReportWriter extends ReportWriter{
	private ReportDocumentBuilder reportDocumentBuilder=new ReportDocumentBuilder(); 
		
	public XMLReportWriter(Salesman salesman){
		this.salesman = salesman;
	}	

	public void writeKindSales(String kind){
		Element kindSalesElement = reportDocumentBuilder.getDoc().createElement(kind+"Sales");
		kindSalesElement.appendChild(reportDocumentBuilder.getDoc().createTextNode(Float.toString(salesman.calculateSales(kind))));
		reportDocumentBuilder.getReportElem().appendChild(kindSalesElement);
	}	
	
	public void setup(String fullPathName){ 
		reportDocumentBuilder.setDocumentBuilderFactory(DocumentBuilderFactory.newInstance());
		try {
			reportDocumentBuilder.setDocBuilder(reportDocumentBuilder.getDocumentBuilderFactory().newDocumentBuilder());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		reportDocumentBuilder.setDoc(reportDocumentBuilder.getDocBuilder().newDocument());
		reportDocumentBuilder.setReportElem(reportDocumentBuilder.getDoc().createElement("Salesman"));
		reportDocumentBuilder.getDoc().appendChild(reportDocumentBuilder.getReportElem());
	}
	
	public void appendReportDataToXML(String reportData) {
		Element reportField = reportDocumentBuilder.getDoc().createElement(reportData);
		if(reportData.equals("Name")) {
			reportField.appendChild(reportDocumentBuilder.getDoc().createTextNode(salesman.getName()));
		}else if(reportData.equals("AFM")) { 
			reportField.appendChild(reportDocumentBuilder.getDoc().createTextNode(salesman.getAfm()));	
		}else if(reportData.equals("TotalSales")) { 
			reportField.appendChild(reportDocumentBuilder.getDoc().createTextNode(Double.toString(salesman.calculateTotalSales())));
		}
		reportDocumentBuilder.getReportElem().appendChild(reportField);
	}
	
	
	public void writeReportData(){
		appendReportDataToXML("Name");
		appendReportDataToXML("AFM");
		appendReportDataToXML("TotalSales");
	}
	public void writeCommission() {
		Element commission = reportDocumentBuilder.getDoc().createElement("Commission");
    	commission.appendChild(reportDocumentBuilder.getDoc().createTextNode(Double.toString(salesman.calculateCommission())));
    	reportDocumentBuilder.getReportElem().appendChild(commission);
	}
	public void close(String fullPathName){
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    	Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    	transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	    	DOMSource domSource = new DOMSource(reportDocumentBuilder.getDoc());
	    	StreamResult streamResult = new StreamResult(new File(fullPathName));
	    	transformer.transform(domSource, streamResult);
	    	
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	

}

