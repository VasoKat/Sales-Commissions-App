package main.output;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.data.Salesman;

public abstract class ReportWriter {

	protected Salesman salesman;
	
	
	public abstract void writeKindSales(String kind);
	public abstract void setup(String fullPathName);
	public abstract void writeReportData();
	public abstract void writeCommission();
	public abstract void close(String fullPathName);
	public int saveFile(String typeFile) {
		JFileChooser chooser = new JFileChooser();
	    chooser.setFileFilter(new FileNameExtensionFilter("*."+typeFile, typeFile));
	    int returnVar = chooser.showSaveDialog(null);

	    if (returnVar == JFileChooser.APPROVE_OPTION) {
	        File chosenFile = chooser.getSelectedFile();
	        String fullPathName = chosenFile.getAbsolutePath();
	        String nameFile = chosenFile.getName() + "."+typeFile;
	        String parentPath=chosenFile.getParentFile().getAbsolutePath();
	        fullPathName = parentPath + "/" + nameFile;

	        try {
	        	 
	        	setup(fullPathName);
	        	writeReportData();	 
	        	writeKindSales("Trouser");
	        	writeKindSales("Skirt");
	        	writeKindSales("Shirt");
	        	writeKindSales("Coat");
	        	writeCommission();
	        	close(fullPathName);	
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            returnVar = JFileChooser.CANCEL_OPTION;
	        }  
	    }   
	    return returnVar;
	}
}
