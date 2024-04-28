package tests.input;

import org.junit.jupiter.api.Test;
import main.data.Salesman;
import main.input.HTMLInput;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HTMLInputTest {
	private Salesman salesman;
	
	public void setup() {
		File fileHTML = new File("..\\test_output_files\\test-case-3-HTML.html");
        HTMLInput htmlInput = new HTMLInput(fileHTML);
        htmlInput.readFile();
        salesman = htmlInput.getSalesman();
	}
	
    @Test
    public void readHTMLTest() {
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