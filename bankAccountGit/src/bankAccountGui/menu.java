package bankAccountGui;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class menu extends JFrame{
	
	File userF;
	FileWriter fWriter;
	FileReader fReader;
	BufferedWriter fW;
	BufferedReader fR;
	DecimalFormat dF = new DecimalFormat("#0.00");
	JPanel overviewPanel;
	JPanel withdrawPanel;
	JPanel depositPanel;
	JPanel accountPanel;
	JTabbedPane options;
	private JLabel lblWelcome;
	private JLabel lblYourBalanceIs;
	private JLabel lblLastLogin;
	private JButton btnReturnToLogin;
	
	String fileContent = "";
	private JButton btnDeposit;
	private JLabel lblDepositBalance;
	private JLabel lblDeposit;
	private JTextField depositTextField;
	
	private JButton btnWithdraw;
	private JLabel lblWithdraw;
	private JLabel lblWithdrawBalance;
	private JTextField withdrawTextField;
	
	double depositAmount = 0;
	double withdrawAmount = 0;
	private JPasswordField passwordPane;
	private JTextField usernamePane;
	
	private JCheckBox fNameCheckBox;
	private JCheckBox lNameCheckBox;
	private JCheckBox usernameCheckBox;
	private JCheckBox passwordCheckBox;
	
	boolean fNameChecked = false;
	boolean lNameChecked = false;
	boolean usernameChecked = false;
	boolean passwordChecked = false;
	
	public menu(File userFile) {
		
		super("Menu");
		
		this.userF = userFile;
		
		options = new JTabbedPane();

		overviewPanel = new JPanel(null);
		depositPanel = new JPanel(null);
		withdrawPanel = new JPanel(null);
		
//--------------------------------------------------------------------------------------------------------------------------------------------------
		//Overview Panel
		
		lblWelcome = new JLabel("Welcome " + getFirstName() + "!");
		lblWelcome.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 30));
		int welcomeWidth = (int) lblWelcome.getPreferredSize().getWidth();
		
		lblWelcome.setBounds((int)Math.abs(250-((welcomeWidth/2))), 10, welcomeWidth, 41);
		
		lblYourBalanceIs = new JLabel("Your current balance is: $" + getBalance());
		lblYourBalanceIs.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		int balanceWidth = (int) lblYourBalanceIs.getPreferredSize().getWidth();
		lblYourBalanceIs.setBounds((int)Math.abs(250-(balanceWidth/2)), 45, balanceWidth, 41);
		
		lblLastLogin = new JLabel();
		
		btnReturnToLogin = new JButton("Return to Login");
		btnReturnToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				openLogin();
				
			}
		});
		
		overviewPanel.add(btnReturnToLogin);
		
		if(getLastLogin() != null){
			
			lblLastLogin.setText("Your last login was on " + getLastLogin());
			btnReturnToLogin.setBounds(250-((int)btnReturnToLogin.getPreferredSize().getWidth()/2), 169, (int)btnReturnToLogin.getPreferredSize().getWidth(), 25);


		}else if (getLastLogin() == null){
			
			btnReturnToLogin.setBounds(250-((int)btnReturnToLogin.getPreferredSize().getWidth()/2), 100, (int)btnReturnToLogin.getPreferredSize().getWidth(), 25);
			
		}
		lblLastLogin.setFont(new Font("Yu Gothic UI Light", Font.BOLD | Font.ITALIC, 18));
		lblLastLogin.setBounds((int)Math.abs(250-(lblLastLogin.getPreferredSize().getWidth()/2)), 90, 
				(int)lblLastLogin.getPreferredSize().getWidth() + 20, 41);
		
		overviewPanel.add(lblWelcome);
		overviewPanel.add(lblYourBalanceIs);
		overviewPanel.add(lblLastLogin);
		
//-----------------------------------------------------------------------------------------------------------------------------------------------------------
		//Deposit Panel
				
		btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds((250-((int)btnDeposit.getPreferredSize().getWidth()/2)), 169, (int)btnDeposit.getPreferredSize().getWidth(), 25);
		
		lblDepositBalance = new JLabel("Your current balance is: $" + getBalance());
		lblDepositBalance.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblDepositBalance.setBounds((250-((int)lblDepositBalance.getPreferredSize().getWidth()/2)), 24, (int)lblDepositBalance.getPreferredSize().getWidth(), 41);
		
		lblDeposit = new JLabel("Deposit: ");
		lblDeposit.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		lblDeposit.setBounds(150, 101, 97, 16);

		depositTextField = new JTextField();
		depositTextField.setBounds(225, 97, 50, 22);
		depositTextField.setColumns(10);
		
		depositTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				deposit();
				
			}
		});
		
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				deposit();
				
			}
		});
		
		depositPanel.add(depositTextField);
		depositPanel.add(lblDeposit);
		depositPanel.add(lblDepositBalance);
		depositPanel.add(btnDeposit);
		
//-------------------------------------------------------------------------------------------------------------------------------------------------------------
		//Withdraw Panel
		btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(213, 169, (int)btnWithdraw.getPreferredSize().getWidth(), 25);
		
		lblWithdrawBalance = new JLabel("Your current balance is: $" + getBalance());
		lblWithdrawBalance.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblWithdrawBalance.setBounds((250 - ((int)lblWithdrawBalance.getPreferredSize().getWidth()/2)), 24, (int)lblWithdrawBalance.getPreferredSize().getWidth(), 41);
		
		lblWithdraw = new JLabel("Withdraw: ");
		lblWithdraw.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		lblWithdraw.setBounds(125, 101, 97, 16);
		
		withdrawTextField = new JTextField();
		withdrawTextField.setBounds(225, 97, 50, 22);
		withdrawTextField.setColumns(10);
		
		withdrawTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				withdraw();
				
			}
		});
		
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				withdraw();
				
			}
		});
		
		withdrawPanel.add(withdrawTextField);
		withdrawPanel.add(lblWithdraw);
		withdrawPanel.add(lblWithdrawBalance);
		withdrawPanel.add(btnWithdraw);
//-------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		options.add("Home", overviewPanel);
		options.add("Deposit", depositPanel);
		options.add("Withdraw", withdrawPanel);
		
		getContentPane().add(options);
		accountPanel = new JPanel(null);
		options.add("Account", accountPanel);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		lblSettings.setBounds(194, 13, 134, 40);
		accountPanel.add(lblSettings);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(53, 80, 85, 16);
		accountPanel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(261, 80, 97, 16);
		accountPanel.add(lblLastName);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(53, 144, 100, 16);
		accountPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(261, 144, 97, 16);
		accountPanel.add(lblPassword);
		
		passwordPane = new JPasswordField();
		passwordPane.setEditable(false);
		passwordPane.setBounds(338, 141, 97, 22);
		passwordPane.setText(getPassword());
		accountPanel.add(passwordPane);
		
		JTextField fNamePane = new JTextField();
		fNamePane.setEditable(false);
		fNamePane.setText(getFirstName());
		fNamePane.setBounds(126, 80, 93, 22);
		accountPanel.add(fNamePane);
		
		JTextField lNamePane = new JTextField();
		lNamePane.setEditable(false);
		lNamePane.setText(getLastName());
		lNamePane.setBounds(338, 80, 93, 22);
		accountPanel.add(lNamePane);
		
		usernamePane = new JTextField();
		usernamePane.setEditable(false);
		usernamePane.setText(getUsername());
		usernamePane.setBounds(126, 141, 93, 22);
		accountPanel.add(usernamePane);
		
		fNameCheckBox = new JCheckBox("");
		fNameCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				if(fNameCheckBox.isSelected() == true){
					
					fNamePane.setEditable(true);
					fNameChecked = false;
					
				}else{
										
					if(fNameChecked == false && !fNamePane.getText().equals(getFirstName())){
						
					int confirmChange =	JOptionPane.showConfirmDialog(null, "Are you sure you would like to change your first name to: " + fNamePane.getText());
					fNameChecked = true;
					fNameCheckBox.setSelected(false);
						
					}
					
					fNamePane.setEditable(false);

					
				}
			}
		});
		fNameCheckBox.setBounds(24, 77, 25, 25);
		accountPanel.add(fNameCheckBox);
		
		lNameCheckBox = new JCheckBox("");
		lNameCheckBox.setBounds(232, 77, 25, 25);
		lNameCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				if(lNameCheckBox.isSelected() == true){
					
					lNamePane.setEditable(true);
					
				}else{
					
					lNamePane.setEditable(false);
					
				}
			}
		});
		accountPanel.add(lNameCheckBox);
		
		usernameCheckBox = new JCheckBox("");
		usernameCheckBox.setBounds(24, 141, 25, 25);
		usernameCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				if(usernameCheckBox.isSelected() == true){
					
					usernamePane.setEditable(true);
					
				}else{
					
					usernamePane.setEditable(false);
					
				}
			}
		});
		accountPanel.add(usernameCheckBox);
		
		passwordCheckBox = new JCheckBox("");
		passwordCheckBox.setBounds(232, 141, 25, 25);
		passwordCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				if(passwordCheckBox.isSelected() == true){
					
					passwordPane.setEditable(true);
					
				}else{
					
					passwordPane.setEditable(false);
					
				}
			}
		});
		accountPanel.add(passwordCheckBox);
		
		setRecentLoginDate();
		
	}
	
	public void withdraw(){
		
		double balance = Double.parseDouble(getBalance());
		boolean validNumber = true;
		
		String currentLine = "";
		fileContent = "";
		
		try{
		
		fReader = new FileReader(userF);
		fR = new BufferedReader(fReader);
		
		withdrawAmount = Double.parseDouble(withdrawTextField.getText());
		withdrawTextField.setText(null);

		withdrawAmount *= 100; withdrawAmount = (int)withdrawAmount; withdrawAmount /= 100;
		
		if(withdrawAmount <= balance && withdrawAmount != 0){
		
		int confirmResponse = JOptionPane.showConfirmDialog(null, "Are you sure you would like to withdraw $" + dF.format(withdrawAmount) + "?", "Confirm", 0);

		if(confirmResponse == 0){
			
		
		while((currentLine = fR.readLine()) != null){
			
			fileContent += currentLine + System.lineSeparator();
			
		}
		
		balance -= withdrawAmount;
		
		fileContent = fileContent.replace(fileContent.substring(fileContent.indexOf("Balance: "), fileContent.indexOf("Account created")), 
				"Balance: " + dF.format(balance) + System.lineSeparator());
		
		
		
		JOptionPane.showMessageDialog(null, "$" + dF.format(withdrawAmount) + " withdrawn!", "Withdraw completed", JOptionPane.INFORMATION_MESSAGE);
		
		}else{
			
			JOptionPane.showMessageDialog(null, "Withdraw cancelled", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
			validNumber = false;
			
		}
		
		}else if (withdrawAmount > balance && (withdrawAmount != 0) ){
			
			JOptionPane.showMessageDialog(null, "$" + dF.format(withdrawAmount) + " is greater than your current balance of $" + dF.format(balance), "Error", JOptionPane.ERROR_MESSAGE);
			validNumber = false;
			
		}else if(withdrawAmount == 0){
			
			JOptionPane.showMessageDialog(null, " Please enter a value greater than $0.00 to withdraw!", "Error", JOptionPane.ERROR_MESSAGE);
			validNumber = false;
			
		}
				
		}catch(Exception e1){
			
			JOptionPane.showMessageDialog(null, "Please enter numbers only in the box!", "Error", JOptionPane.ERROR_MESSAGE);
			validNumber = false;
			
		}finally{
			
			try {
				
				fReader.close();
				fR.close();
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		try{
			
			if(validNumber == true){
			fWriter = new FileWriter(userF, false);
			fW = new BufferedWriter(fWriter);
			
			fW.write(fileContent);
			fW.flush();
			
			lblDepositBalance.setText("Your current balance is: $" + getBalance());
			lblDepositBalance.setBounds((250 - ((int)lblDepositBalance.getPreferredSize().getWidth()/2)), 24, (int)lblDepositBalance.getPreferredSize().getWidth(), 41);

			lblWithdrawBalance.setText("Your current balance is: $" + getBalance());
			lblWithdrawBalance.setBounds((250 - ((int)lblWithdrawBalance.getPreferredSize().getWidth()/2)), 24, (int)lblWithdrawBalance.getPreferredSize().getWidth(), 41);

			lblYourBalanceIs.setText("Your current balance is: $" + getBalance());
			lblYourBalanceIs.setBounds((int)Math.abs(250-(lblYourBalanceIs.getPreferredSize().getWidth()/2)), 45, (int)lblYourBalanceIs.getPreferredSize().getWidth(), 41);
			
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			
			if(validNumber == true){
			
			try {
				
				fWriter.close();
				fW.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
		}
			
	}
	
	public void deposit(){
		
		double balance = Double.parseDouble(getBalance());
		
		String currentLine = "";
		fileContent = "";
		int confirmResponse = 0;
		boolean isNumber = true;
		
		try{
			
		fReader = new FileReader(userF);
		fR = new BufferedReader(fReader);
		
		depositAmount = Double.parseDouble(depositTextField.getText());
		depositTextField.setText(null);
		depositAmount *= 100; depositAmount = (int)depositAmount; depositAmount/= 100;
		confirmResponse = JOptionPane.showConfirmDialog(null, "Are you sure you would like to deposit $" + dF.format(depositAmount) + "?", "Confirm", 0);

		if(confirmResponse == 0){
			
		while((currentLine = fR.readLine()) != null){
			
			fileContent += currentLine + System.lineSeparator();
			
		}
			
		balance += depositAmount;
		
		fileContent =fileContent.replace(fileContent.substring(fileContent.indexOf("Balance: "), fileContent.indexOf("Account created")), 
				"Balance: " + dF.format(balance) + System.lineSeparator());
		
		}
		
		}catch(Exception e){
			
			JOptionPane.showMessageDialog(null, "Please enter numbers only in the box!" , "Error", JOptionPane.ERROR_MESSAGE);
			isNumber = false;
			
		}finally{
			
			try {
				
				fReader.close();
				fR.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(confirmResponse == 0 && isNumber == true){
					
		try{
		
		fWriter = new FileWriter(userF, false);
		fW = new BufferedWriter(fWriter);
		
		fW.write(fileContent);
		fW.flush();
		
		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			
			try {
				
				fWriter.close();
				fW.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		lblDepositBalance.setText("Your current balance is: $" + getBalance());
		lblDepositBalance.setBounds((250 - ((int)lblDepositBalance.getPreferredSize().getWidth()/2)), 24, (int)lblDepositBalance.getPreferredSize().getWidth(), 41);

		lblWithdrawBalance.setText("Your current balance is: $" + getBalance());
		lblWithdrawBalance.setBounds((250 - ((int)lblWithdrawBalance.getPreferredSize().getWidth()/2)), 24, (int)lblWithdrawBalance.getPreferredSize().getWidth(), 41);

		lblYourBalanceIs.setText("Your current balance is: $" + getBalance());
		lblYourBalanceIs.setBounds((int)Math.abs(250-(lblYourBalanceIs.getPreferredSize().getWidth()/2)), 45, (int)lblYourBalanceIs.getPreferredSize().getWidth(), 41);

		
		JOptionPane.showMessageDialog(null, "$" + dF.format(depositAmount) + " deposited!", "Deposit completed", JOptionPane.INFORMATION_MESSAGE);
		
		}else{
			
			if(isNumber == true){
			JOptionPane.showMessageDialog(null, "Deposit cancelled", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
		
		
		
	}
	
	public String getFirstName(){
		
		
		try{
			
		fReader = new FileReader(userF);
		fR = new BufferedReader(fReader);
		
		return fR.readLine().replaceFirst("First Name: ", "");
		
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}finally{
			
			try{
				
				fReader.close();
				fR.close();
				
			}catch(Exception e){
				
				e.printStackTrace();
				
			}
			
		}
	}
	
	public String getLastName(){
		
		try{
			
			fReader = new FileReader(userF);
			fR = new BufferedReader(fReader);
			
			fR.readLine();
			
			return fR.readLine().replaceFirst("Last Name: ", "");
			
			}catch(Exception e){
				
				e.printStackTrace();
				return null;
				
			}finally{
				
				try {
					
					fReader.close();
					fR.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
	}
	
	public String getUsername(){
		
		try{
			
			fReader = new FileReader(userF);
			fR = new BufferedReader(fReader);
			
			fR.readLine();
			fR.readLine();
			
			return fR.readLine().replaceFirst("Username: ", "");
			
			}catch(Exception e){
				
				e.printStackTrace();
				return null;
				
			}finally{
				
				try {
					
					fReader.close();
					fR.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		
	}
	
	public String getPassword(){
		
		
		try{
			
			fReader = new FileReader(userF);
			fR = new BufferedReader(fReader);
			
			fR.readLine();
			fR.readLine();
			fR.readLine();
			
			return fR.readLine().replaceFirst("Password: ", "");
			
			}catch(Exception e){
				
				e.printStackTrace();
				return null;
				
			}finally{
				
				try {
					
					fReader.close();
					fR.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
	}
	
	public String getBalance(){
		
		try{
			
			fReader = new FileReader(userF);
			fR = new BufferedReader(fReader);
			
			fR.readLine();
			fR.readLine();
			fR.readLine();
			fR.readLine();
			
			return fR.readLine().replaceFirst("Balance: ", "");
			
			}catch(Exception e){
				
				e.printStackTrace();
				return "0.00";
				
			}finally{
				
				try {
					
					fReader.close();
					fR.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
	}
	
	public String getDate(){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		
		Date date = new Date();
		
		return dateFormat.format(date);
		
	}
	
	
	public String getDateCreated(){
		
		String dateCreated = "";
		
		return dateCreated;
		
	}
	
	public String getLastLogin(){
		
		String lastLogin = "";
		
		try{
			
			fReader = new FileReader(userF);
			fR = new BufferedReader(fReader);
			
			String currentLine = "";
			String loginLine = "";
			
			while((currentLine = fR.readLine()) != null){
				
				loginLine = currentLine;
				
			}
			
			loginLine = loginLine.replace("Last login: ", "");
			
			if(!loginLine.equals("null")){
			
			//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			int monthNumber = Integer.parseInt(loginLine.substring(5, 7));
			int formattedHour;
			String amOrPm;

			if(Integer.parseInt(loginLine.substring(11, 13)) > 12){
				
				formattedHour = Integer.parseInt(loginLine.substring(11, 13)) - 12;
				amOrPm = "PM";
			
			}else if (Integer.parseInt(loginLine.substring(11,13)) == 12){
				
				formattedHour = 12;
				amOrPm = "PM";
				
			}else if (Integer.parseInt(loginLine.substring(11, 13)) == 0){
				
				formattedHour = 12;
				
				amOrPm = "AM";

				
			}else{
				
				formattedHour = Integer.parseInt(loginLine.substring(11, 13));
				amOrPm = "AM";
				
			}
			
			switch(monthNumber){
			
			case 1:
				
				lastLogin =  "January " + loginLine.substring(8, 10) + ", " + loginLine.substring(0,4) + " at " + formattedHour + ":" 
				+ loginLine.substring(14, 16) + " " + amOrPm;
				break;
				
			case 2:
				
				lastLogin =  "February " + loginLine.substring(8, 10) + ", " + loginLine.substring(0,4) + " at " + formattedHour + ":" 
				+ loginLine.substring(14, 16) + " " + amOrPm;
				break;
				
			case 3:
				
				lastLogin =  "March " + loginLine.substring(8, 10) + ", " + loginLine.substring(0,4) + " at " + formattedHour + ":" 
				+ loginLine.substring(14, 16) + " " + amOrPm;
				break;
				
			case 4: 
				
				lastLogin =  "April " + loginLine.substring(8, 10) + ", " + loginLine.substring(0,4) + " at " + formattedHour + ":" 
				+ loginLine.substring(14, 16) + " " + amOrPm;
				break;
				
			case 5:
				
				lastLogin =  "May " + loginLine.substring(8, 10) + ", " + loginLine.substring(0,4) + " at " + formattedHour + ":" 
				+ loginLine.substring(14, 16) + " " + amOrPm;
				break;
				
			case 6:
				
				lastLogin =  "June " + loginLine.substring(8, 10) + ", " + loginLine.substring(0,4) + " at " + formattedHour + ":" 
				+ loginLine.substring(14, 16) + " " + amOrPm;
				break;
				
			case 7:
				
				lastLogin =  "July " + loginLine.substring(8, 10) + ", " + loginLine.substring(0,4) + " at " + formattedHour + ":" 
				+ loginLine.substring(14, 16) + " " + amOrPm;
				break;
				
			case 8: 
				
				lastLogin =  "August " + loginLine.substring(8, 10) + ", " + loginLine.substring(0,4) + " at " + formattedHour + ":" 
				+ loginLine.substring(14, 16) + " " + amOrPm;
				break;
				
			case 9:
				
				lastLogin =  "September " + loginLine.substring(8, 10) + ", " + loginLine.substring(0,4) + " at " + formattedHour + ":" 
				+ loginLine.substring(14, 16) + " " + amOrPm;
				break;
				
			case 10:
				
				lastLogin =  "October " + loginLine.substring(8, 10) + ", " + loginLine.substring(0,4) + " at " + formattedHour + ":" 
				+ loginLine.substring(14, 16) + " " + amOrPm;
				break;
				
			case 11:
				
				lastLogin =  "November " + loginLine.substring(8, 10) + ", " + loginLine.substring(0,4) + " at " + formattedHour + ":" 
				+ loginLine.substring(14, 16) + " " + amOrPm;
				break;
				
			case 12:
				
				lastLogin =  "December " + loginLine.substring(8, 10) + ", " + loginLine.substring(0,4) + " at " + formattedHour + ":" 
				+ loginLine.substring(14, 16) + " " + amOrPm;
				break;
			
			default: 
				
				lastLogin = null;
				break;
			}
			
			}else{
				
				lastLogin = null;
				
			}
			
		}catch(Exception e){
			
			lastLogin = null;
			e.printStackTrace();
			
		}finally{
			
			try {
				
				fReader.close();
				fR.close();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		return lastLogin;
		
	}
	
	public void setRecentLoginDate(){
		
		try{
			
			fReader = new FileReader(userF);
			fR = new BufferedReader(fReader);
			
			String currentLine = "";
			
			while((currentLine = fR.readLine()) != null){
				
				fileContent += currentLine + System.lineSeparator();
				
			}
			
			fileContent = fileContent.replace(fileContent.substring(fileContent.indexOf("Last login: ")), "Last login: " + getDate());
			
			fWriter = new FileWriter(userF);
			fW = new BufferedWriter(fWriter);
			
			fW.write(fileContent);
			fW.flush();
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			
			try {
				
				fReader.close();
				fR.close();
				
				fWriter.close();
				fW.close();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
	public void setFirstName(){
		
		try{
			
			
			
		}catch(Exception e){
			
			
			
		}finally{
			
			
			
		}
		
	}
	
	public void setLastName(){
		
		
		
	}
	
	public void setUsername(){
		
		
		
	}
	
	public void setPassword(){
		
		
		
	}
	
	public void openLogin(){
		
		login login = new login();
		
		login.setSize(450, 300);
		login.setResizable(false);
		login.setVisible(true);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setLocationRelativeTo(null);
		
		dispose();
		
	}
}
