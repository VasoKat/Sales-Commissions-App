package tests.output;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.FileUtils;
import java.nio.charset.StandardCharsets;

import main.data.Company;
import main.data.Receipt;
import main.data.Salesman;
import main.output.TXTReportWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class TXTReportWriterTest {
	private Salesman salesman;
	private TXTReportWriter txtReportWriter;
	private String filePath;
	private File fileTxtTest;
	private File fileTxtTestModel;
	
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
		txtReportWriter = new TXTReportWriter(salesman);
        filePath="..\\test_output_files\\\\test-TXT-to-write.txt";
        fileTxtTest = new File(filePath);
        fileTxtTestModel = new File("..\\test_output_files\\\\test-TXT-model.txt");
	}
	
    @Test
    public void testTXTReportWriter() {
    	initializeSalesman();
    	initializeReceipt();
    	setup();
        assertNotNull(salesman);
        assertDoesNotThrow(() -> {
        	txtReportWriter.setup(filePath);
        	txtReportWriter.writeReportData();
        	txtReportWriter.writeKindSales("Trouser");
            txtReportWriter.writeKindSales("Skirt");
            txtReportWriter.writeKindSales("Shirt");
            txtReportWriter.writeKindSales("Coat");
            txtReportWriter.writeCommission();
            txtReportWriter.close(filePath);
        });
        assertTrue(fileTxtTest.exists());
        try {
			assertEquals(FileUtils.readFileToString(fileTxtTestModel, StandardCharsets.UTF_8),
			        FileUtils.readFileToString(fileTxtTest, StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
        fileTxtTest.delete();
    }
}
