package main.output;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ReceiptFileUpdaterHTML extends ReceiptFileUpdater {
	private int lastUnorderedListIndex;
    private String newReceipt;
    private StringBuilder stringBuilder;

    @Override
    public void writeFile(String receiptData){
    	newReceipt=newReceipt+"\n\t <li><b>" + receiptData + ":</b> ";
    	int areReceiptDataWritten=0;
        if (receiptData.equals("Receipt ID")) {
        	newReceipt=newReceipt+ receipt.getReceiptID();
        } else if (receiptData.equals("Date")) {
        	newReceipt=newReceipt+receipt.getDate();
        } else if (receiptData.equals("Kind")) {
        	newReceipt=newReceipt+receipt.getKind();
        } else if (receiptData.equals("Sales")) {
        	newReceipt=newReceipt+receipt.getSales();
        } else if (receiptData.equals("Items")) {
        	newReceipt=newReceipt+receipt.getItems();
        } else if (receiptData.equals("Company")) {
        	newReceipt=newReceipt+receipt.getCompany().getName();
        } else if (receiptData.equals("Country")) {
        	newReceipt=newReceipt+receipt.getCountry();
        } else if (receiptData.equals("City")) {
        	newReceipt=newReceipt+receipt.getCity();
        } else if (receiptData.equals("Street")) {
        	newReceipt=newReceipt+receipt.getStreet();
        } else if (receiptData.equals("Number")) {
        	newReceipt=newReceipt+receipt.getNumber();
        	areReceiptDataWritten=1;
        }
        newReceipt=newReceipt+"</li>";
        if (areReceiptDataWritten==1) {
        	newReceipt=newReceipt+"\n\t</ul></li>\n  ";
        }
    }

    @Override
    public void setup(){
        try {
        	 BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToAppend));
        	 stringBuilder = new StringBuilder();
             String line;
             while ((line = bufferedReader.readLine()) != null) {
            	 stringBuilder.append(line).append("\n");
             }
             bufferedReader.close();
             lastUnorderedListIndex = stringBuilder.toString().lastIndexOf("</ul>");
             newReceipt = stringBuilder.toString().substring(0, lastUnorderedListIndex)+"  <li>\n\t<h4>Receipt</h4>\n\t<ul>";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void close(){
    	newReceipt = newReceipt +stringBuilder.toString().substring(lastUnorderedListIndex);
    	 BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(fileToAppend));
			bufferedWriter.write(newReceipt);
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
