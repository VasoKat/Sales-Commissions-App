package tests.output;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import main.data.Company;
import main.data.Receipt;
import main.data.Salesman;
import main.output.XMLReportWriter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class XMLReportWriterTest {
	private Salesman salesman;
	private XMLReportWriter xmlReportWriter;
	private String fullPathName;
	private File fileXmlTestModel;
	private File fileXmlTest;
	
	public void initializeSalesman() {
		salesman = new Salesman(); 
        salesman.setName("Anna");
        salesman.setAfm("123456789");
	}
	
	public void initializeReceipt() {
		Receipt receipt=new Receipt();
        receipt.setKind("Coat");
        receipt.setSales(100000);
        receipt.setItems(100000);
        receipt.setReceiptID(1);
        receipt.setDate("1/1/2000");
        receipt.setCountry("Greece");
        receipt.setCity("Ioannina");
        receipt.setCountry("Greece");
        receipt.setStreet("Kaloudi");
        receipt.setNumber(10);
        Company company=new Company();
        company.setName("Creations");
        receipt.setCompany(company);
        ArrayList<Receipt> allReceipts=new ArrayList<Receipt>();
        allReceipts.add(receipt);
        salesman.setReceipts(allReceipts);
	}
	
	public void setup() {
		xmlReportWriter = new XMLReportWriter(salesman);
        fullPathName="..\\test_output_files\\test-XML-to-write.xml";
        fileXmlTestModel = new File("..\\test_output_files\\\\test-XML-model.xml");
        fileXmlTest = new File(fullPathName);
	}
	
    @Test
    public void testXMLReportWriter() {
    	initializeSalesman();
    	initializeReceipt();
    	setup();
        assertNotNull(salesman);
        assertDoesNotThrow(() -> {
        	xmlReportWriter.setup(fullPathName);
        	xmlReportWriter.writeReportData();
        	xmlReportWriter.writeKindSales("Trouser");
        	xmlReportWriter.writeKindSales("Skirt");
        	xmlReportWriter.writeKindSales("Shirt");
        	xmlReportWriter.writeKindSales("Coat");
        	xmlReportWriter.writeCommission();
        	xmlReportWriter.close(fullPathName);
        });
        assertTrue(fileXmlTest.exists());
        try {
			assertEquals(FileUtils.readFileToString(fileXmlTestModel, StandardCharsets.UTF_8),
			        FileUtils.readFileToString(fileXmlTest, StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
        fileXmlTest.delete();
    }
}
