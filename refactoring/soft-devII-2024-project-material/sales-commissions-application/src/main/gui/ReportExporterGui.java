package main.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.UIManager;

import main.output.HTMLReportWriter;
import main.output.TXTReportWriter;
import main.output.XMLReportWriter;
import main.data.Salesman;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReportExporterGui extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel resultWindowPanel = new JPanel();
	private JTextField totalSalesTextField;
	private JTextField totalItemsTextField;
	private JTextField trouserSalesTextField;
	private JTextField shirtSalesTextField;
	private JTextField coatSalesTextField;
	private JTextField skirtSalesTextField;
	private JTextField commissionTextField;
	private ReportSelectionGui selectionWindow;
	private Salesman selectedSalesman;
	private double totalSales;
	private int totalItems;
	private float shirtSales;
	private float skirtSales;
	private float trousersSales;
	private float coatsSales;
	private double commission;


	public ReportExporterGui(final ReportSelectionGui selectionWindow, Salesman salesman,double totalSales,int totalItems,
			float shirtSales,float skirtSales,float trousersSales,float coatsSales,double commission) {
		this.selectionWindow = selectionWindow;
		selectedSalesman = salesman;
		this.totalSales = totalSales;
		this.totalItems = totalItems;
		this.shirtSales = shirtSales;
		this.skirtSales = skirtSales;
		this.trousersSales = trousersSales;
		this.coatsSales = coatsSales;
		this.commission = commission;
		
		initialise();
	}	
	
	
	public void createExportButton(String fileType,int[] bounds) {

		JButton ReportButton = new JButton("\u0395\u03BE\u03B1\u03B3\u03C9\u03B3\u03AE \u03C3\u03B5 "+fileType);
		ReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(fileType=="XML") {
					outputXMLButtonPressed(evt);
				}else if(fileType=="TXT") {
					outputTXTButtonPressed(evt);
				}else if (fileType=="HTML") {
					outputHTMLButtonPressed(evt);
				}	
			}
		});
		ReportButton.setBackground(UIManager.getColor("Button.light"));
		ReportButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		ReportButton.setBounds(436, bounds[0], 163, bounds[1]);
		resultWindowPanel.add(ReportButton);
	}
	
	public void addLabel(String label,int[] bounds){
		JLabel lblNewLabel = new JLabel(label);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(60,bounds[0], bounds[1], bounds[2]);
		resultWindowPanel.add(lblNewLabel);
	}
	
	public void createNavigationButton(String button,int bound) {
		JButton btnNewButton = new JButton(button);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(button=="Cancel") {
					cancelButtonPressed(evt);
				}else {
					okButtonPressed(evt);
				}	
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(bound, 360, 101, 33);
		resultWindowPanel.add(btnNewButton);
	}
	
	public JTextField initReportDataTextField(JTextField reportDataTextField) {
		if (reportDataTextField==totalSalesTextField) {
			totalSalesTextField=new JTextField();
			return totalSalesTextField;
		}else if(reportDataTextField==totalItemsTextField) {
			totalItemsTextField=new JTextField();
			return totalItemsTextField;
		}else if(reportDataTextField==trouserSalesTextField) {
			trouserSalesTextField=new JTextField();
			return trouserSalesTextField;
		}else if(reportDataTextField==shirtSalesTextField) {
			shirtSalesTextField=new JTextField();
			return shirtSalesTextField;
		}else if(reportDataTextField==coatSalesTextField) {
			coatSalesTextField=new JTextField();
			return coatSalesTextField;
		}else if(reportDataTextField==skirtSalesTextField) {
			skirtSalesTextField=new JTextField();
			return skirtSalesTextField;
		}else if(reportDataTextField==commissionTextField) {
			commissionTextField=new JTextField();
			return commissionTextField;
		}
		return null;	
	}
	
	public void displayReportDataField(int bound,JTextField reportData)
	{
		JTextField reportDataTextField=initReportDataTextField(reportData);
		reportDataTextField.setEditable(false);
		reportDataTextField.setBounds(208, bound, 86, 20);
		resultWindowPanel.add(reportDataTextField);
		reportDataTextField.setColumns(10);
	}
	
	private void initialise(){
		setBounds(100, 100, 686, 456);
		getContentPane().setLayout(new BorderLayout());
		resultWindowPanel.setBackground(SystemColor.controlHighlight);
		resultWindowPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(resultWindowPanel, BorderLayout.CENTER);
		resultWindowPanel.setLayout(null);
		
		{
			createExportButton("XML",new int[]{189, 70});
		}
		{
			createExportButton("TXT",new int[]{32, 70});
		}
		{
			createExportButton("HTML",new int[]{110, 70});
		}
		{
			addLabel("\u03A3\u03C5\u03BD\u03BF\u03BB\u03B9\u03BA\u03AE \u03B1\u03BE\u03AF\u03B1",new int[]{41, 84, 43});
	
		}
		{
			addLabel("\u03A3\u03CD\u03BD\u03BF\u03BB\u03BF \u03C0\u03C9\u03BB\u03AE\u03C3\u03B5\u03C9\u03BD",new int[]{95, 124,14});
	
		}
		{
			addLabel("\u03A0\u03C9\u03BB\u03AE\u03C3\u03B5\u03B9\u03C2 \u03C0\u03B1\u03BD\u03C4\u03B5\u03BB\u03BF\u03BD\u03B9\u03CE\u03BD",new int[]{138, 138,14});
		}
		{
			addLabel("\u03A0\u03C9\u03BB\u03AE\u03C3\u03B5\u03B9\u03C2 \u039C\u03C0\u03BB\u03BF\u03C5\u03B6\u03CE\u03BD",new int[]{178, 124,14});
		}
		{
			addLabel("\u03A0\u03C9\u03BB\u03AE\u03C3\u03B5\u03B9\u03C2 \u03C0\u03B1\u03BB\u03C4\u03CE\u03BD",new int[]{220, 124,14});
		}
		{
			addLabel("\u03A0\u03C9\u03BB\u03AE\u03C3\u03B5\u03B9\u03C2 \u03C6\u03BF\u03C5\u03C3\u03C4\u03CE\u03BD",new int[]{255, 124,14});
		}
		{
			addLabel("\u03A0\u03C1\u03BF\u03BC\u03AE\u03B8\u03B5\u03B9\u03B1",new int[]{301, 101,14});
		}
		displayReportDataField(53,this.totalSalesTextField);
		displayReportDataField(93,totalItemsTextField);
		displayReportDataField(136,trouserSalesTextField);
		displayReportDataField(176,shirtSalesTextField);
		displayReportDataField(218,coatSalesTextField);
		displayReportDataField(253,skirtSalesTextField);
		displayReportDataField(299,commissionTextField);
		createNavigationButton("\u039F\u039A",246);
		createNavigationButton("Cancel",357);
		updateResults();
	}
	
	public void updateKindSales(float kindSales,JTextField kindSalesTextField) {
		if(kindSales>=0)
			kindSalesTextField.setText(Float.toString(kindSales));
		else 
			kindSalesTextField.setEnabled(false);
	}
	public void updateReportData(double reportData,JTextField reportDataTextField) {
		if(reportData>=0)
			reportDataTextField.setText(Double.toString(reportData));
		else 
			reportDataTextField.setEnabled(false);
	}
	public void updateTotalItems(int totalItems,JTextField totalItemsTextField) {
		if(totalItems>=0)
			totalItemsTextField.setText(Integer.toString(totalItems));
		else 
			totalItemsTextField.setEnabled(false);
	}
	private void updateResults() {
		updateReportData(totalSales,this.totalSalesTextField);
		updateTotalItems(totalItems,totalItemsTextField);
		updateKindSales(shirtSales,shirtSalesTextField);
		updateKindSales(skirtSales,skirtSalesTextField);
		updateKindSales(coatsSales,coatSalesTextField);
		updateKindSales(trousersSales,trouserSalesTextField);
		updateReportData(commission,commissionTextField);
	}
	private void outputTXTButtonPressed(ActionEvent evt) {
		TXTReportWriter makeTXTFile = new TXTReportWriter(selectedSalesman);
		int returnVal =makeTXTFile.saveFile("txt");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
	        JOptionPane.showMessageDialog(null, "Η αναφορά αποθηκεύτηκε επιτυχώς");
	    }	
	}
	private void outputXMLButtonPressed(ActionEvent evt) {
		XMLReportWriter makeXMLFile = new XMLReportWriter(selectedSalesman);
		int returnVal =makeXMLFile.saveFile("xml");	
		if (returnVal == JFileChooser.APPROVE_OPTION) {
	        JOptionPane.showMessageDialog(null, "Η αναφορά αποθηκεύτηκε επιτυχώς");
	    }	
	}
	private void outputHTMLButtonPressed(ActionEvent evt) {
		HTMLReportWriter makeHTMLFile = new HTMLReportWriter(selectedSalesman);
		int returnVal =makeHTMLFile.saveFile("html");	
		if (returnVal == JFileChooser.APPROVE_OPTION) {
	        JOptionPane.showMessageDialog(null, "Η αναφορά αποθηκεύτηκε επιτυχώς");
	    }	
	}
	private void okButtonPressed(ActionEvent evt) {
		System.exit(0);		
	}
	
	private void cancelButtonPressed(ActionEvent evt) {
		selectionWindow.setVisible(true);
		this.dispose();	
	}
	
}
