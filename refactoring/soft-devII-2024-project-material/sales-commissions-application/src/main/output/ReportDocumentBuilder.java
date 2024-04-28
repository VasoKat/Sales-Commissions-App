package main.output;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ReportDocumentBuilder {
	private DocumentBuilderFactory DocumentBuilderFactory;
	private DocumentBuilder docBuilder;
	private Document doc;
	private Node salesman;
	private Element reportElement;
	public DocumentBuilderFactory getDocumentBuilderFactory() {
		return DocumentBuilderFactory;
	}
	public void setDocumentBuilderFactory(DocumentBuilderFactory documentBuilderFactory) {
		DocumentBuilderFactory = documentBuilderFactory;
	}
	public DocumentBuilder getDocBuilder() {
		return docBuilder;
	}
	public void setDocBuilder(DocumentBuilder docBuilder) {
		this.docBuilder = docBuilder;
	}
	public Document getDoc() {
		return doc;
	}
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	public Node getSalesman() {
		return salesman;
	}
	public void setSalesman(Node salesman) {
		this.salesman = salesman;
	}
	public Element getReportElem() {
		return reportElement;
	}
	public void setReportElem(Element reportElement) {
		this.reportElement = reportElement;
	}
	
}

