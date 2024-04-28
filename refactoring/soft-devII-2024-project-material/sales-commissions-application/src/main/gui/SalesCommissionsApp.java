package main.gui;
import main.input.HTMLInput;
import main.input.TXTInput;
import main.input.XMLInput;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.LayoutStyle.ComponentPlacement;

import main.data.Salesman;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SalesCommissionsApp extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel inputWindowPanel = new JPanel();
	private DefaultListModel <String> listModel = new DefaultListModel <String>();
	private JList <String> salesmansList = new JList <String>();
	private ArrayList <Salesman> allSalesmans;
	private  Salesman salesman = new Salesman();
	private Salesman selectedSalesman = null;
	static SalesCommissionsApp dialog = new SalesCommissionsApp();
	@SuppressWarnings("unused")
	private File inputFile;
	private String fileTypeFlag;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
			dialog.setVisible(true);
			
			javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels = javax.swing.UIManager.getInstalledLookAndFeels();
			for (int i = 0; i < installedLookAndFeels.length; i++) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SalesCommissionsApp(){
		
		initialise();
	}
	
	public JButton createInsertFileButton(String fileType) {
		JButton buttonInput = new JButton("\u0395\u03B9\u03C3\u03B1\u03B3\u03C9\u03B3\u03AE \u03B1\u03C0\u03CC "+fileType);
		buttonInput.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonInput.setBackground(UIManager.getColor("InternalFrame.borderLight"));
		buttonInput.setFocusPainted(false);
		return buttonInput;
	}
	
	public JLabel addLabelForInputWindow(String label,int fontSize) {
		JLabel labelForInputWindow = new JLabel(label);
		labelForInputWindow.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
		return labelForInputWindow;
	}
	
	public JButton createNavigationButton(String button) {
		JButton navigationButton = new JButton(button);
		navigationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (button=="OK") {
					okButtonPressed(evt);		
				}else if(button=="Cancel") { 	
					cancelButtonPressed(evt);
				}
			}

		
		});
		if (button=="OK") {
			navigationButton.setToolTipText("");
		}	
		navigationButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		navigationButton.setBackground(UIManager.getColor("Button.shadow"));
		return navigationButton;
	}
	
	public void attachFileTypeListener(JButton buttonInput,String fileType) {
		buttonInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (fileType=="txt") {
					insertFromTXT(evt);
				}else if(fileType=="xml") {	
					insertFromXML(evt);
				}else if (fileType=="html") {
					insertFromHTML(evt);
				}
			}
		});
	}
	
	public void initialise() {
		allSalesmans = new ArrayList <Salesman>();
		
		setBackground(new Color(0, 0, 0));
		setBounds(100, 100, 736, 472);
		getContentPane().setLayout(new BorderLayout());
		inputWindowPanel.setBackground(SystemColor.controlHighlight);
		inputWindowPanel.setBorder(null);
		getContentPane().add(inputWindowPanel, BorderLayout.CENTER);
		
				JButton buttonTXTInput =createInsertFileButton("TXT");
				JButton buttonXMLInput =createInsertFileButton("XML");
				JButton buttonHTMLInput =createInsertFileButton("HTML");
				JLabel label =addLabelForInputWindow("\u0395\u03C0\u03B9\u03BB\u03AD\u03BE\u03C4\u03B5 \u03B5\u03AF\u03B4\u03BF\u03C2 \u03B1\u03C1\u03C7\u03B5\u03AF\u03BF\u03C5 \u03B3\u03B9\u03B1 \u03C6\u03CC\u03C1\u03C4\u03C9\u03C3\u03B7 \u03B1\u03C0\u03BF\u03B4\u03B5\u03AF\u03BE\u03B5\u03C9\u03BD:",14);
			
				salesmansList.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						selectSalesman(e);
					}
				});
				
				salesmansList.setFont(new Font("Times New Roman", Font.PLAIN, 19));
				salesmansList.setBackground(UIManager.getColor("Button.light"));
				salesmansList.setBorder(new LineBorder(new Color(0, 0, 0)));
				
				JLabel listOfSalesmenLabel =addLabelForInputWindow("\u039B\u03B9\u03C3\u03C4\u03B1 \u0391\u03BD\u03C4\u03B9\u03C0\u03C1\u03BF\u03C3\u03CE\u03C0\u03C9\u03BD",16);
				
				JButton okButton =createNavigationButton("OK");
				JButton cancelButton =createNavigationButton("Cancel");
				GroupLayout gl_inputWindowPanel = new GroupLayout(inputWindowPanel);
				gl_inputWindowPanel.setHorizontalGroup(
					gl_inputWindowPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_inputWindowPanel.createSequentialGroup()
							.addGroup(gl_inputWindowPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_inputWindowPanel.createSequentialGroup()
									.addGap(258)
									.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_inputWindowPanel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(label)
									.addComponent(buttonTXTInput, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
									.addComponent(buttonHTMLInput, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
									.addComponent(buttonXMLInput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(18)
							.addGroup(gl_inputWindowPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(salesmansList, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
								.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(57, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_inputWindowPanel.createSequentialGroup()
							.addContainerGap(453, Short.MAX_VALUE)
							.addComponent(listOfSalesmenLabel)
							.addGap(143))
				);
				gl_inputWindowPanel.setVerticalGroup(
					gl_inputWindowPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_inputWindowPanel.createSequentialGroup()
							.addGap(23)
							.addComponent(label)
							.addGap(11)
							.addComponent(listOfSalesmenLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_inputWindowPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_inputWindowPanel.createSequentialGroup()
									.addComponent(buttonTXTInput, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addGap(42)	
									.addComponent(buttonHTMLInput, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addGap(42)
									.addComponent(buttonXMLInput, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
								.addComponent(salesmansList, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
							.addGap(40)
							.addGroup(gl_inputWindowPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(25, Short.MAX_VALUE))
				);
				inputWindowPanel.setLayout(gl_inputWindowPanel);
				attachFileTypeListener(buttonTXTInput,"txt");
				attachFileTypeListener(buttonXMLInput,"xml");
				attachFileTypeListener(buttonHTMLInput,"html");
	}
	
	
	private void cancelButtonPressed(ActionEvent e) {
		System.exit(0);	
	}

private void insertFromHTML(ActionEvent evt){
		
		JFileChooser HTMLFileChooser;
		HTMLFileChooser = new JFileChooser();     
		HTMLFileChooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);		       
		HTMLFileChooser.showOpenDialog(null);
		boolean salesmanDuplicate = false;
		try{
			File receiptFileHTML = HTMLFileChooser.getSelectedFile();
			HTMLInput inputFileHTML = new HTMLInput(receiptFileHTML);	
			inputFileHTML.readFile();
			salesman = inputFileHTML.getSalesman();
			salesman.setFileType("HTML");
			salesman.getReceiptFileUpdater().setFileToAppend(receiptFileHTML);				
			allSalesmans.add(salesman);
			for(int i = 0; i< listModel.getSize(); i++){
				if(salesman.getName().equals(listModel.getElementAt(i))){
					salesmanDuplicate = true;

				}
			}
			if(salesmanDuplicate == true){
				JOptionPane.showMessageDialog(null,"Ο αντιπρόσωπος υπάρχει ήδη στη λίστα");

			}
			else{
				listModel.addElement(salesman.getName());
				salesmansList.setModel(listModel);
				fileTypeFlag = "HTML";
			}
			
		}catch (NullPointerException e){
			
			JOptionPane.showMessageDialog(null,"Δεν επιλέχθηκε κανένα αρχείο");

		}catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null,"Προέκυψε κάποιο πρόβλημα κατά την ανάγνωση του αρχείου");
		}
		
	}
	
	private void insertFromTXT(ActionEvent evt){
		
		JFileChooser TXTFileChooser;
		TXTFileChooser = new JFileChooser();     
		TXTFileChooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);		       
		TXTFileChooser.showOpenDialog(null);
		boolean salesmanDuplicate = false;
		try{
			File receiptFileTXT = TXTFileChooser.getSelectedFile();
			TXTInput inputFileTXT = new TXTInput(receiptFileTXT);	
			inputFileTXT.readFile();
			salesman = inputFileTXT.getSalesman();
			salesman.setFileType("TXT");
			salesman.getReceiptFileUpdater().setFileToAppend(receiptFileTXT);				
			allSalesmans.add(salesman);
			for(int i = 0; i< listModel.getSize(); i++){
				if(salesman.getName().equals(listModel.getElementAt(i))){
					salesmanDuplicate = true;

				}
			}
			if(salesmanDuplicate == true){
				JOptionPane.showMessageDialog(null,"Ο αντιπρόσωπος υπάρχει ήδη στη λίστα");

			}
			else{
				listModel.addElement(salesman.getName());
				salesmansList.setModel(listModel);
				fileTypeFlag = "TXT";
			}
			
		}catch (NullPointerException e){
			
			JOptionPane.showMessageDialog(null,"Δεν επιλέχθηκε κανένα αρχείο");

		}catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null,"Προέκυψε κάποιο πρόβλημα κατά την ανάγνωση του αρχείου");
		}
		
	}
	
	private void insertFromXML(ActionEvent evt2){
		JFileChooser XMLFileChooser;
		XMLFileChooser = new JFileChooser();     
		XMLFileChooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);		       
		XMLFileChooser.showOpenDialog(null);
		boolean salesmanDuplicate = false;
		try{
			File receiptFileXML = XMLFileChooser.getSelectedFile();
			XMLInput inputFileXML = new XMLInput(receiptFileXML);	
			inputFileXML.readFile();
			salesman = inputFileXML.getSalesman();
			salesman.setFileType("XML");
			salesman.getReceiptFileUpdater().setFileToAppend(receiptFileXML);				
			allSalesmans.add(salesman);
			for(int i = 0; i< listModel.getSize(); i++){
				if(salesman.getName().equals(listModel.getElementAt(i))){
					salesmanDuplicate = true;

				}
			}
			if(salesmanDuplicate == true){
				JOptionPane.showMessageDialog(null,"Ο αντιπρόσωπος υπάρχει ήδη στη λίστα");

			}
			else{
				listModel.addElement(salesman.getName());
				salesmansList.setModel(listModel);
				fileTypeFlag = "XML";
			}
		}catch (IllegalArgumentException e){
		
			JOptionPane.showMessageDialog(null,"Δεν επιλέχθηκε κανένα αρχείο");

		}
        
             
	}
	
	
	private void selectSalesman(MouseEvent e){
		
		String salesmanName;
        if(salesmansList.getSelectedIndex()>=0){
        	
        	salesmanName = salesmansList.getSelectedValue().toString();
            for(int i=0; i<allSalesmans.size(); i++){
                if(salesmanName.equals(allSalesmans.get(i).getName())){
                	
                		selectedSalesman = allSalesmans.get(i);
                		break;
                		
                }
            }
        	
        }
	}
	
	private void okButtonPressed(ActionEvent evt) {
		if(salesmansList.isSelectionEmpty()){
			JOptionPane.showMessageDialog(null,"Δεν έχετε επιλέξει αντιπρόσωπο");

		}
		else{
			ReportSelectionGui sw = new ReportSelectionGui(dialog,selectedSalesman,fileTypeFlag);
			this.setVisible(false);
			sw.setVisible(true);
		}	
	}	

}
