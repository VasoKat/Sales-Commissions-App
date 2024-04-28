package tests.output;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.FileUtils;
import java.nio.charset.StandardCharsets;

import main.data.Company;
import main.data.Receipt;
import main.output.ReceiptFileUpdaterTXT;

import java.io.File;
import java.io.IOException;


public class TXTReceiptFileUpdaterTest {
	private Receipt receipt;
	private ReceiptFileUpdaterTXT txtReceiptFileUpdaterTest;
	private File fileTxtTest;
	private File fileTxtTestModel;
	
	public void initializeReceipt() {
		receipt=new Receipt();
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
	}
	
	public void setup() {
		txtReceiptFileUpdaterTest = new ReceiptFileUpdaterTXT();
        fileTxtTest = new File("..\\test_output_files\\\\test-TXT-to-append.txt");
        txtReceiptFileUpdaterTest.setFileToAppend(fileTxtTest);
        txtReceiptFileUpdaterTest.setReceipt(receipt);
        fileTxtTestModel = new File("..\\test_output_files\\\\test-TXT-final.txt");
	}
	
    @Test
    public void testTXTReceiptFileUpdater() {
    	initializeReceipt();
    	setup();
        assertDoesNotThrow(() -> {
        	txtReceiptFileUpdaterTest.appendFile();
        });
        assertTrue(fileTxtTest.exists());
        try {
			assertEquals(FileUtils.readFileToString(fileTxtTestModel, StandardCharsets.UTF_8).trim().replaceAll("\\r\\n", "\n"),
			        FileUtils.readFileToString(fileTxtTest, StandardCharsets.UTF_8).trim().replaceAll("\\r\\n", "\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        fileTxtTest.delete();
    }
}
