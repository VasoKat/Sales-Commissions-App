package main.gui;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

import java.awt.Font;

import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

import main.data.Company;
import main.data.Receipt;
import main.data.Salesman;



public class ReportSelectionGui extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel selectionWindowPanel = new JPanel();
	private JTextField dateTextField;
	private JTextField kindTextField;
	private JTextField salesTextField;
	private JTextField itemsTextField;
	private JTextField companyTextField;
	private JTextField countryTextField;
	private JTextField cityTextField;
	private JTextField streetTextField;
	private JTextField receiptIDTextField;
	private JTextField numberTextField;
	private JTextField numOfReceiptsTextField;
	private JTextField SalesmanNameTextField;
	private int numOfReceipts = 0;
	private double totalSales;
	private JCheckBox totalSalesCheckBox;
	private JCheckBox totalItemsCheckBox;
	private JCheckBox commissionCheckBox;
	private JCheckBox categoryCheckBox;
	private JRadioButton skirtRadio;
	private JRadioButton shirtRadio;
	private JRadioButton trousersRadio;
	private JRadioButton coatRadio;
	private int totalItems;
	private float shirtSales;
	private float skirtSales;
	private float coatsSales;
	private float trousersSales;
	private double commission;
	private SalesCommissionsApp inputDialog;
	private Salesman selectedSalesman;
	@SuppressWarnings("unused")
	private String fileType;
	
	public ReportSelectionGui(SalesCommissionsApp dialog, Salesman salesman, String fileTypeFlag) {
		inputDialog = dialog;
		selectedSalesman = salesman;
		fileType = fileTypeFlag;
		initialise();
		
	}
	
	public JTextField initReceiptDataTextField(JTextField receiptDataTextField)
	{
		if (receiptDataTextField==dateTextField) {
			dateTextField=new JTextField();
			return dateTextField;
		}else if(receiptDataTextField==kindTextField) {
			kindTextField=new JTextField();
			return kindTextField;
		}else if(receiptDataTextField==salesTextField) {
			salesTextField=new JTextField();
			return salesTextField;
		}else if(receiptDataTextField==itemsTextField) {
			itemsTextField=new JTextField();
			return itemsTextField;
		}else if(receiptDataTextField==companyTextField) {
			companyTextField=new JTextField();
			return companyTextField;
		}else if(receiptDataTextField==countryTextField) {
			countryTextField=new JTextField();
			return countryTextField;
		}else if(receiptDataTextField==cityTextField) {
			cityTextField=new JTextField();
			return cityTextField;
		}else if(receiptDataTextField==streetTextField) {
			streetTextField=new JTextField();
			return streetTextField;
		}else if(receiptDataTextField==receiptIDTextField) {
			receiptIDTextField=new JTextField();
			return receiptIDTextField;
		}else if(receiptDataTextField==numberTextField) {
			numberTextField=new JTextField();
			return numberTextField;
		}
		return null;
		
	}
	
	public JCheckBox initReportDataCheckBox(JCheckBox reportDataCheckBox,String reportData)
	{
		if (reportDataCheckBox==totalSalesCheckBox) {
			totalSalesCheckBox=new JCheckBox(reportData);
			return totalSalesCheckBox;
		}else if(reportDataCheckBox==totalItemsCheckBox) {
			totalItemsCheckBox=new JCheckBox(reportData);
			return totalItemsCheckBox;
		}else if(reportDataCheckBox==categoryCheckBox) {
			categoryCheckBox=new JCheckBox(reportData);
			return categoryCheckBox;
		}else if(reportDataCheckBox==commissionCheckBox) {
			commissionCheckBox=new JCheckBox(reportData);
			return commissionCheckBox;
		}
		return null;
		
	}
	public JRadioButton initKindRadio(JRadioButton kindRadio,String kind)
	{
		if (kindRadio==skirtRadio) {
			skirtRadio=new JRadioButton(kind);
			return skirtRadio;
		}else if(kindRadio==shirtRadio) {
			shirtRadio=new JRadioButton(kind);
			return shirtRadio;
		}else if(kindRadio==trousersRadio) {
			trousersRadio=new JRadioButton(kind);
			return trousersRadio;
		}else if(kindRadio==coatRadio) {
			coatRadio=new JRadioButton(kind);
			return coatRadio;
		}
		return null;
		
	}
	public void displayReportField(JCheckBox reportDataCheckBox, String reportData, int[] bounds)
	{
		JCheckBox reportFieldCheckBox=initReportDataCheckBox(reportDataCheckBox,reportData);
		reportFieldCheckBox.setBackground(SystemColor.controlHighlight);;
		reportFieldCheckBox.setBounds(52, bounds[0], bounds[1], 25);
		reportFieldCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		getContentPane().add(reportFieldCheckBox);
	}
	public void displayKindRadio(JRadioButton kindRadio, int[] bounds)
	{
		
		kindRadio.setBackground(SystemColor.controlHighlight);
		kindRadio.setEnabled(false);
		kindRadio.setBounds(119, bounds[0], bounds[1], 25);
		kindRadio.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		getContentPane().add(kindRadio);
	}
	public void displayReceiptData(JTextField receiptDataTextField, int bound)
	{
		JTextField receiptData=initReceiptDataTextField(receiptDataTextField);
		receiptData.setVisible(false);
		receiptData.setBounds(543, bound, 133, 20);
		getContentPane().add(receiptData);
		receiptData.setColumns(10);
	}
	
	
	public JLabel addLabel(String receiptData,int[] bounds) {
		final JLabel receiptDataLabel = new JLabel(receiptData);
		receiptDataLabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		receiptDataLabel.setVisible(false);
		receiptDataLabel.setBounds(470, bounds[0], bounds[1], bounds[2]);
		getContentPane().add(receiptDataLabel);
		return receiptDataLabel;
	}
	
	public void createNavigationButton(String button,int[] bounds) {
		JButton NavigationButton = new JButton(button);
		NavigationButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		NavigationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(button.equals("OK")){
					okButtonPressed(evt);
				}else {	
					cancelButtonPressed(evt);
				}	
		
				
			}
		});
		NavigationButton.setBounds(bounds[0], 483, bounds[1], 32);
		getContentPane().add(NavigationButton);
	}
	
	public void addLabelForSalesman(String label,int[] bounds) {
		JLabel labelForSalesman = new JLabel(label);
		labelForSalesman.setFont(new Font("Tahoma", Font.BOLD, bounds[0]));
		labelForSalesman.setBounds(0, bounds[1], bounds[2], bounds[3]);
		getContentPane().add(labelForSalesman);
	}
	
	public void deselectKindRadio(JRadioButton kindRadio) {
		if(kindRadio.isSelected())
			kindRadio.setSelected(false);
	}
	
	public void initialise(){
		ArrayList<JLabel> receiptDataLabelList = new ArrayList<>();
		getContentPane().setBackground(SystemColor.controlHighlight);
		setBounds(100, 100, 717, 597);
		getContentPane().setLayout(null);
		selectionWindowPanel.setBounds(0, 0, 701, 1);
		selectionWindowPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(selectionWindowPanel);
		selectionWindowPanel.setLayout(null);
		displayReportField(totalSalesCheckBox,"\u03A3\u03C5\u03BD\u03BF\u03BB\u03B9\u03BA\u03AE \u03B1\u03BE\u03AF\u03B1 \u03C0\u03C9\u03BB\u03AE\u03C3\u03B5\u03C9\u03BD",new int[]{129, 169});
		displayReportField(totalItemsCheckBox,"\u03A3\u03C5\u03BD\u03BF\u03BB\u03B9\u03BA\u03CC\u03C2 \u03B1\u03C1\u03B9\u03B8\u03BC\u03CC\u03C2 \u03C0\u03C9\u03BB\u03AE\u03C3\u03B5\u03C9\u03BD",new int[]{170, 204});
		displayReportField(categoryCheckBox,"\u03A0\u03C9\u03BB\u03AE\u03C3\u03B5\u03B9\u03C2 \u03C3\u03C5\u03B3\u03BA\u03B5\u03BA\u03C1\u03B9\u03BC\u03AD\u03BD\u03B7\u03C2 \u03BA\u03B1\u03C4\u03B7\u03B3\u03BF\u03C1\u03AF\u03B1\u03C2",new int[]{214, 257});
		shirtRadio = initKindRadio(shirtRadio, "\u039C\u03C0\u03BB\u03BF\u03CD\u03B6\u03B5\u03C2");
		displayKindRadio(shirtRadio, new int[]{242, 125});
		trousersRadio = initKindRadio(trousersRadio, "\u03A0\u03B1\u03BD\u03C4\u03B5\u03BB\u03CC\u03BD\u03B9\u03B1");
		displayKindRadio(trousersRadio, new int[]{270, 125});
		coatRadio = initKindRadio(coatRadio, "\u03A0\u03B1\u03BB\u03C4\u03AC");
		displayKindRadio(coatRadio, new int[]{298, 125});
		skirtRadio = initKindRadio(skirtRadio, "\u03A6\u03BF\u03CD\u03C3\u03C4\u03B5\u03C2");
		displayKindRadio(skirtRadio, new int[]{326, 125});
		displayReportField(commissionCheckBox,"\u0395\u03BC\u03C6\u03AC\u03BD\u03B9\u03C3\u03B7 \u03A0\u03C1\u03BF\u03BC\u03AE\u03B8\u03B5\u03B9\u03B1\u03C2",new int[]{375, 204});
		
		final JToggleButton addReceiptToggleButton = new JToggleButton("\u03A0\u03C1\u03BF\u03C3\u03B8\u03AE\u03BA\u03B7 \u03BD\u03AD\u03B1\u03C2 \u03B1\u03C0\u03CC\u03B4\u03B5\u03B9\u03BE\u03B7\u03C2");
		addReceiptToggleButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addReceiptToggleButton.setBackground(new Color(135, 206, 235));
		
		addReceiptToggleButton.setBounds(411, 12, 265, 47);
		getContentPane().add(addReceiptToggleButton);
		displayReceiptData(dateTextField,120);
		displayReceiptData(kindTextField,163);
		displayReceiptData(salesTextField,208);
		displayReceiptData(itemsTextField,250);
		displayReceiptData(companyTextField,287);
		displayReceiptData(countryTextField,329);
		displayReceiptData(cityTextField,367);
		displayReceiptData(streetTextField,406);
		displayReceiptData(receiptIDTextField,81);
		displayReceiptData(numberTextField,445);
		JLabel receiptIDLabel=addLabel("Receipt ID:",new int[]{84, 71, 14});
		receiptDataLabelList.add(receiptIDLabel);
		JLabel dateLabel=addLabel("Date:",new int[]{123, 46, 14});
		receiptDataLabelList.add(dateLabel);
		JLabel kindLabel=addLabel("Kind:",new int[]{166, 46, 14});
		receiptDataLabelList.add(kindLabel);
		JLabel salesLabel=addLabel("Sales:",new int[]{214, 46, 14});
		receiptDataLabelList.add(salesLabel);
		JLabel itemsLabel=addLabel("Items:",new int[]{253, 46, 14});
		receiptDataLabelList.add(itemsLabel);
		JLabel companyLabel=addLabel("Company:",new int[]{284, 63, 14});
		receiptDataLabelList.add(companyLabel);
		JLabel countryLabel=addLabel("Country:",new int[]{329, 63, 14});
		receiptDataLabelList.add(countryLabel);
		JLabel cityLabel=addLabel("City:",new int[]{364, 46, 25});
		receiptDataLabelList.add(cityLabel);
		JLabel streetLabel=addLabel("Street:",new int[]{409, 46, 14});
		receiptDataLabelList.add(streetLabel);
		JLabel numberLabel=addLabel("Number:",new int[]{448, 63, 14});
		receiptDataLabelList.add(numberLabel);
	
		final JButton addReceiptButton = new JButton("\u03A0\u03C1\u03BF\u03C3\u03B8\u03AE\u03BA\u03B7");
		addReceiptButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		addReceiptButton.setVisible(false);
		addReceiptButton.setBounds(519, 482, 105, 39);
		getContentPane().add(addReceiptButton);
		
		JLabel lblNewLabel_10 = new JLabel("\u0391\u03C0\u03BF\u03B4\u03B5\u03AF\u03BE\u03B5\u03B9\u03C2 \u03C0\u03BF\u03C5 \u03C0\u03C1\u03BF\u03C3\u03B8\u03AD\u03B8\u03B7\u03BA\u03B1\u03BD:");
		lblNewLabel_10.setBounds(469, 532, 157, 14);
		getContentPane().add(lblNewLabel_10);
		
		numOfReceiptsTextField = new JTextField();
		numOfReceiptsTextField.setText("0");
		numOfReceiptsTextField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		numOfReceiptsTextField.setBounds(636, 528, 40, 20);
		getContentPane().add(numOfReceiptsTextField);
		numOfReceiptsTextField.setColumns(10);
		createNavigationButton("OK",new int[]{40, 89});
		createNavigationButton("Cancel",new int[]{147, 94});
		addLabelForSalesman("\u0395\u03C0\u03B9\u03BB\u03AD\u03BE\u03C4\u03B5 \u03C0\u03BB\u03B7\u03C1\u03BF\u03C6\u03BF\u03C1\u03AF\u03B5\u03C2 \u03C0\u03C1\u03BF\u03C2 \u03B5\u03BC\u03C6\u03AC\u03BD\u03B9\u03C3\u03B7",new int[]{12, 75, 271, 29});
		addLabelForSalesman("\u0391\u03BD\u03C4\u03B9\u03C0\u03C1\u03CC\u03C3\u03C9\u03C0\u03BF\u03C2:",new int[]{16,8, 129, 25});
		
		SalesmanNameTextField = new JTextField();
		SalesmanNameTextField.setBackground(SystemColor.controlHighlight);
		SalesmanNameTextField.setEditable(false);
		SalesmanNameTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		SalesmanNameTextField.setBounds(135, 4, 174, 32);
		getContentPane().add(SalesmanNameTextField);
		SalesmanNameTextField.setColumns(10);
		
		categoryCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				skirtRadio.setEnabled(true);
				shirtRadio.setEnabled(true);
				coatRadio.setEnabled(true);
				trousersRadio.setEnabled(true);
				if(categoryCheckBox.isSelected()==false){
					deselectKindRadio(skirtRadio);
					deselectKindRadio(shirtRadio);
					deselectKindRadio(coatRadio);
					deselectKindRadio(trousersRadio);
					
					skirtRadio.setEnabled(false);
					shirtRadio.setEnabled(false);
					coatRadio.setEnabled(false);
					trousersRadio.setEnabled(false);
				}
			}
		});
		
		addReceiptToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				receiptDataLabelList.get(0).setVisible(true);receiptIDTextField.setVisible(true);
				receiptDataLabelList.get(1).setVisible(true);dateTextField.setVisible(true);
				receiptDataLabelList.get(2).setVisible(true);kindTextField.setVisible(true);
				receiptDataLabelList.get(3).setVisible(true);salesTextField.setVisible(true);
				receiptDataLabelList.get(4).setVisible(true);itemsTextField.setVisible(true);
				receiptDataLabelList.get(5).setVisible(true);companyTextField.setVisible(true);
				receiptDataLabelList.get(6).setVisible(true);countryTextField.setVisible(true);
				receiptDataLabelList.get(7).setVisible(true);streetTextField.setVisible(true);
				receiptDataLabelList.get(8).setVisible(true);cityTextField.setVisible(true);
				receiptDataLabelList.get(9).setVisible(true);numberTextField.setVisible(true);
				addReceiptButton.setVisible(true);
				if(addReceiptToggleButton.isSelected()==false){
					receiptDataLabelList.get(0).setVisible(false);receiptIDTextField.setVisible(false);
					receiptDataLabelList.get(1).setVisible(false);dateTextField.setVisible(false);
					receiptDataLabelList.get(2).setVisible(false);kindTextField.setVisible(false);
					receiptDataLabelList.get(3).setVisible(false);salesTextField.setVisible(false);
					receiptDataLabelList.get(4).setVisible(false);itemsTextField.setVisible(false);
					receiptDataLabelList.get(5).setVisible(false);companyTextField.setVisible(false);
					receiptDataLabelList.get(6).setVisible(false);countryTextField.setVisible(false);
					receiptDataLabelList.get(7).setVisible(false);streetTextField.setVisible(false);
					receiptDataLabelList.get(8).setVisible(false);cityTextField.setVisible(false);
					receiptDataLabelList.get(9).setVisible(false);numberTextField.setVisible(false);
					addReceiptButton.setVisible(false);
				}
			}
		});
		
		addReceiptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			
				addReceiptButtonPressed(evt);
				
			}
		});
		
		try{
			SalesmanNameTextField.setText(selectedSalesman.getName());
		}catch(NullPointerException e){
			
			JOptionPane.showMessageDialog(null,"Προέκυψε κάποιο πρόβλημα, προσπαθήστε ξανά");

		}
		
		
	}
	
	protected float calculateSalesClothingKind(JRadioButton shirtRadio,String clothingKind){
		float sales;
		if (shirtRadio.isSelected()) {
			sales = selectedSalesman.calculateSales(clothingKind);
		}else {
			sales = -1;
		}
		return (float) sales;
	}
	
	protected double calculateReportData(JCheckBox checkBox,String calculationType){
		double sales;
		if (checkBox.isSelected() && calculationType=="sales") {
			sales = selectedSalesman.calculateTotalSales();
		}else if(checkBox.isSelected() && calculationType=="commission"){
			sales = selectedSalesman.calculateCommission();
		}else if(checkBox.isSelected() && calculationType=="items"){
			sales = selectedSalesman.calculateTotalItems();
		}else {	
			sales = -1;
		}
		return sales;
	}
	
	protected void okButtonPressed(ActionEvent evt) {
		totalSales = calculateReportData(totalSalesCheckBox,"sales");
		totalItems = (int) calculateReportData(totalItemsCheckBox,"items");		
		shirtSales =calculateSalesClothingKind(shirtRadio,"Shirt");
		skirtSales = calculateSalesClothingKind(skirtRadio,"Skirt");
		coatsSales = calculateSalesClothingKind(coatRadio,"Coat");
		trousersSales = calculateSalesClothingKind(trousersRadio,"Trouser");
		commission = calculateReportData(commissionCheckBox,"commission");
		ReportExporterGui reportExporterGui = new ReportExporterGui(this,selectedSalesman, totalSales, totalItems, shirtSales, skirtSales, trousersSales, coatsSales, commission);
		reportExporterGui.setVisible(true);
		this.setVisible(false);		
	}
	
	
	private void addReceiptButtonPressed(ActionEvent evt) {
		int emptyFieldCounter=0;
		JTextField[] receiptFields = {dateTextField,kindTextField,salesTextField,itemsTextField,companyTextField,countryTextField,cityTextField,streetTextField,numberTextField};
		for(int i=0;i<receiptFields.length;i++) {
			if (receiptFields[i].getText().isEmpty()) {
				emptyFieldCounter++;
			}
		}
		if(emptyFieldCounter==receiptFields.length) {
			JOptionPane.showMessageDialog(null,"Πρέπει να συμπληρώσετε όλα τα πεδία");
		}
		else{
			 
			if(addReceipt()==1) {
				appendFile();
			}	
			
		}
		receiptIDTextField.setText("");	
		dateTextField.setText("");			
		kindTextField.setText("");	
		salesTextField.setText("");
		itemsTextField.setText("");	
		companyTextField.setText("");	
		countryTextField.setText("");	
		cityTextField.setText("");	
		streetTextField.setText("");	
		numberTextField.setText("");	

	}

	private void appendFile(){
		
		Receipt receipt= new Receipt();

		receipt.setReceiptID(Integer.parseInt(receiptIDTextField.getText()));
		receipt.setDate(dateTextField.getText());
		receipt.setKind(kindTextField.getText());
		receipt.setSales(Double.parseDouble(salesTextField.getText()));
		receipt.setItems(Integer.parseInt(itemsTextField.getText()));
		Company company=new Company();
		company.setName(companyTextField.getText());
		receipt.setCompany(company);
		receipt.setCountry(countryTextField.getText());
		receipt.setCity(cityTextField.getText());
		receipt.setStreet(streetTextField.getText());
		receipt.setNumber(Integer.parseInt(numberTextField.getText()));
		
		selectedSalesman.getReceiptFileUpdater().setReceipt(receipt);
		selectedSalesman.getReceiptFileUpdater().appendFile();
	}
	
	private int addReceipt(){
		
		try{
			Receipt receipt = new Receipt();
			
			if(kindTextField.getText().equals("Shirts")) {		
				receipt.setKind("Shirt");
				
			}else if (kindTextField.getText().equals("Skirts")) {
				receipt.setKind("Skirt");
			}else if (kindTextField.getText().equals("Trousers")) {
				receipt.setKind("Trouser");
			}else if(kindTextField.getText().equals("Coats")) {		
				receipt.setKind("Coat");
			} else {
	            JOptionPane.showMessageDialog(null, "Δεν συμπληρώσατε σωστά κάποιο πεδίο, προσπαθήστε ξανά");
	            return 0; 
	        }
	
			receipt.setReceiptID(Integer.parseInt(receiptIDTextField.getText()));			
			receipt.setDate(dateTextField.getText());
			receipt.setSales(Double.parseDouble(salesTextField.getText()));
			receipt.setItems(Integer.parseInt(itemsTextField.getText()));
			receipt.getCompany().setName(companyTextField.getText());
			receipt.getCompany().getCompanyAddress().setCountry(countryTextField.getText());
			receipt.getCompany().getCompanyAddress().setCity(cityTextField.getText());
			receipt.getCompany().getCompanyAddress().setStreet(streetTextField.getText());
			receipt.getCompany().getCompanyAddress().setStreetNumber(Integer.parseInt(numberTextField.getText()));
			selectedSalesman.getReceipts().add(receipt);
			numOfReceipts++;
			numOfReceiptsTextField.setText(Integer.toString(numOfReceipts));
			JOptionPane.showMessageDialog(null,"Η απόδειξη προσθέθηκε επιτυχώς");
			return 1;
		}catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null,"Δεν συμπληρώσατε σωστά κάποιο πεδίο, προσπαθήστε ξανά");
			return 0;

		}
	}
	
	private void cancelButtonPressed(ActionEvent evt) {		
		dispose();
		inputDialog.setVisible(true);		
	}
	
	
}
