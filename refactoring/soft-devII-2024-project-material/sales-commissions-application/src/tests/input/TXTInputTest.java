package tests.input;

import org.junit.jupiter.api.Test;
import main.data.Salesman;
import main.input.TXTInput;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TXTInputTest {
	private Salesman salesman;
	
	public void setup() {
		File fileTXT = new File("..\\test_output_files\\test-case-1-TXT.txt");
        TXTInput txtInput = new TXTInput(fileTXT);
        txtInput.setup();
        txtInput.readSalesmanData();
        txtInput.readReceiptData();
        salesman = txtInput.getSalesman();
	}
	
    @Test
    public void readTXTTest() {
    	setup();
        assertNotNull(salesman);
        assertEquals("Apostolos Zarras", salesman.getName());
        assertEquals("130456093", salesman.getAfm());
        assertEquals("25/2/2014", salesman.getReceipts().get(0).getDate()); 
        assertEquals("Ioannina", salesman.getReceipts().get(1).getCompany().getCompanyAddress().getCity());
        assertEquals("Greece", salesman.getReceipts().get(1).getCompany().getCompanyAddress().getCountry());
        assertEquals(2000, salesman.getReceipts().get(0).getSales());
    }
}