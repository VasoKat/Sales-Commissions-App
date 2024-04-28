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
import main.output.HTMLReportWriter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class HTMLReportWriterTest {
	private Salesman salesman;
	private HTMLReportWriter htmlReportWriter;
	private String filePath;
	private File fileHtmlTestModel;
	private File fileHtmlTest;
	
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
		htmlReportWriter = new HTMLReportWriter(salesman);
        filePath="..\\test_output_files\\\\test-HTML-to-write.html";
        fileHtmlTestModel = new File("..\\test_output_files\\\\test-HTML-model.html");
        fileHtmlTest = new File(filePath);
	}
	
    @Test
    public void testHTMLReportWriter() {
    	initializeSalesman();
    	initializeReceipt();
    	setup();
        assertNotNull(salesman);
        assertDoesNotThrow(() -> {
            htmlReportWriter.setup(filePath);
            htmlReportWriter.writeReportData();
            htmlReportWriter.writeKindSales("Trouser");
            htmlReportWriter.writeKindSales("Skirt");
            htmlReportWriter.writeKindSales("Shirt");
            htmlReportWriter.writeKindSales("Coat");
            htmlReportWriter.writeCommission();
            htmlReportWriter.close(filePath);
        });
        assertTrue(fileHtmlTest.exists());
        try {
            assertEquals(FileUtils.readFileToString(fileHtmlTestModel, StandardCharsets.UTF_8).trim().replaceAll("\\r\\n", "\n"), FileUtils.readFileToString(fileHtmlTest, StandardCharsets.UTF_8).trim().replaceAll("\\r\\n", "\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileHtmlTest.delete();
    }
}
