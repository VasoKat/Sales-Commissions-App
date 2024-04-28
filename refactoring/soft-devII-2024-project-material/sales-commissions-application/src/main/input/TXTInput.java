 package main.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



public class TXTInput extends Input{
	private BufferedReader bufferedReader;
	private int inputFileFieldsIndex = 0;
	public TXTInput(File receiptFileTXT){
		this.inputFile = receiptFileTXT;
		inputFilePath =  inputFile.getAbsolutePath();
	}

	public void setup(){
		try {
			bufferedReader = new BufferedReader(new FileReader(inputFilePath));
		}catch (IOException e) {
			e.printStackTrace();
        }
	}
	
	public void readSalesmanData() {
		String line;
		try {
			while ((line = bufferedReader.readLine()) != null) {
	            String inputFileField = getInputFileField(line);
	            
	            if (inputFileField == null) {
	                continue;
	            }
	            if (inputFileField.equals(inputFileFields[inputFileFieldsIndex])) {
	                String inputFileFieldValue = line.substring(line.indexOf(":") + 1).trim();
	                setInputFileField(inputFileFieldValue, inputFileField);
	                inputFileFieldsIndex++;
	            } else {
	                throw new IOException("Τα δεδομένα του αρχείου δεν είναι στη σωστή σειρά.");
	            }
	            if(inputFileField.equals("AFM")){
	            	break;
	            }	
	        }
		}catch (IOException e) {
			e.printStackTrace();
        }
		
	}

	public void readReceiptData() {
		try {
	        String line;

	        while ((line = bufferedReader.readLine()) != null) {
	            String inputFileField = getInputFileField(line);
	            inputFileFieldsIndex = resetInputFileFieldsIndex(inputFileFieldsIndex,INDEX_OF_FIRST_RECEIPT_FIELD);          
	            if (inputFileField == null) {
	                continue;
	            }
            	if (inputFileField.equals(inputFileFields[inputFileFieldsIndex])) {
            		String inputFileFieldValue = line.substring(line.indexOf(":") + 1).trim();
            		setInputFileField(inputFileFieldValue, inputFileField);
            		inputFileFieldsIndex++;
            	} else {
	                throw new IOException("Τα δεδομένα του αρχείου δεν είναι στη σωστή σειρά.");
	            }	
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
	
	private String getInputFileField(String line) {
		for (int i=0;i<inputFileFields.length;i++) {
			if(line.startsWith(inputFileFields[i])) {
				return inputFileFields[i];
			}
		}
	    return null; 
	}
}
