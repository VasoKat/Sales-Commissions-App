package tests.output;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.FileUtils;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import main.data.Company;
import main.data.Receipt;
import main.output.ReceiptFileUpdaterXML;

import java.io.File;
import java.io.IOException;


public class XMLReceiptFileUpdaterTest {
	private Receipt receipt;
	private ReceiptFileUpdaterXML xmlReceiptFileUpdaterTest;
	private File fileXmlTest;
	private Path oldFilePath;
	private Path newFilePath;
	private File fileXmlTestModel;
	
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
		xmlReceiptFileUpdaterTest = new ReceiptFileUpdaterXML();
        fileXmlTest = new File("..\\test_output_files\\\\test-XML-to-append.xml");
        xmlReceiptFileUpdaterTest.setFileToAppend(fileXmlTest);
        xmlReceiptFileUpdaterTest.setReceipt(receipt);
        oldFilePath=Paths.get("..\\test_output_files\\\\test-XML-to-append.xml");
        newFilePath=Paths.get("..\\test_output_files\\\\test-XML-to-replace.xml");
        fileXmlTestModel = new File("..\\test_output_files\\\\test-XML-final.xml");
	}
	
	public void restoreFiles() {
		try {
			Files.copy(newFilePath,oldFilePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @Test
    public void testXMLReceiptFileUpdater() {
    	initializeReceipt();
    	setup();
        assertDoesNotThrow(() -> {
        	xmlReceiptFileUpdaterTest.appendFile();
        });
        assertTrue(fileXmlTest.exists());
        try {
			assertEquals(FileUtils.readFileToString(fileXmlTestModel, StandardCharsets.UTF_8).trim().replaceAll("\\r\\n", "\n"),
			        FileUtils.readFileToString(fileXmlTest, StandardCharsets.UTF_8).trim().replaceAll("\\r\\n", "\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        restoreFiles();
    }
}
