package main.output;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ReceiptFileUpdaterXML  extends ReceiptFileUpdater{
	private ReportDocumentBuilder reportDocumentBuilder=null;

	public void writeFile(String receiptData){
		
		Element element = this.reportDocumentBuilder.getDoc().createElement(receiptData.replaceAll("\\s", ""));
		if (receiptData=="Receipt ID") {
			element.appendChild(this.reportDocumentBuilder.getDoc().createTextNode(""+receipt.getReceiptID()));
		}else if (receiptData=="Date") {
			element.appendChild(this.reportDocumentBuilder.getDoc().createTextNode(""+receipt.getDate()));
		}else if (receiptData=="Kind") {
			element.appendChild(this.reportDocumentBuilder.getDoc().createTextNode(""+receipt.getKind()));
		}else if (receiptData=="Sales") {
			element.appendChild(this.reportDocumentBuilder.getDoc().createTextNode(""+receipt.getSales()));
		}else if (receiptData=="Items") {
			element.appendChild(this.reportDocumentBuilder.getDoc().createTextNode(""+receipt.getItems()));
		}else if (receiptData=="Company") {
			element.appendChild(this.reportDocumentBuilder.getDoc().createTextNode(""+receipt.getCompany().getName()));
		}else if (receiptData=="Country") {
			element.appendChild(this.reportDocumentBuilder.getDoc().createTextNode(""+receipt.getCountry()));
		}else if (receiptData=="City") {
			element.appendChild(this.reportDocumentBuilder.getDoc().createTextNode(""+receipt.getCity()));
		}else if (receiptData=="Street") {
			element.appendChild(this.reportDocumentBuilder.getDoc().createTextNode(""+receipt.getStreet()));
		}else if (receiptData=="Number") {
			element.appendChild(this.reportDocumentBuilder.getDoc().createTextNode(""+receipt.getNumber()));
			
		}
		this.reportDocumentBuilder.getReportElem().appendChild(element);
	}

	public void close(){
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	   	 	transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			DOMSource source = new DOMSource(this.reportDocumentBuilder.getDoc());
			StreamResult result = new StreamResult(fileToAppend);
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	public void setup() {
	    try {
	        this.reportDocumentBuilder = new ReportDocumentBuilder();
	        reportDocumentBuilder.setDocumentBuilderFactory(DocumentBuilderFactory.newInstance());
	        reportDocumentBuilder.setDocBuilder(reportDocumentBuilder.getDocumentBuilderFactory().newDocumentBuilder());
	        reportDocumentBuilder.setDoc(reportDocumentBuilder.getDocBuilder().parse(fileToAppend));
	        Element receiptsElement = (Element) reportDocumentBuilder.getDoc().getElementsByTagName("Receipts").item(0);
	        reportDocumentBuilder.setReportElem(reportDocumentBuilder.getDoc().createElement("Receipt"));
	        receiptsElement.appendChild(reportDocumentBuilder.getReportElem());
	    } catch (ParserConfigurationException | IOException | SAXException e) {
	        e.printStackTrace();
	    }
	}


}
