package tests.input;

import org.junit.jupiter.api.Test;
import main.data.Salesman;
import main.input.XMLInput;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class XMLInputTest {
	private Salesman salesman;
	
	public void setup() {
		File fileXML = new File("..\\test_output_files\\test-input-XML.xml");
        XMLInput xmlInput = new XMLInput(fileXML);
        xmlInput.setup();
        xmlInput.readSalesmanData();
        xmlInput.readReceiptData();
        salesman = xmlInput.getSalesman();
	}
	
    @Test
    public void readXMLTest() {
    	setup();
        assertNotNull(salesman);
        assertEquals("Vassileios Zarras", salesman.getName());
        assertEquals("130456097", salesman.getAfm());
        assertEquals("25/2/2014", salesman.getReceipts().get(0).getDate()); 
        assertEquals("Ioannina", salesman.getReceipts().get(1).getCompany().getCompanyAddress().getCity());
        assertEquals("Greece", salesman.getReceipts().get(1).getCompany().getCompanyAddress().getCountry());
        assertEquals(2000, salesman.getReceipts().get(0).getSales());
    }
}

